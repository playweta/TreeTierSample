package org.student.util;

import org.student.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库帮助类
 * 通用的数据库操作方法
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/student_DB?useSSL=false&serverTimezone=CST&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    //public static Connection connection = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;

    Student student = null;

    /**
     * 获取Connection对象
     * 利用Class.forName()方法来加载JDBC驱动程序（Driver）至DriverManager
     * 从DriverManager中用URL，用户名和密码来获取Connection对象并返回给方法
     * @return Connection（数据库操作对象）
     * @throws SQLException Class.forName();
     * @throws ClassNotFoundException getConnection();
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * 用getConnection方法获取Connection对象，并获取prepareStatement对象，
     * 并将外部sql语句传入方法中返回prepareStatement对象，因为sql语句中的
     * 通配符要用SetXxx()方法传入sql语句中所以，因为这个方法修改和查询都要调用，
     * 而查询全部不需要向sql语句传入数据，那么这里就用到普通的for循环遍历数组
     * 在数组之前，先判断数组是否为null，以防止空指针异常.而SetXxx方法的
     * 传参个数是由sql语句的？号数量决定的，所以我们将参数params集合遍历，
     * 而数组下标是从0开始，而SetXxx方法的第一个参数是从1开始，所以这里要要给i+1，
     * 依次将集合中的数据遍历到sql语句中，而后返回PreparedStatement对象
     * @param sql 增删查改需要传入的sql语句
     * @param params 需要传入sql语句中的数据
     * @return preparedStatement对象
     * @throws SQLException prepareStatement, getConnection
     * @throws ClassNotFoundException getConnection
     */
    public static PreparedStatement createPreparedStatement(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        pstmt = getConnection().prepareStatement(sql);
        // Object[] obj = {name,age,...,x}
        /*
            SetXxx()方法的个数依赖于sql语句中通配符(?)的个数又和数组params的个数一致
            SetXxx()方法的个数 -> 数组params的个数一致
         */
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                //setXxx从1开始            数组的元素下标从0开始
                pstmt.setObject(i + 1, params[i]);
            }
        }
        return pstmt;
    }

    /**
     * 关闭资源，因为Connection对象不会被Dao层调用，所以我们
     * 这里直接调用getConnection方法，并将其关闭。
     *
     * @param rs
     * @param stmt
     */
    public static void closeAll(ResultSet rs, Statement stmt) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (getConnection() != null) getConnection().close();
        } catch ( SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int getTotalCount(String sql){
        int count = -1;
        try {
            PreparedStatement pstmt = createPreparedStatement(sql, null);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt);
        }
        return count;
    }



    /**
     * 通用的增删改
     * 将两个参数传入createPreparedStatement方法，并返回一个PreparedStatement类型对象，
     * 而后调用该方法的executeUpdate方法，返回一个int类型的对象，而后我们判断是否修改成功(返回对象>0),
     * 并将这个boolean值返回
     * @param sql dao层传入的sql语句
     * @param params sql语句中的?(通配符)需要传入的数据
     * @return 判断是否修改(增删改)成功
     */
    public static boolean executeUpdate(String sql, Object[] params) {
        try {
            pstmt = createPreparedStatement(sql, params);
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll(null, pstmt);

        }
    }


    /**
     * 通用的查：适用于任何查询
     * 这里直接将数据传入createPreparedStatement方法，并返回
     * 一个PreparedStatement对象，而后将返回回来的对象给与编译
     * 并返回一个ResultSet类型的集合，集合中存储着从数据库中查询
     * 出来的数据。
     * @param sql
     * @param params
     * @return ResultSet：返回查询结果集
     */
    public static ResultSet executeQuery(String sql, Object[] params) {
        List<Student> students = new ArrayList<>();
        try {
            pstmt = createPreparedStatement(sql, params);
            rs = pstmt.executeQuery();
            return rs;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return rs;
        }
    }
}
