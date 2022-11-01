import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.PayLoad;
import files.ReUseableMethods;

public class Basic1 {

	public static void main(String[] args) {
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/Json").body(PayLoad.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP"))
					.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String placeId=js.getString("place_id");
		String status=js.getString("status");
		
		System.out.println("*******************************************");
		System.out.println("Place Id is "   +placeId);
		System.out.println("Status is "   + status);
		
		
		String newAddress = "29, side layout, cohen 09";
		
		String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id",placeId)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1=ReUseableMethods.rawToJson(getPlaceResponse);
		String actualAddress =js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);

		

	}

}
