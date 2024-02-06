package joes;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("datasource.blog")
    DataSourceProperties blogDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    DataSource blogDataSource(DataSourceProperties blogDataSourceProperties) {
        return DataSourceBuilder.create()
        .url(blogDataSourceProperties.determineUrl())
        .username(blogDataSourceProperties.determineUsername())
        .password(blogDataSourceProperties.determinePassword())
        .build();
    }

    @Bean
    DataSourceScriptDatabaseInitializer blogDataSourceScriptDatabaseInitializer(DataSource blogDataSource) {
        var settings = new DatabaseInitializationSettings();
        settings.setSchemaLocations(List.of("classpath:blog-schema.sql"));
        return new DataSourceScriptDatabaseInitializer(blogDataSource, settings);
    }

    @Bean
    @ConfigurationProperties("datasource.subscriber")
    DataSourceProperties subscriberDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    DataSource subscriberDataSource(DataSourceProperties subscriberDataSourceProperties) {
        return DataSourceBuilder.create()
                .url(subscriberDataSourceProperties.determineUrl())
                .username(subscriberDataSourceProperties.determineUsername())
                .password(subscriberDataSourceProperties.determinePassword())
                .build();
    }

    @Bean
    DataSourceScriptDatabaseInitializer subscriberDataSourceScriptDatabaseInitializer(DataSource subscriberDataSource) {
        var settings = new DatabaseInitializationSettings();
        settings.setSchemaLocations(List.of("classpath:subscriber-schema.sql"));
        return new DataSourceScriptDatabaseInitializer(subscriberDataSource, settings);
    }
}
