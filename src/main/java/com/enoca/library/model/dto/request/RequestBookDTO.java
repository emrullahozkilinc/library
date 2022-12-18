package com.enoca.library.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestBookDTO {
    private final String name;
    private final String author;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate date;
    private final long isbn;
}
