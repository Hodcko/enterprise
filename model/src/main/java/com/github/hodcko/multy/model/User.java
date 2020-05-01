package com.github.hodcko.multy.model;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column
    protected String name;
    @Column (name = "second_name")
    protected String secondName;
    @Column
    protected String email;
    @Column (name = "curs_id")
    protected Integer cursId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCursId() {
        return cursId;
    }

    public void setCursId(Integer curs_id) {
        this.cursId = curs_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", curs_id=" + cursId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(cursId, user.cursId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cursId);
    }
}
