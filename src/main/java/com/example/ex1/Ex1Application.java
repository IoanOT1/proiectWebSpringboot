package com.example.ex1;

import com.example.ex1.model.Client;
import com.example.ex1.repository.*;
import com.example.ex1.service.ClientManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
public class Ex1Application implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private SignatureRepository signatureRepository;
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Autowired
    private WarrantyRepository warrantyRepository;
    @Autowired
    ClientManagementService clientManagementService;

    public static void main(String[] args) {
        SpringApplication.run(com.example.ex1.Ex1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {}

}