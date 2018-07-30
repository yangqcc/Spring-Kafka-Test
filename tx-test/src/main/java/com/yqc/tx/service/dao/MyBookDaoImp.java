package com.yqc.tx.service.dao;

import com.yqc.tx.entity.MyBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class MyBookDaoImp implements MyBookDaoInt {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(MyBook myBook) {
        String sql = "insert my_test(title,author,submission_date) VALUES(?,?,?)";
        jdbcTemplate.update(
                sql,    //SQL语句
                new Object[]{myBook.getTitle(), myBook.getAuthor(), myBook.getSubmission_date()}, //查询参数数组
                new int[]{Types.VARCHAR, Types.VARCHAR, Types.DATE});  //查询参数SQL数据类型数组
    }

    public void insert() {
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            String sql = "insert my_test(id,title,author,submission_date) VALUES(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < 10; i++) {
                ps.setInt(1, i + 10);
                ps.setString(2, "scala" + i);
                ps.setString(3, "author" + i);
                ps.setDate(4, new Date(new java.util.Date().getTime()));
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
