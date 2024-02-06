package joes;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

@SpringBootApplication
public class Main {
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  JdbcClient blogClient (DataSource blogDataSource) {
    return JdbcClient.create(blogDataSource);
  }

  @Bean
  JdbcClient subscriberClient (DataSource subscriberDataSource) {
    return JdbcClient.create(subscriberDataSource);
  }


  @Bean
  ApplicationRunner applicationRunner (JdbcClient blogClient, JdbcClient subscriberClient) {
    return args -> {
      System.out.println(blogClient.sql("SELECT * from blog").query(Blog.class).list());
      System.out.println(subscriberClient.sql("SELECT * from subscriber").query(Subscriber.class).list());
    };
  }
}
