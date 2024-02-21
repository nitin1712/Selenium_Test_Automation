package com.automation.API_Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.automation.core.ExtentManager;
import com.automation.core.ExtentTestManager;
import com.automation.core.ExtentTestNGListener;
import com.automation.core.ReadConfig;
import com.automation.webdriver.utility.ApiUtil;
import com.automation.webdriver.utility.AssertionImpl;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners(ExtentTestNGListener.class)
public class API_Test {
    private RequestSpecification httpRequest;
    private Response response;
    private static int updatedPetId;
    private Object obj;
    JSONObject jsonObject;
    JSONParser parser = new JSONParser();
    ApiUtil apiUtil=new ApiUtil();

    @BeforeClass
    public void setUp() throws IOException {
        RestAssured.baseURI =  new ReadConfig().readPropertiesFile("config.properties").getProperty("baseURI");
    }


    @Test(description = "get request to check all available pets" , priority = 1)
    public void checkPetStatus() {
        Response response=apiUtil.getPetStatus("available");
        AssertionImpl.assertTrue(apiUtil.checkApiReturnsAllPetsWithSpecificStatus(response,"available"), "API is expected to return all pets with available status");
    }



    @Test(description = "Make post request to add new pet",priority = 2)
    public void postNewPet() throws ParseException, FileNotFoundException, IOException{
        obj = parser.parse(new FileReader(System.getProperty("user.dir")+"/src/test/resources/put.json"));
        jsonObject = (JSONObject) obj;
        httpRequest = RestAssured.given().contentType(ContentType.JSON);
        response= httpRequest.when().body(jsonObject).post("/pet").then().extract().response();
        ExtentTestManager.getTest().log(Status.INFO, response.print());
        AssertionImpl.assertEquals(200, response.getStatusCode());
        updatedPetId=response.jsonPath().get("id");
        AssertionImpl.assertEquals(jsonObject.get("id").toString(), response.jsonPath().get("id").toString(), "Newly created pet id is not correct");
        AssertionImpl.assertEquals(jsonObject.get("name"), response.jsonPath().get("name"), "Newly created pet name is not correct");
    }


    @Test(description = "Verified newly added pet" , dependsOnMethods = { "postNewPet" })
    public void getAddedNewPet() {
        httpRequest = RestAssured.given();
        response= httpRequest.when().get("/pet/"+updatedPetId).then().extract().response();
        ExtentTestManager.getTest().log(Status.INFO, response.print());
        AssertionImpl.assertEquals(200, response.getStatusCode());
        AssertionImpl.assertEquals(jsonObject.get("id").toString(), response.jsonPath().get("id").toString(), "Newly added pet is not verified");
    }


    @Test(description = "Update Pet",dependsOnMethods = { "getAddedNewPet" } , priority = 3)
    public void updateNewPet() throws ParseException, FileNotFoundException, IOException{
        obj = parser.parse(new FileReader(System.getProperty("user.dir")+"/src/test/resources/update.json"));
        jsonObject = (JSONObject) obj;
        httpRequest = RestAssured.given().contentType(ContentType.JSON);
        response= httpRequest.when().body(jsonObject).put("/pet").then().extract().response();
        ExtentTestManager.getTest().log(Status.INFO, response.print());
        AssertionImpl.assertEquals(200, response.getStatusCode());
        AssertionImpl.assertEquals(jsonObject.get("id").toString(), response.jsonPath().get("id").toString(), "Updated pet id is not correct");
        AssertionImpl.assertEquals(jsonObject.get("status"), response.jsonPath().get("status"), "Updated pet status is not correct");
    }



    @Test(description = "Verified updated info of added pet" , dependsOnMethods = { "updateNewPet" })
    public void getUpdatedPetInfo() {
        httpRequest = RestAssured.given();
        response= httpRequest.when().get("/pet/"+updatedPetId).then().extract().response();
        ExtentTestManager.getTest().log(Status.INFO, response.print());
        AssertionImpl.assertEquals(200, response.getStatusCode());
        AssertionImpl.assertEquals(jsonObject.get("status").toString(), response.jsonPath().get("status").toString(), "Updated pet info jot correct");
    }


    @Test(description = "Delete created Pet" , priority = 4)
    public void deletePet() throws InterruptedException, IOException, ParseException{
        httpRequest = RestAssured.given();
        response= httpRequest.delete("/pet/"+updatedPetId+"").then().extract().response();
        ExtentTestManager.getTest().log(Status.INFO, response.print());
        AssertionImpl.assertEquals(200, response.getStatusCode());
    }


    @Test(description = "check deleted" , dependsOnMethods = { "deletePet" })
    public void checkPetAfterDeletion() {
        httpRequest = RestAssured.given();
        response= httpRequest.when().get("/pet/"+updatedPetId).then().extract().response();
        ExtentTestManager.getTest().log(Status.INFO, response.print());
        AssertionImpl.assertEquals(404, response.getStatusCode());
        AssertionImpl.assertEquals("Pet not found", response.jsonPath().get("message"), "Updated pet info jot correct");
    }


    @AfterMethod
    public void tearDown() {
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }
}
