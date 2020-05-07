package registration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class LibrarianUser {

        private String username;
        private String password;
        private String fullname;
        private String address;
        private String phonenumber;

       public LibrarianUser(){}
        public LibrarianUser( String username, String password, String fullname, String address, String phonenumber) {

            this.username = username;
            this.password = password;
            this.fullname=fullname;
            this.address=address;
            this.phonenumber=phonenumber;
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


   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibrarianUser user = (LibrarianUser) o;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if(!fullname.equals(user.fullname)) return false;
        if(!address.equals(user.address)) return false;
        if(!phonenumber.equals(user.phonenumber)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, fullname, address, phonenumber);
    }*/

    @Override
    public String toString() {
        return "LibrarianUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", address='" + address + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
