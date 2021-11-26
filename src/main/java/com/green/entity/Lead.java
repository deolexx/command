package com.green.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("lead")
public class Lead extends Student {
    // TODO: 25.11.21 Still need to create unique fields for Lead class


    public Lead() {
    }

    public Lead(int id, String username, String firstName, String lastName, String group) {
        super(id, username, firstName, lastName, group);
    }
}
