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

//    @Column(name = "mentors_set")
//    @ElementCollection(targetClass=Mentor.class)
//    Set<Mentor> mentorsSet;
//
//    public Set<Mentor> getMentorsSet() {
//        return mentorsSet;
//    }
//
//    public void setMentorsSet(Set<Mentor> mentorsSet) {
//        this.mentorsSet = mentorsSet;
//    }
}
