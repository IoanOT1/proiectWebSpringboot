package com.example.ex1.service;

import com.example.ex1.model.Client;
import com.example.ex1.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientManagementService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getBy(String firstName, String lastName) {
        return clientRepository.findAll()
                .stream()
                .filter((client -> firstName == null || Objects.equals(client.getFirstName(), firstName)))
                .filter((client -> lastName == null || Objects.equals(client.getLastName(), lastName)))
                .collect(Collectors.toList());
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Integer clientId) {
        clientRepository.delete(
                clientRepository.findById(clientId)
                        .orElseThrow(() -> new RuntimeException("Delete failed, no client with the given id")));
    }
}
