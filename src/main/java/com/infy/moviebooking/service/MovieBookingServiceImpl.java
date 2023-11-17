package com.infy.moviebooking.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.moviebooking.dto.MovieBookingDTO;
import com.infy.moviebooking.entity.MovieBooking;
import com.infy.moviebooking.exception.MovieBookingException;
import com.infy.moviebooking.repository.MovieBookingRepository;
import com.infy.moviebooking.validator.MovieBookingValidator;
@Service(value = "movieBookingService")
@Transactional
public class MovieBookingServiceImpl implements MovieBookingService{
    
    @Autowired
    private MovieBookingRepository movierep;
	@Override
	public Double calculateBookingAmount(Integer noOfSeats, String screenName) {
		// TODO Auto-generated method stub
		Double amount=0.0;
		if(screenName.equals("Rhombus"))
		{
			amount=100.0*noOfSeats;
		}
		else
			if(screenName.equals("Sapphire"))
			{
				amount=200.0*noOfSeats;
			}
			else
				if(screenName.equals("Turquoise"))
				{
					amount=300.0*noOfSeats;
				}
		return amount;
	}

	@Override
	public MovieBookingDTO bookMovie(MovieBookingDTO movieBookingDTO) throws MovieBookingException {
		MovieBookingValidator.validate(movieBookingDTO);
		List<MovieBooking> bookingList = movierep.getBookingDetails(movieBookingDTO.getCustomerPhoneNo(),movieBookingDTO.getShowDate());
		if(!bookingList.isEmpty()) {
			throw new MovieBookingException("MovieBookingService.BOOKING_EXIST");
		}
		MovieBooking bookings = MovieBookingDTO.prepareEntity(movieBookingDTO); 
		calculateBookingAmount(bookings.getNoOfSeats(),bookings.getScreenName());
		bookings= movierep.save(bookings);
		MovieBookingDTO bookingDto = MovieBookingDTO.prepareDTO(bookings);
		return bookingDto;
		
	}

	@Override
	public List<MovieBookingDTO> getBookingByScreenName(String screenName) throws MovieBookingException {
		List<MovieBooking> movieBookings = movierep.getBookingsByScreenName(screenName);
		if(movieBookings.isEmpty()) {
			throw new MovieBookingException("MovieBookingService.NO_BOOKING");
		}
		List<MovieBookingDTO> listMovie = new ArrayList<>();
		movieBookings.forEach((m)->{
			MovieBookingDTO dtos = MovieBookingDTO.prepareDTO(m);
		listMovie.add(dtos);
		});
		return listMovie;
		
		
	}

}
