package org.example.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;

import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Petition.findAll",
                query = "select p from Petition p"
        )
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Petition")
public class Petition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "petition")
    @Cascade(value = CascadeType.ALL)
    private List<Vote> votes;
}
