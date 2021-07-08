package com.nicat.bookpay.service;

import com.nicat.bookpay.request.ReqMenuCourseItem;
import com.nicat.bookpay.response.*;

public interface MenuCourseItemService {
    RespMenuCourseItemList getMenuCourseItemList();
    RespMenuCourseItem getMenuCourseItemById(Long menuItemId);
    RespStatus addMenuCourseItem(ReqMenuCourseItem reqMenuCourseItem);
    RespStatus updateMenuCourseItem(ReqMenuCourseItem reqMenuCourseItem);
    RespStatus deleteMenuItem(Long menuItemId);
}
