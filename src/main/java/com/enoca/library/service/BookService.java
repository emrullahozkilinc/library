package com.enoca.library.service;

import com.enoca.library.mapper.BookMapper;
import com.enoca.library.model.dto.request.RequestBookDTO;
import com.enoca.library.model.dto.response.BookDTO;
import com.enoca.library.model.entity.Book;
import com.enoca.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDTO> getAll(){
        return bookRepository.findAll()
                .stream().map(bookMapper::toBookDTOFromBook)
                .collect(Collectors.toList());
    }

    public BookDTO addBook(RequestBookDTO requestBookDTO){
        Book mappedBook = bookMapper.toBookFromRequestBookDTO(requestBookDTO);
        Book book = bookRepository.save(mappedBook);
        return bookMapper.toBookDTOFromBook(book);
    }

    public String updateBook(Long bookId, RequestBookDTO requestBookDTO){
        Optional<Book> bookFromDB = bookRepository.findById(bookId);

        bookFromDB.ifPresentOrElse(book -> {
            book.setDate(requestBookDTO.getDate());
            book.setName(requestBookDTO.getName());
            book.setAuthor(requestBookDTO.getAuthor());
            book.setIsbn(requestBookDTO.getIsbn());

            bookRepository.save(book);
        }, () -> {throw new RuntimeException("Book not found.");});
        return "Book updated";
    }

    public String deleteBook(Long bookId){
        Optional<Book> bookFromDB = bookRepository.findById(bookId);
        bookFromDB.ifPresentOrElse(bookRepository::delete, () -> {throw new RuntimeException("Book not found.");});
        return "Book deleted";
    }
}
