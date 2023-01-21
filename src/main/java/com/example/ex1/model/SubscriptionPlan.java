package com.example.ex1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ApiModel(value = "SubscriptionPlan",
        description = "Subscription plans are used in contracts")
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subscriptionPlanId;
    @NotBlank(message = "The name of the subscription plan cannot be null")
    @ApiModelProperty(required = true, value = "The name of the subscription plan", notes = "Cannot be blank")
    private String name;
    @ElementCollection
    @ApiModelProperty(required = true, value = "The list of details about the subscription plan")
    private List<String> details;
    @ApiModelProperty(required = true, value = "The required amount to be paid (in euros)")
    private BigDecimal pricePerYear;
}