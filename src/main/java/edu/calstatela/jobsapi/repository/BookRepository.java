package edu.calstatela.jobsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.calstatela.jobsapi.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
