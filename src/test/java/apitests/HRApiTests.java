package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HRApiTests {

    String HrUrl="http://3.85.62.235:1000/ords/hr";

    String SpartanUrl="http://3.85.62.235:8000/api";

    //TASK
    /*
        Create a new class HRApiTests
        createa a @Test getALLRegionsTest
        send a get request to AllRegions API endpoint
        -print status code
        -print content type
        -pretty print response JSON
        verify that status code is 200
        verify that content type is "application/json"
        verify that json response body contains "Americas"
        verify that json response body contains "Europe"
        *try to use static imports for both RestAssured and testng
        *store response inside the Response type variable
     */

    @Test
    public void getAllregionsTest(){
        Response response = given().get(HrUrl+"/regions");

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response contentType() = " + response.contentType());

        System.out.println(response.prettyPrint());

        assertEquals(response.getStatusCode(),200);

        assertEquals(response.contentType(),"application/json");

        assertTrue(response.body().asString().contains("Americas"));

        assertTrue(response.body().asString().contains("Europe"));

    }

    /*
        Given the accept type XML
        When I send get request to /api/spartans/3
        Then status code must be 406
     */

    @Test
    public void XMLtest(){
        given().accept(ContentType.XML)
                .when().get(SpartanUrl + "/spartans/3")
                .then().statusCode(406);
    }





}
