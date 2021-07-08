package com.nicat.bookpay.controller;

import com.nicat.bookpay.request.ReqBooking;
import com.nicat.bookpay.response.*;
import com.nicat.bookpay.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping(value = "/getBookingList")
    public RespBookingList getBookingList(){
        return bookingService.getBookingList();
    }

    @RequestMapping(value ="/getBookingById/{bookingId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespBooking getBookingById(@PathVariable("bookingId") Long bookingId){
        return bookingService.getBookingById(bookingId);
    }

    @PostMapping(value = "/addBooking")
    public RespStatus addBooking(@RequestBody ReqBooking reqBooking){
        return bookingService.addBooking(reqBooking);
    }

    @PostMapping(value = "/updateBooking")
    public RespStatus updateBooking(@RequestBody ReqBooking reqBooking){
        return bookingService.updateBooking(reqBooking);
    }

    @PostMapping(value = "/deleteBooking")
    public RespStatus deleteBooking(@PathParam("bookingId") Long bookingId){
        return bookingService.deleteBooking(bookingId);
    }
}
