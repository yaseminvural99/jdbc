package apitests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CBTrainingTestWithJson {

    @BeforeClass
    public void setUpClass(){
        baseURI = ConfigurationReader.get("cbtApiUrl");
    }

    @Test
    public void getAstudentWithJsonPath(){
        //request part
        Response response = given().pathParam("id", 3408)
                .when().get("/student/{id}");

        assertEquals(response.statusCode(),200);

        //how to assign response body to JsonPath object
        JsonPath json = response.jsonPath();

        //get the values from jsonpath object
        String firstName = json.getString("students.firstName[0]");
        System.out.println("firstName = " + firstName);
        String lastName = json.get("students.lastName[0]");
        System.out.println("lastName = " + lastName);

        String phone = json.getString("students.contact[0].phone");
        System.out.println("phone = " + phone);

        String email = json.getString("students.contact[0].emailAddress");
        System.out.println("email = " + email);

        //city and zipcode
        String city = json.getString("students.company.address[0].city");
        System.out.println("city = " + city);
        String zipCode = json.getString("students.company.address[0].zipCode");
        System.out.println("zipCode = " + zipCode);

    }
}
