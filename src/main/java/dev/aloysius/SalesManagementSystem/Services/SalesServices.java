package dev.aloysius.SalesManagementSystem.Services;

import dev.aloysius.SalesManagementSystem.Domains.Sale.Sales;
import dev.aloysius.SalesManagementSystem.Repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesServices {

    private final SalesRepository salesRepository;

    public void createNewSales(Sales sales) {
        salesRepository.save(sales);
    }

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }
}
