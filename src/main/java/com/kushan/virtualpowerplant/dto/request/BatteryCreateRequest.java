package com.kushan.virtualpowerplant.dto.request;

import javax.validation.constraints.NotEmpty;

/**
 * @author Ravindu
 * 9/22/2022
 */
public record BatteryCreateRequest(@NotEmpty(message = "Battery Name is required") String name, int postcode,
                                   double wattCapacity) {
}
