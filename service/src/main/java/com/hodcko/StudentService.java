package com.hodcko;



import java.util.List;

public class StudentService implements Service {
    private Dao dao = StudentManager.getInstance();

    private static volatile Service instance;

    public static Service getInstance(){
        Service localInstance = instance;
        if(localInstance == null){
            synchronized (Service.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new StudentService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveStudent(Student student) {
        dao.saveStudent(student);
    }

    @Override
    public List<Student> getStudentsList() {
        return dao.getStudentsList();
    }

    @Override
    public void setSpec(String spec, Student student) {
        dao.setSpec(spec, student);
    }

    @Override
    public String getSpec(Student student) {
        return dao.getSpec(student);
    }
}
