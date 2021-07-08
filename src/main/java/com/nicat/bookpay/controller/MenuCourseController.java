package com.nicat.bookpay.controller;

import com.nicat.bookpay.request.ReqMenuCourse;
import com.nicat.bookpay.response.*;
import com.nicat.bookpay.service.MenuCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/menuCourse")
public class MenuCourseController {

    @Autowired
    private MenuCourseService menuCourseService;

    @GetMapping(value = "/getMenuCourseList")
    public RespMenuCourseList getMenuCourseList(){
        return menuCourseService.getMenuCourseList();
    }

    @RequestMapping(value ="/getMenuCourseItemById/{menuCourseId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespMenuCourse getMenuCourseItemById(@PathVariable("menuCourseId") Long menuCourseId){
        return menuCourseService.getMenuCourseById(menuCourseId);
    }

    @PostMapping(value = "/addMenuCourse")
    public RespStatus addMenuCourse(@RequestBody ReqMenuCourse reqMenuCourse){
        return menuCourseService.addMenuCourse(reqMenuCourse);
    }

    @PostMapping(value = "/updateMenuCourse")
    public RespStatus updateMenuCourse(@RequestBody ReqMenuCourse reqMenuCourse){
        return menuCourseService.updateMenuCourse(reqMenuCourse);
    }

    @PostMapping(value = "/deleteMenuCourse")
    public RespStatus deleteMenuCourse(@PathParam("menuCourseId") Long menuCourseId){
        return menuCourseService.deleteMenuCourse(menuCourseId);
    }
}
