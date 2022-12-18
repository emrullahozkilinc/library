package com.enoca.library.model.dto.response;

import com.enoca.library.model.entity.Book;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.util.List;

@Data
public class LibraryDTO {
    @JsonBackReference
    private final List<Book> books;
    private final String name;
}
