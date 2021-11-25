package com.green.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("mentor")
public class Mentor extends User{
    public Mentor() {
    }

    public Mentor(int id, String username, String firstName, String lastName, String group) {
        super(id, username, firstName, lastName, group);
    }

}
