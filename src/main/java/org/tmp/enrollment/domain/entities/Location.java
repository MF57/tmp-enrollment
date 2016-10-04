package org.tmp.enrollment.domain.entities;

import lombok.Data;

@Data
public class Location {

    private String cityName;
    private String streetAndNumber;
    private String postalCode;
}
