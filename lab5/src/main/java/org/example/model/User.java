package org.example.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Getter
@Setter
@Component
public class User {

    private String id;
}
