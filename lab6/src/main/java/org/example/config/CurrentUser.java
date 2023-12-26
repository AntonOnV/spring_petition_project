package org.example.config;

import lombok.Getter;
import lombok.Setter;
import org.example.model.User;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CurrentUser {
    private User user;
}
