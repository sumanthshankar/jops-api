package edu.calstatela.jobsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.calstatela.jobsapi.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
