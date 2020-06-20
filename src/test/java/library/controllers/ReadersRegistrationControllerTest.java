package library.controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import library.services.FileSystemService;
import library.services.UserService;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class ReadersRegistrationControllerTest extends ApplicationTest {
    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    private ReadersRegistrationController controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = "test-ADGUB";
        FileSystemService.initApplicationHomeDirIfNeeded();
        UserService.loadUsersFromFile();
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        UserService.loadUsersFromFile();
        controller = new ReadersRegistrationController();
        controller.usernameField2 = new TextField();
        controller.passwordField2 = new PasswordField();
        controller.fullnameField2 = new TextField();
        controller.addressField2= new TextField();
        controller.phonenumberField2 = new TextField();
        controller.registrationMessage2 = new Text();
        controller.usernameField2.setText(TEST_USER);
        controller.passwordField2.setText(TEST_PASSWORD);
    }
    @Test
    public void testAddReaderActionCode() {
        controller.handleReadersRegisterAction();
        assertEquals(1, UserService.getReaderUsers().size());
        assertEquals("Account created successfully!", controller.registrationMessage2.getText());
    }
    @Test
    public void testAddSameReaderTwice() {
        controller.handleReadersRegisterAction();
        controller.handleReadersRegisterAction();
        assertEquals("An account with the username " + TEST_USER + " already exists!", controller.registrationMessage2.getText());
    }
}
