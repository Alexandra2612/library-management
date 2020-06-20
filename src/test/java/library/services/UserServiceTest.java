package library.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.model.LibrarianUser;
import library.model.ReaderUser;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest extends ApplicationTest {
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "test-ADGUB";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }
    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
    }
    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception {
        UserService.loadUsersFromFile();
        assertTrue(Files.exists(UserService.LIBRARIANS_PATH));
        assertTrue(Files.exists(UserService.READERS_PATH));
    }
    @Test
    public void testLoadUsersFromFile() throws Exception {
        UserService.loadUsersFromFile();
        assertNotNull(UserService.libUsers);
        assertNotNull(UserService.readerUsers);
        assertEquals(0, UserService.libUsers.size());
        assertEquals(0, UserService.readerUsers.size());
    }
    @Test
    public void testAddOneLibrarian() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addLibrarianUser("testUsername", "testPassword", "testFullname","testAddress","testPhoneNumber");
        assertNotNull(UserService.libUsers);
        assertEquals(1, UserService.libUsers.size());
    }

    @Test
    public void testAddOneLibrarianIsPersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addLibrarianUser("testUsername", "testPassword", "testFullname","testAddress","testPhoneNumber");
        List<LibrarianUser> librarians = new ObjectMapper().readValue(UserService.LIBRARIANS_PATH.toFile(), new TypeReference<List<LibrarianUser>>() {
        });
        assertNotNull(librarians);
        assertEquals(1, librarians.size());
    }
    @Test
    public void testAddOneReader() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addReaderUser("testUsername", "testPassword", "testFullname","testAddress","testPhoneNumber");
        assertNotNull(UserService.readerUsers);
        assertEquals(1, UserService.readerUsers.size());
    }

    @Test
    public void testAddOneReaderIsPersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addReaderUser("testUsername", "testPassword", "testFullname","testAddress","testPhoneNumber");
        List<ReaderUser> readers = new ObjectMapper().readValue(UserService.READERS_PATH.toFile(), new TypeReference<List<ReaderUser>>() {
        });
        assertNotNull(readers);
        assertEquals(1, readers.size());
    }

}
