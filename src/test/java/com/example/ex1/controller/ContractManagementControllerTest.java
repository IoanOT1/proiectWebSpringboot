package com.example.ex1.controller;

import com.example.ex1.model.Client;
import com.example.ex1.model.Contract;
import com.example.ex1.model.SubscriptionPlan;
import com.example.ex1.repository.*;
import com.example.ex1.service.ClientManagementService;
import com.example.ex1.service.ContractManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ContractManagementController.class)
public class ContractManagementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ContractRepository contractRepository;
    @MockBean
    private DeviceRepository deviceRepository;
    @MockBean
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @MockBean
    private SignatureRepository signatureRepository;
    @MockBean
    private WarrantyRepository warrantyRepository;

    @MockBean
    private ContractManagementService contractManagementService;
    @MockBean
    private ClientManagementService clientManagementService;

    @Test
    public void createContract() throws Exception {
        Contract contract = Contract.builder()
                .billingInterval(6)
                .date(new Date())
                .duration(12)
                .build();

        when(contractManagementService.create(any(), any(), any(), any(), any())).thenReturn(contract);

        List<String> values = Arrays.asList("1", "1", "1");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("clientId", "1");
        params.add("subscriptionId", "1");
        params.add("signatureId", "1");
        params.addAll("devicesId", values);

        mockMvc.perform(MockMvcRequestBuilders.post("/contracts")
                        .contentType("application/json")
                        .params(params)
                        .content(objectMapper.writeValueAsString(contract)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.duration").value(contract.getDuration()));
    }
}

    /*
    @Test
    public void createAlbumDetails() throws Exception{
        AlbumDetails albumDetails = new AlbumDetails("Some new AlbumDetails");

        when(shopService.saveAlbumDetails(any())).thenReturn(albumDetails);

        mockMvc.perform(post("/shop/albumDetails/new")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(albumDetails)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.description").value(albumDetails.getDescription())
                );
    }

    @Test
    public void createAlbum() throws Exception{
        Album album = new Album("Album1", 5);
        String albumDetailsId = "1";
        String artistId = "1";

        when(shopService.saveAlbum(any(),anyInt(),anyInt())).thenReturn(album);

        mockMvc.perform(post("/shop/album/new/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(album))
                        .param("albumDetailsId", albumDetailsId)
                        .param("artistId", artistId))
                .andExpect(status().is2xxSuccessful())
                .andExpect((jsonPath("$.albumName")).value(album.getAlbumName()))
                .andExpect((jsonPath("$.albumQuantity")).value(album.getAlbumQuantity()));
    }

     */
