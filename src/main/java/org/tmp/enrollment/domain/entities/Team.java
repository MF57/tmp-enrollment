package org.tmp.enrollment.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class Team {
    @Id
    private String id;
    private String name;
    private List<String> userNames = new ArrayList<>();
}
