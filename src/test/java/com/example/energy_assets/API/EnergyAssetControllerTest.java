package com.example.energy_assets.API;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class EnergyAssetControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnGetAllAsJson() throws Exception {
        this.mockMvc.perform(get("/api")).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void shouldReturnGetAllHttpStatusCodeOK() throws Exception {
        this.mockMvc.perform(get("/api")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void shouldReturnGetByIdAsJson() throws Exception {
        this.mockMvc.perform(get("/api/566a95ce-9de5-4bff-9b55-bbfba9b8de0c")).andDo(print())   //id exists in db
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void shouldReturnGetByIdHttpStatusCodeOK() throws Exception {
        this.mockMvc.perform(get("/api/566a95ce-9de5-4bff-9b55-bbfba9b8de0c")).andDo(print()).andExpect(status().isOk()); //id exists in db

    }


    @Test
    public void shouldReturnLatestTimestampAsJson() throws Exception {
        this.mockMvc.perform(get("/api/latestTimestamp")).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void shouldReturnLatestTimestampHttpStatusCodeOK() throws Exception {
        this.mockMvc.perform(get("/api/latestTimestamp")).andDo(print()).andExpect(status().isOk());

    }


    @Test
    public void shouldReturnSelectByTimeperiodAsJson() throws Exception {
        this.mockMvc.perform(get("/api/2022-10-19T15:53:25.862000/2022-10-19T21:48:31.558000")).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void shouldReturnSelectByTimeperiodHttpStatusCodeOK() throws Exception {
        this.mockMvc.perform(get("/api/2022-10-19T15:53:25.862000/2022-10-19T21:48:31.558000")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void shouldReturnUpdateByIdOk() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/api/566a95ce-9de5-4bff-9b55-bbfba9b8de0c")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON).
                        content(getUpdateInJson());
        ;

        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private String getUpdateInJson() {

        return "{\"activepower\":" + 100 + ", \"voltage\":" + 100 + "}";
    }

    @Test
    public void shouldReturnAddOk() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/api")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON);
        ;

        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldReturnDeleteOk() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/e2a4c0fb-d9d9-4acc-b00a-12f1d6fb5129")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}