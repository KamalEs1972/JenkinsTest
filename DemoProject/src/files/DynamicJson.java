package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;










public class DynamicJson {
	
	@Test

	public void addBook()

	{



	RestAssured.baseURI="http://216.10.245.166";

	String response=given().log().all().

	header("Content-Type","application/json").

	body(PayLoad.AddBook()).

	when().

	post("/Library/Addbook.php").

	then().log().all().assertThat().statusCode(200).

	extract().response().asString();

	JsonPath js= ReUseableMethods.rawToJson(response);

	   String id=js.get("ID");

	   System.out.println(id);

	   

	   //deleteBOok

	}

	







	}


