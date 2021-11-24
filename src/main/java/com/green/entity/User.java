package com.green.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Table(schema = "command",name = "user")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "user")
public class User implements Comparable<User> {
    @XmlElement
    @Id
    @Column(name = "user_id")
    private int id;
    @XmlElement
    @Column(name = "username")
    private String username;
    @XmlElement
    @Column(name = "first_name")
    private String firstName;
    @XmlElement
    @Column(name = "last_name")
    private String lastName;
    @XmlElement
    @Column(name = "role")
    private String role;
    @XmlElement
    @Column(name = "group")
    private String group;

    public User(int id, String username, String firstName, String lastName, String role, String group) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.group = group;
    }

    public User(String username, String firstName, String lastName, String role, String group) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.group = group;
    }

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    public int compareTo(User user) {
        return this.getId() > user.getId() ? 1 : -1;

    }
}

