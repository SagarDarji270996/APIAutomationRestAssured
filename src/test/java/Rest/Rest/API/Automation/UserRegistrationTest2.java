package Rest.Rest.API.Automation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Rest.Rest.API.Automation.Utility.BaseTest;
import Rest.Rest.API.Automation.Utility.ExcelLib;
import Rest.Rest.API.Automation.Utility.FrameworkConstants;
import Rest.Rest.API.Automation.Utility.StringUtility;

public class UserRegistrationTest2 extends BaseTest {

	@DataProvider(name = "testData")
	public Object[][] testData() throws Exception {
	    ExcelLib excelLib = new ExcelLib(FrameworkConstants.DATA_FILE_PATH, "Sheet1");
	    List<List<String>> testDataList = excelLib.getTestData();

	    String[][] testDataArray = new String[testDataList.size()][];
	    for (int i = 0; i < testDataList.size(); i++) {
	        List<String> rowData = testDataList.get(i);
	        testDataArray[i] = rowData.toArray(new String[0]);
	    }

	    return testDataArray;
	}


    @Test(dataProvider = "testData")
    public void testUserRegistration(String username, String firstName, String lastName, String email, String password) {
        String registrationEndpoint = readConfigurationFile("Registration_Endpoint");
        
        // Generate random strings for the fields
        String randomString = StringUtility.generateRandomString(5);
        String finalUsername = username + randomString;
        String finalFirstname = firstName + randomString;
        String finalLastname = lastName + randomString;
        String finalEmail = email + randomString + "@gmail.com";
        String finalPassword = password + randomString;
        
        
        System.out.println("Generated data for registration:");
        System.out.println("Username: " + finalUsername);
        System.out.println("First Name: " + finalFirstname);
        System.out.println("Last Name: " + finalLastname);
        System.out.println("Email: " + finalEmail);
        System.out.println("Password: " + finalPassword);

        // Build the request body using the generated random data
        String requestBody = String.format("{\"username\": \"%s\", \"first_name\": \"%s\", " +
                "\"last_name\": \"%s\", \"email\": \"%s\", \"password\": \"%s\"}",
                finalUsername, finalFirstname, finalLastname, finalEmail, finalPassword);

        // Send POST request for user registration
        Response registrationResponse = RestAssured
                .given().spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(registrationEndpoint);

        // Assert registration response
        registrationResponse.then().statusCode(201); // Corrected assertion for 201 status code

        // Store the registered username and password for the next test
        configProperties.setProperty("registeredUsername", finalUsername);
        configProperties.setProperty("registeredPassword", finalPassword);
        System.out.println("Registered username: " + finalUsername);
        System.out.println("Registered password: " + finalPassword);
    }
   
}