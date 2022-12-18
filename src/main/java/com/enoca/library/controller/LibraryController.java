package com.enoca.library.controller;

import com.enoca.library.model.dto.request.RequestLibraryDTO;
import com.enoca.library.model.dto.response.LibraryDTO;
import com.enoca.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app/libraries")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping
    public ResponseEntity<List<LibraryDTO>> getAllLibraries(){
        return ResponseEntity.ok(libraryService.getAll());
    }

    @PostMapping
    public ResponseEntity<LibraryDTO> addLibrary(@RequestBody RequestLibraryDTO requestLibraryDTO){
        return ResponseEntity.ok(libraryService.addLibrary(requestLibraryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLibrary(@PathVariable("id") Long id, @RequestBody RequestLibraryDTO requestLibraryDTO){
        return ResponseEntity.ok(libraryService.updateLibrary(id, requestLibraryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibrary(@PathVariable("id") Long id){
        return ResponseEntity.ok(libraryService.deleteLibrary(id));
    }

    @PostMapping("/book/{libraryId}/{bookId}")
    public ResponseEntity<String> addBookToLibrary(@PathVariable("libraryId") Long libraryId, @PathVariable Long bookId){
        return ResponseEntity.ok(libraryService.addBookToLibrary(bookId, libraryId));
    }
}
