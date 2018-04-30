package edu.calstatela.jobsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.calstatela.jobsapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByemailId(String emailId);

}
