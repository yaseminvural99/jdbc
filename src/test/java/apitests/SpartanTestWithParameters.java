package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.*;

public class SpartanTestWithParameters {

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI= ConfigurationReader.get("SpartanUrl");
    }

    @Test
    public void helloTest(){
        Response response = when().get("/hello");

        //verify response
        assertEquals(response.statusCode(),200);

        //verify content type
        assertEquals(response.contentType(),"text/plain;charset=UTF-8");


        //verify we have header
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //verify content length is 17
        assertEquals(response.getHeader("Content-Length"),"17");

        //verify body is Hello from Sparta
        assertEquals(response.body().asString(),"Hello from Sparta");
    }

     /*Given accept type is Json
      And Id parameter value is 5
      When user sends GET request to /api/spartans/{id}
      Then response status code should be 200
      And response content-type: application/json;charset=UTF-8
      And "Blythe" should be in response payload
    */

     @Test
    public void idPositiveTest(){
         /*
         we can use path parameter dynamic way like this
          */

         Response response = given().accept(ContentType.JSON)
                 .and().pathParam("id",5)
                 .when().get("spartans/{id}");

         assertEquals(response.statusCode(),200);
         assertEquals(response.contentType(),"application/json;charset=UTF-8");
         assertTrue(response.body().asString().contains("Blythe"));
     }


        /*  Given accept type is Json
      And Id parameter value is 500
      When user sends GET request to /api/spartans/{id}
      Then response status code should be 404
      And response content-type: application/json;charset=UTF-8
      And Spartan Not Found" message should be in response payload
      */
    @Test
    public void getSpartanIdNegativeTest(){

        Response response = given().accept(ContentType.JSON).and().pathParam("id",500)
                .when().get("/spartans/{id}");

        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Spartan Not Found"));
    }


    /*
    Given accept type is Json
    And query parameter values are :
    gender|Female
    nameContains|e
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json;charset=UTF-8
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */
    @Test
    public void PositiveQueryParamTest(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans/search?gender=Female&nameContains=e");


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }


    /*
    Given accept type is Json
    And query parameter values are :
    gender|Female
    nameContains|e
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json;charset=UTF-8
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */
    @Test
    public void PositiveQueryParamTest02(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("gender","Female")
                .queryParam("nameContains","e")
                .when().get("/spartans/search");


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }

    /*
    Given accept type is Json
    And query parameter values are :
    gender|Female
    nameContains|e
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json;charset=UTF-8
    And "Female" should be in response payload
    And "Janette" should be in response payload
    ((((with map))))
     */
    @Test
    public void PositiveQueryParamTest03(){

        Map<String,Object> paramsMap=new HashMap<String, Object>();
        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams(paramsMap)
                .when().get("/spartans/search");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }
}
