package com.company.carshowroomssimulatorspring.controller;

import com.company.carshowroomssimulatorspring.exception.ResourceNotFoundException;
import com.company.carshowroomssimulatorspring.model.CarShowroom;
import com.company.carshowroomssimulatorspring.model.Rating;
import com.company.carshowroomssimulatorspring.model.Vehicle;
import com.company.carshowroomssimulatorspring.repository.CarShowroomRepository;
import com.company.carshowroomssimulatorspring.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingController {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    CarShowroomRepository carShowroomRepository;

    //get all reviews
    @GetMapping("/rating")
    public List<Rating> getAllCars(){
        return ratingRepository.findAll();
    }

    // post a new rating to selected shoroom
    @PostMapping("/rating")
    public Rating addRating(@Valid @RequestBody Rating rating){

        long showroomId = rating.getShowroomId();

        CarShowroom carShowroom = carShowroomRepository.findById(showroomId)
                .orElseThrow(() -> new ResourceNotFoundException("CarShowroom", "id", showroomId));
        rating.setShowroom(carShowroom);
        return ratingRepository.save(rating);
    }
}
