package com.example.ex1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ApiModel(value = "Signature", description = "The digital signature of clients required in contracts")
public class Signature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer signatureId;
    @NotBlank
    @ApiModelProperty(required = true,
            value = "The hash of the signature",
            notes = "Cannot be blank")
    private String hash;
    @PastOrPresent(message = "The date must be in the past or the present")
    @ApiModelProperty(required = true, value = "The date of the signature")
    private Date date;
}
