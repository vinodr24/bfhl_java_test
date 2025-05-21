package com.example.bfhlJavaTest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SqlProblemSolver {
    
    private static final Logger logger = LoggerFactory.getLogger(SqlProblemSolver.class);
    
    public String solveSqlProblem(String regNo) {
        // Extract the last two digits from regNo
        String lastTwoDigits = extractLastTwoDigits(regNo);
        int lastTwoDigitsNum = Integer.parseInt(lastTwoDigits);
        
        // Determine which question to solve based on even or odd last two digits
        boolean isEven = lastTwoDigitsNum % 2 == 0;
        
        logger.info("Registration number: {}, Last two digits: {}, isEven: {}", 
                   regNo, lastTwoDigits, isEven);
        
        if (isEven) {
            // Even - Question 2
            logger.info("Solving Question 2 (Even)");
            return solveQuestion2();
        } else {
            // Odd - Question 1
            logger.info("Solving Question 1 (Odd)");
            return solveQuestion1();
        }
    }
    
    private String extractLastTwoDigits(String regNo) {
        // Assuming regNo format is like "REG12347"
        if (regNo.length() < 2) {
            return regNo;
        }
        return regNo.substring(regNo.length() - 2);
    }
    
    private String solveQuestion1() {
        // Solution for Question 1: Find the highest salary not on the 1st day of any month
        // Include employee name, age, and department
        
        return "SELECT p.AMOUNT AS SALARY, "
             + "CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, "
             + "EXTRACT(YEAR FROM SYSDATE()) - EXTRACT(YEAR FROM e.DOB) AS AGE, "
             + "d.DEPARTMENT_NAME "
             + "FROM PAYMENTS p "
             + "JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID "
             + "JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID "
             + "WHERE EXTRACT(DAY FROM p.PAYMENT_TIME) != 1 "
             + "ORDER BY p.AMOUNT DESC "
             + "FETCH FIRST 1 ROW ONLY";
    }
    
    private String solveQuestion2() {
        // Solution for Question 2: Calculate number of employees who are younger than each employee
        // Grouped by their respective departments
        
        return "SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME, "
             + "COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT "
             + "FROM EMPLOYEE e1 "
             + "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID "
             + "LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e1.DOB < e2.DOB "
             + "GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME "
             + "ORDER BY e1.EMP_ID DESC";
    }
}