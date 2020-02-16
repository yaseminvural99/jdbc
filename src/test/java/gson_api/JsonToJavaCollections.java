package gson_api;

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


public class JsonToJavaCollections {

    @BeforeClass
    public void setUpClass(){

        RestAssured.baseURI= ConfigurationReader.get("SpartanApiUrl");
    }

    @Test
    public void SpartanJsonToMap(){
        Response response = given().accept(ContentType.JSON).and()
                .pathParam("id", 15)
                .when().get("/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        /*
            {
                "id": 15,
                "name": "Meta",
                "gender": "Female",
                "phone": 1938695106
            }
         */

        Map<String,Object> spartanMap=response.body().as(Map.class);

        System.out.println("spartanMap = " + spartanMap);
        String name= (String) spartanMap.get("name");
        assertEquals(name,"Meta");
    }

    @Test
    public void allSpartansToList(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans/");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        List<Map<String,Object>> spartanListOfMap=response.body().as(List.class);

        System.out.println("spartanListOfMap = " + spartanListOfMap.size());

        System.out.println("spartanListOfMap.get(0) = " + spartanListOfMap.get(0));

        System.out.println("spartanListOfMap.get(0).get(\"name\") = " + spartanListOfMap.get(0).get("name"));
    }

    @Test
    public void regionJsonMap(){
        //request
        Response response = get("http://54.161.128.36:1000/ords/hr/regions");
        assertEquals(response.statusCode(),200);

        System.out.println(response.prettyPrint());
        
        Map<String,Object> regionMap=response.body().as(Map.class);

        System.out.println("regionMap.get(\"items\") = " + regionMap.get("items"));
    }

}
