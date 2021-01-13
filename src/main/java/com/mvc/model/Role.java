package com.mvc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {

    }


}
