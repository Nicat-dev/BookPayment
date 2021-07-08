package com.nicat.bookpay.service;

import com.nicat.bookpay.request.ReqChef;
import com.nicat.bookpay.response.RespChef;
import com.nicat.bookpay.response.RespChefList;
import com.nicat.bookpay.response.RespStatus;

public interface ChefService {
    RespChefList getChefList();
    RespChef getChefById(Long chefId);
    RespStatus addChef(ReqChef reqChef);
    RespStatus updateChef(ReqChef reqChef);
    RespStatus deleteChef(Long chefId);
}
