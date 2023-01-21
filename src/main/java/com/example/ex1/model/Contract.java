package com.example.ex1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ApiModel(value = "Contract", description = "Contract between the company and " +
        " a client, can include devices and needs to be signed using a digital signature")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;
    @PastOrPresent
    @NotNull
    @ApiModelProperty(required = true, value = "The date the contract was established")
    private Date date;
    @Min(value = 1, message = "The billing interval cannot be less than 1 month")
    @Max(value = 12, message = "The billing interval cannot be more than 12 months")
    @NotNull
    @ApiModelProperty(required = true,
            value = "The interval (in months) for payments,",
            example = "4",
            notes=" the value has to be between 1 and 12")
    private Integer billingInterval;
    @Min(value = 12, message = "The duration cannot be less than 12 months")
    @NotNull
    @ApiModelProperty(required = true,
            value = "The duration of the contract (in months), ",
            example = "24",
            notes = "cannot be less than 12")
    private Integer duration;
    @OneToMany
    @ApiModelProperty(required = true,
            value = "The devices included in the contract",
            notes = "There must be at least one device")
    private List<Device> devices;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull
    @ApiModelProperty(required = true, value = "The client involved in the contract")
    private Client client;
    @OneToOne
    @NotNull
    @ApiModelProperty(required = true, value = "The subscription plan the client agreed to")
    private SubscriptionPlan subscriptionPlan;
    @OneToOne
    @NotNull
    @ApiModelProperty(required = true, value = "The client's digital signature")
    private Signature signature;

}
