package com.kushan.virtualpowerplant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ravindu
 * 9/22/2022
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPostcodeRange extends RuntimeException {
    public InvalidPostcodeRange(String message) {
        super(message);
    }
}
