package com.example.ex1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ApiModel(value = "Warranty",
        description = "Warranty for devices include into contracts.")
public class Warranty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warrantyId;
    @NotBlank(message = "details cannot be empty")
    @ApiModelProperty(required = true,
            value = "Details about the warranty",
            notes = "Cannot be blank")
    private String details;
    @Min(value = 12, message = "The duration cannot be less than 12 months")
    @ApiModelProperty(required = true,
            value = "The warranty will be in effect for this amount of time (months)",
            example = "24")
    private Integer duration;
    @ElementCollection
    @NotEmpty(message = "There must be at least one condition")
    @ApiModelProperty(required = true,
            value = "The list of conditions that must be met in order for the warranty to be valid")
    private List<String> conditions;
}
