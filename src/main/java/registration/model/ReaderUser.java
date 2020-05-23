package registration.model;


import java.util.ArrayList;

public class ReaderUser {
    private String username;
    private String password;
    private String fullname;
    private String address;
    private String phonenumber;
    private ArrayList<Imprumut> listaimprumuturi;

    public ReaderUser(){}
    public ReaderUser( String username, String password, String fullname, String address, String phonenumber) {
        this.username = username;
        this.password = password;
        this.fullname=fullname;
        this.address=address;
        this.phonenumber=phonenumber;
        this.listaimprumuturi=new ArrayList<Imprumut>();
    }
    public void adaugaImprumut(Imprumut i){
        if(listaimprumuturi==null)
            listaimprumuturi=new ArrayList<Imprumut>();
        listaimprumuturi.add(i);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void Fullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public ArrayList<Imprumut> getListaimprumuturi() {
        return listaimprumuturi;
    }

    public void setListaimprumuturi(ArrayList<Imprumut> listaimprumuturi) {
        this.listaimprumuturi = listaimprumuturi;
    }

    @Override
    public String toString() {
        return "ReaderUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", address='" + address + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", listaimprumuturi='" + listaimprumuturi + '\'' +
                '}';
    }
}
