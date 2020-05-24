package registration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Imprumut {
    @JsonProperty("date")
    private Date date;
    @JsonProperty("book")
    private Book book;
    public Imprumut(){}
    public Imprumut(Book b){
        this.book=b;
        date=new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Imprumut{" +
                "date=" + date +
                ", book=" + book +
                '}';
    }
}
