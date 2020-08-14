package com.panda.repository.impl;

import com.panda.entity.Book;
import com.panda.entity.Borrow;
import com.panda.entity.Reader;
import com.panda.repository.BorrowRepository;
import com.panda.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Panda
 * @create 2020/7/23 13:09
 */
public class BorrowRepositoryImpl implements BorrowRepository {
    /**
     * 借阅
     * @param bookid
     * @param readerid
     * @param borrowTime
     * @param returnTime
     * @param adminid
     * @param state
     */
    @Override
    public void insert(Integer bookid, Integer readerid, String borrowTime, String returnTime, Integer adminid, Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "INSERT INTO borrow(bookid,readerid,borrowtime,returntime,state) VALUES(?,?,?,?,0)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,bookid);
            preparedStatement.setInt(2,readerid);
            preparedStatement.setString(3,borrowTime);
            preparedStatement.setString(4,returnTime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }

    }

    /**
     * 用户借阅记录
     * @param id
     * @param index
     * @param limit
     * @return
     */
    @Override
    public List<Borrow> findAllByReaderId(Integer id, Integer index, Integer limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT br.id,b.name,b.author,b.publish,br.borrowtime,br.returntime,r.name,r.tel,r.cardid,br.state FROM borrow br,book b, reader r WHERE br.readerid=? and br.bookid=b.id and br.readerid=r.id limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,index);
            preparedStatement.setInt(3,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //Book book = new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                //Reader reader = new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                list.add(new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }

    /**
     * 用户借阅总数
     * @param readerId
     * @return
     */
    @Override
    public int count(Integer readerId) {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT COUNT(*) FROM borrow br,book b, reader r WHERE br.readerid=? and br.bookid=b.id and br.readerid=r.id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,readerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    /**
     * 所有未审核借阅申请
     * @param state
     * @return
     */
    @Override
    public List<Borrow> findAllByState(Integer state,Integer index, Integer limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT br.id,b.name,b.author,b.publish,br.borrowtime,br.returntime,r.name,r.tel,r.cardid,br.state FROM borrow br,book b, reader r WHERE br.state=? and br.bookid=b.id and br.readerid=r.id limit ?,?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,state);
            statement.setInt(2,index);
            statement.setInt(3,limit);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Book book = new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                //Reader reader = new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                list.add(new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return list;
    }

    /**
     * 借阅审核状态为state的总数
     * @param state
     * @return
     */
    @Override
    public int countByState(Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT COUNT(*) FROM borrow br,book b, reader r WHERE br.state=? and br.bookid=b.id and br.readerid=r.id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    /**
     * 处理审批
     * @param borrowId
     * @param state
     * @param adminId
     */
    @Override
    public void handle(Integer borrowId, Integer state, Integer adminId) {
        Connection connection = JDBCTools.getConnection();
        String sql = "UPDATE borrow SET adminid=?,state=? WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,adminId);
            statement.setInt(2,state);
            statement.setInt(3,borrowId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, statement, null);
        }
    }
}
