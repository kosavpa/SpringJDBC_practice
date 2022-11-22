package basic_package.DAOs;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

import java.sql.Driver;


@Component("dataSource")
@PropertySource("classpath:Application.yml")
public class DataSourceLoader extends SimpleDriverDataSource {
    public DataSourceLoader(
            @Value("${driverClassName}") String driver,
            @Value("${url}") String url,
            @Value("${user_name}") String username,
            @Value("${password}") String password) {

        super();

        try {
            this.setDriverClass((Class<? extends Driver>) Class.forName(driver));
            this.setUrl(url);
            this.setUsername(username);
            this.setPassword(password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
