package com.cafeteria.user_service.web.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.cafeteria.user_service.AbstractIT;
import com.cafeteria.user_service.Records.UserRecords;

import io.restassured.http.ContentType;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import org.springframework.test.context.jdbc.Sql;

@Sql("/test-user-data.sql")
public class UserControllerTest extends AbstractIT {


    @Test
    public void shouldReturnUserById() {
        UserRecords userRecords = given().contentType(ContentType.JSON)
                .when()
                .get("/api/users/requests/{code}", "11111111-1111-1111-1111-111111111111")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(UserRecords.class);

        assertThat(userRecords.userId()).isEqualTo(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        assertThat(userRecords.userName()).isEqualTo("Alice Johnson");
        assertThat(userRecords.userEmail()).isEqualTo("alice.johnson@example.com");
        assertThat(userRecords.imageUrl()).isEqualTo("https://example.com/images/alice.jpg");
    }
}
