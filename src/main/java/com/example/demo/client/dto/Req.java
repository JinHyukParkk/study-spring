package com.example.demo.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Req<T> {

    private Header header;

    private T responseBody;


    @Getter @Setter
    public static class Header {
        private String responseCode;

        @Override
        public String toString() {
            return "Header{" +
                    "responseCode='" + responseCode + '\'' +
                    '}';
        }
    }
}
