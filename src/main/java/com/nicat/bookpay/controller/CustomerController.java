package com.nicat.bookpay.controller;

import com.nicat.bookpay.request.ReqCustomer;
import com.nicat.bookpay.response.RespCustomer;
import com.nicat.bookpay.response.RespCustomerGenderList;
import com.nicat.bookpay.response.RespCustomerList;
import com.nicat.bookpay.response.RespStatus;
import com.nicat.bookpay.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/getCustomerList")
    public RespCustomerList getCustomerList(){
        return customerService.getCustomerList();
    }

    @RequestMapping(value ="/getCustomerById/{customerId}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespCustomer getCustomerById(@PathVariable("customerId") Long customerId){
        return customerService.getCustomerById(customerId);
    }
    @RequestMapping(value ="/getCustomerByGender/{gender}",method = {RequestMethod.GET,RequestMethod.POST})
    public RespCustomerGenderList getCustomerByGender(@PathVariable("gender") Integer gender){
        return customerService.getCustomerListByGender(gender);
    }

    @PostMapping(value = "/addCustomer")
    public RespStatus addCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.addCustomer(reqCustomer);
    }

    @PostMapping(value = "/updateCustomer")
    public RespStatus updateCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.updateCustomer(reqCustomer);
    }

    @PostMapping(value = "/deleteCustomer")
    public RespStatus deleteCustomer(@PathParam("customerId") Long customerId){
        return customerService.deleteCustomer(customerId);
    }
}
