package org.student.entity;

import java.util.List;

/**
 * 分页帮助
 * <p>
 * 当前页       currentPage
 * 页面大小     pageSize
 * 当前页的数据集合     students
 * 总数据      totalCount
 * 总页数      totalPage
 */
public class Page {

    //当前页:currentPage
    private int currentPage;
    //页面大小:pageSize
    private int pageSize;
    //当前页的数据集合:students
    private List<Student> students;
    //总数据:totalCount
    private int totalCount;
    //总页数
    private int totalPage;

    public Page() {
    }

    /**
     *
     * 当我们调用了数据总数的set()和页面大小的set()以后，自动计算出总页数
     *
     * @param currentPage 当前页数
     * @param pageSize 页面大小
     * @param students 当前页数据(Student)集合
     * @param totalCount 总数据数
     * @param //totalPage 总页数
     */
    public Page(int currentPage, int pageSize, List<Student> students, int totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.students = students;
        this.totalCount = totalCount;
        //     总页数   =      数据总数    %    页面大小==0   ?         数据总数/页面大小        :          数据总量/页面大小+1
        this.totalPage = this.totalCount%this.pageSize==0 ? this.totalCount/this.pageSize : this.totalCount/this.pageSize+1;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * 获取总数据:totalCount
     * @return 总数据
     */
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取总页数:totalPage
     * @return 总页数
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 因为总页数是通过数据总数/页面大小来获取的，
     * 所以我们不需要set总页数
     *
     * 总页数 = 100/20  =数据总数/页面大小
     * 总页数 = 103/20 = 数据总数/页面大小+1
     *
     * 总页数 = 数据总数 % 页面大小==0 ? 数据总数/页面大小 : 数据总量/页面大小+1
     *
     * 设置总页数:totalPage
     * @param totalPage 总页数的值
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
     */
}
