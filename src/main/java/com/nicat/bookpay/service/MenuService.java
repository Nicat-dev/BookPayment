package com.nicat.bookpay.service;

import com.nicat.bookpay.request.ReqMenu;
import com.nicat.bookpay.response.RespMenu;
import com.nicat.bookpay.response.RespMenuList;
import com.nicat.bookpay.response.RespStatus;

public interface MenuService {
    RespMenuList getMenuList();
    RespMenu getMenuById(Long menuId);
    RespStatus addMenu(ReqMenu reqMenu);
    RespStatus updateMenu(ReqMenu reqMenu);
    RespStatus deleteMenu(Long menuId);
}
