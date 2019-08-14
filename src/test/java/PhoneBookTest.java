import exceptions.InvalidContactException;
import java.util.List;
import java.util.Optional;
import models.Contact;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PhoneBookTest {

    private static final Contact CONTACT_1 = new Contact("Jhon Doe", "Jhonny", "0123456789", "asd@qwe.com");
    private static final Contact CONTACT_2 = new Contact("Jane Doe", "Janeeee", "9876543210", "asd@qwe.com");
    private static final Contact CONTACT_3 = new Contact("Test User", "Testy", "1234598760", "das@frequentis.com");

    private PhoneBook sut;

    @Before
    public void initialize() {
        sut = new PhoneBook();

        sut.addContact(CONTACT_1);
        sut.addContact(CONTACT_2);
    }

    @Test
    public void nonNullContact_addToThePhoneBook_shouldAppearInTheContactList() {
        // then
        List<Contact> actual = sut.exportContacts();

        assertEquals(2, actual.size());
        assertEqualContacts(CONTACT_1, actual.get(0));
    }

    @Test
    public void notEmptyPhoneBook_exportContacts_shouldCloneThePhoneBook() {
        // when
        List<Contact> actual = sut.exportContacts();

        // then
        assertEquals(2, actual.size());
        assertEqualContacts(CONTACT_1, actual.get(0));
        assertEqualContacts(CONTACT_2, actual.get(1));
    }

    @Test(expected = InvalidContactException.class)
    public void nullContact_addToThePhoneBook_shouldThrowException() {
        // when
        sut.addContact(null);
    }

    @Test
    public void notEmptyPhoneBook_removeContact_shouldRemoveTheGivenContact() {
        // given
        sut.addContact(CONTACT_3);

        // when
        sut.removeContact(CONTACT_2);

        // then
        List<Contact> actual = sut.exportContacts();

        assertEquals(2, actual.size());
        assertEqualContacts(CONTACT_1, actual.get(0));
        assertEqualContacts(CONTACT_3, actual.get(1));
    }

    @Test
    public void emptyPhoneBook_removeContact_shouldNotModifyThePhoneBook() {
        // when
        sut.removeContact(CONTACT_1);

        // then
        List<Contact> actual = sut.exportContacts();

        assertEquals(1, actual.size());
    }

    @Test
    public void theGivenElementExistInTheList_editContact_shouldEditTheGivenContact() {
        // given
        Contact expected = new Contact(CONTACT_1);
        expected.setPhoneNumber("11111111111");

        // when
        sut.editContact(CONTACT_1, expected);

        // then
        List<Contact> actual = sut.exportContacts();

        assertEquals(2, actual.size());
        assertEqualContacts(expected, actual.get(0));
        assertEqualContacts(CONTACT_2, actual.get(1));
    }

    @Test
    public void theGivenElementDoesNotExistInTheList_editContact_shouldNotModifyThePhoneBook() {
        // given
        Contact expected = new Contact(CONTACT_1);
        expected.setPhoneNumber("11111111111");

        // when
        sut.editContact(CONTACT_3, expected);

        // then
        List<Contact> actual = sut.exportContacts();

        assertEquals(2, actual.size());
        assertEqualContacts(CONTACT_1, actual.get(0));
        assertEqualContacts(CONTACT_2, actual.get(1));
    }

    @Test
    public void theSelectedContactExists_findByName_shouldReturnTheContact() {
        // when
        Optional<Contact> actual = sut.findByName(CONTACT_1.getName());

        // then
        assertTrue(actual.isPresent());
        assertEqualContacts(CONTACT_1, actual.get());
    }

    @Test
    public void theSelectedContactDoesNotExists_findByName_shouldReturnEmptyOptional() {
        // when
        Optional<Contact> actual = sut.findByName(CONTACT_3.getName());

        // then
        assertTrue(!actual.isPresent());
    }

    @Test
    public void theSelectedContactExists_findByPhoneNumber_shouldReturnTheContact() {
        // when
        Optional<Contact> actual = sut.findByNumber(CONTACT_1.getPhoneNumber());

        // then
        assertTrue(actual.isPresent());
        assertEqualContacts(CONTACT_1, actual.get());
    }

    @Test
    public void theSelectedContactDoesNotExists_findByPhoneNumber_shouldReturnEmptyOptional() {
        // when
        Optional<Contact> actual = sut.findByNumber(CONTACT_3.getPhoneNumber());

        // then
        assertTrue(!actual.isPresent());
    }

    @Test
    public void theSelectedContactExists_findContact_shouldReturnTheContact() {
        // when
        Optional<Contact> actual = sut.findContact(CONTACT_1.getName(), CONTACT_1.getPhoneNumber());

        // then
        assertTrue(actual.isPresent());
        assertEqualContacts(CONTACT_1, actual.get());
    }

    @Test
    public void theSelectedContactDoesNotExists_findContact_shouldReturnEmptyOptional() {
        // when
        Optional<Contact> actual = sut.findContact(CONTACT_1.getName(), CONTACT_2.getPhoneNumber());

        // then
        assertTrue(!actual.isPresent());
    }

    private void assertEqualContacts(final Contact expected, final Contact actual) {
        assertThat(expected.getPhoneNumber(), equalTo(actual.getPhoneNumber()));
        assertThat(expected.getName(), equalTo(actual.getName()));
        assertThat(expected.getNickName(), equalTo(actual.getNickName()));
        assertThat(expected.getEmail(), equalTo(actual.getEmail()));
    }
}