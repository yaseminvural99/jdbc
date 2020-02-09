package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;  // static import gives us that not need to write class name
import static org.testng.Assert.*;

public class SpartanTest {

    String spartanAllUrl="http://3.85.62.235:8000/api/spartans";

    @Test
    public void viewAllSpartans(){

        Response response = RestAssured.get(spartanAllUrl);

        //print status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print response print
        //System.out.println(response.body().asString());

        //prettyPrint
        System.out.println(response.prettyPrint());
    }


    /* when user send GET request to /api/spartans end point
        Then status code must be 200
        And body should contains Orion
    */
    @Test
    public void test01(){
        Response response = RestAssured.get(spartanAllUrl);

        //verify status code is 200
        assertEquals(response.getStatusCode(),200);

        //verify body includes Orion
        Assert.assertTrue(response.body().asString().contains("Orion"));
    }


    /*  Given accept type application/json
        when user send GET request to /api/spartans end point
        Then status code must be 200
        And Response content type must be json
        And body should contains Orion
    */
    @Test
    public void test02(){
        Response response = given().accept(ContentType.JSON).when().get(spartanAllUrl);

        //verify status code is 200
        assertEquals(response.getStatusCode(),200);

        //verify response content type is json
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        //verify body includes Orion
        Assert.assertTrue(response.body().asString().contains("Orion"));
    }

    /*  Given accept type application/xml
       when user send GET request to /api/spartans end point
       Then status code must be 200
       And Response content type must be xml
       And body should contains Orion
   */
    @Test
    public void test03(){
        Response response = given().accept(ContentType.XML).when().get(spartanAllUrl);

        //verify status code is 200
        assertEquals(response.getStatusCode(),200);

        //verify response content type is json
        assertEquals(response.contentType(),"application/xml;charset=UTF-8");

        //verify body includes Orion
        Assert.assertTrue(response.body().asString().contains("Orion"));

    }


    /*  Given accept type application/xml
       when user send GET request to /api/spartans end point
       Then status code must be 200
       And Response content type must be xml
       And body should contains Orion
   */
    @Test
    public void test04(){

        //lazy way to write test case in one line
       given().accept(ContentType.XML).
            when().get(spartanAllUrl).
            then().statusCode(200).
            and().contentType("application/xml;charset=UTF-8");

    }

     /*
        Given the accept type Json
        When I send get request to /api/spartans/3
        Then status code must be 200
        And Content type should be json

      */
     @Test
    public void test05(){
         spartanAllUrl=spartanAllUrl+"/3";
         given().accept(ContentType.JSON).
                 when().get(spartanAllUrl).
                 then().statusCode(200).
                 and().contentType("application/json;charset=UTF-8");
     }

}
