package ma.benouss.task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ma.benouss.task.common.BaseEntity;
import ma.benouss.task.entity.enumuration.Status;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Task extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
