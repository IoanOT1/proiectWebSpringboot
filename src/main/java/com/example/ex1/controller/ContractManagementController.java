package com.example.ex1.controller;


import com.example.ex1.model.Contract;
import com.example.ex1.service.ContractManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contracts")
@Api(value = "/contracts", description = "Operations related to contract management")
public class ContractManagementController {

    private final ContractManagementService contractManagementService;
    public ContractManagementController(ContractManagementService contractManagementService) {
        this.contractManagementService = contractManagementService;
    }

    @ApiOperation(value="This operation creates a new contract given " +
            "ids for a client, a subscription, a signature and " +
            "a list of ids for devices",
            response = Contract.class)
    @PostMapping
    public ResponseEntity<Contract> createContract( @RequestBody Contract contract,
                                                    @RequestParam Integer clientId,
                                                    @RequestParam Integer subscriptionId,
                                                    @RequestParam Integer signatureId,
                                                    @RequestParam List<Integer> devicesId) {
        Contract createdContract = contractManagementService.create(
                clientId,
                subscriptionId,
                devicesId,
                signatureId,
                contract);
        return ResponseEntity
                .created(URI.create("/contracts/" + createdContract.getContractId()))
                .body(createdContract);
    }

}
