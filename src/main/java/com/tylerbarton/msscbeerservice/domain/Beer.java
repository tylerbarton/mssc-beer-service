package com.tylerbarton.msscbeerservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Creates a JPA entity for beer so that it may be stored in a database
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {
    // Identifying information
    @Id
    @GeneratedValue(generator="UUID") // Generates a UUID for us
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @Column(length=36, columnDefinition="varchar(36)", updatable=false, nullable=false)
    private UUID io;

    // Version Properties
    @Version
    private Long version;

    // Descriptive data
    @CreationTimestamp
    @Column(updatable=false)                // Creation is static
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp lastModifiedDate;
    private String beerName;
    private String beerStyle;               // could be BeerStyleEnum later
    @Column(unique=true)                    // upc should be unique
    private String upc;
    private BigDecimal price;               // money represented by BigDecimal

    // Quantity data
    private Integer minOnHand;
    private Integer quantityToBrew;
}
