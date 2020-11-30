package org.student.service.impl;

import org.student.dao.IStudentDao;
import org.student.dao.impl.StudentDaoImpl;
import org.student.entity.Student;
import org.student.service.IStudentService;

import java.util.List;

/**
 * 业务逻辑层：逻辑性的增删查改 （增：查+增），
 * 对dao(StudentDao)层进行的组装
 */
public class StudentServiceImpl implements IStudentService {
    IStudentDao studentDao = new StudentDaoImpl();

    /**
     * 查询总数据量
     *
     * @return 总数据量（多少个学生）
     */
    public int getTotalCount() {
        return studentDao.getTotalCount();
    }

    /**
     * 查询一个页面的students(sql数据)
     *
     * @param currentPage 当前页(页数)
     * @param pageSize    页面大小(每页显示的数据条数)
     * @return student集合
     */
    public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
        return studentDao.queryStudentsByPage(currentPage, pageSize);
    }

    /**
     * 根据学号查询学生
     * @param sno 学号
     * @return 学生对象
     */
    public Student queryStudentBySno(int sno) {
        return studentDao.queryStudentBySno(sno);
    }

    /**
     * 查询全部学生
     * @return 学生集合
     */
    public List<Student> queryAllStudent(){
        return studentDao.queryAllStudent();
    }

    /**
     * 传入学号和学生对象来修改学生
     * @param sno 学号
     * @param student 学生对象
     * @return 布尔值
     */
    public boolean updateStudentBySno(int sno,Student student) {
        //判断调用isExist方法判断此人是否存在于数据库中
        if (studentDao.isExist(sno)) {
            //存在调用updateStudentBySno方法将sno对象和student对象传入方法返回布尔值
            return studentDao.updateStatementBySno(sno, student);
        } else {
            //不存在
            return false;
        }
    }

    /**
     * 传入学号，来删除学生
     * @param sno 学号
     * @return 布尔值
     */
    public boolean deleteStudentBySno(int sno){
        //判断调用isExist方法判断此人是否存在于数据库中
        if (studentDao.isExist(sno)) {
            //存在调用deleteStudentBySno方法将student对象传入方法返回布尔值
            return studentDao.deleteStudentBySno(sno);
        } else {
            //不存在
            return false;
        }
    }

    /**
     * 传入学生对象，来添加学生
     * @param student 学生对象
     * @return 布尔值
     */
    public boolean addStudent(Student student) {
        //判断调用isExist方法判断此人是否存在于数据库中
        if (!studentDao.isExist(student.getSno())) {
            //不存在调用addStudent方法并将student对象传入方法返回布尔值
            return studentDao.addStudent(student);
        } else {
            //存在
            System.out.println("此人已经存在");
            return false;
        }
    }

}

