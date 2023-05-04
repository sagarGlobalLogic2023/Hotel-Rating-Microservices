package com.rating.user.client;

import com.rating.user.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "RATING-SERVICE")
@Service
public interface RatingService {

    @PostMapping("/api/v1/ratings")
    ResponseEntity<Rating> createRating(Rating values);
    @PutMapping("/api/v1/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, Rating rating);
    @DeleteMapping("/api/v1/ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);
}