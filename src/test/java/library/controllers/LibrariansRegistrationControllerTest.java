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


public class LibrariansRegistrationControllerTest extends ApplicationTest {
    public static final String TEST_USER = "testUser";
    public static final String TEST_PASSWORD = "testPassword";
    private LibrariansRegistrationController controller;
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
        controller = new LibrariansRegistrationController();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.fullnameField = new TextField();
        controller.addressField = new TextField();
        controller.phonenumberField = new TextField();
        controller.registrationMessage = new Text();
        controller.usernameField.setText(TEST_USER);
        controller.passwordField.setText(TEST_PASSWORD);
    }
    @Test
    public void testAddLibrarianActionCode() {
        controller.handleRegisterAction();
        assertEquals(1, UserService.getLibUsers().size());
        assertEquals("Account created successfully!", controller.registrationMessage.getText());
    }
    @Test
    public void testAddSameLibrarianTwice() {
        controller.handleRegisterAction();
        controller.handleRegisterAction();
        assertEquals("An account with the username " + TEST_USER + " already exists!", controller.registrationMessage.getText());
    }
}
