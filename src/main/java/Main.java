import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import models.Contact;

public class Main {

    public static PhoneBook phoneBook;
    public static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        phoneBook = new PhoneBook();
        String num;
        boolean exit = false;

        reader = new BufferedReader(new InputStreamReader(System.in));

        do {
            printMenu();
            try {
                num = reader.readLine();
                Integer choice = Integer.valueOf(num);

                switch (choice) {
                    case 0: {
                        addNewContact();
                        break;
                    }
                    case 1: {
                        editContact();
                        break;
                    }
                    case 2: {
                        removeContact();
                        break;
                    }
                    case 3: {
                        findByName();
                        break;
                    }
                    case 4: {
                        findByNumber();
                        break;
                    }
                    case 5: {
                        findContact();
                        break;
                    }
                    case 6: {
                        exportContacts();
                        break;
                    }
                    case 7: {
                        exit = true;
                        break;
                    }
                    default: {
                        System.out.println("Invalid choice!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Something went wrong, please try again!");
            }
        } while (!exit);

        reader.close();
    }

    private static void exportContacts() {
        List<Contact> contacts = phoneBook.exportContacts();

        for (int i = 0; i < contacts.size(); ++i) {
            System.out.println(i + ". " + contacts.get(i));
        }
    }

    private static void findContact() throws IOException {
        System.out.println("\nPlease provide the name of the contact you want to find.");
        String name = reader.readLine();

        System.out.println("\nPlease provide the phone number of the contact you want to find.");
        String phoneNumber = reader.readLine();

        phoneBook.findContact(name, phoneNumber).ifPresent(System.out::println);
    }

    private static void findByNumber() throws IOException {
        System.out.println("\nPlease provide the phone number of the contact you want to find.");
        String phoneNumber = reader.readLine();

        phoneBook.findByNumber(phoneNumber).ifPresent(System.out::println);
    }

    private static void findByName() throws IOException {
        System.out.println("\nPlease provide the name of the contact you want to find.");
        String name = reader.readLine();

        phoneBook.findByName(name).ifPresent(System.out::println);
    }

    private static void removeContact() throws IOException {
        System.out.println("\nPlease select the id of the contact that you want to remove.");

        Integer id = Integer.valueOf(reader.readLine());

        phoneBook.removeContact(phoneBook.exportContacts().get(id));
    }

    private static void editContact() throws IOException {
        System.out.println("\nPlease select the id of the contact that you want to edit.");

        Integer id = Integer.valueOf(reader.readLine());

        System.out.println("\nPlease specified the modified contact.");
        Contact modified = getContact();

        phoneBook.editContact(phoneBook.exportContacts().get(id), modified);
    }

    private static void addNewContact() throws IOException {
        System.out.println("Please add a contact:");
        phoneBook.addContact(getContact());
    }

    public static void printMenu() {
        System.out.println("==========================================");
        System.out.println("Please pick an action:");
        System.out.println("0: Add new contact.");
        System.out.println("1: Edit existing contact.");
        System.out.println("2: Remove contact.");
        System.out.println("3: Find contact by name.");
        System.out.println("4: Find contact by phone number.");
        System.out.println("5: Find contact by name and phone number.");
        System.out.println("6: List contacts.");
        System.out.println("7. Exit.");
        System.out.println("==========================================");
    }

    public static Contact getContact() throws IOException {
        Contact contact = new Contact();

        System.out.print("Name: ");
        contact.setName(reader.readLine());
        System.out.print("Phone number: ");
        contact.setPhoneNumber(reader.readLine());
        System.out.print("Nickname: ");
        contact.setNickName(reader.readLine());
        System.out.print("Email: ");
        contact.setEmail(reader.readLine());

        return contact;
    }
}
