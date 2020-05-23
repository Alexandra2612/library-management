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

    @Override
    public String toString() {
        return "Imprumut{" +
                "date=" + date +
                ", book=" + book +
                '}';
    }
}
