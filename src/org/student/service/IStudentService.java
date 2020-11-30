package org.student.service;

import org.student.entity.Student;

import java.util.List;

/**
 *
 */
public interface IStudentService {

    /**
     * 查询总数据量
     *
     * @return 总数据量（多少个学生）
     */
    int getTotalCount();

    /**
     * 查询一个页面的students(sql数据)
     *
     * @param currentPage 当前页(页数)
     * @param pageSize    页面大小(每页显示的数据条数)
     * @return student集合
     */
    List<Student> queryStudentsByPage(int currentPage, int pageSize);

    /**
     * 根据学号查询学生
     *
     * @param sno 学号
     * @return 学生对象
     */
    Student queryStudentBySno(int sno);

    /**
     * 查询全部学生
     *
     * @return 学生集合
     */
    List<Student> queryAllStudent();

    /**
     * 传入学号和学生对象来修改学生
     *
     * @param sno     学号
     * @param student 学生对象
     * @return 布尔值
     */
    boolean updateStudentBySno(int sno, Student student);

    /**
     * 传入学号，来删除学生
     *
     * @param sno 学号
     * @return 布尔值
     */
    boolean deleteStudentBySno(int sno);

    /**
     * 传入学生对象，来添加学生
     *
     * @param student 学生对象
     * @return 布尔值
     */
    boolean addStudent(Student student);
}
