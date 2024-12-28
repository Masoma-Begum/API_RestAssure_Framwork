package api.endpoints;

//I have to manually import this three
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.github.scribejava.core.model.Response;

import api.payload.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class UserEndPoints {
	public static Response createUser(UserPayload payload){
		 Response response = (Response) given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .body(payload)
		.when()
		   .post(Routes.postURL)
		   .then()
		   .log().all()
		   .extract().response();
		   
		 
		 return response;
	}
	
	public static Response getUser(String userName){
		 Response response = (Response) given()
		   .pathParam("username", userName)
		.when()
		   .get(Routes.getURL)
		   .then()
		   .log().all()
		   .extract().response();

		 
		 return response;
	}
	
	public static Response updateUser(String userName, UserPayload payload){
		 Response response = (Response) given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .pathParam("username", userName)
		  .body(payload)
		.when()
		   .put(Routes.updateURL);
		 
		 return response;
	}
	
	public static Response deleteUser(String userName){
		 Response response = (Response) given()
		  .pathParam("username", userName)
		.when()
		   .delete(Routes.deleteURL);
		 
		 return response;
	}


}
