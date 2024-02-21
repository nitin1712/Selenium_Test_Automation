package com.automation.webdriver.utility;
import java.util.List;
import com.automation.webdriver.core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * APiUtil class has utility methods for API
 * 
 * @author ngupta
 *
 */
public class ApiUtil extends BaseTest{
    RequestSpecification httpRequest;


    /**
     * The below method is used to get Pet Status
     *
     * @author ngupta
     * @param  status
     * @return Response
     */
    public Response getPetStatus(String status) {
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest.queryParam("status",status).get("/pet/findByStatus");
    }



    /**
     * The below method is used to check whether API returns data for pets with specific status
     *
     * @author ngupta
     * @param response
     * @param  status
     * @return boolean
     */
    public boolean checkApiReturnsAllPetsWithSpecificStatus(Response response,String status) {
        List<String> jsonResponse = response.jsonPath().getList("$");
        for(int i=0;i<jsonResponse.size();i++) {
            if(!response.jsonPath().getString("status["+i+"]").contains(status)) {
                return false;
            }
        }
        return true;
    }

}



