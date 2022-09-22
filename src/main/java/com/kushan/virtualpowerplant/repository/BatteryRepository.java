package com.kushan.virtualpowerplant.repository;

import com.kushan.virtualpowerplant.entity.Battery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ravindu
 * 9/22/2022
 */
@Repository
public interface BatteryRepository extends MongoRepository<Battery,String> {
    List<Battery> findByPostcodeBetween(int lowerPostcode,int upperPostcode);
}
