import java.util.Objects;

public class Person {
    private String name;
    private String lastName;
    private String middleName;
    private String whenBorn;
    private long phoneNumber;
    private char gender;

    public Person(String name, String lastName, String middleName, String whenBorn, long phoneNumber, char gender) {
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.whenBorn = whenBorn;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Person(String[] arr) {
        this.name = arr[1];
        this.lastName = arr[0];
        this.middleName = arr[2];
        this.whenBorn = arr[3];
        this.phoneNumber = Long.parseLong(arr[4]);
        this.gender = arr[5].charAt(0);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setWhenBorn(String whenBorn) {
        this.whenBorn = whenBorn;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("<%s> <%s> <%s> <%s> <%s> <%s>", lastName, name, middleName, whenBorn, phoneNumber, gender);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return phoneNumber == person.phoneNumber && gender == person.gender && Objects.equals(name, person.name) && Objects.equals(lastName, person.lastName) && Objects.equals(middleName, person.middleName) && Objects.equals(whenBorn, person.whenBorn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, middleName, whenBorn, phoneNumber, gender);
    }
}
