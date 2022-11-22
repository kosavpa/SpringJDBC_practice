package basic_package.DAOs;

import basic_package.entities.Person;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


@Component("selectAllPersonByFirstName")
public class SelectAllPersonByFirstName extends MappingSqlQuery<Person> {
    private static final String SQL = "select id, first_name, last_name, birth_date " +
            "from person " +
            "where first_name = :firstName";

    public SelectAllPersonByFirstName(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter("firstName", Types.VARCHAR));
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
