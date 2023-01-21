package com.example.ex1.controller;

import com.example.ex1.model.Client;
import com.example.ex1.model.SubscriptionPlan;
import com.example.ex1.service.ClientManagementService;
import com.example.ex1.service.PlanManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
@Api(value = "/clients", description = "Operations related to managing clients")
public class ClientManagerController {
    private final ClientManagementService clientManagementService;
    public ClientManagerController(ClientManagementService clientManagementService) {
        this.clientManagementService = clientManagementService;
    }

    @PostMapping
    @ApiOperation(value="This operation adds the client to the database or updates it if the id is already present",
            response = Client.class)
    public ResponseEntity<Client> save(@RequestBody @Valid Client client) {
        Client createdClient = clientManagementService.save(client);
        return ResponseEntity
                .created(URI.create("/clients/" + createdClient.getClientId()))
                .body(createdClient);
    }

    @GetMapping
    @ApiOperation(value="This operation gets all the clients with a given " +
            "firstName or lastName, both are optional",
            response = List.class)
    public ResponseEntity<List<Client>> getBy(@RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {
        return ResponseEntity.ok().body(clientManagementService.getBy(firstName, lastName));
    }
}
