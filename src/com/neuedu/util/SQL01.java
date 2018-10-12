package com.neuedu.util;

import com.neuedu.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL01 {
    public static void main(String[] args) {
       /* List<Student> listStudent=getAll();
        for(Student s:listStudent){
            System.out.println(s);
        }*/

       Student student=new Student();
       student.setSno("2018101201");
       student.setSname("张");
       student.setSsex("男");
       student.setSage(20);
       student.setSdept("挖掘机系");

       student.setBirthday(DateUtil.getDate("1992-02-03"));
        int i=insert(student);
        System.out.println("影响的行数："+i);

    }
    //插入-- 一条记录的插入

    public static int insert(Student student){
        Connection conn=null;
        PreparedStatement pstm=null;
        int i=-1;
        String sql="insert into student(sno,sname,ssex,sage,sdept,birthday) values(?,?,?,?,?,?)";//使用？作为占位符
        conn=SqlUtil.getConn();
        try {
            pstm=conn.prepareStatement(sql);//有占位符，参数添加
            pstm.setString(1,student.getSno());
            pstm.setString(2,student.getSname());
            pstm.setString(3,student.getSsex());
            pstm.setInt(4,student.getSage());
            pstm.setString(5,student.getSdept());
            pstm.setDate(6,student.getBirthday());

            i=pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlUtil.close(pstm,conn);
        }

        return i;
    }

    //查找
    public static List<Student> getAll(){
        Connection conn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        List<Student> lists=new ArrayList<>();
        String sql="select * from student";
        conn=SqlUtil.getConn();
        try {
            pstm=conn.prepareStatement(sql);
            rs=pstm.executeQuery();
            while (rs.next()){
                Student s=new Student();
                s.setSno(rs.getString("sno"));
                s.setSname(rs.getString("sname"));
                s.setSage(rs.getInt("sage"));
                s.setSsex(rs.getString("ssex"));
                s.setSdept(rs.getString("sdept"));
                s.setBirthday(rs.getDate("birthday"));
                lists.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            SqlUtil.close(rs,pstm,conn);
        }
        return lists;
    }


}
