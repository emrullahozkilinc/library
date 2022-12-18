package com.enoca.library.mapper;

import com.enoca.library.model.dto.request.RequestLibraryDTO;
import com.enoca.library.model.dto.response.LibraryDTO;
import com.enoca.library.model.entity.Library;
import org.mapstruct.Mapper;

@Mapper(implementationName = "LibraryMapperImpl", componentModel = "spring")
public interface LibraryMapper {

    Library toLibraryFromRequestLibraryDTO(RequestLibraryDTO requestLibraryDTO);

    LibraryDTO toLibraryDTOFromLibrary(Library library);
}
