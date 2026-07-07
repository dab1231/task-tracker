package org.example.tasktrackerbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.tasktrackerbackend.enums.TaskStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "tasks")
public class Task implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Override
    public Long getId() {
        return id;
    }
}
