package org.tmp.enrollment.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

    @Getter @Setter
    private LocalDateTime enrollmentEndDataTime;

    @Getter @Setter
    private List<User> enrolledParticipants;
}
