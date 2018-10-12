package com.neuedu.util;

import com.neuedu.entity.Student;

import java.lang.invoke.SerializedLambda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQL01 {
    public static void main(String[] args) {
       /* List<Student> listStudent=getAll();
        for(Student s:listStudent){
            System.out.println(s);
        }*/

       /*Student student=new Student();
       student.setSno("2018101203");
       student.setSname("里1");
       student.setSsex("男");
       student.setSage(20);
       student.setSdept("挖掘机系");

       student.setBirthday(DateUtil.getDate("1992-02-03"));
        int i=insert(student);
        System.out.println("影响的行数："+i);*/

      /* int i=delete("2018101203");
        System.out.println("影响的行数："+i);*/

      //一条记录
        Student student=find("2018101203");
        System.out.println(student);


    }
    //修改

    // 删除
     public static int delete(String sno){
        String sql="delete from student where sno=?";
        return UpdateQuery.update(sql,sno);
     }

    // 查找一条记录
    public static Student find(String sno){
        String sql="select * from student where sno=?";
        List<Student> listu=UpdateQuery.query(sql, new GetResultSet<Student>() {//匿名内部类
            @Override
            public Student get(ResultSet rs) throws SQLException{
                Student s=new Student();
                s.setSno(rs.getString("sno"));
                s.setSname(rs.getString("sname"));
                s.setSage(rs.getInt("sage"));
                s.setSsex(rs.getString("ssex"));
                s.setSdept(rs.getString("sdept"));
                s.setBirthday(rs.getDate("birthday"));
                return s;
            }
        },sno);
        if(listu==null || listu.size()==0){
            return null;
        }
        return listu.get(0);
    }

    //插入-- 一条记录的插入
    public static int insert(Student student){
        String sql="insert into student(sno,sname,ssex,sage,sdept,birthday) values(?,?,?,?,?,?)";
        return UpdateQuery.update(sql,student.getSno(),student.getSname(),student.getSsex(),student.getSage(),student.getSdept(),student.getBirthday());
    }
   /* public static int insert(Student student){
        Connection conn=null;
        PreparedStatement pstm=null;
        int i=-1;
        String sql="insert into student(sno,sname,ssex,sage,sdept,birthday) values(?,?,?,?,?,?)";//使用？作为占位符
        conn=SqlUtil.getConn();
        try {
            pstm=conn.prepareStatement(sql);//有占位符，参数添加
            *//*pstm.setObject(1,student.getSno());
            pstm.setObject(2,student.getSname());
            pstm.setObject(3,student.getSsex());
            pstm.setObject(4,student.getSage());
            pstm.setObject(5,student.getSdept());
            pstm.setObject(6,student.getBirthday());*//*
            SqlUtil.insertParamentertoPreparedStatement(pstm,student.getSno(),student.getSname(),student.getSsex(),student.getSage(),student.getSdept(),student.getBirthday());

            i=pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlUtil.close(pstm,conn);
        }

        return i;
    }*/

    //查找
    public static List<Student> getAll() {
        String sql="select * from student";
        List<Student> listu=UpdateQuery.query(sql, new GetResultSet<Student>() {//匿名内部类
            @Override
            public Student get(ResultSet rs) throws SQLException{
                Student s=new Student();
                s.setSno(rs.getString("sno"));
                s.setSname(rs.getString("sname"));
                s.setSage(rs.getInt("sage"));
                s.setSsex(rs.getString("ssex"));
                s.setSdept(rs.getString("sdept"));
                s.setBirthday(rs.getDate("birthday"));
                return s;
            }
        });
        return listu;
    }
   /* public static List<Student> getAll(){
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
*/

}
