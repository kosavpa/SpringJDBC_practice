package basic_package.DAOs;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertPerson extends SqlUpdate {
    private static final String SQL = "insert into person (first_name, last_name, birth_date) values (:firstName, :lastName, :birthDate)";

    public InsertPerson(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        declareParameter(new SqlParameter("birthDate", Types.DATE));
        setGeneratedKeysColumnNames("id");
        setReturnGeneratedKeys(true);
    }
}
