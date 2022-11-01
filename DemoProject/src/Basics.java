import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.PayLoad;
import files.ReUseableMethods;
public class Basics {
	
	
	//public void test()
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/Json").body(PayLoad.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.18 (Ubuntu)").
		extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		
		// update a place
		
		String newAddress="12400 Skipper, Woodbridge, USA";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/Json").body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		  //Get place
		String GetPlaceRespong = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeId).when()
		.get("maps/api/place/get/json").then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1=ReUseableMethods.rawToJson(GetPlaceRespong);
		String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		
		Assert.assertEquals(actualAddress, newAddress);
		  
		
		
	
				
	}

}
