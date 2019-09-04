package com.example.demo.controller;
import com.example.demo.entity.*;
import com.example.demo.message.ResponseMessage;
import com.example.demo.repository.*;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {




    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CarDataRepository carDataRepository;

    @Autowired
    private CarColorRepository carColorRepository;

    @Autowired
    private CarTypeRepository carTypeRepository;

    /* ======================================================= */

    @GetMapping(path = "/carData")
    public Collection<CarData> getCarData(){
        return carDataRepository.findAll().stream().collect(Collectors.toList());

    }

    /* ======================================================= */

    @GetMapping("/carColors")
    public Collection<CarColor> carColor() {
        return carColorRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    /* ======================================================= */

    @GetMapping("/review")
    public Collection<Review> Review() {
        return reviewRepository.findAll().stream().collect(Collectors.toList());
    }


    /* ======================================================= */

    @GetMapping(path = "/status")
    public Collection<Status> getStatus() {
        Collection<Status> statusC = statusRepository.findAll().stream().collect(Collectors.toList());
        Logger logger = LoggerFactory.getLogger("fileLogger");
        logger.debug(statusC.toString());
        return statusC;
    }

    /* ======================================================= */
    @PostMapping("/review/{carID}/{carColorID}/{statusID}/{carTypeID}")
    public ResponseEntity<?> newReviews(@RequestBody Review review,
                             @PathVariable Long carID ,
                             @PathVariable Long carTypeID,
                             @PathVariable Long carColorID ,
                             @PathVariable Long statusID,
                             CarColor carColor,
                             Status status,
                             CarType carType,
                             CarData carData){


        carType = carTypeRepository.findById(carTypeID).get();
        carData = carDataRepository.findById(carID).get();
        status = statusRepository.findById(statusID).get(); //เตรียม object มา join
        carColor = carColorRepository.findById(carColorID).get();

        review.setCarType(carType);
        review.setCarData(carData);
        review.setCarColor(carColor);
        review.setStatus(status);
        reviewRepository.save(review);
        return new ResponseEntity<>(new ResponseMessage("Save Review successfully!"), HttpStatus.OK);
    }

    /* ======================================================= */

    @DeleteMapping("review/{reviewID}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewID){
        if(!ReviewService.deleteReview(reviewID)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(new ResponseMessage("Delete Review successfully!"), HttpStatus.OK);
    }
    /* ======================================================= */

    @PutMapping("/review/update/{statusID}/{reviewID}")
    public ResponseEntity<?> updateReview(@RequestBody Review updateReview,
                               @PathVariable Long statusID,
                               @PathVariable Long reviewID,
                               Status status) {

        status = statusRepository.findById(statusID).get(); //เตรียม object มา join
        updateReview.setStatus(status);
        reviewRepository.save(updateReview);
        return new ResponseEntity<>(new ResponseMessage("Update Review successfully!"), HttpStatus.OK);

    }

    /* ======================================================= */


}
