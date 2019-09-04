package com.example.demo.service;


import com.example.demo.entity.Review;
import com.example.demo.entity.Status;
import com.example.demo.message.ResponseMessage;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.StatusRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private StatusRepository statusRepository;

    private static ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    //ลบข้อมูล

    public static boolean deleteReview(Long reviewID) {
        try {
            reviewRepository.deleteById(reviewID);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }


    //    public Review createReview(Review review) {
//        return reviewRepository.save(review);
//    }


//    //อัพเดทข้อข้อมูล
//    public Optional<Review> updateReview(Review updateReview,
//                                           Long statusID,
//                                           Long reviewID,
//                                          Status status) {
//        status = statusRepository.findById(statusID).get(); //เตรียม object มา join
//        updateReview.setStatus(status);
//        reviewRepository.save(updateReview);
//        return Optional.of(reviewRepository.save(updateReview));

//    }
}
