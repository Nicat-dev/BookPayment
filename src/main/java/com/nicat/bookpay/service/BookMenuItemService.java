package com.nicat.bookpay.service;

import com.nicat.bookpay.request.ReqBookMenuItem;
import com.nicat.bookpay.response.RespBookMenuItem;
import com.nicat.bookpay.response.RespBookMenuItemList;
import com.nicat.bookpay.response.RespStatus;

public interface BookMenuItemService {
    RespBookMenuItemList getBookMenuItemList();
    RespBookMenuItem getBookMenuItemById(Long bookMenuItemId);
    RespStatus addBookMenuItem(ReqBookMenuItem reqBookMenuItem);
    RespStatus updateBookMenuItem(ReqBookMenuItem reqBookMenuItem);
    RespStatus deleteBookMenuItem(Long bookMenuItemId);

}
