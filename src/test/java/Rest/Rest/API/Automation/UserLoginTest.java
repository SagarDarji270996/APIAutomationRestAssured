package Rest.Rest.API.Automation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import Rest.Rest.API.Automation.Utility.BaseTest;

public class UserLoginTest extends BaseTest {

	@Test
	public void testUserAuthentication() {
		String authEndpoint = readConfigurationFile("Login_Endpoint");

		// Retrieve registered username and password from config properties
		String username = configProperties.getProperty("registeredUsername");
		String password = configProperties.getProperty("registeredPassword");
		System.out.println("Retrieved username: " + username);
		System.out.println("Retrieved password: " + password);

		// Build the request body
		String requestBody = String.format("{\"username\": \"%s\", \"password\": \"%s\"}",
				username, password);

		// Log the request details including headers
		System.out.println("Request Method: POST");
		System.out.println("Request URI: " + authEndpoint);
		System.out.println("Request Body: " + requestBody);

		// Send POST request for user authentication
		Response authResponse = RestAssured
				.given().spec(requestSpec)
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post(authEndpoint);

		// Log the response details
		System.out.println("Response Status Code: " + authResponse.getStatusCode());
		System.out.println("Response Body: " + authResponse.getBody().asString());
		System.out.println("Response Headers: " + authResponse.getHeaders());

		// Assert authentication response
		authResponse.then().statusCode(200); // Update the expected status code as needed
	}
}
