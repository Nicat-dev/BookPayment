package com.nicat.bookpay.service;

import com.nicat.bookpay.request.ReqCustomer;
import com.nicat.bookpay.response.RespCustomer;
import com.nicat.bookpay.response.RespCustomerGenderList;
import com.nicat.bookpay.response.RespCustomerList;
import com.nicat.bookpay.response.RespStatus;

public interface CustomerService {
    RespCustomerList getCustomerList();
    RespCustomerGenderList getCustomerListByGender(Integer gender);
    RespCustomer getCustomerById(Long customerId);
    RespStatus addCustomer(ReqCustomer reqCustomer);
    RespStatus updateCustomer(ReqCustomer reqCustomer);
    RespStatus deleteCustomer(Long customerId);
}
