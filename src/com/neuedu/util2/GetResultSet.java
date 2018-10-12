package com.neuedu.util2;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetResultSet {
    //是对ResultSet的一个对象进行处理
    Object get(ResultSet rs) throws SQLException;
}
