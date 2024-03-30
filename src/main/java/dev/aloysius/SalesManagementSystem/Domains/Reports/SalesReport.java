package dev.aloysius.SalesManagementSystem.Domains.Reports;

import java.math.BigDecimal;

public record SalesReport(int sales, BigDecimal totalRevenue, String topProduct, String topSeller) {
}
