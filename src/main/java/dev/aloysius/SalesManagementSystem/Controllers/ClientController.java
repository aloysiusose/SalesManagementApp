package dev.aloysius.SalesManagementSystem.Controllers;

import dev.aloysius.SalesManagementSystem.Domains.Client.Clients;
import dev.aloysius.SalesManagementSystem.Domains.Client.CreateClient;
import dev.aloysius.SalesManagementSystem.Services.ClientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServices clientServices;
    @PostMapping("/")
    public void createNewClient(@RequestBody CreateClient client){
        clientServices.createNewClient(client);
    }
    @GetMapping("/")
    public List<Clients> getAllClients(){
        return clientServices.getAllClients();
    }
    @PutMapping("/")
    public void updateClient(@RequestBody CreateClient client){
        clientServices.updateClient(client);
    }

    @DeleteMapping("/")
    public void deleteClient(@RequestBody CreateClient client){
        clientServices.deleteClient(client);
    }

}
