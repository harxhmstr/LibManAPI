package com.harsh.library_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

	 @Autowired
	 BookRepository repo;

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(repo.findAll());
    }

    // GET one book by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        Book b = repo.findById(id).orElse(null);
        if(b==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(b);
    }

    // POST - add a book
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        repo.save(book);
        return ResponseEntity.status(201).body("Book Added");
    }

    // DELETE a book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
    		if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
    
    //UPDATE a book
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id,@RequestBody Book book) {
    		Book exist=repo.findById(id).orElse(null);
    		if(exist==null) return ResponseEntity.notFound().build();
    		exist.setTitle(book.getTitle());
    		exist.setAuthor(book.getAuthor());
    		repo.save(exist);
    		return ResponseEntity.ok("Book updated");
    		  		
    }
}