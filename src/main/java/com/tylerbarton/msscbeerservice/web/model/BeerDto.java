package com.tylerbarton.msscbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Beer data transfer object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDto {
    @JsonProperty("beerId")
    @Null
    private UUID id;
    @Null
    private Integer version;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastModifiedDate;
    @NotBlank
    private String beerName;
    @NotNull
    private BeerStyleEnum beerStyle;
    @Positive
    @NotNull
    private Long upc;
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;
    @PositiveOrZero
    private Integer quantityOnHand;
    @JsonSerialize(using=LocalDateSerializer.class)
    @JsonDeserialize(using=LocalDateDeserializer.class)
    private LocalDate myLocalDate;
}
