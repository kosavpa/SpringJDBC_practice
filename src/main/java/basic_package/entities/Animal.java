package basic_package.entities;


public class Animal {
    private int id;
    private int personId;
    private String firstName;
    private String animalType;

    public Animal() {
    }

    public Animal(String firstName, String animalType) {
        this.firstName = firstName;
        this.animalType = animalType;
    }

    public Animal(int personId, String firstName, String animalType) {
        this.personId = personId;
        this.firstName = firstName;
        this.animalType = animalType;
    }

    public Animal(int id, int personId, String firstName, String animalType) {
        this.id = id;
        this.personId = personId;
        this.firstName = firstName;
        this.animalType = animalType;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (id != animal.id) return false;
        if (personId != animal.personId) return false;
        if (firstName != null ? !firstName.equals(animal.firstName) : animal.firstName != null) return false;
        return animalType != null ? animalType.equals(animal.animalType) : animal.animalType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + personId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (animalType != null ? animalType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "basic_package.entities.Animal{" +
                "id=" + id +
                ", personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", animalType='" + animalType + '\'' +
                '}';
    }
}
