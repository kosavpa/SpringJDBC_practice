package basic_package;


import basic_package.DAOs.DataSourceLoader;
import basic_package.DAOs.MainDAO_layer;
import basic_package.entities.Animal;
import basic_package.entities.Person;
import com.mysql.cj.result.LocalDateValueFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.util.*;


@ComponentScan(basePackages = "basic_package")
public class MainClass {
    public static void main(String[] args) throws ParseException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainClass.class);
//        Date.from(LocalDate.of(1992,07,22).atStartOfDay().toInstant(OffsetTime.now().getOffset()));
        System.out.println(ctx.getBean("dao",MainDAO_layer.class)
                                .insertPersonExcludeAnimal(new Person(
                                                            "Draike",
                                                            "Macchiken",
                                                            new java.sql.Date(new GregorianCalendar(1988, 8,11).getTimeInMillis()))));
    }
}
