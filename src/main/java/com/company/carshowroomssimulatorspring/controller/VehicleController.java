package com.company.carshowroomssimulatorspring.controller;

import com.company.carshowroomssimulatorspring.exception.ResourceNotFoundException;
import com.company.carshowroomssimulatorspring.model.utility.CSVFileWriter;
import com.company.carshowroomssimulatorspring.model.CarShowroom;
import com.company.carshowroomssimulatorspring.model.Vehicle;
import com.company.carshowroomssimulatorspring.repository.CarShowroomRepository;
import com.company.carshowroomssimulatorspring.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    CarShowroomRepository carShowroomRepository;

    //showing all cars
    @GetMapping("/product")
    public List<Vehicle> getAllCars(){
        return vehicleRepository.findAll();
    }

    //adding a new car
    @PostMapping("/product")
    public Vehicle addCar(@Valid @RequestBody Vehicle vehicle){
        long showroomId = vehicle.getShowroomId();
        CarShowroom carShowroom = carShowroomRepository.findById(showroomId).orElseThrow(() -> new ResourceNotFoundException("CarShowroom", "id", showroomId));

        if(carShowroom.getCarList().size() < carShowroom.getMaxShowroomCapacity()) {
            carShowroom.getCarList().add(vehicle);
            return vehicleRepository.save(vehicle);
        }
        else System.err.println("Showroom is full!");
        return null;
    }

    //deleting a car
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> removeCar(@PathVariable(value = "id") Long vehicleId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", vehicleId));
        vehicleRepository.delete(vehicle);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/csv")
    public List<Vehicle> saveAllCars(){
        List<Vehicle> allCars = vehicleRepository.findAll();
        CSVFileWriter.writeCsvFile(allCars, "allcars.csv");
        return allCars;

    }
}
