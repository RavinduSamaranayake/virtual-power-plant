package com.kushan.virtualpowerplant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kushan.virtualpowerplant.dto.request.BatteryCreateRequest;
import com.kushan.virtualpowerplant.dto.request.BulkBatteryCreateRequest;
import com.kushan.virtualpowerplant.dto.response.BatteryStatResponse;
import com.kushan.virtualpowerplant.entity.Battery;
import com.kushan.virtualpowerplant.service.BatteryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Ravindu
 * 9/22/2022
 */
@WebMvcTest(BatteryController.class)
@Slf4j
public class BatteryControllerWebLayerTest {
    @Autowired
    private BatteryController batteryController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatteryService batteryService;

    @Test
    void acceptBulkBatteryCreateRequest() throws Exception {
        List<BatteryCreateRequest> inputList = List.of(new BatteryCreateRequest("testBat_01", 9001, 12.5),
                new BatteryCreateRequest("testBat_02", 9002, 12.5),
                new BatteryCreateRequest("testBat_03", 9003, 12.5),
                new BatteryCreateRequest("testBat_04", 9004, 12.5));

        BulkBatteryCreateRequest request = new BulkBatteryCreateRequest(inputList);

        List<Battery> returnedList = List.of(new Battery("01", "testBat_01", 9001, 12.5),
                new Battery("02", "testBat_02", 9002, 12.5),
                new Battery("03", "testBat_03", 9003, 12.5),
                new Battery("04", "testBat_04", 9004, 12.5));

        when(batteryService.acceptBulkBatteryCreateRequest(any(BulkBatteryCreateRequest.class)))
                .thenReturn(returnedList);

        mockMvc.perform(MockMvcRequestBuilders.post("/batteries/bulk_create")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        log.info("WebLayer Battery Controller AcceptBulkCreateRequest test completed......");
    }

    @Test
    void getStatsWithinPostcodes() throws Exception {
        BatteryStatResponse statResponse = new BatteryStatResponse(
                List.of("testBat_01", "testBat_02", "testBat_03"), 37.5, 12.5);
        when(batteryService.getBatteryStatsWithinPostcodes(anyInt(), anyInt())).thenReturn(statResponse);
        mockMvc.perform(MockMvcRequestBuilders.get("/batteries/postcode_range?lowerPostcode=9000005&upperPostcode=90000000"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalWatt").value("37.5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.averageWatt").value("12.6"))
                .andExpect(status().isOk());
        log.info("WebLayer Battery Controller getStatsWithinPostcodes test completed......");
    }

}
