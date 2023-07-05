package com.hbelmiro.demos;

import io.quarkus.test.QuarkusDevModeTest;
import io.restassured.http.ContentType;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class LiveReloadTest {

    @RegisterExtension
    public final static QuarkusDevModeTest test = new QuarkusDevModeTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class));

    @Test
    void testNewWorkflow() throws IOException {
        given()
                .contentType(ContentType.JSON)
                .accept("*/*")
                .body("{ \"workflowdata\" : { \"name\" : \"helber\" } }")
                .when()
                .post("/service").then()
                .statusCode(404);

        try (FileInputStream inputStream = new FileInputStream("src/test/resources/workflow-service.sw.json")) {
            test.addResourceFile("workflow-service.sw.json", new String(Objects.requireNonNull(inputStream).readAllBytes()));
        }

        given()
                .contentType(ContentType.JSON)
                .accept("*/*")
                .body("{ \"workflowdata\" : { \"name\" : \"helber\" } }")
                .when()
                .post("/service").then()
                .statusCode(201);

    }

}