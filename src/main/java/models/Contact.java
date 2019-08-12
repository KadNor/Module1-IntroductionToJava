package models;

/**
 * Stores the personal data of a person.
 */
public class Contact {

    private String name;
    private String nickName;
    private String phoneNumber;
    private String email;

    public Contact() {
    }

    public Contact(final String name, final String nickName, final String phoneNumber, final String email) {
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Contact(final Contact contact) {
        this.name = contact.name;
        this.nickName = contact.nickName;
        this.phoneNumber = contact.phoneNumber;
        this.email = contact.email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "name: " + name +
                "; nickName: " + nickName +
                "; phoneNumber: " + phoneNumber +
                "; email: " + email;
    }
}
