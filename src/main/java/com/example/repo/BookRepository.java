package com.example.repo;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    //返回书籍信息列表（标题，列表图片）
    List<Book> getBooksByType(String type);
}
