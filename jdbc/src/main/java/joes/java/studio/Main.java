package joes.java.studio;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;
import java.util.Set;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner (CustomerRepository repo) {
        return args -> {
            Set<Order> set = Set.of(new Order(null, "order1"), new Order(null, "order2"));
            Profile p = new Profile(null, "user", "password");
            Customer c = repo.save(new Customer(null, "A", p, set));
            repo.findAll().forEach(System.out::println);
        };
    }

}

@Table("customer_orders")
record Order(@Id Integer id, String name) {}

@Table("customer_profile")
record Profile(@Id Integer id, String username, String password) {}

record Customer(@Id Integer id, String name, Profile p, Set<Order> orders) {}
interface CustomerRepository extends ListCrudRepository<Customer, Integer> {

}



