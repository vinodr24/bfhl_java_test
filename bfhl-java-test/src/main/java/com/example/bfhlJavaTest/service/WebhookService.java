package com.example.bfhlJavaTest.service;

import com.example.bfhlJavaTest.model.Models.SolutionRequest;
import com.example.bfhlJavaTest.model.Models.WebhookRequest;
import com.example.bfhlJavaTest.model.Models.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {
    
    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);
    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    
    @Autowired
    private RestTemplate restTemplate;
    
    public WebhookResponse generateWebhook(WebhookRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);
        
        logger.info("Sending webhook generation request: {}", request);
        
        ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(
                GENERATE_WEBHOOK_URL,
                entity,
                WebhookResponse.class
        );
        
        WebhookResponse webhookResponse = response.getBody();
        logger.info("Received webhook response: {}", webhookResponse);
        
        return webhookResponse;
    }
    
    public void submitSolution(String webhookUrl, String accessToken, String finalQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);
        
        SolutionRequest request = new SolutionRequest(finalQuery);
        HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);
        
        logger.info("Submitting solution to webhook: {}", webhookUrl);
        logger.info("Solution query: {}", finalQuery);
        
        ResponseEntity<String> response = restTemplate.postForEntity(
                webhookUrl,
                entity,
                String.class
        );
        
        logger.info("Solution submission response: {}", response.getBody());
    }
}