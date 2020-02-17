package HWorks;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class Hw2SpartanApi {

    @BeforeClass
    public void setUpClass(){

        RestAssured.baseURI = ConfigurationReader.get("SpartanApiUrl");
    }

    /*
        Given accept type is json
        And path param id is 20
        When user sends a get request to "/spartans/{id}"
        Then status code is 200
        And content-type is "application/json;char"
        And response header contains Date
        And Transfer-Encoding is chunked
        And response payload values match the following:
        id is 20,
        name is "Lothario",
        gender is "Male",
        phone is 7551551687
     */

    @Test
    public void Q01(){
        Response response = given().accept(ContentType.JSON).and()
                .pathParam("id", 20)
                .when().get("spartans/{id}");
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
        response.then().assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().headers("Transfer-Encoding","chunked")
                .and().body("id",equalTo(20),
                    "name",equalTo("Lothario"),
                                             "gender",equalTo("Male"),
                                                "phone",equalTo(7551551687l));
    }

    /*
        Given accept type is json
        And query param gender = Female
        And queary param nameContains = r
        When user sends a get request to "/spartans/search"
        Then status code is 200
        And content-type is "application/json;char"
        And all genders are Female
        And all names contains r
        And size is 20
        And totalPages is 1
        And sorted is false
     */

    @Test
    public void Q2(){

        given().accept(ContentType.JSON).and()
                .queryParam("gender","Female")
                .and().queryParam("nameContains","r")
                .when().get("spartans/search")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().body("content.gender",hasItem("Female"),
                    "content.name",hasItem(stringContainsInOrder("r")),
                                                "size",equalTo(20),
                                                   "totalPages", equalTo(1),
                                                      "pageable.sort.sorted",equalTo(false));
    }




}
