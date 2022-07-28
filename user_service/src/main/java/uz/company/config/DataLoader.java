package uz.company.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.swing.*;
import java.util.Properties;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final DataSource dataSource;
    @Value(value = "${spring.jdbc.hibernate.ddl-auto}")
    private String ddl;
    @Override
    public void run(String... args) throws Exception {
        if (ddl!=null&&ddl.equals("create")){
                Resource resource = new ClassPathResource("schema.sql");
                ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
                databasePopulator.execute(dataSource);
        }
    }

}
