package com.kushan.virtualpowerplant.controller;

import com.kushan.virtualpowerplant.dto.request.BatteryCreateRequest;
import com.kushan.virtualpowerplant.dto.request.BulkBatteryCreateRequest;
import com.kushan.virtualpowerplant.dto.response.BatteryStatResponse;
import com.kushan.virtualpowerplant.entity.Battery;
import com.kushan.virtualpowerplant.service.BatteryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

/**
 * @author Ravindu
 * 9/23/2022
 */
@Slf4j
public class BatteryControllerUnitTest {
    BatteryController batteryController;
    BulkBatteryCreateRequest request;
    List<Battery> savedList;
    BatteryStatResponse statResponse;

    @BeforeEach
    void setup(){
        log.info("Battery Controller unit test init......");
        BatteryService batteryService = Mockito.mock(BatteryService.class);
        List<BatteryCreateRequest> inputList = List.of(new BatteryCreateRequest("testBat_01",9001,12.5),
                new BatteryCreateRequest("testBat_02",9002,12.5),
                new BatteryCreateRequest("testBat_03",9003,12.5),
                new BatteryCreateRequest("testBat_04",9004,12.5));

        this.request = new BulkBatteryCreateRequest(inputList);

        this.savedList = List.of(new Battery("01","testBat_01",9001,12.5),
                new Battery("02","testBat_02",9002,12.5),
                new Battery("03","testBat_03",9003,12.5),
                new Battery("04","testBat_04",9004,12.5));

        this.statResponse = new BatteryStatResponse(List.of("testBat_01","testBat_02","testBat_03"),37.5,12.5);
        this.batteryController = new BatteryController(batteryService);

        when(batteryService.acceptBulkBatteryCreateRequest(request)).thenReturn(savedList);
        when(batteryService.getBatteryStatsWithinPostcodes(9001,9004)).thenReturn(statResponse);
    }

    @Test
    void shouldAcceptBulkCreateRequest(){
        assertIterableEquals(savedList, batteryController.acceptBulkCreateRequest(request));
        log.info("Battery Controller AcceptBulkCreateRequest test completed......");
    }
    @Test
    void shouldGetStatsWithinPostcodes(){
        assertEquals(statResponse, batteryController.getStatsWithinPostcodes(9001,9004).getBody());
        log.info("Battery Controller GetStatsWithinPostcodes test completed......");
    }

}
