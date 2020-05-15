package com.github.hodcko.multy.model;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "teacher")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Teacher extends User{

    @Column (name = "curs_id")
    protected Integer cursId;

    public Teacher(){
    }

    public Teacher(String name, String secondName, String email, Integer curs_id) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.cursId = curs_id;
    }

    public Teacher(Integer id, String name, String secondName, String email, Integer curs_id) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.cursId = curs_id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private AuthUser authUser;

    @ManyToOne
    @JoinColumn(name = "curs_id", insertable = false, updatable = false)
    private Curs curs;

    public Integer getCursId() {
        return cursId;
    }

    public void setCursId(Integer cursId) {
        this.cursId = cursId;
    }

    public Curs getCurs() {
        return curs;
    }

    public void setCurs(Curs curs) {
        this.curs = curs;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Override
    public String toString() {
        return "Teacher{" +
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
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) &&
                Objects.equals(name, teacher.name) &&
                Objects.equals(secondName, teacher.secondName) &&
                Objects.equals(email, teacher.email) &&
                Objects.equals(cursId, teacher.cursId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, email, cursId);
    }
}
