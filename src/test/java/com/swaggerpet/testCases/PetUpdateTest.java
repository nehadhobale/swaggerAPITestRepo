package com.swaggerpet.testCases;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PetUpdateTest {
    private static Logger logger = LoggerFactory.getLogger(PetTest.class);
    @Test
    public void updatePet(){
        JSONObject petData = new JSONObject();
        petData.put("id", 9223372036854575002L);
        petData.put("name", "Tuffy");
        JSONObject petCategory = new JSONObject();
        petCategory.put("name", "Dog");

        petData.put("category", petCategory);

        logger.info(String.valueOf(petData));

        Response response = given()
                .header("Content-Type","application/json")
                .body(petData.toString())
                .when()
                .put("https://petstore.swagger.io/v2/pet");

        Assert.assertEquals(response.getStatusCode(),200,"Status code is not matching");
        logger.info("Data is updated successfully");
    }
}
