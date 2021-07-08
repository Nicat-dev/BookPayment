package com.nicat.bookpay.controller;

import com.nicat.bookpay.request.ReqMenuCourseItem;
import com.nicat.bookpay.response.*;
import com.nicat.bookpay.service.MenuCourseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/menuCourseItem")
public class MenuCourseItemController {

    @Autowired
    private MenuCourseItemService menuCourseItemService;

    @GetMapping(value = "/getMenuCourseItemList")
    public RespMenuCourseItemList getMenuCourseItemList(){
        return menuCourseItemService.getMenuCourseItemList();
    }

    @RequestMapping(value ="/getMenuCourseItemById/{menuId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespMenuCourseItem getCustomerById(@PathVariable("menuId") Long menuId){
        return menuCourseItemService.getMenuCourseItemById(menuId);
    }

    @PostMapping(value = "/addMenuCourseItem")
    public RespStatus addMenuCourseItem(@RequestBody ReqMenuCourseItem reqMenuCourseItem){
        return menuCourseItemService.addMenuCourseItem(reqMenuCourseItem);
    }

    @PostMapping(value = "/updateMenuCourseItem")
    public RespStatus updateCustomer(@RequestBody ReqMenuCourseItem reqMenuCourseItem){
        return menuCourseItemService.updateMenuCourseItem(reqMenuCourseItem);
    }

    @PostMapping(value = "/deleteMenuCourseItem")
    public RespStatus deleteStudent(@PathParam("menuCourseItemId") Long menuCourseItemId){
        return menuCourseItemService.deleteMenuItem(menuCourseItemId);
    }
}
