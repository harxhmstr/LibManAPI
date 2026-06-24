package com.harsh.library_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    static List<Book> books = new ArrayList<>();
    static int nextId = 1;

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(books);
    }

    // GET one book by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        for(Book b : books) {
            if(b.getId() == id) return ResponseEntity.ok(b);
        }
        return ResponseEntity.notFound().build();
    }

    // POST - add a book
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        books.add(new Book(nextId++, book.getTitle(), book.getAuthor()));
        return ResponseEntity.status(201).body("book added scuessfully");
    }

    // DELETE a book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        if(books.removeIf(b -> b.getId() == id)) {
        		return ResponseEntity.ok("Book deleted");
        }
        return ResponseEntity.notFound().build();
    }
    
    //UPDATE a book
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id,@RequestBody Book book) {
    		for(Book b:books){
    			if(b.getId()==id) {
    				b.setTitle(book.getTitle());
    				b.setAuthor(book.getAuthor());
    				return ResponseEntity.ok("Book updated successfully!!!");
    			}	
    		}
    		return ResponseEntity.notFound().build();
    }
}