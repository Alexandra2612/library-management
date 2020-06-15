package library.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Book {
    @JsonProperty("title")
    private String title;
    @JsonProperty("author")
    private String author;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("pieces")
    private int pieces;
    public Book(){}
    public Book(String title, String author,int duration, int pieces){
        this.author=author;
        this.title=title;
        this.duration=duration;
        this.pieces=pieces;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getDuration() {
        return duration;
    }

    public int getPieces() {
        return pieces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return duration == book.duration &&
                pieces == book.pieces &&
                title.equals(book.title) &&
                author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, duration, pieces);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", duration=" + duration +
                ", pieces=" + pieces +
                '}';
    }
}
