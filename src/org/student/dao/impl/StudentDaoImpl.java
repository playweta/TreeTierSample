package org.student.dao.impl;

import org.student.dao.IStudentDao;
import org.student.entity.Student;
import org.student.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库访问层：原子性的增删查改
 */
public class StudentDaoImpl implements IStudentDao {

    Student student = null;

    /**
     * 查询总数据量
     *
     * @return 总数据量
     */
    public int getTotalCount() {
        String sql = "select count(1) from student";
        return DBUtil.getTotalCount(sql);
    }

    /**
     * 查询一个页面的students(sql数据)
     *
     * @param currentPage 当前页(页数)
     * @param pageSize    页面大小(每页显示的数据条数)
     * @return student集合
     * <p>
     * 下面是一个应用实例：
     * select * from orders_history where type=8 limit 1000,10;
     * 该条语句将会从表 orders_history 中查询第1000条数据之后的10条数据，也就是第1001条到第1010条数据。
     */
    public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
        List<Student> students = new ArrayList<>();
        //select * from student limit 页数*页面大小,页面大小(记录行的偏移量,页面大小) ;
        String sql = "select * from student limit ?,?";
        //因为mysql是从0页开始，所以要给currentPage-1
        Object[] params = {(currentPage - 1) * pageSize, pageSize};
        ResultSet rs = DBUtil.executeQuery(sql, params);
        try {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("sno"),
                        rs.getString("sname"),
                        rs.getInt("sage"),
                        rs.getString("saddress")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs, DBUtil.pstmt);
        }
        return students;
    }

    /**
     * 根据学号查询学生
     *
     * @param sno 学号sno
     *            将学号传给sql语句，然后查询学生
     * @return 学生对象student/null
     * 通过学号查询学生从而返回一个学生
     */
    public Student queryStudentBySno(int sno) {
        String sql = "select * from student where sno= ?";
        Object[] params = {sno};
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery(sql, params);
            if (rs.next()) {
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                student = new Student(no, name, age, address);
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(rs, DBUtil.pstmt);
        }
    }

    /**
     * 查询全部学生(很多学生),所以要用集合把从数据库查询出来的一个个学遍历到集合中
     *
     * @return 学生对象student/null
     * 直接用查询所有的sql语句查询表里的所有数据，返回全部学生
     */
    public List<Student> queryAllStudent() {
        List<Student> students = new ArrayList<>();
        String sql = "select * from student";
        ResultSet rs = null;
        try {
            rs = DBUtil.executeQuery(sql, null);
            while (rs.next()) {
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                student = new Student(no, name, age, address);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {

            DBUtil.closeAll(rs, DBUtil.pstmt);

        }
    }

    /**
     * 调用queryStudentBySno方法，返回一个学生对象，判断该学生是否为null
     * 如果为空返回boolean值，从而判断该学生是否存在于数据库中
     *
     * @param sno 学号sno(唯一标识符主键)
     *            传入学号给queryStudentBySno方法用于返回Student对象
     * @return boolean(ture / false)
     * 返回布尔值，从而判断该学生是否存在于数据库
     */
    public boolean isExist(int sno) {
        return queryStudentBySno(sno) != null;
    }

    /**
     * 添加学生
     * 将student对象传入方法，然后吧每一个数据传入sql语句，
     * 并预编译一下,用executeUpdate()方法将sql语句传给数据库
     * 而后返回一个int(1/0)类型的对象，判断对象的值是否大于0，从而
     * 判断添加成功
     *
     * @param student 将对象中的值传入数据库语句中并用
     *                executeUpdate传给数据库返回一个ResultSet
     * @return 返回一个布尔值rs(ResultSet类型)，判断是否添加成功
     */
    public boolean addStudent(Student student) {
        String sql = "insert into student(sno,sname,sage,saddress) values(?,?,?,?)";
        Object[] params = {student.getSno(), student.getSname(), student.getSage(), student.getSaddress()};
        return DBUtil.executeUpdate(sql, params);
    }

    /**
     * 根据学号修改学生：根据sno知道待改的人的信息，把这个人修改成student
     *
     * @param sno     学号sno
     * @param student 学生对象
     */
    public boolean updateStatementBySno(int sno, Student student) {
        String sql = "update student set sname=?,sage=?,saddress=? where sno = ?";
        Object[] params = {student.getSname(), student.getSage(), student.getSaddress(), sno};
        return DBUtil.executeUpdate(sql, params);
    }

    /**
     * 根据学号删除学生
     *
     * @param sno 学号：sno
     * @return 布尔值(true / false)
     */
    public boolean deleteStudentBySno(int sno) {
        String sql = "delete from student where sno = ?";
        Object[] params = {sno};
        return DBUtil.executeUpdate(sql, params);
    }
}
