package joes;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContactPerson {

    private String firstName;

    private String lastName;

    private String phone;

    public ContactPerson(String firs, String las, String ph) {
        this.firstName = firs;
        this.lastName = las;
        this.phone = ph;
    }

    public ContactPerson() {

    }

    // standard getters, setters
}