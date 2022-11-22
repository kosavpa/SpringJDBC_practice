package basic_package.entities;



import java.sql.Date;
import java.util.List;


public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<Animal> animals;

    public Person() {
    }

    public Person(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Person(int id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Person(int id, String firstName, String lastName, List<Animal> animals) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.animals = animals;
    }

    public Person(String firstName, String lastName, Date birthDate, List<Animal> animals) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.animals = animals;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
    }

    public void removeAnimal(Animal animal){
        animals.remove(animal);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        return animals != null ? animals.equals(person.animals) : person.animals == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (animals != null ? animals.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", animals=" + animals +
                '}';
    }
}
