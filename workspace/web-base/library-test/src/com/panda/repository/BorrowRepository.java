package com.panda.repository;

import com.panda.entity.Borrow;

import java.util.List;

/**
 * @Author Panda
 * @create 2020/7/23 13:06
 */
public interface BorrowRepository {
    public void insert(Integer bookid, Integer readerid, String borrowTime, String returnTime, Integer adminid,Integer state);
    public List<Borrow> findAllByReaderId(Integer id, Integer index, Integer limit);
    public int count(Integer readerId);
    public List<Borrow> findAllByState(Integer state,Integer index, Integer limit);
    public int countByState(Integer state);
    public void handle(Integer borrowId, Integer state, Integer adminId);
}

