package com.panda.service.impl;

import com.panda.entity.Book;
import com.panda.entity.Borrow;
import com.panda.repository.BookRepository;
import com.panda.repository.BorrowRepository;
import com.panda.repository.impl.BookRepositoryImpl;
import com.panda.repository.impl.BorrowRepositoryImpl;
import com.panda.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author Panda
 * @create 2020/7/22 18:07
 */
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new BookRepositoryImpl();
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();

    private final int LIMIT = 6;//每页6条数据；

    /**
     * 图书数据，分页
     * @param page 第几页
     * @return page页的数据
     */
    @Override
    public List<Book> findAll(int page) {
        int index = (page-1)*LIMIT;
        return bookRepository.findAll(index,LIMIT);
    }

    /**
     * 图书总页数
     * @return 返回计算后得到的 总页数
     */
    @Override
    public int getPages() {
        int count = bookRepository.count();
        int pages = 0;
        if (count % LIMIT == 0){
            pages = count/LIMIT;
        } else {
            pages = count/LIMIT +1;
        }
        return pages;
    }

    /**
     * 用户借阅
     * @param bookid
     * @param readerid
     */
    @Override
    public void addBorrow(Integer bookid, Integer readerid) {
        //借书时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime = simpleDateFormat.format(date);
        //还书时间，借书时间+14天
        Calendar calendar = Calendar.getInstance();
        int dates = calendar.get(Calendar.DAY_OF_YEAR) + 14;
        calendar.set(Calendar.DAY_OF_YEAR, dates);
        Date date2 = calendar.getTime();
        String returnTime = simpleDateFormat.format(date2);
        borrowRepository.insert(bookid,readerid,borrowTime,returnTime,null,0);

    }

    /**
     * 用户借阅数据，分页
     * @param id
     * @param page
     * @return
     */
    @Override
    public List<Borrow> findAllBorrowByReaderId(Integer id, Integer page) {
        //业务：将page 换算成 index，limit
        Integer index = (page-1)*LIMIT;
        return borrowRepository.findAllByReaderId(id, index,LIMIT);
    }

    /**
     * 用户借阅总页数
     * @param readerId
     * @return
     */
    @Override
    public int getBorrowPages(Integer readerId) {
        int count = borrowRepository.count(readerId);
        int pages = 0;
        if (count % LIMIT == 0){
            pages = count/LIMIT;
        } else {
            pages = count/LIMIT +1;
        }
        return pages;
    }

    /**
     * 管理员，未审批借阅申请数据，分页
     * @param state
     * @param page
     * @return
     */
    @Override
    public List<Borrow> findAllBorrowByState(Integer state, Integer page) {
        //业务：将page 换算成 index，limit
        Integer index = (page-1)*LIMIT;
        return borrowRepository.findAllByState(state, index, LIMIT);
    }

    /**
     * 管理员界面：未审核状态的总数
     * @param state
     * @return
     */
    @Override
    public int getBorrowPagesByState(Integer state) {
        int count = borrowRepository.countByState(state);
        int pages = 0;
        if (count % LIMIT == 0){
            pages = count/LIMIT;
        } else {
            pages = count/LIMIT +1;
        }
        return pages;
    }

    @Override
    public void handleBorrow(Integer borrowId, Integer state, Integer adminId) {
        borrowRepository.handle(borrowId, state, adminId);
    }
}
