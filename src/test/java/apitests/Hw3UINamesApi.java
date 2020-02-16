package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class Hw3UINamesApi {

    @BeforeClass
    public void setUpClass(){

        RestAssured.baseURI = "https://uinames.com/api/";
    }

    /*
        1. Send a get request without providing any parameters
        2. Verify status code 200, content type application/json; charset=utf-8
        3. Verify that name, surname, gender, region fields have value
     */
    @Test
    public void noParamsTest(){
        given().accept(ContentType.JSON)
                .when().get()
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8")
                .and().body("name",is(notNullValue()),
                 "surname",is(notNullValue()),
                                           "gender",is(notNullValue()),
                                             "region",is(notNullValue()));
    }

    /*
        1. Create a request by providing query parameter: gender, male or female
        2. Verify status code 200, content type application/json; charset=utf-8
        3. Verify that value of gender field is same from step 1
     */
    @Test
    public void gendertest(){
        given().accept(ContentType.JSON).and()
                .queryParam("gender","male")
                .when().get()
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8")
                .and().body("gender",equalTo("female"));
    }

    /*
        1. Create a request by providing query parameters: a valid region and gender
                NOTE: Available region values are given in the documentation
        2. Verify status code 200, content type application/json; charset=utf-8
        3. Verify that value of gender field is same from step 1
        4. Verify that value of region field is same from step 1
     */
    @Test
    public void twoParamTest(){
        given().accept(ContentType.JSON).and()
                .queryParam("gender","male")
                .and().queryParam("region", "germany")
                .when().get()
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8")
                .and().body("gender",equalTo("male"),
                    "region",equalTo("Germany"));
    }

    /*
        1. Create a request by providing query parameter: invalid gender
        2. Verify status code 400 and status line contains Bad Request
        3. Verify that value of error field is Invalid gender
     */
    @Test
    public void invalidGenderTest(){
        given().accept(ContentType.JSON).and()
                .queryParam("gender","maale")
                .when().get()
                .then().assertThat().statusCode(400)
                .and().statusLine(containsString("Bad Request"))
                .and().body("error",equalTo("Invalid gender"));
    }

    /*
        1. Create a request by providing query parameter: invalid region
        2. Verify status code 400 and status line contains Bad Request
        3. Verify that value of error field is Region or language not found
     */
    @Test
    public void invalidRegionTest(){
        given().accept(ContentType.JSON).and()
                .queryParam("region", "gerssmany")
                .when().get()
                .then().assertThat().statusCode(400)
                .and().statusLine(containsString("Bad Request"))
                .and().body("error",equalTo("Region or language not found"));
    }

    /*
        1. Create request by providing query parameters: a valid region and amount (must be bigger than 1)
        2. Verify status code 200, content type application/json; charset=utf-8
        3. Verify that all objects have different name+surname combination
     */
    @Test
    public void amountAndRegionsTest(){
        Response response = given().accept(ContentType.JSON).and()
                .queryParam("region", "germany")
                .and().queryParam("amount", 20)
                .when().get();
        response.then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8");

        List<String> names=response.path("name");
        List<String> surnames=response.path("surname");
        List<String> fullName=new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            fullName.add(names.get(i)+ " " + surnames.get(i));
        }
        int dublicate=0;
        for(int i=0; i<fullName.size()-1; i++){
            for (int j=i+1; j<fullName.size(); j++){
                if(fullName.get(i).equals(fullName.get(j))){
                    dublicate++;
                }
            }
        }

        Assert.assertEquals(dublicate,0);
    }

    /*
        1. Create a request by providing query parameters: a valid region, gender and amount (must be bigger than 1)
        2. Verify status code 200, content type application/json; charset=utf-8
        3. Verify that all objects the response have the same region and gender passed in step 1
     */
    @Test
    public void threeParamsTest(){
        given().accept(ContentType.JSON).and()
                .queryParam("gender","male")
                .and().queryParam("region", "germany")
                .and().queryParam("amount", 20)
                .when().get()
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8")
                .and().body("gender",hasItem("male"),
                "region",hasItem("Germany"));
    }

    /*
        1. Create a request by providing query parameter: amount (must be bigger than 1)
        2. Verify status code 200, content type application/json; charset=utf-8
        3. Verify that number of objects returned in the response is same as the amount passed in step 1
     */
    @Test
    public void amountCountTest(){
        Response response = given().accept(ContentType.JSON).and()
                .queryParam("amount", 20)
                .when().get();
        response.then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8");
        List<Object> names=response.path("name");

        Assert.assertEquals(names.size(),20);

    }

}
