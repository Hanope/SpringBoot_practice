package com.example.demo;

import com.example.demo.model.Gift;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.net.URI;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private TestRestTemplate restTemplate;
    private String baseUrl;
    private Gift validGift;

    @Before
    public void setupTest() {
        // mockMvc 생성
        // 서버가 실행되지 않아도 테스트 가능
        restTemplate = new TestRestTemplate();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        baseUrl = "http://127.0.0.1:8080";

        validGift = new Gift();
        validGift.setName("watch");
        validGift.setColor("green");
    }

    private String jsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    public void testMockMvcStyle() throws Exception {
        MvcResult ret = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String sJson = ret.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Gift gift = mapper.readValue(sJson, Gift.class);

        assertThat(gift.getName()).isEqualTo(validGift.getName());
        assertThat(gift.getColor()).isEqualTo(validGift.getColor());
    }

    @Test
    public void testStrGet() throws Exception {
        URI uri = URI.create(baseUrl + "/");
        String sResponse = restTemplate.getForObject(uri, String.class);
        String svalidJson = jsonStringFromObject(validGift);
        assertThat(sResponse).isEqualTo(svalidJson);
    }

    @Test
    public void testObjectGet() throws Exception {
        URI uri = URI.create(baseUrl + "/");
        Gift gift = restTemplate.getForObject(uri, Gift.class);

        assertThat(gift.getName()).isEqualTo(validGift.getName());
        assertThat(gift.getColor()).isEqualTo(validGift.getColor());
    }
}
