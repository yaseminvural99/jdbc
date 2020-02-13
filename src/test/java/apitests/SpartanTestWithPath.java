package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithPath {

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI= ConfigurationReader.get("SpartanApiUrl");
    }

        /*
        Given accept type is json
            And path param id is 10
            When user sends a get request to "/spartans/{id}"
            Then status code is 200
            And content-type is "application/json;char"
            And response payload values match the following:
                id is 10,
                name is "Lorenza",
                gender is "Female",
                phone is 3312820936
        */
     @Test
    public void getSpartanWithPath(){
         //request
         Response response = given().accept(ContentType.JSON).and()
                 .pathParam("id", 10)
                 .when().get("spartans/{id}");

         assertEquals(response.statusCode(),200);
         assertEquals(response.contentType(),"application/json;charset=UTF-8");

         //print response body
         System.out.println("ID: " + response.body().path("id").toString());
         System.out.println("Name: " + response.body().path("name").toString());
         System.out.println("Gender: " + response.body().path("gender").toString());
         System.out.println("Phone: " + response.body().path("phone").toString());

         //save them
         int id=response.path("id");
         String firstName=response.path("name");
         String spartanGender=response.path("gender");
         long phone=response.path("phone");

         //do the assertion
         assertEquals(id,10);
         assertEquals(firstName,"Lorenza");
         assertEquals(spartanGender,"Female");
         assertEquals(phone,3312820936L);
     }

     @Test
    public void getAllSpartansWithPath(){
         //request
         Response response = get("/spartans");

         assertEquals(response.statusCode(),200);

         //print the first id
         int firstId = response.path("id[1]");
         String firstName=response.path("name[1]");
         String spartanGender=response.path("gender[1]");
         long phone=response.path("phone[1]");

         System.out.println("firstId = " + firstId);
         System.out.println("firstName = " + firstName);
         System.out.println("spartanGender = " + spartanGender);
         System.out.println("phone = " + phone);
         System.out.println("******************************************");

         //get last name
         int lastId = response.path("id[-1]");
         String lastFirstName=response.path("name[-1]");
         String lastspartanGender=response.path("gender[-1]");
         Integer lastphone=response.path("phone[-1]");

         System.out.println("lastId = " + lastId);
         System.out.println("lastFirstName = " + lastFirstName);
         System.out.println("lastspartanGender = " + lastspartanGender);
         System.out.println("lastphone = " + lastphone);
         System.out.println("******************************************");


         //get all firstnames and print out
         List<String> names =response.path("name");

         System.out.println("names.size() = " + names.size());

         List<Object> phones =response.path("phone");

         for (Object phn :phones ) {
             System.out.println("phone = " + phn);
         }


     }
}
