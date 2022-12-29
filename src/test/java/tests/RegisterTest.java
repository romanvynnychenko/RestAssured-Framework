package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.RegisterSeccessfulResp;
import pojo.RegisterSuccessfulReq;
import pojo.RegisterUnsuccessfulResp;
import test.components.Specifications;

import java.io.FileNotFoundException;

import static constants.Constants.Resourses.REGISTER;
import static io.restassured.RestAssured.given;

public class RegisterTest {


    @Test(dataProvider = "regDataAndExpectedResult")
    public void seccessfulRegister(String email, String password, Integer expectedId, String expectedToken)
            throws FileNotFoundException {

        RegisterSuccessfulReq regReq = new RegisterSuccessfulReq();
        regReq.setEmail(email);
        regReq.setPassword(password);

        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(200));

        RegisterSeccessfulResp regResp = given()
                .when()
                .body(regReq)
                .post(REGISTER)
                .then().log().all()
                .extract().as(RegisterSeccessfulResp.class);

        Assert.assertNotNull(regResp.getId());
        Assert.assertNotNull(regResp.getToken());

        Assert.assertEquals(expectedId, regResp.getId());
        Assert.assertEquals(expectedToken, regResp.getToken());
    }

    @Test
    public void unSuccessfulRegister() throws FileNotFoundException {

        String errorText = "Missing password";

        RegisterSuccessfulReq regReq = new RegisterSuccessfulReq();
        regReq.setEmail("sydney@fife");
        regReq.setPassword("");

        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(400));
        RegisterUnsuccessfulResp unsuccessfulResp = given()
                .when()
                .body(regReq)
                .post(REGISTER)
                .then().log().all()
                .extract().as(RegisterUnsuccessfulResp.class);

        Assert.assertNotNull(unsuccessfulResp.getError());
        Assert.assertEquals(errorText, unsuccessfulResp.getError());
    }

    @DataProvider
    public Object[][] regDataAndExpectedResult() {
        return new Object[][]{
                {"eve.holt@reqres.in", "pistol", 4, "QpwL5tke4Pnpja7X4"}
        };
    }
}
