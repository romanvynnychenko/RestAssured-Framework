package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.ListResourceResp;
import test.components.Specifications;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static constants.Constants.Resourses.LIST_RESOURCE;
import static io.restassured.RestAssured.given;

public class ListResourceTest {
    @Test
    public void sortValidationByYear() throws FileNotFoundException {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec(200));

        List<ListResourceResp> listResourceResp = given()
                .when()
                .get(LIST_RESOURCE)
                .then().log().all()
                .extract().jsonPath().getList("data", ListResourceResp.class);
        List<Integer> years = listResourceResp.stream().map(x -> x.getYear()).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(sortedYears, years);
    }

}
