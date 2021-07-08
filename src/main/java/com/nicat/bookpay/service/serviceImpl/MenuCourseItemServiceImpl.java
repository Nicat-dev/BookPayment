package com.nicat.bookpay.service.serviceImpl;

import com.nicat.bookpay.entity.MenuCourse;
import com.nicat.bookpay.entity.MenuCourseItem;
import com.nicat.bookpay.entity.MenuItem;
import com.nicat.bookpay.enums.EnumAvaibleStatus;
import com.nicat.bookpay.exception.ExceptionConstants;
import com.nicat.bookpay.repostory.MenuCourseDao;
import com.nicat.bookpay.repostory.MenuCourseItemDao;
import com.nicat.bookpay.repostory.MenuItemDao;
import com.nicat.bookpay.request.ReqMenuCourseItem;
import com.nicat.bookpay.response.RespMenuCourseItem;
import com.nicat.bookpay.response.RespMenuCourseItemList;
import com.nicat.bookpay.response.RespStatus;
import com.nicat.bookpay.service.MenuCourseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuCourseItemServiceImpl implements MenuCourseItemService {
    @Autowired
    private MenuCourseItemDao menuCourseItemDao;
    @Autowired
    private MenuCourseDao menuCourseDao;
    @Autowired
    private MenuItemDao menuItemDao;

    @Override
    public RespMenuCourseItemList getMenuCourseItemList() {
        RespMenuCourseItemList response = new RespMenuCourseItemList();
        try{
            List<RespMenuCourseItem> respMenuCourseItemList = new ArrayList<>();
            List<MenuCourseItem> menuCourseItemList = menuCourseItemDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (menuCourseItemList == null){
                response.setStatus(new RespStatus(ExceptionConstants.MENUCOURSEITEM_NOT_FOUND,"Menu course item not found"));
                return response;
            }for (MenuCourseItem menuCourseItem : menuCourseItemList){
                RespMenuCourseItem respMenuCourseItem = getMenuCourseItemById(menuCourseItem.getCourseItemId());
                respMenuCourseItemList.add(respMenuCourseItem);
            }
            response.setRespMenuCourseItemList(respMenuCourseItemList);
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespMenuCourseItem getMenuCourseItemById(Long menuItemId) {
        RespMenuCourseItem response = new RespMenuCourseItem();
        try{
            if (menuItemId == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request Data"));
                return response;
            }
            MenuCourseItem menuCourseItem = menuCourseItemDao.findMenuCourseItemByCourseItemIdAndActive(menuItemId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuCourseItem == null){
                response.setStatus(new RespStatus(ExceptionConstants.MENUCOURSEITEM_NOT_FOUND,"MenuCourseItem not found"));
                return response;
            }
            response.setRespCourseItemId(menuCourseItem.getCourseItemId());
            response.setRespCourse(menuCourseItem.getCourse());
            response.setRespMenuItem(menuCourseItem.getMenuItem());
            response.setRespActive(menuCourseItem.getActive());
            response.setRespDataDate(menuCourseItem.getDateData());
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespStatus addMenuCourseItem(ReqMenuCourseItem reqMenuCourseItem) {
        RespStatus status = null;
        try{
            Long courseId = reqMenuCourseItem.getReqCourseId();
            Long menuItemId = reqMenuCourseItem.getReqMenuItem();
            if (courseId == null || menuItemId == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request");
            }
            MenuCourse course = menuCourseDao.findMenuCourseByCourseIdAndActive(courseId,EnumAvaibleStatus.ACTIVE.getValue());
            if (course == null){
                return  new RespStatus(ExceptionConstants.MENUCOURSE_NOT_FOUND,"Menu Course not found");
            }
            MenuItem menuItem = menuItemDao.findMenuItemByItemIdAndActive(menuItemId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuItem == null){
                return new RespStatus(ExceptionConstants.MENUITEM_NOT_FOUND,"Menu Item not found");
            }
            MenuCourseItem menuCourseItem = new MenuCourseItem();
            menuCourseItem.setCourse(course);
            menuCourseItem.setMenuItem(menuItem);
            status = RespStatus.getSuccesMessage();
            menuCourseItemDao.save(menuCourseItem);

        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus updateMenuCourseItem(ReqMenuCourseItem reqMenuCourseItem) {
        RespStatus status = null;
        try {
            Long courseItemId = reqMenuCourseItem.getReqCourseItemId();
            Long courseId = reqMenuCourseItem.getReqCourseId();
            Long menuCourseId = reqMenuCourseItem.getReqMenuItem();
            if (courseItemId==null || menuCourseId==null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Ivalid Requset");
            }
            MenuCourseItem menuCourseItem = menuCourseItemDao.findMenuCourseItemByCourseItemIdAndActive(courseItemId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuCourseItem == null){
                return new RespStatus(ExceptionConstants.MENUCOURSEITEM_NOT_FOUND,"menu course item not found");
            }
            MenuCourse course = menuCourseDao.findMenuCourseByCourseIdAndActive(courseId,EnumAvaibleStatus.ACTIVE.getValue());
            if (course == null){
                return new RespStatus(ExceptionConstants.MENUCOURSE_NOT_FOUND,"menu course not found");
            }
            MenuItem menuItem = menuItemDao.findMenuItemByItemIdAndActive(menuCourseId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuItem==null){
                return new RespStatus(ExceptionConstants.MENUITEM_NOT_FOUND,"menu item not found");
            }
            menuCourseItem.setCourse(course);
            menuCourseItem.setMenuItem(menuItem);
            menuCourseItemDao.save(menuCourseItem);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus deleteMenuItem(Long menuItemId) {
        RespStatus status = null;
        try{
            if (menuItemId == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request");
            }
            MenuCourseItem menuCourseItem = menuCourseItemDao.findMenuCourseItemByCourseItemIdAndActive(menuItemId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuCourseItem== null){
                return new RespStatus(ExceptionConstants.MENUCOURSEITEM_NOT_FOUND,"menu course not found");
            }
            menuCourseItem.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            menuCourseItemDao.save(menuCourseItem);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }
}
