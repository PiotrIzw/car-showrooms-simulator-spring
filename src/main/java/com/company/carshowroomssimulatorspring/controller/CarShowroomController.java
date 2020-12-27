package com.company.carshowroomssimulatorspring.controller;

import com.company.carshowroomssimulatorspring.exception.ResourceNotFoundException;
import com.company.carshowroomssimulatorspring.model.CarShowroom;
import com.company.carshowroomssimulatorspring.model.ItemCondition;
import com.company.carshowroomssimulatorspring.model.Rating;
import com.company.carshowroomssimulatorspring.model.Vehicle;
import com.company.carshowroomssimulatorspring.repository.CarShowroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarShowroomController {

    @Autowired
    CarShowroomRepository carShowroomRepository;

    //showing all showrooms
    @GetMapping("/fulfillment")
    public List<CarShowroom> getAllShowrooms(){
        return carShowroomRepository.findAll();
    }

    //getting average rating of showroom
    @GetMapping("/fulfillment/{id}/rating")
    public double getAverageShowroomRating(@PathVariable(value = "id") Long showroomId){
        CarShowroom carShowroom = carShowroomRepository.findById(showroomId)
                .orElseThrow(() -> new ResourceNotFoundException("CarShowroom", "id", showroomId));
        List<Rating> rating = carShowroom.getRating();
        double avgRating = 0;
        for(Rating r : rating){
            avgRating += r.getRating().ordinal() + 1;
        }
        avgRating /= rating.size();
        return avgRating;
    }


    //adding a new showroom
    @PostMapping("/fulfillment")
    public CarShowroom addShowroom(@Valid @RequestBody CarShowroom carShowroom){
        List<Vehicle> carList = new ArrayList<>();
        carShowroom.setCarList(carList);
        //TODO
        return carShowroomRepository.save(carShowroom);
    }

    //deleting a showroom
    @DeleteMapping("/fulfillment/{id}")
    public ResponseEntity<?> removeShowroom(@PathVariable(value = "id") Long showroomId){
        CarShowroom carShowroom = carShowroomRepository.findById(showroomId)
                .orElseThrow(() -> new ResourceNotFoundException("CarShowroom", "id", showroomId));
        carShowroomRepository.delete(carShowroom);
        return ResponseEntity.ok().build();
    }

    //getting all cars of selected showroom
    @GetMapping("/fulfillment/{id}/products")
    public List<Vehicle> findCarsInShowroom(@PathVariable(value = "id") Long showroomId){
        CarShowroom carShowroom = carShowroomRepository.findById(showroomId)
                .orElseThrow(() -> new ResourceNotFoundException("CarShowroom", "id", showroomId));

        return carShowroom.getCarList();
    }

    //get percentage showroom filling
    @GetMapping("/fulfillment/{id}/fill")
    public double getPercentageShowroomFill(@PathVariable(value = "id") Long showroomId){
        CarShowroom carShowroom = carShowroomRepository.findById(showroomId)
                .orElseThrow(() -> new ResourceNotFoundException("CarShowroom", "id", showroomId));

        int showroomCapacity = carShowroom.getMaxShowroomCapacity();
        int carsAmount = carShowroom.getCarList().size();
        System.out.println(showroomCapacity + " " + carsAmount);
        if(showroomCapacity != 0){
            if(carsAmount != 0){
                return ((double)carsAmount / (double)showroomCapacity) * 100;
            }
            return 100;
        }

        return -1;
    }
}
