package org.example.config;

import org.example.repository.VoteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public VoteRepository voteRepository() {
        return new VoteRepository();
    }
}
