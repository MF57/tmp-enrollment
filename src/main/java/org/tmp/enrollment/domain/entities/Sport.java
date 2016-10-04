package org.tmp.enrollment.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Sport {

    @Getter private final String name;

    public Sport(String name) {
        this.name = name;
    }
}
