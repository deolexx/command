package com.green.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("student")
public class Student extends User {
    // TODO: 25.11.21 Still need to create unique fields for Student class
    public Student() {
    }

    public Student(int id, String username, String firstName, String lastName, String group) {
        super(id, username, firstName, lastName, group);
    }
    @XmlElement
    // fetch = FetchType.EAGER  added to avoid LazyInitializationException
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Mentor> mentors = new HashSet<>();


    public Set<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(Set<Mentor> mentors) {
        this.mentors = mentors;
    }
}
