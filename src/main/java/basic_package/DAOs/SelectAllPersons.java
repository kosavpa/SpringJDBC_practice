package basic_package.DAOs;


import basic_package.entities.Person;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component("selectAllPerson")
public class SelectAllPersons extends MappingSqlQuery<Person> {
    private static final String SQL = "select id, first_name, last_name, birth_date " +
                                "from person";

    public SelectAllPersons(DataSource ds) {
        super(ds, SQL);
    }

    @Override
    protected Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setFirstName(resultSet.getString("first_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setBirthDate(resultSet.getDate("birth_date"));

        return person;
    }
}
