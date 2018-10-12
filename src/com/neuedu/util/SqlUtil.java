package com.neuedu.util;

import java.sql.*;

public class SqlUtil {
    //1:加载驱动 --静态代码块
     static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static final String URL="jdbc:mysql://127.0.0.1:3306/pms?characterEncoding=utf8";
     private static final String USERNAME="root";
     private static final String PASSWORD="123456";
    //2:连接数据库-- 静态方法
    public static Connection getConn(){
         Connection conn=null;
        try {
            conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 为PreparedStatement对象传入参数
    public static void insertParamentertoPreparedStatement(PreparedStatement pstm,Object ...objs){
        try{
            if(objs!=null && objs.length>0){
                for(int i=0;i<objs.length;i++){
                    pstm.setObject(i+1,objs[i]);
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //3:关闭-- 静态方法
    //使用可变的变量作为参数-- 可变数组
    public static void close(Object ...objs){
        if(objs!=null && objs.length>0){//判断objs对象是否存在，存在并且有值，执行下面程序
            try{
                for(Object ob:objs){//foreach
                    if(ob!=null){ //对象是否存在
                        if(ob instanceof Connection){//判断是否是Connection对象，
                            ((Connection)ob).close();//如果是，则强制转换，然后关闭
                        }

                        if(ob instanceof Statement){
                            ((Statement)ob).close();
                        }

                        if(ob instanceof ResultSet){
                            ((ResultSet)ob).close();
                        }

                    }
                }


            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }


}
