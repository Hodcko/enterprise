package com.hodcko;


import java.util.*;
import java.util.stream.Collectors;

public class University {
    private static List<Student> successfulStudent = new LinkedList<>();
    private static List<Student> studentsList = new LinkedList<>();

    public static void addStudentToList(Student student){
        studentsList.add(student);
    }

    public int countOfStudents(){
        return studentsList.size();
    }

    public List<Student> getSuccessfulStudent(){
      successfulStudent = studentsList.stream().filter(e -> (int)e.getAverageGrade() > 7)
              .collect(Collectors.toCollection(ArrayList::new));
      return successfulStudent;
    }

    public static List<Student> getStudentsList() {
        return studentsList;
    }

    public static String showLessonPlan(Student student){
        if (student.getSpec().equalsIgnoreCase("java")){
            return "Java lesson plan";
        }else if(student.getSpec().equalsIgnoreCase("php")){
            return "What cannot die dead";
        }else
            return "What is it?";

    }


}
