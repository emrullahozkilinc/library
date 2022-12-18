package com.enoca.library.service;

import com.enoca.library.mapper.LibraryMapper;
import com.enoca.library.model.dto.request.RequestLibraryDTO;
import com.enoca.library.model.dto.response.LibraryDTO;
import com.enoca.library.model.entity.Book;
import com.enoca.library.model.entity.Library;
import com.enoca.library.repository.BookRepository;
import com.enoca.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;
    private final LibraryMapper libraryMapper;

    public List<LibraryDTO> getAll(){
        return libraryRepository.findAll()
                .stream().map(libraryMapper::toLibraryDTOFromLibrary)
                .collect(Collectors.toList());
    }

    public LibraryDTO addLibrary(RequestLibraryDTO requestLibraryDTO){
        Library mappedLibrary = libraryMapper.toLibraryFromRequestLibraryDTO(requestLibraryDTO);
        Library library = libraryRepository.save(mappedLibrary);
        return libraryMapper.toLibraryDTOFromLibrary(library);
    }

    public String updateLibrary(Long libraryId, RequestLibraryDTO requestLibraryDTO){
        Optional<Library> libraryFromDB = libraryRepository.findById(libraryId);

        libraryFromDB.ifPresentOrElse(library -> {
            library.setName(requestLibraryDTO.getName());

            libraryRepository.save(library);
        }, () -> {throw new RuntimeException("Library not found.");});
        return "Library Updated";
    }

    public String deleteLibrary(Long libraryId){
        Optional<Library> libraryFromDB = libraryRepository.findById(libraryId);
        libraryFromDB.ifPresentOrElse(libraryRepository::delete, () -> {throw new RuntimeException("Library not found.");});
        return "Library Deleted.";
    }

    public String addBookToLibrary(Long bookId, Long libraryId){
        Optional<Book> bookFromDB = bookRepository.findById(bookId);
        Optional<Library> libraryFromDB = libraryRepository.findById(libraryId);

        libraryFromDB.ifPresentOrElse(library -> {
            library.getBooks().add(bookFromDB.orElseThrow(() -> new RuntimeException("Book not found.")));
            bookFromDB.get().setLibrary(library);
            bookRepository.save(bookFromDB.get());
            libraryRepository.save(library);
        },() -> {throw new RuntimeException("Library not found.");});
        return "Book added to library";
    }
}
