package com.infy.moviebooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.infy.moviebooking.entity.MovieBooking;

public interface MovieBookingRepository extends CrudRepository<MovieBooking, Integer>{
	@Query("SELECT b FROM MovieBooking b Where b.customerPhoneNo =:c and b.showDate =:s")
List<MovieBooking>getBookingDetails(@Param("c")Long customerPhoneNo,@Param("s")LocalDate showDate);
List<MovieBooking>getBookingsByScreenName(String screenName);
}
