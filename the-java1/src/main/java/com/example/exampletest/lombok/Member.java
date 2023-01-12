package com.example.exampletest.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class Member {

    private String name;

    public int age;

}
