package org.tmp.enrollment.domain.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private String name;
    private List<Tournament> tournaments;
}
