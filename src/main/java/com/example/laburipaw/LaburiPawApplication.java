package com.example.laburipaw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LaburiPawApplication {
    public static void main(String[] args) {
        SpringApplication.run(LaburiPawApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(ProdusRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Produs("JackD", 1));
            repository.save(new Produs("Chips", 10));
            repository.save(new Produs("kafea", 1000));
            repository.save(new Produs("David", 10));
            repository.save(new Produs("Michelle", 2));

            // fetch all customers
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            repository.findAll().forEach(customer -> {
                System.out.println(customer.toString());
            });
            System.out.println("");

            repository.deleteAll();

            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            repository.findAll().forEach(customer -> {
                System.out.println(customer.toString());
            });
            System.out.println("");

            repository.save(new Produs("JackD", 2));
            Produs produsToUpdate=repository.findAll().iterator().next();
            produsToUpdate.setCost(10);
            produsToUpdate.setNume("Moneeeey");
            repository.save(produsToUpdate);

            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            repository.findAll().forEach(customer -> {
                System.out.println(customer.toString());
            });
            System.out.println("");
//            // fetch an individual customer by ID
//            Produs customer = repository.findById(1L);
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(customer.toString());
//            log.info("");
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByLastName("Bauer").forEach(bauer -> {
//                log.info(bauer.toString());
//            });
//            log.info("");
        };
    }

}
