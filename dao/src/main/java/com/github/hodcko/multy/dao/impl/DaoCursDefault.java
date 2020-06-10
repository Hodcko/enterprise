package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.converter.CursConverter;
import com.github.hodcko.multy.dao.converter.StudentConverter;
import com.github.hodcko.multy.dao.converter.TeacherConverter;
import com.github.hodcko.multy.dao.entity.CursEntity;
import com.github.hodcko.multy.dao.entity.StudentEntity;
import com.github.hodcko.multy.dao.entity.TeacherEntity;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.entity.GroupDTO;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoCursDefault implements DaoCurs {


    private static final Logger log = LoggerFactory.getLogger(DaoCursDefault.class);
    private final SessionFactory factory;

    public DaoCursDefault(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public Curs createCurs(String names, LocalDate start, LocalDate end) {
        try {
            Session session =  factory.getCurrentSession();
            CursEntity curs = session.createQuery("select c from CursEntity c where name = :name", CursEntity.class)
                    .setParameter("name", names).getSingleResult();
            curs.setStart(start);
            curs.setEnd(end);
            log.info("create curs {}, start {}, end {}", names, start, end);
            return CursConverter.fromEntity(curs);
        }catch (HibernateError e){
            log.error("fail to create curs {}, start {}, end {}", names, start, end, e);
        }
        return null;
    }

    @Override
    public boolean deleteCurs(int cursId) {
        try {
            Session session =  factory.getCurrentSession();
            session.createQuery("update CursEntity c set c.start = null, c.end = null where c.id = :id")
                    .setParameter("id", cursId).executeUpdate();
            log.info("curs with id {} deleted", cursId);
            return true;
        }catch (HibernateError e){
            log.error("fail to delete curs with id {}", cursId, e);
        }
        return false;
    }

    @Override
    public Curs getCurs(int cursId) {
        try {
            Session session =  factory.getCurrentSession();
            CursEntity curs = session.get(CursEntity.class, cursId);
            log.info("curs get with id {}", cursId);
            return CursConverter.fromEntity(curs);
        }catch (HibernateError e){
            log.error("fail to curs get with id {}", cursId, e);
        }
        return null;
    }

    @Override
    public List<GroupDTO> getMyStudents(int cursId, int numPage){
        int offset = 3;
        int limit = (numPage - 1) * offset;
        try {
            Session session =  factory.getCurrentSession();
            List<GroupDTO> groupDtos = session.createNativeQuery("select s.name, s.second_name as secondName , s.email, g.grade " +
                    "from student s join gradebook g on s.id = g.student_id  where g.curs_id = :id ")
                    .setParameter("id", cursId)
                    .addScalar("secondName", StandardBasicTypes.STRING)
                    .addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("grade", StandardBasicTypes.INTEGER)
                    .setResultTransformer(Transformers.aliasToBean(GroupDTO.class))
                    .setFirstResult(limit)
                    .setMaxResults(offset)
                    .list();
            log.info("get all students of curs with id {}", cursId);
            return groupDtos;
        }catch (HibernateException e){
            log.error("fail to get all students of curs with id {}", cursId, e);
        }
        return null;
    }



    @Override
    public int countOfStudents(int cursId){
        try {
            Session session =  factory.getCurrentSession();
            Long count = (Long) session.createQuery("select count(s.name) " +
                    "from StudentEntity s join GradebookEntity g on s.id = g.studentId  where g.cursId = :id")
                    .setParameter("id", cursId).getSingleResult();
            log.info("get count of students with curs id {}", cursId);
            return count.intValue();
        }catch (HibernateException e){
            log.error("fail to get count of students with curs id {}", cursId, e);
        }
        return 0;
    }

    @Override

    public boolean inviteStudentOnCurs(int studentId, int cursId){
        try {
            Session session =  factory.getCurrentSession();
            StudentEntity student = session.get(StudentEntity.class, studentId);
            student.getCurses().add(session.get(CursEntity.class, cursId));
            session.saveOrUpdate(student);
            log.info("student with id {} invited on curs {}",studentId, cursId);
            return true;
        }catch (HibernateException e){
            log.error("fail to invite student with id {} on curs {}",studentId, cursId, e);
        }return false;
    }

    @Override
    public List<Teacher> getColleagues(int cursId){
        try {
            Session session = factory.getCurrentSession();;
            CursEntity curs = session.get(CursEntity.class, cursId);
            List<TeacherEntity> teacherEntities = curs.getTeachers();
            List<Teacher> teacherList = new ArrayList<>();
            if(!teacherEntities.isEmpty()){
                for (TeacherEntity teacherEntity : teacherEntities) {
                    teacherList.add(TeacherConverter.fromEntity(teacherEntity));
                }
            }
            log.info("get colleagues from curs with id {} {}",cursId, curs.getTeachers());
            return teacherList;
        }catch (HibernateException e){
            log.error("fail to get colleagues from curs with id {} {}", cursId, e);
            return null;
        }
    }

    @Override

    public List<Student> getClassmates(int cursId){
        try {
            Session session = factory.getCurrentSession();
            CursEntity curs = session.get(CursEntity.class, cursId);
            List<StudentEntity> studentEntities = curs.getStudents();
            List<Student> studentList = new ArrayList<>();
            if(!studentEntities.isEmpty()){
                for (StudentEntity studentEntity : studentEntities) {
                    studentList.add(StudentConverter.fromEntity(studentEntity));
                }
            }
            log.info("get classmates from curs with id {} {}",cursId, curs.getStudents());
            return studentList;
        }catch (HibernateException e) {
            log.error("fail to get classmates from curs id {}", cursId, e);
            return null;
        }
    }

}
