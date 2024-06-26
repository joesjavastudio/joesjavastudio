package joes.java.studio;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.util.Assert;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@EnableCaching
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(CustomerRepository repo) {
        return args -> {
            Set<Order> set = Set.of(new Order(null, "order1"), new Order(null, "order2"));
            Profile p = new Profile(null, "user", "password");
            Customer c = repo.save(new Customer(2, "B", p, set, null));
//            repo.findAll().forEach(System.out::println);
//
//            var result1 = repo.findById(c.id()).get();
//
//            var result2 = repo.findById(c.id()).get();
//
//            Assert.state(result1 == result2, "these 2 should equal");
//
//            repo.save(new Customer(1, "B", p, set));
//            System.out.println("there should be sql statements");
//            result1 = repo.findById(1).get();
//            System.out.println("there should be NO sql statements");
//            result2 = repo.findById(1).get();

        };
    }

//    @Bean
//    ApplicationRunner pagination(CustomerRepository repo) {
//        return args -> {
//            Sort sort = Sort.by(Sort.Direction.ASC, "name");
//            Pageable pageable = PageRequest.of(0, 2, sort);
//            Page<Customer> page = repo.findAll(pageable);
//            System.out.println(page.getContent());
//        };
//    }

}

@Table("customer_orders")
record Order(@Id Integer id, String name) {}

@Table("customer_profile")
record Profile(@Id Integer id, String username, String password) {}

record Customer(@Id Integer id, String name, Profile p, Set<Order> orders, @Version Integer version) {}
interface CustomerRepository extends ListCrudRepository<Customer, Integer> {
    String CUSTOMER_CACHE_NAME = "customer";

    Page<Customer> findAll(Pageable pageable);

    @Override
    @Cacheable(CUSTOMER_CACHE_NAME)
    Optional<Customer> findById(Integer id);

    @Override
    @CacheEvict(value = CUSTOMER_CACHE_NAME, key = "#result.id")
    <S extends Customer> S save(S entity);
}



