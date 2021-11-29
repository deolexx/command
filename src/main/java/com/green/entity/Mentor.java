package com.green.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@DiscriminatorValue("mentor")
public class Mentor extends User {

    // TODO: 25.11.21 Still need to create unique fields for Mentor class
    public Mentor() {
    }

    public Mentor(int id, String username, String firstName, String lastName, String group) {
        super(id, username, firstName, lastName, group);
    }


    @XmlElement
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(schema = "command",
            name = "mentor_student",
            joinColumns = {@JoinColumn(name = "mentor_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private Set<Student> students = new HashSet<>();


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
