package uz.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.util.Properties;
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    public static void main(String[] args) {
        if (isStart()) {
            SpringApplication.run(UserServiceApplication.class, args);
        }
    }
    public static boolean isStart() {
        Properties props = new Properties();
        try {
            props.load(new ClassPathResource("/application.properties").getInputStream());
            if (props.getProperty("spring.jdbc.hibernate.ddl-auto").equals("update")) {
                return true;
            } else {
                String confirm=null;
             confirm = JOptionPane.showInputDialog("Ma'lumotlarni o'chirib yuborma! Keyin bilmay qoldim dema !!!");
                if (confirm != null && confirm.equals("jokha"))
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
