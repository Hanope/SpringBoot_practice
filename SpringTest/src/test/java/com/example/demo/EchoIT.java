package com.example.demo;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class EchoIT {

    @Test
    public void firstEchoTest() {
        get("/echo/hello").then().assertThat().body("message", equalTo("hello"));
    }
}
