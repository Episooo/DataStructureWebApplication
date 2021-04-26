package cn.episooo.datastructurewebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "cn.episooo.datastructurewebapplication.dao"
)
public class DataStructureWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataStructureWebApplication.class, args);
    }

}
