package com.kushan.virtualpowerplant.controller;

import com.kushan.virtualpowerplant.dto.request.BatteryCreateRequest;
import com.kushan.virtualpowerplant.dto.request.BulkBatteryCreateRequest;
import com.kushan.virtualpowerplant.dto.response.BatteryStatResponse;
import com.kushan.virtualpowerplant.entity.Battery;
import com.kushan.virtualpowerplant.service.BatteryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ravindu
 * 9/22/2022
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/batteries")
public class BatteryController {
    private final BatteryService batteryService;

    @GetMapping
    public ResponseEntity<List<Battery>> getAll(){
        log.info("Fetch All batteries called..");
        return ResponseEntity.ok(batteryService.getAllBatteries());
    }

    @GetMapping("/postcode_range")
    public ResponseEntity<BatteryStatResponse> getStatsWithinPostcodes(@RequestParam("lowerPostcode") int lowerPostcode
            ,@RequestParam("upperPostcode") int upperPostcode){
        log.info("Fetch battery stats within postcode range {} - {} called..",lowerPostcode,upperPostcode);
        return ResponseEntity.ok(batteryService.getBatteryStatsWithinPostcodes(lowerPostcode,upperPostcode));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public Battery acceptCreateRequest(@Valid @RequestBody BatteryCreateRequest request){
        log.info("BatteryCreateRequest : {} accepted..",request);
        return batteryService.createBattery(request);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/bulk_create")
    public List<Battery> acceptBulkCreateRequest(@Valid @RequestBody BulkBatteryCreateRequest request){
        log.info("BulkBatteryCreateRequest : {} accepted..",request);
        return batteryService.acceptBulkBatteryCreateRequest(request);
    }
}
