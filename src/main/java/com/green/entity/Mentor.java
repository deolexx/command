package com.green.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("mentor")
public class Mentor extends User {
    // TODO: 25.11.21 Still need to create unique fields for Mentor class
    public Mentor() {
    }

    public Mentor(int id, String username, String firstName, String lastName, String group) {
        super(id, username, firstName, lastName, group);
    }

    // TODO: 26.11.2021 now if we delete mentor
    //  all mapped students also will be deleted
    //  still need some tests
    @XmlElement
    // fetch = FetchType.EAGER  added to avoid LazyInitializationException
    // cascade = CascadeType.REMOVE added to delete mapped students when Mentor removed
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Student> students = new HashSet<>();


    // TODO: 26.11.2021  @Xml annotation?
    /*
    Additional method for add Student to the students Set
     */
    public void addStudent(Student student) {
        this.students.add(student);
        student.getMentors().add(this);
    }

    /*
    Additional method for removing Student from the students Se
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
        student.getMentors().remove(this);
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
