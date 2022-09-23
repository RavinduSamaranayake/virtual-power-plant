package com.kushan.virtualpowerplant.service;

import com.kushan.virtualpowerplant.dto.request.BatteryCreateRequest;
import com.kushan.virtualpowerplant.dto.request.BulkBatteryCreateRequest;
import com.kushan.virtualpowerplant.dto.response.BatteryStatResponse;
import com.kushan.virtualpowerplant.entity.Battery;
import com.kushan.virtualpowerplant.exception.InvalidPostcodeRange;
import com.kushan.virtualpowerplant.repository.BatteryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * @author Ravindu
 * 9/22/2022
 */
@Service
@RequiredArgsConstructor
public class BatteryService {
    private final BatteryRepository repository;

    public Battery createBattery(BatteryCreateRequest request) {
        return repository.save(getBatteryFromRequest(request));
    }

    public List<Battery> acceptBulkBatteryCreateRequest(BulkBatteryCreateRequest request) {
        var batteryList = request.batteryCreateRequestList().stream()
                .map(this::getBatteryFromRequest).toList();
        return repository.saveAll(batteryList);
    }

    public List<Battery> getAllBatteries() {
        return repository.findAll();
    }

    public BatteryStatResponse getBatteryStatsWithinPostcodes(int lowerPostcode, int upperPostcode) {
        if (lowerPostcode >= upperPostcode)
            throw new InvalidPostcodeRange("lowerPostcode : " + lowerPostcode + "higher than upperPostcode : " + upperPostcode);
        var batteryList = repository.findByPostcodeBetween(lowerPostcode - 1, upperPostcode);
        if (batteryList.isEmpty()) return new BatteryStatResponse(List.of(), 0.0, 0.0);
        var sortedNameList = batteryList.stream().filter(battery -> battery.getName() != null)
                .sorted(Comparator.comparing(Battery::getName, String.CASE_INSENSITIVE_ORDER)).map(Battery::getName).toList();
        var totalWatt = batteryList.stream().mapToDouble(Battery::getWattCapacity).sum();
        var avgWatt = totalWatt / batteryList.size();
        return new BatteryStatResponse(sortedNameList, totalWatt, avgWatt);
    }

    private Battery getBatteryFromRequest(BatteryCreateRequest request) {
        return new Battery(UUID.randomUUID().toString(), request.name(), request.postcode(), request.wattCapacity());
    }
}
