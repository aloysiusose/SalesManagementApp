package dev.aloysius.SalesManagementSystem.Services;

import dev.aloysius.SalesManagementSystem.Domains.Client.Clients;
import dev.aloysius.SalesManagementSystem.Domains.Client.CreateClient;
import dev.aloysius.SalesManagementSystem.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServices {

    private final ClientRepository clientRepository;

    public void createNewClient(CreateClient client){
        if (clientRepository.findByEmail(client.email()).isPresent()) {
            throw new IllegalStateException();
        }
        Clients clients = toClients(client);
        clientRepository.save(clients);

    }
    public List<Clients> getAllClients(){
        return clientRepository.findAll();
    }
    public void updateClient(CreateClient client){
        if (clientRepository.findByEmail(client.email()).isPresent()) {
            throw new IllegalStateException();
        }
        Clients clients = clientRepository.findByEmail(client.email()).get();
        clients.setAddress(client.address());
        clients.setFirstName(client.firstName());
        clients.setLastName(client.lastName());
        clients.setEmail(client.email());
        clients.setMobile(client.mobile());
        clientRepository.save(clients);
    }

    private Clients toClients(CreateClient client){
        Clients clients = new Clients();
        clients.setFirstName(client.firstName());
        clients.setLastName(client.lastName());
        clients.setEmail(client.email());
        clients.setMobile(client.mobile());
        clients.setAddress(client.address());
        return clients;
    }

    public void deleteClient(CreateClient client) {
        clientRepository.delete(toClients(client));
    }
}
