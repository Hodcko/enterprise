package com.github.hodcko.multy.dao.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "curs")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column (name = "start_date")
    private LocalDate start;
    @Column (name = "end_date")
    private LocalDate end;

    public CursEntity() {
    }

    public CursEntity(Integer id, String name, LocalDate start, LocalDate end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public CursEntity(Integer id, String name, LocalDate start, LocalDate end, List<TeacherEntity> teachers) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.teachers = teachers;
    }



    @OneToMany(mappedBy = "curs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherEntity> teachers = new ArrayList<>();

    @ManyToMany(mappedBy = "curses", cascade = CascadeType.ALL)
    private List<StudentEntity> students = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public List<TeacherEntity> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherEntity> teachers) {
        this.teachers = teachers;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Curs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursEntity curs = (CursEntity) o;
        return Objects.equals(id, curs.id) &&
                Objects.equals(name, curs.name) &&
                Objects.equals(start, curs.start) &&
                Objects.equals(end, curs.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, end);
    }
}
