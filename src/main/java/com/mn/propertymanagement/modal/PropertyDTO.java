package com.mn.propertymanagement.modal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDTO {

    private Long id;

    private String propertyType;

    private String propertyDescription;

    private Double price;

    private String address;

    private Long userId;

}
