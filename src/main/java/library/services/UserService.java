package library.services;

import org.apache.commons.io.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.exceptions.CouldNotWriteUsersException;
import library.exceptions.IncorrectPassword;
import library.exceptions.PasswordFieldEmptyException;
import library.exceptions.UserDoesNotExist;
import library.exceptions.UsernameAlreadyExistsException;
import library.exceptions.UsernameFieldEmptyException;
import library.model.LibrarianUser;
import library.model.ReaderUser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class UserService {

    private static List<LibrarianUser> libUsers;
    private static List<ReaderUser> readerUsers;
    private static String conectedUser;

    private static final Path LIBRARIANS_PATH = FileSystemService.getPathToFile("config" ,"librarians.json");
    private static final Path READERS_PATH = FileSystemService.getPathToFile( "config","readers.json");

    public static String getConectedUser() {
        return conectedUser;
    }
    public static ReaderUser getsomeUser(String username){
        for (ReaderUser user : readerUsers) {
            if (Objects.equals(username, user.getUsername()))
                return user;
        }
        return null;
    }

    public static void setConectedUser(String conectedUser) {
        UserService.conectedUser = conectedUser;
    }

    public static void loadUsersFromFile() throws IOException {
        if (!Files.exists(LIBRARIANS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("librarians.json"), LIBRARIANS_PATH.toFile());
        }
        if(!Files.exists(READERS_PATH)){
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("readers.json"), READERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        libUsers = objectMapper.readValue(LIBRARIANS_PATH.toFile(),new TypeReference<List<LibrarianUser>>() {
        });
        readerUsers=objectMapper.readValue(READERS_PATH.toFile(),new TypeReference<List<ReaderUser>>() {
        });
    }
    public static void addLibrarianUser(String username, String password, String fullname, String address, String phonenumber) throws UsernameAlreadyExistsException,UsernameFieldEmptyException,PasswordFieldEmptyException{
        checkUserFieldIsNotEmpty(username);
        checkPasswordFieldIsNotEmpty(password);
        checkUserDoesNotAlreadyExist(username);
        libUsers.add(new LibrarianUser(username, encodePassword(username, password), fullname,address,phonenumber));
        persistLibrarians();
    }
    public List<ReaderUser> getReaderUsers(){
        return readerUsers;
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
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(LIBRARIANS_PATH.toFile(), libUsers);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }
    public static void persistReaders() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(READERS_PATH.toFile(), readerUsers);
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
    public static ReaderUser checkReaders(String username, String pass) throws UserDoesNotExist, IncorrectPassword {

        for (ReaderUser user : readerUsers) {
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