package com.neuedu.util2;

import com.neuedu.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQL01 {
    public static void main(String[] args) {
      /*  List<Student> listStudent=getAll();
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
       /* Student student=find("2018101202");
        System.out.println(student);
*/

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
        List<Object> listu=UpdateQuery.query(sql, new GetResultSet() {
            @Override
            public Object get(ResultSet rs) throws SQLException{
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
        List<Student> students=new ArrayList<>();
        for(Object obj:listu){
            students.add((Student)obj);
        }
        return students.get(0);
    }

    //插入-- 一条记录的插入
    public static int insert(Student student){
        String sql="insert into student(sno,sname,ssex,sage,sdept,birthday) values(?,?,?,?,?,?)";
        return UpdateQuery.update(sql,student.getSno(),student.getSname(),student.getSsex(),student.getSage(),student.getSdept(),student.getBirthday());
    }

    //查找
    public static List<Student> getAll() {
        String sql="select * from student";
        List<Object> listu=UpdateQuery.query(sql, new GetResultSet() {
            @Override
            public Object get(ResultSet rs) throws SQLException {
                Student s=new Student();
                s.setSno(rs.getString("sno"));
                s.setSname(rs.getString("sname"));
                s.setSage(rs.getInt("sage"));
                s.setSsex(rs.getString("ssex"));
                s.setSdept(rs.getString("sdept"));
                s.setBirthday(rs.getDate("birthday"));
                return s;
            }
        } );
        List<Student> students=new ArrayList<>();
        for(Object obj:listu){
            students.add((Student)obj);
        }
        return students;
    }


}
