package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Title title;
    private final Phone phone;
    private final Email email;
    private final Description description;
    private final Status status;

    // Data fields
    private final Address address;
    private final List<Suspect> suspects = new ArrayList<>();
    private final List<Victim> victims = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();
    private final List<Witness> witnesses = new ArrayList<>();
    private final List<Document> documents = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Title title, Description description, Phone phone, Email email, Status status,
                  List<Document> documents, Address address,
                  List<Suspect> suspects, List<Victim> victims, List<Witness> witnesses, Set<Tag> tags) {
        requireAllNonNull(title, description, phone, email, status, documents,
                address, suspects, victims, witnesses, tags);
        this.title = title;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.address = address;
        this.documents.addAll(documents);
        this.suspects.addAll(suspects);
        this.victims.addAll(victims);
        this.tags.addAll(tags);
        this.witnesses.addAll(witnesses);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public Address getAddress() {
        return address;
    }


    public List<Document> getDocuments() {
        return documents;
    }

    public List<Suspect> getSuspects() {
        return suspects;
    }

    public List<Victim> getVictims() {
        return victims;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    // TODO: Consider using Collections.unmodifiableList(witnessList) here.
    public List<Witness> getWitnesses() {
        return witnesses;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getTitle().equals(getTitle())
                && (otherPerson.getPhone().equals(getPhone()) || otherPerson.getEmail().equals(getEmail()))
                && otherPerson.getStatus().equals(getStatus());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getTitle().equals(getTitle())
                && otherPerson.getDescription().equals(getDescription())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getStatus().equals(getStatus())
                && otherPerson.getDocuments().equals(getDocuments())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getSuspects().equals(getSuspects())
                && otherPerson.getVictims().equals(getVictims())
                && otherPerson.getWitnesses().equals(getWitnesses())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, phone, email, status, documents,
                            address, suspects, victims, witnesses, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Description: ")
                .append(getDescription())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Status: ")
                .append(getStatus())
                .append(" Documents: ")
                .append(getDocuments())
                .append(" Address: ")
                .append(getAddress())
                .append(" Suspects: ");
        getSuspects().forEach(builder::append);
        builder.append(" Victims: ");
        getVictims().forEach(builder::append);
        builder.append(" Witnesses: ");
        getWitnesses().forEach(builder::append);
        builder.append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
