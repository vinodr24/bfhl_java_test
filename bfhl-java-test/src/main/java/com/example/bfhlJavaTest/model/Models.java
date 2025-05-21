package com.example.bfhlJavaTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Models {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WebhookRequest {
        private String name;
        private String regNo;
        private String email;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WebhookResponse {
        private String webhook;
        private String accessToken;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SolutionRequest {
        private String finalQuery;
    }
}