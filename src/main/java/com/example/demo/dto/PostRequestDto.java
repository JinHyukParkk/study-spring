package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequestDto {

    private String account;
    private String email;
    private String address;
    private String password;

    @JsonProperty("phone_number")  // Object Mapper 로 대체 가능
    private String phoneNumber;

    @JsonProperty("OTP")
    private String OTP;

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", OTP='" + OTP + '\'' +
                '}';
    }
}
