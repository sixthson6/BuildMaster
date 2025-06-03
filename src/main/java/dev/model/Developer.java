package dev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @ElementCollection
    private Set<String> skills;

    @OneToMany(mappedBy = "developer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Task> tasks;

    @ManyToMany
    @JoinTable(
            name = "developer_project",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects;

    @Version
    private Integer version;
}
