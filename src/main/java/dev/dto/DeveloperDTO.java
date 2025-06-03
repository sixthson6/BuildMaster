package dev.dto;

import lombok.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeveloperDTO {
    private Long id;
    private String name;
    private String email;
    private Set<String> skills;
    private Set<Long> projectIds;
}