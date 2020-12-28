package com.company.carshowroomssimulatorspring.controller;

import com.company.carshowroomssimulatorspring.model.ItemCondition;
import com.company.carshowroomssimulatorspring.model.Rating;
import com.company.carshowroomssimulatorspring.model.RatingEnum;
import com.company.carshowroomssimulatorspring.model.Vehicle;
import com.company.carshowroomssimulatorspring.repository.CarShowroomRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RatingControllerTest {

    @Autowired
    private MockMvc mvc;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void shouldReturnValidResponseCodeWhenGettingAllReviews() throws Exception {
        String uri = "/api/rating";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Rating[] vehicleList = mapFromJson(content, Rating[].class);
        assertTrue(vehicleList.length > 0);
    }

    @Test
    public void shouldReturnValidResponseCodeWhenAddingAReview() throws Exception {
        String uri = "/api/rating";
        Rating rating = new Rating();
        rating.setRating(RatingEnum.Five);
        rating.setShowroomId(5);

        String inputJson = mapToJson(rating);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}