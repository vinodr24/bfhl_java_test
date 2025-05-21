package com.example.bfhlJavaTest.runner;

import com.example.bfhlJavaTest.model.Models.WebhookRequest;
import com.example.bfhlJavaTest.model.Models.WebhookResponse;
import com.example.bfhlJavaTest.service.SqlProblemSolver;
import com.example.bfhlJavaTest.service.WebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(AppStartupRunner.class);
    
    @Autowired
    private WebhookService webhookService;
    
    @Autowired
    private SqlProblemSolver sqlProblemSolver;
    
    @Override
    public void run(String... args) {
        logger.info("Application started - initiating webhook flow");
        
        try {
            WebhookRequest request = new WebhookRequest(
                    "Vinod",  
                    "1032222817", 
                    "vinod24012004@gmail.com"  
            );

            WebhookResponse webhookResponse = webhookService.generateWebhook(request);
            
            if (webhookResponse != null) {
                
                String finalQuery = sqlProblemSolver.solveSqlProblem(request.getRegNo());
                
                webhookService.submitSolution(
                        webhookResponse.getWebhook(),
                        webhookResponse.getAccessToken(),
                        finalQuery
                );
                
                logger.info("Solution submitted successfully");
            } else {
                logger.error("Failed to generate webhook");
            }
        } catch (Exception e) {
            logger.error("Error during webhook process", e);
        }
    }
}