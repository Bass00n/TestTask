package ru.javadavelop.testrestapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {TestRestApiApplication.class})
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")

class TestRestApiApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void handleFrequencyOfCharactersInString_ReturnsValidResponseEntity() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/api/string?str=aaaaabcccc");

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("{'a': 5,'c': 4,'b': 1}"))
                .andDo(document("home"));
    }



}
