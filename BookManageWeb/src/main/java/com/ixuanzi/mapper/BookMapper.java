package com.ixuanzi.mapper;

import com.ixuanzi.entity.Book;
import com.ixuanzi.entity.Borrow;
import com.ixuanzi.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface BookMapper {

    // TODO 学生操作
    @Insert("insert into student(name, sex, grade) values(#{name}, #{sex}, #{grade}) ")
    int addStudent(Student student);

    @Delete("delete from student where sid = #{sid}")
    int deleteStudentBySid(int sid);

    @Update("update student set name=#{name}, sex=#{sex}, grade=#{grader}")
    int updateStudentBySid(Student student);

    @Select("select * from student where sid = #{sid}")
    Student selectStudent(int sid);

    // TODO 图书操作
    @Insert("insert into book(title, `desc`, price) values(#{title}, #{desc}, #{price}) ")
    int addBook(Book book);

    @Delete("delete from book where = #{bid}")
    int deleteBookByBid(int bid);

    @Update("update book set title = #{title}, `desc`=#{desc}, price= #{price}")
    int updateBookByBid(int bid);

    @Select("select * from book")
    List<Book> selectBook();

    // TODO 借阅操作
    @Insert("insert into borrow(sid, bid) values(#{sid}, #{bid})")
    int addBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "sid", property = "student", one = @One(select = "selectStudent")),
            @Result(column = "bid", property = "book", one = @One(select = "selectBook"))
    })
    @Select("select * from borrow")
    List<Borrow> selectBorrow();

    @Select("select * from book where bid = #{bid}")
    Book selectBook(int sid);
}
