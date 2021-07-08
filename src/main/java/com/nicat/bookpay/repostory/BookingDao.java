package com.nicat.bookpay.repostory;

import com.nicat.bookpay.entity.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingDao extends CrudRepository<Booking,Long> {
    List<Booking> findAllByActive(Integer active);
    Booking findBookingByBookingIdAndActive(Long id,Integer active);
}
