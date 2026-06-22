package com.harsh.library_api;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    static List<Book> books = new ArrayList<>();
    static int nextId = 1;

    // GET all books
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    // GET one book by id
    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        for(Book b : books) {
            if(b.getId() == id) return b;
        }
        return null;
    }

    // POST - add a book
    @PostMapping
    public String addBook(@RequestBody Book book) {
        books.add(new Book(nextId++, book.getTitle(), book.getAuthor()));
        return "Book added successfully!";
    }

    // DELETE a book
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        books.removeIf(b -> b.getId() == id);
        return "Book deleted!";
    }
}