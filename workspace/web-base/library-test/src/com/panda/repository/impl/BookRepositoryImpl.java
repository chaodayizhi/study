package com.panda.repository.impl;

import com.panda.entity.Book;
import com.panda.entity.BookCase;
import com.panda.repository.BookRepository;
import com.panda.utils.JDBCTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Panda
 * @create 2020/7/22 18:10
 */
public class BookRepositoryImpl implements BookRepository {
    /**
     * 获取 某页 Boook数据
     * @param index 起始下标
     * @param limit 查询间隔
     * @return
     */
    @Override
    public List<Book> findAll(int index, int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT * FROM `book` bk , bookcase bc WHERE bk.bookcaseid=bc.id limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            //处理结果集合
            while (resultSet.next()){
//                BookCase bookCase =null;
//                Book book = null;
                list.add( new Book(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getDouble(6),new BookCase(resultSet.getInt(9),resultSet.getString(10))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    /**
     * 获取Book总记录条数
     * @return
     */
    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT COUNT(*) FROM `book` bk,bookcase bc WHERE bk.bookcaseid=bc.id ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            //处理结果集合
            while (resultSet.next()){
                count = resultSet.getInt(1);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return count;
    }
}
