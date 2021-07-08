package com.nicat.bookpay.service.serviceImpl;

import com.nicat.bookpay.entity.BookMenuItem;
import com.nicat.bookpay.entity.Booking;
import com.nicat.bookpay.entity.MenuCourseItem;
import com.nicat.bookpay.enums.EnumAvaibleStatus;
import com.nicat.bookpay.exception.ExceptionConstants;
import com.nicat.bookpay.repostory.BookingDao;
import com.nicat.bookpay.repostory.BookingMenuItemDao;
import com.nicat.bookpay.repostory.MenuCourseItemDao;
import com.nicat.bookpay.request.ReqBookMenuItem;
import com.nicat.bookpay.response.RespBookMenuItem;
import com.nicat.bookpay.response.RespBookMenuItemList;
import com.nicat.bookpay.response.RespStatus;
import com.nicat.bookpay.service.BookMenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookItemServiceImpl implements BookMenuItemService {
    @Autowired
    private BookingMenuItemDao bookingMenuItemDao;
    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private MenuCourseItemDao menuCourseItemDao;

    @Override
    public RespBookMenuItemList getBookMenuItemList() {
        RespBookMenuItemList response = new RespBookMenuItemList();
        try{
            List<RespBookMenuItem> respBookMenuItemList = new ArrayList<>();
            List<BookMenuItem> bookMenuItems = bookingMenuItemDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (bookMenuItems.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstants.BOOKMENUITEM_NOT_FOUND,"Book menu item not found"));
                return response;
            }for (BookMenuItem bookMenuItem : bookMenuItems){
                RespBookMenuItem respBookMenuItem = getBookMenuItemById(bookMenuItem.getMenuItemId());
                respBookMenuItemList.add(respBookMenuItem);
            }
            response.setRespBookMenuItemList(respBookMenuItemList);
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespBookMenuItem getBookMenuItemById(Long bookMenuItemId) {
        RespBookMenuItem response = new RespBookMenuItem();
        try{
            if (bookMenuItemId == null){
                response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            }
            BookMenuItem bookMenuItem = bookingMenuItemDao.findBookMenuItemByMenuItemIdAndActive(bookMenuItemId, EnumAvaibleStatus.ACTIVE.getValue());
            if (bookMenuItem==null){
                response.setStatus(new RespStatus(ExceptionConstants.BOOKMENUITEM_NOT_FOUND,"BookMenuItem Not Found"));
            }
            response.setRespBookMenuId(bookMenuItemId);
            response.setRespbooking(bookMenuItem.getBooking());
            response.setRespMenuCourseItem(bookMenuItem.getMenuCourseItem());
            response.setActive(bookMenuItem.getActive());
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
        }
        return response;
    }

    @Override
    public RespStatus addBookMenuItem(ReqBookMenuItem reqBookMenuItem) {
        RespStatus status = null;
        try{
            Long bookMenuId = reqBookMenuItem.getReqBookMenuItemId();
            Long bookingId = reqBookMenuItem.getReqBookId();
            Long menuCourseId = reqBookMenuItem.getReqMenuCourseId();

            if(bookingId ==  null || menuCourseId ==null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Ivalid Request data");
            }
            Booking booking = bookingDao.findBookingByBookingIdAndActive(bookingId,EnumAvaibleStatus.ACTIVE.getValue());
            if (booking == null){
                return new RespStatus(ExceptionConstants.BOOKING_NOT_FOUND,"Booking not found");
            }
            MenuCourseItem menuCourseItem = menuCourseItemDao.findMenuCourseItemByCourseItemIdAndActive(menuCourseId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuCourseItem == null){
                return new RespStatus(ExceptionConstants.MENUCOURSEITEM_NOT_FOUND,"Menu course item not found");
            }
            BookMenuItem bookMenuItem = new BookMenuItem();
            bookMenuItem.setBooking(booking);
            bookMenuItem.setMenuCourseItem(menuCourseItem);
            bookingMenuItemDao.save(bookMenuItem);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus updateBookMenuItem(ReqBookMenuItem reqBookMenuItem) {
        RespStatus status = null;
        try {
            Long bookMenuItemId = reqBookMenuItem.getReqBookMenuItemId();
            Long bookingId = reqBookMenuItem.getReqBookId();
            Long menuCourseId = reqBookMenuItem.getReqMenuCourseId();
            if(bookMenuItemId ==  null || menuCourseId ==null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Ivalid Request data");
            }
            BookMenuItem bookMenuItem = bookingMenuItemDao.findBookMenuItemByMenuItemIdAndActive(bookMenuItemId,EnumAvaibleStatus.ACTIVE.getValue());
            if (bookMenuItem == null){
                return new RespStatus(ExceptionConstants.BOOKMENUITEM_NOT_FOUND,"Book menu item not found");
            }
            Booking booking1 = bookingDao.findBookingByBookingIdAndActive(bookingId,EnumAvaibleStatus.ACTIVE.getValue());
            if (booking1 == null){
                return new RespStatus(ExceptionConstants.BOOKING_NOT_FOUND,"Booking not found");
            }
            MenuCourseItem menuCourseItem = menuCourseItemDao.findMenuCourseItemByCourseItemIdAndActive(menuCourseId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuCourseItem == null){
                return new RespStatus(ExceptionConstants.MENUCOURSEITEM_NOT_FOUND,"Menu course item not found");
            }
            bookMenuItem.setBooking(booking1);
            bookMenuItem.setMenuCourseItem(menuCourseItem);
            bookingMenuItemDao.save(bookMenuItem);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus deleteBookMenuItem(Long bookMenuItemId) {
        RespStatus status = null;
        try{
            if (bookMenuItemId == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request data");
            }
            BookMenuItem bookMenuItem = bookingMenuItemDao.findBookMenuItemByMenuItemIdAndActive(bookMenuItemId,EnumAvaibleStatus.ACTIVE.getValue());
            if (bookMenuItem == null){
                return new RespStatus(ExceptionConstants.BOOKMENUITEM_NOT_FOUND,"Book menu item not found");
            }
            bookMenuItem.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            bookingMenuItemDao.save(bookMenuItem);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }
}
