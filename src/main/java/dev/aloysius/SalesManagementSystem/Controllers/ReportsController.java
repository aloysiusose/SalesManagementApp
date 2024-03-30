package dev.aloysius.SalesManagementSystem.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportsController {

    @GetMapping("/product")
    public void generateProductReport(LocalDateTime start, LocalDateTime end){

    }
    @GetMapping("/client")
    public void generateClientReport(LocalDateTime start, LocalDateTime end){

    }
    @GetMapping("/sales")
    public void generateSalesReport(LocalDateTime start, LocalDateTime end){

    }

}
