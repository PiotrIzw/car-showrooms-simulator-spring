package com.company.carshowroomssimulatorspring.repository;

import com.company.carshowroomssimulatorspring.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
