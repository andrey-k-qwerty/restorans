package graduation.raitrest.service;

import graduation.raitrest.TimingExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static graduation.raitrest.util.ValidationUtil.getRootCause;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
//@ExtendWith(TimingExtension.class)
@Sql(scripts = "classpath:db/dataDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ExtendWith(TimingExtension.class)
abstract public class AbstractServiceTest {

    @Autowired
    private Environment env;



    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
    <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
        assertThrows(exceptionClass, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw getRootCause(e);
            }
        });
    }
}