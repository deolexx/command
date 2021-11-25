package com.green.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("lead")
public class Lead extends User {
    public Lead() {
    }

    public Lead(int id, String username, String firstName, String lastName, String group) {
        super(id, username, firstName, lastName, group);
    }
}
