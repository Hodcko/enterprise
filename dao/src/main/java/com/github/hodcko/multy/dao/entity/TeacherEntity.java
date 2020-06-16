package com.github.hodcko.multy.dao.entity;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TeacherEntity extends UserEntity{
    @Column(name = "curs_id")
    protected Integer cursId;

    public TeacherEntity(){
    }

    public TeacherEntity(String name, String secondName, String email, Integer cursId) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.cursId = cursId;
    }

    public TeacherEntity(Integer id, String name, String secondName, String email, Integer cursId) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.cursId = cursId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private AuthUserEntity authUser;

    @ManyToOne
    @JoinColumn(name = "curs_id", insertable = false, updatable = false)
    private CursEntity curs;

    public Integer getCursId() {
        return cursId;
    }

    public void setCursId(Integer cursId) {
        this.cursId = cursId;
    }

    public CursEntity getCurs() {
        return curs;
    }

    public void setCurs(CursEntity curs) {
        this.curs = curs;
    }

    public AuthUserEntity getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUserEntity authUser) {
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
        TeacherEntity teacher = (TeacherEntity) o;
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
