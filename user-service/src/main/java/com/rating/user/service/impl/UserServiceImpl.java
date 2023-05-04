package com.rating.user.service.impl;

import com.rating.user.client.HotelService;
import com.rating.user.entity.Hotel;
import com.rating.user.entity.Rating;
import com.rating.user.entity.User;
import com.rating.user.exception.ResourceNotFoundException;
import com.rating.user.repository.UserRepository;
import com.rating.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, HotelService hotelService) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
    }

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server." + id));

        Rating[] ratingsResponse = restTemplate.getForObject("http://RATING-SERVICE/api/v1/ratings/users/" + user.getId(), Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingsResponse).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}
