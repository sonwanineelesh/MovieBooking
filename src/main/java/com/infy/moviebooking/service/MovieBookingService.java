package com.infy.moviebooking.service;

import java.util.List;

import com.infy.moviebooking.dto.MovieBookingDTO;
import com.infy.moviebooking.exception.MovieBookingException;

public interface MovieBookingService {
	Double calculateBookingAmount(Integer noOfSeats, String screenName);
	MovieBookingDTO bookMovie(MovieBookingDTO movieBookingDTO) throws MovieBookingException;
	List<MovieBookingDTO>getBookingByScreenName(String screenName) throws MovieBookingException;

}
