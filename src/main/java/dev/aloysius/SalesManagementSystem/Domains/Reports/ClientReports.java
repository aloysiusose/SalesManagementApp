package dev.aloysius.SalesManagementSystem.Domains.Reports;

import dev.aloysius.SalesManagementSystem.Domains.Client.Address;

public record ClientReports(int numberOfClients, String topSpendingClient, Address clientAddress) {
}
