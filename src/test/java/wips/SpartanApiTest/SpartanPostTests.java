package wips.SpartanApiTest;

import gson_api.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class SpartanPostTests {

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI = ConfigurationReader.get("SpartanApiUrl");
    }

    Spartan spartan=new Spartan();

    @Test
    public void genderPositiveTest(){

        Spartan spartan=new Spartan();
        spartan.setName("Ferda");
        spartan.setGender("Female");
        spartan.setPhone(1235487965L);

        given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/spartans")
                .then().assertThat().statusCode(201)
                .and().contentType(ContentType.JSON)
                .and().body("success",equalTo("A Spartan is Born!"))
                .and().log().all();
    }




}
