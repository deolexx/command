package com.green.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@DiscriminatorValue("student")
public class Student extends User {


    public Student() {
    }

    public Student(int id, String username, String firstName, String lastName, String group) {
        super(id, username, firstName, lastName, group);
    }


    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "students")
    private Set<Mentor> mentors = new HashSet<>();


    public void addMentor(Mentor mentor) {
        this.mentors.add(mentor);
        mentor.getStudents().add(this);
    }


    public void removeMentor(Mentor mentor) {
        this.mentors.remove(mentor);
        mentor.getStudents().remove(this);
    }


    public Set<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(Set<Mentor> mentors) {
        this.mentors = mentors;
    }
}
