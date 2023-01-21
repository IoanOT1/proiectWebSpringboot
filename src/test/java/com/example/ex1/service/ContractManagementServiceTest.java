package com.example.ex1.service;

import com.example.ex1.model.*;
import com.example.ex1.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContractManagementServiceTest {

    @InjectMocks
    private ContractManagementService contractManagementService;

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Mock
    private ContractRepository contractRepository;
    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private SignatureRepository signatureRepository;

    @Test
    @DisplayName("Create a contract successfully")
    void createContractSuccessfully() {
        Client client = Client.builder()
                .clientId(1)
                .firstName("abc")
                .lastName("def")
                .build();
        SubscriptionPlan subscriptionPlan = SubscriptionPlan.builder()
                .details(List.of("abc", "def"))
                .name("name")
                .subscriptionPlanId(1)
                .pricePerYear(BigDecimal.valueOf(1))
                .build();
        Signature signature = Signature.builder()
                .signatureId(1)
                .date(new Date())
                .hash("dfdff")
                .build();
        Contract contract = Contract.builder()
                .billingInterval(6)
                .date(new Date())
                .duration(12)
                .build();

        contract.setDevices(List.of());
        contract.setSignature(signature);
        contract.setClient(client);
        contract.setSubscriptionPlan(subscriptionPlan);

        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(subscriptionPlanRepository.findById(1)).thenReturn(Optional.of(subscriptionPlan));
        when(signatureRepository.findById(1)).thenReturn(Optional.of(signature));
        when(contractRepository.save(contract)).thenReturn(contract);

        Contract result = contractManagementService.create(
                client.getClientId(),
                subscriptionPlan.getSubscriptionPlanId(),
                List.of(),
                signature.getSignatureId(),
                contract);

        assertEquals(result.getSubscriptionPlan().getName(), subscriptionPlan.getName());
    }

    @Test
    @DisplayName("Create a contract throw no client id exception")
    void createContractClientNotFound() {
        when(clientRepository.findById(1)).thenReturn(Optional.empty());
        String response = "Contract save error, client id invalid";

        RuntimeException result = assertThrows(
                RuntimeException.class,
                () -> contractManagementService.create(1,1,List.of(), 1,any()));

        assertEquals(response, result.getMessage());
    }

    @Test
    @DisplayName("Create a contract throw no subscription plan id exception")
    void createContractSubscriptionNotFound() {
        when(subscriptionPlanRepository.findById(1)).thenReturn(Optional.empty());
        when(clientRepository.findById(1)).thenReturn(Optional.of(new Client()));
        String response = "Contract save error, subscription plan id invalid";

        RuntimeException result = assertThrows(
                RuntimeException.class,
                () -> contractManagementService.create(1,1, List.of(),1,new Contract()));

        assertEquals(response, result.getMessage());
    }

    @Test
    @DisplayName("Create a contract throw no signature id exception")
    void createContractSignatureNotFound() {
        when(subscriptionPlanRepository.findById(1)).thenReturn(Optional.of(new SubscriptionPlan()));
        when(clientRepository.findById(1)).thenReturn(Optional.of(new Client()));
        when(signatureRepository.findById(1)).thenReturn(Optional.empty());
        String response = "Contract save error, signature id invalid";

        RuntimeException result = assertThrows(
                RuntimeException.class,
                () -> contractManagementService.create(1,1, List.of(),1,new Contract()));

        assertEquals(response, result.getMessage());
    }
}

