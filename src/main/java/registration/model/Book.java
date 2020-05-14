package registration.model;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int duration;
    private int pieces;
    public Book(){}
    public Book(String title, String author,int duration, int pieces){
        this.author=author;
        this.title=title;
        this.duration=duration;
        this.pieces=pieces;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    private void setDuration(int duration) {
        this.duration = duration;
    }

    private void setPieces(int pieces) {
        this.pieces = pieces;
    }

    private String getTitle() {
        return title;
    }

    private String getAuthor() {
        return author;
    }

    private int getDuration() {
        return duration;
    }

    private int getPieces() {
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
