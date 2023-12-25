package com.swaggerpet.testCases;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class PetTest {
    private static Logger logger = LoggerFactory.getLogger(PetTest.class);

    @Test
    public void createPet() throws URISyntaxException, IOException {

        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\nxz\\IdeaProjects\\SwaggerPetAPITest\\src\\test\\resources\\testData\\PetTestData.json")));
        //JSONObject petData = new JSONObject(content);
        String petData = JsonPath.parse(content).set("$.id", 30L).jsonString();
        petData = JsonPath.parse(petData).set("$.name", "Tom").jsonString();
        System.out.println(petData);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(petData)
                .when()
                .post("https://petstore.swagger.io/v2/pet");

        Assert.assertEquals(response.getStatusCode(), 200, "Statuscode is not matching");

        logger.info("Statucode is matching");
        logger.error(response.asString());
    }

    @Test
    public void getPet() throws IOException {

        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\nxz\\IdeaProjects\\SwaggerPetAPITest\\src\\test\\resources\\testData\\PetTestData.json")));
        String petId = JsonPath.read(content,"$.id").toString();
        //System.out.println(petId);

        Response response = given()
                .get("https://petstore.swagger.io/v2/pet/"+petId);

        Assert.assertEquals(response.getStatusCode(),200,"Status code is not matching");
        logger.info("res"+response.body().asString());

    }

    @Test
    public void updatePet() throws IOException {
            String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\nxz\\IdeaProjects\\SwaggerPetAPITest\\src\\test\\resources\\testData\\PetTestData.json")));
            String petData = JsonPath.parse(content).set("$.category.id",30L).jsonString();
            petData= JsonPath.parse(petData).set("$.tags[0].id",30L).jsonString();
            petData = JsonPath.parse(petData).set("$.status","Not Available").jsonString();

        Response response = given()
                .header("Content-Type","application/json")
                .body(petData)
                .when()
                .put("https://petstore.swagger.io/v2/pet");

        Assert.assertEquals(response.getStatusCode(),200,"Status code is not matching");
        logger.info("Data is updated successfully");
        logger.info("res"+response.body().asString());

    }

    @Test
    public void deletePet() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\nxz\\IdeaProjects\\SwaggerPetAPITest\\src\\test\\resources\\testData\\PetTestData.json")));
        String petId = JsonPath.read(content,"$.id").toString();
        Response response = RestAssured.given()
                .header("Content-Type","application/json")
                .delete("https://petstore.swagger.io/v2/pet/"+petId);
        Assert.assertEquals(response.getStatusCode(),200,"Status is not matching");
        logger.info("Data is deleted");

    }
}
