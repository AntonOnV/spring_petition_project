package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Petition {

    private String id;
    private String name;
    private String description;

    private List<Vote> votes;


    public void addVote(Vote vote) {
        if (votes ==null ){
            votes = new ArrayList<>();
        }
        votes.add(vote);
    }
}
