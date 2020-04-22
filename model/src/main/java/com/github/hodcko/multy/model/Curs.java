package com.github.hodcko.multy.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "curs")
public class Curs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column (name = "start_date")
    private LocalDate start;
    @Column (name = "end_date")
    private LocalDate end;

    public Curs(Integer id, String name, LocalDate start, LocalDate end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public Curs() {
    }

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

    @Override
    public String toString() {
        return "Curs{" +
                "name=" + name +
                ", start=" + start +
                ", end=" + end +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curs curs = (Curs) o;
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
