package com.green.entity;

import javax.persistence.*;
import java.util.Set;
@Entity
@DiscriminatorValue("mentor")
public class Mentor extends User{
    public Mentor() {
    }

    public Mentor(int id, String username, String firstName, String lastName, String group) {
        super(id, username, firstName, lastName, group);
    }
//    @Column(name = "students_set")
//    @ElementCollection(targetClass=Student.class)
//    Set<Student> studentsSet;
//
//    public Set<Student> getStudentsSet() {
//        return studentsSet;
//    }
//
//    public void setStudentsSet(Set<Student> studentsSet) {
//        this.studentsSet = studentsSet;
//    }
}
