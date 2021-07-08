package com.nicat.bookpay.controller;

import com.nicat.bookpay.request.ReqCustomer;
import com.nicat.bookpay.request.ReqMenu;
import com.nicat.bookpay.response.*;
import com.nicat.bookpay.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/getMenuList")
    public RespMenuList getMenuList(){
        return menuService.getMenuList();
    }

    @RequestMapping(value ="/getMenuById/{menuId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespMenu getMenuById(@PathVariable("menuId") Long menuId){
        return menuService.getMenuById(menuId);
    }

    @PostMapping(value = "/addMenu")
    public RespStatus addMenu(@RequestBody ReqMenu reqMenu){
        return menuService.addMenu(reqMenu);
    }

    @PostMapping(value = "/updateMenu")
    public RespStatus updateMenu(@RequestBody ReqMenu reqMenu){
        return menuService.updateMenu(reqMenu);
    }

    @PostMapping(value = "/deleteMenu")
    public RespStatus deleteMenu(@PathParam("menuId") Long menuId){
        return menuService.deleteMenu(menuId);
    }
}
