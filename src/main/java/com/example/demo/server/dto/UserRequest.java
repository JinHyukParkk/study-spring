package com.example.demo.server.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @ApiModelProperty(value = "사용자의 이름", example = "steve", required = true)
    private String name;

    @ApiModelProperty(value = "사용자의 이메일", example = "steve@gmail.com", required = true)
    private String email;

    @ApiModelProperty(value = "사용자의 나이", example = "10", required = true)
    private int age;

    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}