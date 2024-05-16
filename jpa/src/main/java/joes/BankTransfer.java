package joes;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@DynamicUpdate
public class BankTransfer {
    public Account getSender() {
        return sender;
    }

    public enum State {
        CREATED,
        SETTLED
    }
    @Id
    private String id;

    private String reference;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account receiver;
//
    @Embedded
    private Amount amount;

    @Enumerated(EnumType.STRING)
    private State state;

    @ElementCollection
    @CollectionTable(name = "contact_person")
    private List<ContactPerson> contactPerson;

    public BankTransfer() {
    }

    public BankTransfer(String id, String reference , Account sender, Account receiver, Amount amount) {
        this.id = id;
        this.reference = reference;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.state = State.CREATED;
        this.contactPerson = List.of(new ContactPerson("John Doe", "john.doe@example.com", "123456789"));

    }

    public void settle() {
        this.state = State.SETTLED;
    }

    public Account getReceiver() {
        return receiver;
    }
}