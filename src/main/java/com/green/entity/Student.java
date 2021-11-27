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


    // fetch = FetchType.EAGER  added to avoid LazyInitializationException
    @XmlElement
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "students")
    private Set<Mentor> mentors = new HashSet<>();


    // TODO: 26.11.2021  @Xml annotation?
    /*
    Additional method for add Student to the students Set
     */
    public void addMentor(Mentor mentor) {
        this.mentors.add(mentor);
        mentor.getStudents().add(this);
    }

    /*
    Additional method for removing Student from the students Se
     */
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
