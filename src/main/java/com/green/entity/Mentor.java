package com.green.entity;

import javax.persistence.Entity;
import java.util.Set;
@Entity
public class Mentor extends User{


    Set<Student> studentsSet;

    public Set<Student> getStudentsSet() {
        return studentsSet;
    }

    public void setStudentsSet(Set<Student> studentsSet) {
        this.studentsSet = studentsSet;
    }
}
