package Rest.Rest.API.Automation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import Rest.Rest.API.Automation.Utility.BaseTest;
import Rest.Rest.API.Automation.Utility.StringUtility;

public class UserRegistrationTest extends BaseTest {

	@Test
	public void testUserRegistration() {
		 String registrationEndpoint = readConfigurationFile("Registration_Endpoint");

		// Generate random username, first name, last name, email, and password
		String username = StringUtility.generateRandomString(10);
		String firstName = StringUtility.generateRandomString(8);
		String lastName = StringUtility.generateRandomString(8);
		String email = username + "@example.com";
		String password = StringUtility.generateRandomString(12);

		// Build the request body
		String requestBody = String.format("{\"username\": \"%s\", \"first_name\": \"%s\", " +
				"\"last_name\": \"%s\", \"email\": \"%s\", \"password\": \"%s\"}",
				username, firstName, lastName, email, password);

		// Send POST request for user registration
		Response registrationResponse = RestAssured
				.given().spec(requestSpec)
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post(registrationEndpoint);

		// Assert registration response
		registrationResponse.then().statusCode(201); // Corrected assertion for 201 status code

		// Store the registered username and password for the next test
		configProperties.setProperty("registeredUsername", username);
		configProperties.setProperty("registeredPassword", password);
		System.out.println("Registered username: " + username);
		System.out.println("Registered password: " + password);
	}
}
