package com.nicat.bookpay.service.serviceImpl;

import com.nicat.bookpay.entity.Booking;
import com.nicat.bookpay.entity.Chef;
import com.nicat.bookpay.entity.Customer;
import com.nicat.bookpay.entity.Menu;
import com.nicat.bookpay.enums.EnumAvaibleStatus;
import com.nicat.bookpay.exception.ExceptionConstants;
import com.nicat.bookpay.repostory.BookingDao;
import com.nicat.bookpay.repostory.ChefDao;
import com.nicat.bookpay.repostory.CustomerDao;
import com.nicat.bookpay.repostory.MenuDao;
import com.nicat.bookpay.request.ReqBooking;
import com.nicat.bookpay.response.RespBooking;
import com.nicat.bookpay.response.RespBookingList;
import com.nicat.bookpay.response.RespStatus;
import com.nicat.bookpay.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ChefDao chefDao;

    @Autowired
    private CustomerDao customerDao;


    @Override
    public RespBookingList getBookingList() {
        RespBookingList response = new RespBookingList();
        try {
            List<RespBooking> respBookingList = new ArrayList<>();
            List<Booking> bookList = bookingDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (bookList.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstants.BOOKING_NOT_FOUND,"Booking not found"));
                return response;
            }for (Booking booking : bookList){
                RespBooking respBooking = getBookingById(booking.getBookingId());
                respBookingList.add(respBooking);
            }
            response.setRespBookinList(respBookingList);
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public RespBooking getBookingById(Long bookingId) {
        RespBooking response = new RespBooking();
        try{
            if (bookingId == null){
                response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
                return response;
            }
            Booking booking = bookingDao.findBookingByBookingIdAndActive(bookingId,EnumAvaibleStatus.ACTIVE.getValue());
            if (booking == null){
                response.setStatus(new RespStatus(ExceptionConstants.BOOKING_NOT_FOUND,"Booking not found"));
            }
            response.setRespBookingId(bookingId);
            response.setRespActive(booking.getActive());
            response.setRespChef(booking.getChef());
            response.setRespCustomer(booking.getCustomer());
            response.setRespMenu(booking.getMenu());
            response.setRespDataDate(booking.getDataDate());
            response.setRespTotalPrice(booking.getTotalPrice());
            response.setStatus(RespStatus.getSuccesMessage());

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespStatus addBooking(ReqBooking reqBooking) {
        RespStatus status = null;
        try{
            Long chefId = reqBooking.getReqChefId();
            Long menuId = reqBooking.getReqMenuId();
            Long customerId = reqBooking.getReqCustomerId();
            Long totalPrices = reqBooking.getReqTotalPrice();
            if (chefId == null || menuId ==null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request exception");
            }
            Chef chef = chefDao.findChefByChefIdAndActive(chefId,EnumAvaibleStatus.ACTIVE.getValue());
            if (chef==null){
                return new RespStatus(ExceptionConstants.CHEF_NOT_FOUND,"Chef not found");
            }
            Menu menu = menuDao.findMenuByMenuIdAndActive(menuId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menu == null){
                return new RespStatus(ExceptionConstants.MENU_NOT_FOUND,"Menu not found");
            }
            Customer customer = customerDao.findCustomerByCustomerIdAndActive(customerId,EnumAvaibleStatus.ACTIVE.getValue());
            if (customer == null){
                return new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND,"Customer not found");
            }
            Booking booking = new Booking();
            booking.setTotalPrice(totalPrices);
            booking.setChef(chef);
            booking.setCustomer(customer);
            booking.setMenu(menu);
            bookingDao.save(booking);
            status = RespStatus.getSuccesMessage();

        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus updateBooking(ReqBooking reqBooking) {
        RespStatus status;
        try {
            Long bookingId = reqBooking.getReqBookingId();
            Long totalPrice = reqBooking.getReqTotalPrice();
            if (bookingId == null || totalPrice == null) {
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION, "Invalid Request Exception");
            }
            Booking booking = bookingDao.findBookingByBookingIdAndActive(bookingId, EnumAvaibleStatus.ACTIVE.getValue());
            if (booking == null) {
                return new RespStatus(ExceptionConstants.BOOKING_NOT_FOUND, "Booking not found");
            }
            booking.setTotalPrice(totalPrice);
            bookingDao.save(booking);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Intertnal Exception");

        }
        return status;
    }

    @Override
    public RespStatus deleteBooking(Long bookingId) {
        RespStatus status = null;
        try{
            if (bookingId==null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid request Exception");
            }
            Booking booking = bookingDao.findBookingByBookingIdAndActive(bookingId,EnumAvaibleStatus.ACTIVE.getValue());
            if (booking==null){
                return new RespStatus(ExceptionConstants.BOOKING_NOT_FOUND,"Booking not found");
            }
            booking.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            bookingDao.save(booking);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }
}
