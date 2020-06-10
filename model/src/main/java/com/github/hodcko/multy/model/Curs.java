package com.github.hodcko.multy.model;

import java.time.LocalDate;
import java.util.Objects;


public class Curs {
    private Integer id;

    private String name;

    private LocalDate start;

    private LocalDate end;

    public Curs() {
    }

    public Curs(Integer id, String name, LocalDate start, LocalDate end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
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
