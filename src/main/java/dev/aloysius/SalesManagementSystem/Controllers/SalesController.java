package dev.aloysius.SalesManagementSystem.Controllers;

import dev.aloysius.SalesManagementSystem.Domains.Sale.Sales;
import dev.aloysius.SalesManagementSystem.Services.SalesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesServices salesServices;
    @PostMapping("/")
    public void createNewSale(@RequestBody Sales sales){
        salesServices.createNewSales(sales);
    }
    @GetMapping("/")

    public List<Sales> getAllSales(){
        return salesServices.getAllSales();
    }
    @PutMapping("/")
    public void updateSales(){

    }
}
