package com.example.ex1.service;

import com.example.ex1.model.Contract;
import com.example.ex1.model.Device;
import com.example.ex1.repository.ContractRepository;
import com.example.ex1.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceManagementService {
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    ContractRepository contractRepository;

    public Contract updateDeviceInContract(Integer contractId, List<Integer> devicesId){
        List<Device> devices = deviceRepository.findAllById(devicesId);
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract update error, no contract with given id"));
        System.out.println(contract);
        contract.setDevices(devices);
        System.out.println(contract);
        return contractRepository.save(contract);
    }
}
