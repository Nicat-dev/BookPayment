package com.nicat.bookpay.controller;

import com.nicat.bookpay.request.ReqBookMenuItem;
import com.nicat.bookpay.response.*;
import com.nicat.bookpay.service.BookMenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/bookingMenuItem")
public class BookingMenuItemController {
    @Autowired
    private BookMenuItemService bookMenuItemService;

    @GetMapping(value = "/getbookingMenuItemList")
    public RespBookMenuItemList getbookingMenuItemList(){
        return bookMenuItemService.getBookMenuItemList();
    }

    @RequestMapping(value ="/getBookingMenuItemById/{bookingId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespBookMenuItem getBookingMenuItemById(@PathVariable("bookingId") Long bookingId){
        return bookMenuItemService.getBookMenuItemById(bookingId);
    }

    @PostMapping(value = "/addBookingMenuItem")
    public RespStatus addBookingMenuItem(@RequestBody ReqBookMenuItem reqBookMenuItem){
        return bookMenuItemService.addBookMenuItem(reqBookMenuItem);
    }

    @PostMapping(value = "/updateBookingMenuItem")
    public RespStatus updateBookingMenuItem(@RequestBody ReqBookMenuItem reqBookMenuItem){
        return bookMenuItemService.updateBookMenuItem(reqBookMenuItem);
    }

    @PostMapping(value = "/deleteBookingMenuItem")
    public RespStatus deleteBookingMenuItem(@PathParam("bookingId") Long bookingId){
        return bookMenuItemService.deleteBookMenuItem(bookingId);
    }
}
