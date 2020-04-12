package com.github.hodcko.multy.model;



import java.util.*;

public class Gradebook {

    private List<Integer> grades = new ArrayList<>();
    private Map<Integer , List<Integer>> gradelist = new HashMap<>();

    private static volatile Gradebook instance;

    public static Gradebook getInstance(){
        Gradebook localInstance = instance;
        if(localInstance == null){
            synchronized (Gradebook.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new Gradebook();
                }
            }
        }
        return localInstance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gradebook gradelist1 = (Gradebook) o;
        return Objects.equals(grades, gradelist1.grades) &&
                Objects.equals(gradelist, gradelist1.gradelist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grades, gradelist);
    }
}
