package com.green.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("student")
public class Student extends User {
    public Student() {
    }

    public Student(int id, String username, String firstName, String lastName, String group) {
        super(id, username, firstName, lastName, group);
    }


}
