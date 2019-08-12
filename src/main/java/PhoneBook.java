import exceptions.InvalidContactException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import models.Contact;

/**
 * It is able to store multiple {@link Contact}s and has various methods to edit these.
 */
public class PhoneBook {

    private List<Contact> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public PhoneBook(final List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Adds a new contact to the Phone Book.
     *
     * @param contactToAdd
     * @throws InvalidContactException if the new contact is null.
     */
    public void addContact(final Contact contactToAdd) {
        if (contactToAdd != null) {
            contacts.add(contactToAdd);
        } else {
            throw new InvalidContactException("The given contact is null!");
        }
    }

    /**
     * Removes a contact from the phone book. It uses the PhoneNumber as an unique ID.
     *
     * @param contactToRemove
     * @throws InvalidContactException if the selected contact's phone number is null.
     */
    public void removeContact(final Contact contactToRemove) {
        int index = findIndex(contactToRemove);

        if (index != -1) {
            contacts.remove(index);
        }
    }

    /**
     * Modifies a contact in the phone book.
     *
     * @param contactToEdit it is used to find the contact in the phone book.
     * @param editedContact the selected contact is modified according to this parameter.
     * @throws InvalidContactException if the selected contact's phone number is null.
     */
    public void editContact(final Contact contactToEdit, final Contact editedContact) {
        int index = findIndex(contactToEdit);

        if (index != -1) {
            Contact selectedContact = contacts.get(index);

            selectedContact.setPhoneNumber(editedContact.getPhoneNumber());
            selectedContact.setNickName(editedContact.getNickName());
            selectedContact.setName(editedContact.getName());
            selectedContact.setEmail(editedContact.getEmail());
        }
    }

    /**
     * Creates a clone of each element in the phone book.
     *
     * @return the cloned list of contacts.
     */
    public List<Contact> exportContacts() {
        return contacts.stream()
                       .map(Contact::new)
                       .collect(Collectors.toList());
    }

    /**
     * Searches for the given contact in the phone book.
     *
     * @param name        the name of searched contact.
     * @param phoneNumber the phone number of the searched contact.
     * @return the contact if it exists otherwise {@link Optional} empty.
     */
    // It was not clear that the needed method should search by name and phone number or to search separately.
    public Optional<Contact> findContact(final String name, final String phoneNumber) {
        return contacts.stream()
                       .filter(contact -> contact.getName().equals(name) && contact.getPhoneNumber().equals(phoneNumber))
                       .findFirst();
    }

    /**
     * Searches for the given contact in the phone book.
     *
     * @param name the name of searched contact.
     * @return the contact if it exists otherwise {@link Optional} empty.
     */
    public Optional<Contact> findByName(final String name) {
        return contacts.stream()
                       .filter(contact -> contact.getName().equals(name))
                       .findFirst();
    }

    /**
     * Searches for the given contact in the phone book.
     *
     * @param phoneNumber the phone number of the searched contact.
     * @return the contact if it exists otherwise {@link Optional} empty.
     */
    public Optional<Contact> findByNumber(final String phoneNumber) {
        return contacts.stream()
                       .filter(contact -> contact.getPhoneNumber().equals(phoneNumber))
                       .findFirst();
    }

    private int findIndex(final Contact contactToFind) {
        int index = -1;

        try {
            for (int i = 0; i < contacts.size(); ++i) {
                if (contacts.get(i).getPhoneNumber().equals(contactToFind.getPhoneNumber())) {
                    index = i;
                    break;
                }
            }
        } catch (NullPointerException e) {
            throw new InvalidContactException("The searched contact must not be null!", e);
        }

        return index;
    }
}
