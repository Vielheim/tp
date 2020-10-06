package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Document;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Witness;
import seedu.address.model.person.Reference;
import seedu.address.model.person.Status;
import seedu.address.model.person.Suspect;
import seedu.address.model.person.Victim;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_STATUS = "active";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Status status;
    private Address address;
    private List<Document> documents;
    private List<Suspect> suspects;
    private List<Victim> victims;
    private Set<Tag> tags;
    private List<Witness> witnesses;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        status = Status.createStatus(DEFAULT_STATUS);
        address = new Address(DEFAULT_ADDRESS);
        documents = new ArrayList<>();
        suspects = new ArrayList<>();
        victims = new ArrayList<>();
        witnesses = new ArrayList<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        status = personToCopy.getStatus();
        address = personToCopy.getAddress();
        documents = new ArrayList<>(personToCopy.getDocuments());
        suspects = personToCopy.getSuspects();
        victims = personToCopy.getVictims();
        witnesses = new ArrayList<>(personToCopy.getWitnesses());
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withWitnesses(String ... witnesses) {
        this.witnesses = SampleDataUtil.getWitnessList(witnesses);
        return this;
    }

    /**
     * Sets the {@code Document} of the {@code Person} that we are building.
     */
    public PersonBuilder withDocument(String name, String ref) {
        this.documents = new ArrayList<>();
        this.documents.add(new Document(new Name(name), new Reference(ref)));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        this.status = Status.createStatus(status);
        return this;
    }

    //TODO: Not used anywhere in code, use the witness example and use there
    /**
     * Parses the {@code suspects} into a {@code List<Suspect>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withSuspects(String ... suspects) {
        this.suspects = SampleDataUtil.getSuspectList(suspects);
        return this;
    }

    /**
     * Parses the {@code victims} into a {@code List<Victim>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withVictims(String ... victims) {
        this.victims = SampleDataUtil.getVictimList(victims);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, status, documents, address, suspects, victims, witnesses, tags);
    }


}
