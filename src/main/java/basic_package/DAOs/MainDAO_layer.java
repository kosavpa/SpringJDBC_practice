package basic_package.DAOs;


import basic_package.entities.Animal;
import basic_package.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.*;


@Repository("dao")
public class MainDAO_layer {
    private NamedParameterJdbcTemplate npjt;
    private MappingSqlQuery<Person> selectAllPersons;
    private MappingSqlQuery<Person> selectAllPersonsByFirstName;
    private SqlUpdate updatePerson;
    private JdbcTemplate jdbcTemplate;
    private SqlUpdate insertPerson;

    @Autowired
    public MainDAO_layer(DataSource dataSource) {
        this.npjt = new NamedParameterJdbcTemplate(dataSource);
        this.selectAllPersons = new SelectAllPersons(dataSource);
        this.selectAllPersonsByFirstName = new SelectAllPersonByFirstName(dataSource);
        this.updatePerson = new UpdatePerson(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertPerson = new InsertPerson(dataSource);
    }

    public Person findPersonById(int id){
        String sql = "select p.first_name, p.last_name, p.birth_date, a.id, a.first_name, a.animal_type " +
                     "from person as p " +
                     "left join animal as a on a.person_id = p.id " +
                     "where p.id = :personId";
        Map<String, Object> params = new HashMap<>();
        params.put("personId", id);

        return npjt.query(sql, params, rs -> {
            Map<Integer, Person> personMap = new HashMap<>();
            Person person;

            while (rs.next()) {
                if((person = personMap.get(id)) == null) {
                    person = new Person();

                    person.setAnimals(new ArrayList<>());
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    person.setBirthDate(rs.getDate("birth_date"));
                    person.setId(id);

                    personMap.put(id, person);
                }
                int a = rs.findColumn("animal_type");
                if (rs.findColumn("animal_type") != 0) {
                    Animal animal = new Animal();

                    animal.setAnimalType(rs.getString("animal_type"));
                    animal.setFirstName(rs.getString("a.first_name"));
                    animal.setId(rs.getInt("a.id"));
                    animal.setPersonId(id);

                    person.addAnimal(animal);
                }
            }

            return personMap.get(id);
        });
    }

    public List<Person> findAllPersonsExcludeAnimal(){
        return selectAllPersons.execute();
    }

    public List<Person> findAllPersonsByFirstNameIncludeAnimal(String firstName){
        Map<String, Object> params = new HashMap<>();

        params.put("firstName", firstName);

        return selectAllPersonsByFirstName.executeByNamedParam(params);
    }

    public void updatePersonExcludeAnimal(Person person){
        Map<String, Object> params = new HashMap<>();

        params.put("firstName", person.getFirstName());
        params.put("lastName", person.getLastName());
        params.put("birthDate", person.getBirthDate());
        params.put("id", person.getId());

        updatePerson.updateByNamedParam(params);
    }

    public Person insertPersonWithAnimal(Person person){
        String personSql = "insert into person (first_name, last_name, birth_date) values (?, ?, ?)";
        String animalSql = "insert into animal (person_id, first_name, animal_type) values (?, ?, ?)";
        PreparedStatementCreatorFactory personPSCF = new PreparedStatementCreatorFactory(personSql, Types.VARCHAR, Types.VARCHAR, Types.DATE);
        PreparedStatementCreatorFactory animalPSCF = new PreparedStatementCreatorFactory(animalSql, Types.INTEGER, Types.VARCHAR, Types.VARCHAR);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        personPSCF.setGeneratedKeysColumnNames("id");
        personPSCF.setReturnGeneratedKeys(true);
        animalPSCF.setGeneratedKeysColumnNames("id");
        animalPSCF.setReturnGeneratedKeys(true);

        jdbcTemplate.update(personPSCF.newPreparedStatementCreator(new Object[]{person.getFirstName(), person.getLastName(), person.getBirthDate()}), keyHolder);
        int personKey = keyHolder.getKey().intValue();
        person.setId(personKey);

        for (Animal animal : person.getAnimals()) {
            jdbcTemplate.update(animalPSCF.newPreparedStatementCreator(new Object[]{personKey, animal.getFirstName(), animal.getAnimalType()}), keyHolder);

            animal.setId(keyHolder.getKey().intValue());
            animal.setPersonId(personKey);
        }

        return person;
    }

    public Person insertPersonExcludeAnimal(Person person){
        Map<String, Object> params = new HashMap<>();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        params.put("firstName", person.getFirstName());
        params.put("lastName", person.getLastName());
        params.put("birthDate", person.getBirthDate());

        insertPerson.updateByNamedParam(params, keyHolder);

        person.setId(keyHolder.getKey().intValue());

        return person;
    }
}
