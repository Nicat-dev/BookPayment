package com.nicat.bookpay.service.serviceImpl;


import com.nicat.bookpay.entity.Chef;
import com.nicat.bookpay.entity.Menu;
import com.nicat.bookpay.entity.MenuItem;
import com.nicat.bookpay.enums.EnumAvaibleStatus;
import com.nicat.bookpay.exception.ExceptionConstants;
import com.nicat.bookpay.repostory.ChefDao;
import com.nicat.bookpay.repostory.MenuItemDao;
import com.nicat.bookpay.request.ReqMenuItem;
import com.nicat.bookpay.response.*;
import com.nicat.bookpay.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    @Autowired
    private MenuItemDao menuItemDao;

    @Autowired
    private ChefDao chefDao;

    @Override
    public RespMenuItemList getMenuItemList() {
        RespMenuItemList response = new RespMenuItemList();
        try{
            List<RespMenuItem> respMenuItemList = new ArrayList<>();
            List<MenuItem> menuItemList = menuItemDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (menuItemList.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstants.MENUITEM_NOT_FOUND,"MenuItem not found"));
            }for (MenuItem menuItem : menuItemList){
                RespMenuItem respMenuItem = getMenuItemById(menuItem.getItemId());
                respMenuItemList.add(respMenuItem);
            }
            response.setRespMenuItemList(respMenuItemList);
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespMenuItem getMenuItemById(Long menuItemId) {
        RespMenuItem response = new RespMenuItem();
        try{
            if (menuItemId == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid request"));
                return response;
            }
            MenuItem menuItem = menuItemDao.findMenuItemByItemIdAndActive(menuItemId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuItem == null){
                response.setStatus(new RespStatus(ExceptionConstants.MENUITEM_NOT_FOUND,"menu item not found"));
                return response;
            }
            response.setRespMenuItemId(menuItem.getItemId());
            response.setItemName(menuItem.getItemName());
            response.setRespChef(menuItem.getChef());
            response.setDescription(menuItem.getDescription());
            response.setRespActive(menuItem.getActive());
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespStatus addMenuItem(ReqMenuItem reqMenuItem) {
        RespStatus status = null;
        try{
            Long chefId = reqMenuItem.getReqChefId();
            String menuItemName = reqMenuItem.getReqItemName();
            String menuItemDescription = reqMenuItem.getReqDescription();
            if (menuItemName == null || chefId == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Ivalid request");
            }
            Chef chef = chefDao.findChefByChefIdAndActive(chefId,EnumAvaibleStatus.ACTIVE.getValue());
            if (chef == null){
                return new RespStatus(ExceptionConstants.MENUITEM_NOT_FOUND,"Menu Item not found");
            }
            MenuItem menuItem = new MenuItem();
            menuItem.setItemName(menuItemName);
            menuItem.setDescription(menuItemDescription);
            menuItem.setChef(chef);
            menuItemDao.save(menuItem);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus updateMenuItem(ReqMenuItem reqMenuItem) {
        RespStatus status = null;
        try{
            Long menuItemId = reqMenuItem.getReqItemId();
            Long chefId = reqMenuItem.getReqChefId();
            String menuItemName = reqMenuItem.getReqItemName();
            String menuItemDescription = reqMenuItem.getReqDescription();
            if (menuItemId == null || chefId == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Ivalid request");
            }
            MenuItem menuItem = menuItemDao.findMenuItemByItemIdAndActive(menuItemId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuItem == null){
                return new RespStatus(ExceptionConstants.MENUITEM_NOT_FOUND,"Menu item not found");
            }
            Chef chef = chefDao.findChefByChefIdAndActive(chefId,EnumAvaibleStatus.ACTIVE.getValue());
            if (chef == null){
                return new RespStatus(ExceptionConstants.MENUITEM_NOT_FOUND,"Menu Item not found");
            }
            menuItem.setItemName(menuItemName);
            menuItem.setDescription(menuItemDescription);
            menuItem.setChef(chef);
            menuItemDao.save(menuItem);
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
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Ivalid request");
            }
            MenuItem menuItem = menuItemDao.findMenuItemByItemIdAndActive(menuItemId,EnumAvaibleStatus.ACTIVE.getValue());
            if (menuItem == null){
                return new RespStatus(ExceptionConstants.MENUITEM_NOT_FOUND,"Menu item not found");
            }
            menuItem.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            menuItemDao.save(menuItem);
            status = RespStatus.getSuccesMessage();
        } catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }
}
