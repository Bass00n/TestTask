package ru.javadavelop.testrestapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TestRestControllerTest {
    TestRestController controller = new TestRestController();
    @Test
    void handleFrequencyOfCharactersInString_ReturnsValidResponseEntity() {
        String string = "aaaaabcccc";
        var resultMap = Map.of(
                'a', 5,
                'c', 4,
                'b', 1
        );

        var responseEntity = this.controller.handleFrequencyOfCharactersInString(string);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(resultMap, responseEntity.getBody());
    }

}