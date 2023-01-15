package com.watcha.watchapedia.repository;
import com.watcha.watchapedia.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {

}
