package org.student.dao;

import org.student.entity.Student;

import java.util.List;

/**
 * Dao实现接口
 */
public interface IStudentDao {

    //查询总数据量
    int getTotalCount();

    List<Student> queryStudentsByPage(int currentPage,int pageSize);

    //查询单个学生
    Student queryStudentBySno(int sno);

    //查询学生
    List<Student> queryAllStudent();

    //判断学生是否存在于数据库
    boolean isExist(int sno);

    //添加学生
    boolean addStudent(Student student);

    //修改学生
    boolean updateStatementBySno(int sno, Student student);

    //删除学生
    boolean deleteStudentBySno(int sno);
}
