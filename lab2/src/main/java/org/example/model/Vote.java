package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Vote {

    private String id;

    private String petitionId;

    private User user;

    private LocalDateTime votedAt;
}
