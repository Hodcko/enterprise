package com.github.hodcko.multy.model;

import java.sql.Date;
import java.util.Objects;

public class Curs {
    private int id;
    private String name;
    private Date start;
    private Date end;

    public Curs(int id, String name, Date start, Date end) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
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
        return id == curs.id &&
                Objects.equals(name, curs.name) &&
                Objects.equals(start, curs.start) &&
                Objects.equals(end, curs.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, end);
    }
}
