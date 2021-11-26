package com.green.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("mentor")
public class Mentor extends User{
    // TODO: 25.11.21 Still need to create unique fields for Mentor class
    public Mentor() {
    }

    public Mentor(int id, String username, String firstName, String lastName, String group) {
        super(id, username, firstName, lastName, group);
    }

    // TODO: 26.11.2021 now if we delete mentor
    //  all mapped students also will be deleted
    //  still need some tests

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Student> students = new HashSet<>();

    public void addStudent(Student student){
        this.students.add(student);
        student.getMentors().add(this);
    }
    public void removeStudent(Student student){
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
