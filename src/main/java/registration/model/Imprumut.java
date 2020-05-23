package registration.model;

import java.util.Date;

public class Imprumut {
    private Date date;
    private Book book;
    public Imprumut(){}
    public Imprumut(Book b){
        this.book=b;
        date=new Date();
    }
}
