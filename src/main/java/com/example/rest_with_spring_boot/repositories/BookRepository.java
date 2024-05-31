package com.example.rest_with_spring_boot.repositories;

import com.example.rest_with_spring_boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // JpaRepository ja possui implementacoes prontas para os metodos CRUD
}
