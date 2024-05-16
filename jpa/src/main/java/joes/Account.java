package joes;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Version
    private Integer version;

//    @ElementCollection
//    @CollectionTable(
//            name = "phone_number",
//            joinColumns = @JoinColumn(name = "account_id")
//    )
//    private List<PhoneNumber> phoneNumbers;
    public Account() {
    }

    public Account(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
//        this.phoneNumbers = new ArrayList<>();
    }


//
//    public void addPhoneNumber(PhoneNumber phoneNumber) {
//        if (!phoneNumbers.contains(phoneNumber)) {
//            this.phoneNumbers.add(phoneNumber);
//        }
//    }
//
    public String getId() {
        return id;
    }

//
//    public List<PhoneNumber> getPhoneNumbers() {
//        return phoneNumbers;
//    }
//
    public String getFirstName() {
        return firstName;
    }
//
//    public String getLastName() {
//        return lastName;
//    }
}