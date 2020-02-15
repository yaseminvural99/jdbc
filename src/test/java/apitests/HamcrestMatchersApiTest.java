package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HamcrestMatchersApiTest {

    @Test
    public void singleSpartanbyChaining(){

        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("http://3.85.62.235:8000/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json;charset=UTF-8")
                .and().assertThat().body("id",equalTo(15),
                        "name",equalTo("Meta"),
                                                "gender",equalTo("Female"),
                                                 "phone", equalTo(1938695106));
    }

    @Test
    public void teacherTest(){

        given().accept(ContentType.JSON)
                .and().pathParam("name","Madham").and()
                .when().get("http://api.cybertektraining.com/teacher/name/{name}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().body("teachers.firstName[0]",equalTo("Madham"),
                    "teachers.lastName[0]",equalTo("Mask"),
                                            "teachers.emailAddress[0]",equalTo("jackma@gmail.com"));


    }

    @Test
    public void teacherAllDataWithHamcrest(){

        given().accept(ContentType.JSON)
                .and().pathParam("department","Computer").and()
                .when().get("http://api.cybertektraining.com/teacher/department/{department}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().body("teachers.firstName[0]", equalTo("Madham"),
                "teachers.firstName[1]", equalTo("Ruslan"),
                        "teachers.firstName[2]", equalTo("Alihan"));

    }

    @Test
    public void teachersAllDataWithHamcrest(){
        given().accept(ContentType.JSON).pathParam("name","Computer")
                .when().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().assertThat().statusCode(200).and().contentType("application/json;charset=UTF-8")
                .and().body("teachers.firstName",hasItems("Madham","Ruslan","Alihan"));
    }

}
