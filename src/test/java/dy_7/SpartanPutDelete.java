package dy_7;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanPutDelete {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = ConfigurationReader.get("SpartanApiUrl");
    }

    @Test
    public void updatingSpartan(){
        Map<String,Object> putMap=new HashMap<>();
        putMap.put("name","Ferda");
        putMap.put("gender","Female");
        putMap.put("phone",726292643732L);

            given().pathParam("id",50)
                    .and().contentType(ContentType.JSON)
                    .and().body(putMap)
                    .when().put("/spartans/{id}")
                    .then().assertThat().statusCode(204);

            when().delete("spartans/51").then().assertThat().statusCode(204);

    }
}