package com.hodcko;

import java.util.*;

public class Student{
    private int id;
    private double averageGrade;
    private String name;
    private String secondName;
    private String spec;
    private List<Integer> gradeList = new LinkedList<>();
    private static Map<Integer, List<Integer>> journal = new HashMap<>();


    private static Map<Integer, Map<Enum<Lessons>, List<Integer>>> journal2 = new HashMap<>();
    private Map<Enum<Lessons>, List<Integer>> gradeList2 = new HashMap<>();

    Random random = new Random();

    public Student(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
        this.id = random.nextInt(200);
//        journal.put(this.id, new ArrayList<>());
//        University.addStudentToList(this);
    }

    public void addGrade(Integer ... grade){
        gradeList.addAll(Arrays.asList(grade));
        journal.put(this.id, gradeList);
    }

    public void addGrade2(Enum<Lessons> enums, Integer ... grade){
        if(gradeList2.containsKey(enums)){
            gradeList2.get(enums).addAll(Arrays.asList(grade));
        }
        else {
            gradeList2.put(enums, new ArrayList<>());
            gradeList2.get(enums).addAll(Arrays.asList(grade));
        }
        journal2.put(this.id, gradeList2);
    }


    public int getMaxGrade(){
        return gradeList.stream().max(Integer::compareTo).get();
    }

    public double getAverageGrade(){
        averageGrade = this.gradeList.stream().mapToInt(e -> e).average().getAsDouble();
        return averageGrade;
    }

    public List<Integer> getGradeList() {
        return gradeList;
    }

    public static Map<Integer, List<Integer>> getJournal() {
        return journal;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", spec='" + spec + '\'' +
                '}';
    }


    //    public static void main(String[] args) {
//        Student student = new Student("Alex");
//        Student student1 = new Student("Mike");
//        student.addGrade2(Lessons.BIOLOGY, 10, 10);
//        student.addGrade2(Lessons.MATH, 5);
//        student.addGrade2(Lessons.ENGLISH, 10);
//        student.addGrade2(Lessons.BIOLOGY, 3, 10);
//
//        student1.addGrade2(Lessons.BIOLOGY, 5);
//        student1.addGrade2(Lessons.MATH, 6);
//        student1.addGrade2(Lessons.MATH, 2);
//        student1.addGrade2(Lessons.ENGLISH, 3);
//
//        System.out.println(student.gradeList2);
//        System.out.println(student1.gradeList2);
//
//        System.out.println(Student.journal2);
//    }

}
