package com.company.carshowroomssimulatorspring.repository;

import com.company.carshowroomssimulatorspring.model.CarShowroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarShowroomRepository extends JpaRepository<CarShowroom, Long> {

}
