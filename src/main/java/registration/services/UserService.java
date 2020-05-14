package registration.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import registration.exceptions.CouldNotWriteUsersException;
import registration.exceptions.IncorrectPassword;
import registration.exceptions.PasswordFieldEmptyException;
import registration.exceptions.UserDoesNotExist;
import registration.exceptions.UsernameAlreadyExistsException;
import registration.exceptions.UsernameFieldEmptyException;
import registration.model.LibrarianUser;
import registration.model.ReaderUser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class UserService {

    private static List<LibrarianUser> libUsers;
    private static List<ReaderUser> readerUsers;

    public static void loadUsersFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        libUsers = objectMapper.readValue(Paths.get("src/main/java/registration/services/config/librarians.json").toFile(), new TypeReference<List<LibrarianUser>>() {
        });
        readerUsers=objectMapper.readValue(Paths.get("src/main/java/registration/services/config/readers.json").toFile(), new TypeReference<List<ReaderUser>>() {
        });
    }
    public static void addLibrarianUser(String username, String password, String fullname, String address, String phonenumber) throws UsernameAlreadyExistsException,UsernameFieldEmptyException,PasswordFieldEmptyException{
        checkUserFieldIsNotEmpty(username);
        checkPasswordFieldIsNotEmpty(password);
        checkUserDoesNotAlreadyExist(username);
        libUsers.add(new LibrarianUser(username, encodePassword(username, password), fullname,address,phonenumber));
        persistLibrarians();
    }
    public static void addReaderUser(String username, String password, String fullname, String address, String phonenumber) throws UsernameAlreadyExistsException,UsernameFieldEmptyException,PasswordFieldEmptyException{
        checkUserFieldIsNotEmpty(username);
        checkPasswordFieldIsNotEmpty(password);
        checkUserDoesNotAlreadyExist(username);
        readerUsers.add(new ReaderUser(username, encodePassword(username, password), fullname,address,phonenumber));
        persistReaders();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException{
        for (LibrarianUser user : libUsers)
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        for (ReaderUser user : readerUsers)
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
    }
    private static void checkUserFieldIsNotEmpty(String username) throws UsernameFieldEmptyException{
        if(username.equals(""))
            throw new UsernameFieldEmptyException();
    }
    private static void checkPasswordFieldIsNotEmpty(String password) throws PasswordFieldEmptyException{
        if(password.equals(""))
            throw new PasswordFieldEmptyException();
    }

    private static void persistLibrarians() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get("src/main/java/registration/services/config/librarians.json").toFile(), libUsers);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }
    private static void persistReaders() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get("src/main/java/registration/services/config/readers.json").toFile(), readerUsers);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }
    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

   public static LibrarianUser checkLibrarian(String username, String pass) throws UserDoesNotExist, IncorrectPassword {

       for (LibrarianUser user : libUsers) {
           if (Objects.equals(username, user.getUsername()))
             {
             if(Objects.equals(encodePassword(username,pass),user.getPassword()))

             return user;
             else throw new IncorrectPassword();
               }

       }
       throw new UserDoesNotExist();


   }
}