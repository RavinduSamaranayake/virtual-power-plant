package com.kushan.virtualpowerplant.dto.request;

import java.util.List;

/**
 * @author Ravindu
 * 9/22/2022
 */
public record BulkBatteryCreateRequest(List<BatteryCreateRequest> batteryCreateRequestList) {
}
