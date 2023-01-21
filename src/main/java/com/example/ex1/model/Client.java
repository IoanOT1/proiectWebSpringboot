package com.example.ex1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
// TODO
// import jakarta.persistence.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ApiModel(value = "Client", description = "Contains identifying information for a client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;
    @NotBlank(message = "First name cannot be null")
    @ApiModelProperty(required = true, value = "The first name of the client", example = "Andrei")
    private String firstName;
    @NotBlank(message = "Last name cannot be null")
    @ApiModelProperty(required = true, value = "The last name of the client", example = "Vancea")
    private String lastName;

}
