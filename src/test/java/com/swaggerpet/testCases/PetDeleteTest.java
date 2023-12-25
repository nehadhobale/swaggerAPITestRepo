package com.swaggerpet.testCases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.path.json.JsonPath.given;

public class PetDeleteTest {
    private static Logger logger = LoggerFactory.getLogger(PetTest.class);
    @Test
    public void deletePet(){
        long petId =9223372036854575002L;

        Response response = RestAssured.given()
                .header("Content-Type","application/json")
                .delete("https://petstore.swagger.io/v2/pet/"+petId);

        Assert.assertEquals(response.getStatusCode(),200,"Status is not matching");
        logger.info("Data is deleted");

    }
}
