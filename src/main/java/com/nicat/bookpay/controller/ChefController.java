package com.nicat.bookpay.controller;

import com.nicat.bookpay.repostory.ChefDao;
import com.nicat.bookpay.request.ReqBookMenuItem;
import com.nicat.bookpay.request.ReqChef;
import com.nicat.bookpay.response.*;
import com.nicat.bookpay.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/chef")
public class ChefController {

    @Autowired
    private ChefService chefService;

    @GetMapping(value = "/getChefList")
    public RespChefList getChefList(){
        return chefService.getChefList();
    }

    @RequestMapping(value ="/getChefById/{chefId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespChef getChefById(@PathVariable("chefId") Long chefId){
        return chefService.getChefById(chefId);
    }

    @PostMapping(value = "/addChef")
    public RespStatus addChef(@RequestBody ReqChef reqChef){
        return chefService.addChef(reqChef);
    }

    @PostMapping(value = "/updateChef")
    public RespStatus updateChef(@RequestBody ReqChef reqChef){
        return chefService.updateChef(reqChef);
    }

    @PostMapping(value = "/deleteChef")
    public RespStatus deleteChef(@PathParam("chefId") Long chefId){
        return chefService.deleteChef(chefId);
    }
}
