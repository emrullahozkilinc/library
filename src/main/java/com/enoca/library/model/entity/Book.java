package com.enoca.library.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Library library;

    String name;

    String author;

    LocalDate date;

    long isbn;

    public Book(Library library, String name, String author, LocalDate date, long isbn) {
        this.library = library;
        this.name = name;
        this.author = author;
        this.date = date;
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn == book.isbn && Objects.equals(id, book.id) && Objects.equals(library, book.library) && Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(date, book.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, library, name, author, date, isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", library=" + library +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", isbn=" + isbn +
                '}';
    }
}
