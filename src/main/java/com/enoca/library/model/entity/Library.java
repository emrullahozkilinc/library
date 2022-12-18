package com.enoca.library.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(mappedBy = "library")
    List<Book> books;

    String name;

    public Library(List<Book> books, String name) {
        this.books = books;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(id, library.id) && Objects.equals(books, library.books) && Objects.equals(name, library.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, books, name);
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", books=" + books +
                ", name='" + name + '\'' +
                '}';
    }
}
