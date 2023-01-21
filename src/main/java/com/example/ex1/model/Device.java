package com.example.ex1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ApiModel(value = "Device", description = "Devices which can be included in contracts")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deviceId;
    @NotBlank(message = "The name of the device cannot be empty")
    @ApiModelProperty(required = true,
            value = "Production name of the device",
            notes = "Cannot be blank")
    private String name;
    @NotBlank(message = "The type of the device cannot be empty")
    @ApiModelProperty(required = true,
            value = "Type of the device",
            notes = "Cannot be blank",
            example = "phone")
    private String type;
    @OneToOne
    @ApiModelProperty(required = true, value = "The entirety of warranty booklets associated to the devices")
    private Warranty warranty;
}
