package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.ListUsersResp;
import test.components.Specifications;

import java.io.FileNotFoundException;
import java.util.List;

import static constants.Constants.Resourses.LIST_USERS;
import static io.restassured.RestAssured.given;

public class ListUsersTest {
    @Test
    public void avatarNameContainsIdTest() throws FileNotFoundException {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(200));
        List<ListUsersResp> users = given()
                .when()
                .get(LIST_USERS)
                .then().log().all()
                .extract().body().jsonPath().getList("data", ListUsersResp.class);

        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

    @Test
    public void emailEndsWithReqresDotInTest() throws FileNotFoundException {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(200));
        List<ListUsersResp> users = given()
                .when()
                .get(LIST_USERS)
                .then().log().all()
                .extract().body().jsonPath().getList("data", ListUsersResp.class);

        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
    }
}
