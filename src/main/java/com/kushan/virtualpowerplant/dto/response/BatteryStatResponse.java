package com.kushan.virtualpowerplant.dto.response;
import java.util.List;

/**
 * @author Ravindu
 * 9/22/2022
 */
public record BatteryStatResponse(List<String> batteries, double totalWatt, double averageWatt) {
}
