package com.nicat.bookpay.service;

import com.nicat.bookpay.request.ReqMenuItem;
import com.nicat.bookpay.response.RespMenuItem;
import com.nicat.bookpay.response.RespMenuItemList;
import com.nicat.bookpay.response.RespStatus;

public interface MenuItemService {
    RespMenuItemList getMenuItemList();
    RespMenuItem getMenuItemById(Long menuItemId);
    RespStatus addMenuItem(ReqMenuItem reqMenuItem);
    RespStatus updateMenuItem(ReqMenuItem reqMenuItem);
    RespStatus deleteMenuItem(Long menuItemId);
}
