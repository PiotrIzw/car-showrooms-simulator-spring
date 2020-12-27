package com.company.carshowroomssimulatorspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@EnableJpaAuditing
@SpringBootApplication
public class CarShowroomsSimulatorSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarShowroomsSimulatorSpringApplication.class, args);
    }
}
