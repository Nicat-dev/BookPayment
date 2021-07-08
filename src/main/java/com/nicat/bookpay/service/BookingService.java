package com.nicat.bookpay.service;

import com.nicat.bookpay.request.ReqBooking;
import com.nicat.bookpay.response.RespBooking;
import com.nicat.bookpay.response.RespBookingList;
import com.nicat.bookpay.response.RespStatus;

public interface BookingService {
    RespBookingList getBookingList();
    RespBooking getBookingById(Long bookingId);
    RespStatus addBooking(ReqBooking reqBooking);
    RespStatus updateBooking(ReqBooking reqBooking);
    RespStatus deleteBooking(Long bookingId);
}
