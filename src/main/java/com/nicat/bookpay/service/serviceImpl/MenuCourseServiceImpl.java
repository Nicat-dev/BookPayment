package com.nicat.bookpay.service.serviceImpl;

import com.nicat.bookpay.entity.Menu;
import com.nicat.bookpay.entity.MenuCourse;
import com.nicat.bookpay.enums.EnumAvaibleStatus;
import com.nicat.bookpay.exception.ExceptionConstants;
import com.nicat.bookpay.repostory.MenuCourseDao;
import com.nicat.bookpay.repostory.MenuDao;
import com.nicat.bookpay.request.ReqMenuCourse;
import com.nicat.bookpay.response.*;
import com.nicat.bookpay.service.MenuCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuCourseServiceImpl implements MenuCourseService {
    @Autowired
    private MenuCourseDao menuCourseDao;
    @Autowired
    private MenuDao menuDao;

    @Override
    public RespMenuCourseList getMenuCourseList() {
        RespMenuCourseList response = new RespMenuCourseList();
        try{
            List<RespMenuCourse> respMenuCourseList = new ArrayList<>();
            List<MenuCourse> menuCourseList = menuCourseDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (menuCourseList.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstants.MENUCOURSE_NOT_FOUND,"Menu course not found"));
                return response;
            }for (MenuCourse menuCourse : menuCourseList){
                RespMenuCourse respMenuCourse = getMenuCourseById(menuCourse.getCourseId());
                respMenuCourseList.add(respMenuCourse);
            }
            response.setRespMenuCourseList(respMenuCourseList);
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
        }
        return response;
    }

    @Override
    public RespMenuCourse getMenuCourseById(Long menuCourseId) {
        RespMenuCourse response = new RespMenuCourse();
        try{
            if (menuCourseId == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request Data"));
                return response;
            }
            MenuCourse menuCourse = menuCourseDao.findMenuCourseByCourseIdAndActive(menuCourseId, EnumAvaibleStatus.ACTIVE.getValue());
            if (menuCourse == null){
                response.setStatus(new RespStatus(ExceptionConstants.MENUCOURSE_NOT_FOUND,"Menu Course not found"));
            }
            response.setRespCourseId(menuCourse.getCourseId());
            response.setRespName(menuCourse.getName());
            response.setRespMenu(menuCourse.getMenu());
            response.setActive(menuCourse.getActive());
            response.setRespPrice(menuCourse.getPrice());
            response.setRespDataDate(menuCourse.getCourseDate());
            response.setStatus(RespStatus.getSuccesMessage());

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespStatus addMenuCourse(ReqMenuCourse reqMenuCourse) {
        RespStatus status = null;
        try {
            String name = reqMenuCourse.getReqName();
            Long menuId = reqMenuCourse.getReqMenu();
            Long price = reqMenuCourse.getReqPrice();
            if (menuId == null || name == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Ivalid request data");
            }
            Menu menu = menuDao.findMenuByMenuIdAndActive(menuId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menu == null){
                return new RespStatus(ExceptionConstants.MENU_NOT_FOUND,"menu not found");
            }
            MenuCourse menuCourse = new MenuCourse();
            menuCourse.setMenu(menu);
            menuCourse.setName(name);
            menuCourse.setPrice(price);
            menuCourseDao.save(menuCourse);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus updateMenuCourse(ReqMenuCourse reqMenuCourse) {
        RespStatus status = null;
        try{
            Long id = reqMenuCourse.getReqMenuCourseId();
            String name = reqMenuCourse.getReqName();
            Long menuId = reqMenuCourse.getReqMenu();
            Long price = reqMenuCourse.getReqPrice();
            if (id == null || name == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Ivalid request data");
            }
            MenuCourse menuCourse = menuCourseDao.findMenuCourseByCourseIdAndActive(id,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuCourse == null){
                return new RespStatus(ExceptionConstants.MENUCOURSE_NOT_FOUND,"Menu Course not found");
            }
            Menu menu = menuDao.findMenuByMenuIdAndActive(menuId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menu == null){
                return new RespStatus(ExceptionConstants.MENU_NOT_FOUND,"menu not found");
            }
            menuCourse.setMenu(menu);
            menuCourse.setName(name);
            menuCourse.setPrice(price);
            menuCourseDao.save(menuCourse);
            status = RespStatus.getSuccesMessage();

        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus deleteMenuCourse(Long menuCourseId) {
        RespStatus status = null;
        try{
            if (menuCourseId == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request");
            }
            MenuCourse menuCourse = menuCourseDao.findMenuCourseByCourseIdAndActive(menuCourseId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuCourse == null){
                return new RespStatus(ExceptionConstants.MENUCOURSE_NOT_FOUND,"Menu Course not found");
            }
            menuCourse.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            menuCourseDao.save(menuCourse);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }
}
