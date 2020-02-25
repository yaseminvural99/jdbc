package day_8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidation {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = ConfigurationReader.get("SpartanApiUrl");
    }

    @Test
    public void jsonCshemaValiditionForSpartan(){

        given().accept(ContentType.JSON)
                .pathParam("id", 5)
                .when().get("/spartans/{id}")
                .then().statusCode(200).body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));
    }

}