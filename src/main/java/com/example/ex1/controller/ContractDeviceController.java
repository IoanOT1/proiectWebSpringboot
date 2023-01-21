package com.example.ex1.controller;

import com.example.ex1.model.Contract;
import com.example.ex1.service.DeviceManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/devices")
@Api(value = "/devices", description = "Operations on devices included into contracts")
public class ContractDeviceController {
    private final DeviceManagementService deviceManagementService;

    public ContractDeviceController(DeviceManagementService deviceManagementService) {
        this.deviceManagementService = deviceManagementService;
    }

    @PostMapping
    @ApiOperation(value="This operation updates the list of devices for a contract with the given id",
            response = Contract.class)
    public ResponseEntity<Contract> updateDevicesForContract( @RequestParam Integer contractId,
                                                              @RequestParam List<Integer> devicesId) {
        Contract updatedContract =  this.deviceManagementService.updateDeviceInContract(contractId, devicesId);
        return ResponseEntity
                .created(URI.create("/devices/" + updatedContract.getContractId()))
                .body(updatedContract);
    }
}
