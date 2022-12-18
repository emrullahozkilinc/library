package com.enoca.library.mapper;

import com.enoca.library.model.dto.request.RequestBookDTO;
import com.enoca.library.model.dto.response.BookDTO;
import com.enoca.library.model.entity.Book;
import org.mapstruct.Mapper;

@Mapper(implementationName = "BookMapperImpl", componentModel = "spring")
public interface BookMapper {

    Book toBookFromRequestBookDTO(RequestBookDTO requestBookDTO);

    BookDTO toBookDTOFromBook(Book book);
}
