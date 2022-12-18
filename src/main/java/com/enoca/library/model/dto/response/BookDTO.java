package com.enoca.library.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDTO {
    @JsonManagedReference
    private final LibraryDTO library;
    private final String name;
    private final String author;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate date;
    private final long isbn;
}
