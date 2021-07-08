package com.nicat.bookpay.service;

import com.nicat.bookpay.request.ReqMenuCourse;
import com.nicat.bookpay.response.RespMenuCourse;
import com.nicat.bookpay.response.RespMenuCourseList;
import com.nicat.bookpay.response.RespStatus;

public interface MenuCourseService {
    RespMenuCourseList getMenuCourseList();
    RespMenuCourse getMenuCourseById(Long menuCourseId);
    RespStatus addMenuCourse(ReqMenuCourse reqMenuCourse);
    RespStatus updateMenuCourse(ReqMenuCourse reqMenuCourse);
    RespStatus deleteMenuCourse(Long menuCourseId);
}
