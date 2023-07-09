package com.mn.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//PK

    @Column(name = "PROPERTY_TITLE", nullable = false)
    private String propertyType;

    private String propertyDescription;

    private Double price;

    private String address;
}
