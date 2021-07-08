package com.nicat.bookpay.service.serviceImpl;

import com.nicat.bookpay.entity.Menu;
import com.nicat.bookpay.enums.EnumAvaibleStatus;
import com.nicat.bookpay.exception.ExceptionConstants;
import com.nicat.bookpay.repostory.MenuDao;
import com.nicat.bookpay.request.ReqMenu;
import com.nicat.bookpay.response.RespMenu;
import com.nicat.bookpay.response.RespMenuList;
import com.nicat.bookpay.response.RespStatus;
import com.nicat.bookpay.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public RespMenuList getMenuList() {
        RespMenuList response = new RespMenuList();
        try {
            List<RespMenu> respMenuList = new ArrayList<>();
            List<Menu> menuList = menuDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (menuList.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid request"));
                return response;
            }for (Menu menu : menuList){
                RespMenu respMenu = getMenuById(menu.getMenuId());
                respMenuList.add(respMenu);
            }
            response.setRespMenuList(respMenuList);
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespMenu getMenuById(Long menuId) {
        RespMenu response = new RespMenu();
        try{
            if (menuId == null){
                response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
                return response;
            }
            Menu menu = menuDao.findMenuByMenuIdAndActive(menuId, EnumAvaibleStatus.ACTIVE.getValue());
            if (menu ==null){
                response.setStatus(new RespStatus(ExceptionConstants.MENU_NOT_FOUND,"Menu not found"));
                return response;
            }
            response.setRespMenuId(menu.getMenuId());
            response.setRespBasePrice(menu.getBasePrice());
            response.setRespDataDate(menu.getMenuDate());
            response.setRespActive(menu.getActive());
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespStatus addMenu(ReqMenu reqMenu) {
        RespStatus status = null;
        try{
            Long id = reqMenu.getReqMenuId();
            Long basePrice = reqMenu.getReqBasePrice();
            if (id==null || basePrice==null){
                return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            }
            Menu menu = new Menu();
            menu.setMenuId(id);
            menu.setBasePrice(basePrice);
            menuDao.save(menu);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            return status;
        }
        return status;
    }

    @Override
    public RespStatus updateMenu(ReqMenu reqMenu) {
        RespStatus status = null;
        try {
            Long id = reqMenu.getReqMenuId();
            Long basePrice = reqMenu.getReqBasePrice();
            if (id==null || basePrice==null){
                return  new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            }
            Menu menu = menuDao.findMenuByMenuIdAndActive(id,EnumAvaibleStatus.ACTIVE.getValue());
            if (menu == null){
                return  new RespStatus(ExceptionConstants.MENU_NOT_FOUND,"menu not found");
            }
            menu.setMenuId(id);
            menu.setBasePrice(basePrice);
            menuDao.save(menu);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            return status;
        }
        return status;
    }

    @Override
    public RespStatus deleteMenu(Long menuId) {
        RespStatus status = null;
        try {
            if (menuId==null){
                return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            }
            Menu menu = menuDao.findMenuByMenuIdAndActive(menuId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menu == null){
                return  new RespStatus(ExceptionConstants.MENU_NOT_FOUND,"Menu not found");
            }
            menu.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            menuDao.save(menu);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            status = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }
}
