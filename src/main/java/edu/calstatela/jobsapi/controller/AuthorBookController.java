package edu.calstatela.jobsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.calstatela.jobsapi.model.Author;
import edu.calstatela.jobsapi.model.Book;
import edu.calstatela.jobsapi.repository.AuthorRepository;
import edu.calstatela.jobsapi.repository.BookRepository;

@RestController
public class AuthorBookController {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@PostMapping("/author/new-author")
	public ResponseEntity<?> postAuthor(@RequestBody Author author) {
		authorRepository.save(author);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/author/new-book")
	public ResponseEntity<?> postBook(@RequestBody Book book) {
		if(book.getAuthor() != null) {
			for(Author author: book.getAuthor()) {
				System.out.println(author.getAuthorName());
			}
		} else {
			//bookRepository.save(book);
		}
		//bookRepository.save(book);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/author/{bookId}")
	public ResponseEntity<?> editBook(@PathVariable(value = "bookId")
	                                  @RequestBody Book book) {
		bookRepository.save(book);
		return ResponseEntity.ok().build();
	}

}
