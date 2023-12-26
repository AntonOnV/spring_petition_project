package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="votes")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "petitionId"})
})
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="petitionId")
    private Petition petition;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private LocalDateTime votedAt;
}
