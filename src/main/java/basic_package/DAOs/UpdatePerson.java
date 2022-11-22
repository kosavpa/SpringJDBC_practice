package basic_package.DAOs;


import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;


public class UpdatePerson extends SqlUpdate {
    private static final String SQL = "update person " +
            "set first_name = :firstName, " +
            "last_name = :lastName, " +
            "birth_date = :birthDate " +
            "where id = :id";

    public UpdatePerson(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        declareParameter(new SqlParameter("birthDate", Types.VARCHAR));
        declareParameter(new SqlParameter("id", Types.VARCHAR));
    }
}
