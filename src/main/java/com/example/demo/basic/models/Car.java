package com.example.demo.basic.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class Car {

    @NotBlank
    private String name;

    @NotBlank
    @JsonProperty("car_number")
    private String carNumber;

    @NotBlank
    @JsonProperty("TYPE")
    private String type;

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}