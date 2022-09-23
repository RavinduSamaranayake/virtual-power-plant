package com.kushan.virtualpowerplant.service;

import com.kushan.virtualpowerplant.dto.response.BatteryStatResponse;
import com.kushan.virtualpowerplant.entity.Battery;
import com.kushan.virtualpowerplant.repository.BatteryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ravindu
 * 9/23/2022
 */
@Slf4j
public class BatteryServiceUnitTest {
    BatteryService batteryService;
    BatteryStatResponse expectedStatResponse;

    @BeforeEach
    void setup() {
        log.info("Battery Service unit test init......");
        BatteryRepository repository = Mockito.mock(BatteryRepository.class);

        List<Battery> fetchedBatteriesFromRepository = List.of(
                new Battery("03", "testBat_03", 9003, 12.5),
                new Battery("01", "testBat_01", 9001, 12.5),
                new Battery("02", "testBat_02", 9002, 12.5));

        this.expectedStatResponse = new BatteryStatResponse(List.of("testBat_01", "testBat_02", "testBat_03"), 37.5, 12.5);
        this.batteryService = new BatteryService(repository);

        when(repository.findByPostcodeBetween(9000, 9004)).thenReturn(fetchedBatteriesFromRepository);
    }

    @Test
    void shouldGetBatteryStatsWithinPostcodes() {
        BatteryStatResponse actualResponse = batteryService.getBatteryStatsWithinPostcodes(9001, 9004);
        assertThat(actualResponse)
                .usingRecursiveComparison()
                .isEqualTo(expectedStatResponse);
        log.info("Battery Service test completed......");
    }
}
