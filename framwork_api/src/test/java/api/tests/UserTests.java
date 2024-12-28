package api.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;
import com.github.scribejava.core.model.Response;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import groovy.util.logging.Log;
import io.restassured.path.json.JsonPath;
//I have to manually import this three
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class UserTests implements ITestListener {// Here ITestListner is used for print failed test cases name in console
	
	 Faker faker;
	 UserPayload payload;
	 
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		payload = new UserPayload();
		
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setUsername(faker.name().firstName());
		payload.setUsername(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password(5, 10));
		payload.setPhone(faker.phoneNumber().cellPhone());
	}
    
	@Test (priority = 1, testName = "Create User")
	public void testPostUser() {
    Response  response = UserEndPoints.createUser(payload);
    //response.then().log().all();
    Assert.assertEquals(response.getCode(), 200);
	}
	
	@Test (priority = 2, testName = "Get User")
	public void testGetUserByName() {
		 Response  response = UserEndPoints.getUser(this.payload.getUsername());
		    Assert.assertEquals(response.getCode(),200);
		
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		payload.setUsername(faker.name().firstName());
		payload.setUsername(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.payload.getUsername(),payload);
		Assert.assertEquals(response.getCode(), 200);
		
		//Check after update
		Response afterUpdate = UserEndPoints.getUser(this.payload.getUsername());
		Assert.assertEquals(response.getCode(), 200);
	}
	
	@Test (priority = 4)
	public void testDeleteUserByName() {
		Response response = UserEndPoints.deleteUser(this.payload.getUsername());
		Assert.assertEquals(response.getCode(), 200);
	}
	
	 @Override
	    public void onTestFailure(ITestResult result) {
	        System.out.println(result.getMethod().getMethodName() + " test method is failed");
	    }

	    // Implement other methods of ITestListener as needed
	    @Override
	    public void onTestStart(ITestResult result) {}
	    @Override
	    public void onTestSuccess(ITestResult result) {}
	    @Override
	    public void onTestSkipped(ITestResult result) {}
	    @Override
	    public void onStart(ITestContext context) {}
	    @Override
	    public void onFinish(ITestContext context) {}
}
