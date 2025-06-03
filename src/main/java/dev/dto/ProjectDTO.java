package dev.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private String status;

    private Set<Long> taskIds;         // Only task IDs
    private Set<Long> developerIds;    // Only developer IDs
}
