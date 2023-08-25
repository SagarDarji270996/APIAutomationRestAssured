package Rest.Rest.API.Automation.Utility;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public abstract class BaseTest extends FrameworkUtility {
	
	protected static RequestSpecification requestSpec;
	protected static ResponseSpecification responseSpec;
    protected static Properties configProperties;
	
	@BeforeSuite
	public void setBaseURI() {
		
        AllureLogger.logToAllure("The base URI is : "+readConfigurationFile("Base_URI"));
		requestSpec = new RequestSpecBuilder().
                		setBaseUri(readConfigurationFile("Base_URI")).
                		build();
		
		configProperties = new Properties();
        
	}

	/*****************************************************************************************************************/
//	@AfterSuite
	public void afterSuite() {

	}
	
	/****************************************************************************************************************/
//	@BeforeClass
	public void beforeClass() {
	}
	
	/****************************************************************************************************************/	
//	@AfterClass
	public void afterClass(){

	}
	
	/************************************************************************************************************************/
	@BeforeMethod
	public void beforeMethod() {
    	responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();        
	}

//	@AfterMethod
	public void afterMethod() {

	}

}
/*****************************************************************************************************************/