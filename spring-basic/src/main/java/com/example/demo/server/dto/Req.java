package com.example.demo.server.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Req<T> {

    private Header header;

    private T responseBody;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Header {
        private String responseCode;

    }
}