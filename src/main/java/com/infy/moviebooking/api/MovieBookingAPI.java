package com.infy.moviebooking.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.moviebooking.dto.MovieBookingDTO;
import com.infy.moviebooking.exception.MovieBookingException;
import com.infy.moviebooking.service.MovieBookingService;
@RestController
@RequestMapping(value = "/api")
@Validated
public class MovieBookingAPI {
	@Autowired
	private MovieBookingService movieBookingService;
	@PostMapping(value = "/movie")
	public ResponseEntity<MovieBookingDTO> bookMovie(@Valid@RequestBody  MovieBookingDTO movieBookingDTO) throws MovieBookingException
	{
		MovieBookingDTO movies = movieBookingService.bookMovie(movieBookingDTO);
		return new ResponseEntity<MovieBookingDTO>(movies,HttpStatus.CREATED);
	}
	@GetMapping(value = "/movie/{screenName}")
	public ResponseEntity<List<MovieBookingDTO>>getBookingByScreenName(@PathVariable
			@Pattern(regexp = "(Sapphire|Turquoise|Rhombus)",
			message = "{bookmovie.screenname.invalid}")	String screenName) throws MovieBookingException
	{
		List<MovieBookingDTO> listOfMovie = movieBookingService.getBookingByScreenName(screenName);
		return new ResponseEntity<List<MovieBookingDTO>>(listOfMovie,HttpStatus.OK);
		
		
	}
	
	

}
