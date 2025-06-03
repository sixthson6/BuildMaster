package dev.audit;

import lombok.*;
import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

//@Document(collection = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    private String id;

    private String actionType;

    private String entityType;

    private String entityId;

    private Instant timestamp;

    private String actorName;

    private String payload;
}

