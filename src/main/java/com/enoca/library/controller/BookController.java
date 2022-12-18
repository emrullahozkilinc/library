package com.enoca.library.controller;

import com.enoca.library.model.dto.request.RequestBookDTO;
import com.enoca.library.model.dto.response.BookDTO;
import com.enoca.library.model.entity.Book;
import com.enoca.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody RequestBookDTO requestBookDTO){
        return ResponseEntity.ok(bookService.addBook(requestBookDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") Long id, @RequestBody RequestBookDTO requestBookDTO){
        return ResponseEntity.ok(bookService.updateBook(id, requestBookDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
}
