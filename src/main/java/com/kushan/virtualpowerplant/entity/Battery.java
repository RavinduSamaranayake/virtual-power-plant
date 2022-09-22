package com.kushan.virtualpowerplant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ravindu
 * 9/22/2022
 */
@Data
@AllArgsConstructor
@Document(collection = "battery")
public class Battery {
    @Id
    private String id;
    private String name;
    @Indexed
    private int postcode;
    private double wattCapacity;
}
