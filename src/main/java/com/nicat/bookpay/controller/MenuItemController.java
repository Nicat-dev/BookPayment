package com.nicat.bookpay.controller;

import com.nicat.bookpay.request.ReqMenuItem;
import com.nicat.bookpay.response.*;
import com.nicat.bookpay.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/menuItemController")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping(value = "/getMenuItemList")
    public RespMenuItemList getMenuCourseItemList(){
        return menuItemService.getMenuItemList();
    }

    @RequestMapping(value ="/getMenuItemById/{menuId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespMenuItem getMenuItemById(@PathVariable("menuId") Long menuId){
        return menuItemService.getMenuItemById(menuId);
    }

    @PostMapping(value = "/addMenuItem")
    public RespStatus addMenuItem(@RequestBody ReqMenuItem reqMenuItem){
        return menuItemService.addMenuItem(reqMenuItem);
    }

    @PostMapping(value = "/updateMenuItem")
    public RespStatus updateMenuItem(@RequestBody ReqMenuItem reqMenuItem){
        return menuItemService.updateMenuItem(reqMenuItem);
    }

    @PostMapping(value = "/deleteMenuItem")
    public RespStatus deleteMenuItem(@PathParam("menuCourseItemId") Long menuCourseItemId){
        return menuItemService.deleteMenuItem(menuCourseItemId);
    }
}
