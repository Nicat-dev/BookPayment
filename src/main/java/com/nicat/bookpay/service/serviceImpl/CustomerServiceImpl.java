package com.nicat.bookpay.service.serviceImpl;

import com.nicat.bookpay.entity.Customer;
import com.nicat.bookpay.enums.EnumAvaibleStatus;
import com.nicat.bookpay.enums.EnumGenderStatus;
import com.nicat.bookpay.exception.ExceptionConstants;
import com.nicat.bookpay.repostory.CustomerDao;
import com.nicat.bookpay.request.ReqCustomer;
import com.nicat.bookpay.response.RespCustomer;
import com.nicat.bookpay.response.RespCustomerGenderList;
import com.nicat.bookpay.response.RespCustomerList;
import com.nicat.bookpay.response.RespStatus;
import com.nicat.bookpay.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public RespCustomerList getCustomerList() {
        RespCustomerList response = new RespCustomerList();
        try{
            List<RespCustomer> respCustomerList = new ArrayList<>();
            List<Customer> customerList = customerDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (customerList.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND,"Customer not found"));
            }for (Customer customer: customerList){
                RespCustomer respCustomer = getCustomerById(customer.getCustomerId());
                respCustomerList.add(respCustomer);
            }
            response.setRespCustomerList(respCustomerList);
            response.setStatus(RespStatus.getSuccesMessage());
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
        }
        return response;
    }

    @Override
    public RespCustomer getCustomerById(Long customerId) {
        RespCustomer response = new RespCustomer();
        try{
            if (customerId == null){
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request data"));
                return response;
            }
            Customer customer = customerDao.findCustomerByCustomerIdAndActive(customerId, EnumAvaibleStatus.ACTIVE.getValue());
            if (customer==null){
                response.setStatus(new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND,"Customer not found"));
                return response;
            }
            response.setCustomerId(customer.getCustomerId());
            response.setCustomerName(customer.getCustomerName());
            response.setCustomerSurname(customer.getCustomerSurname());
            response.setGender(customer.getGender());
            response.setDataDate(customer.getDataDate());
            response.setActive(customer.getActive());
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
        return response;
    }

    @Override
    public RespCustomerGenderList getCustomerListByGender(Integer gender) {
        RespCustomerGenderList response = new RespCustomerGenderList();
        try{
            List<RespCustomer> respCustomerList = new ArrayList<>();
            if (gender == 1){
            List<Customer> customerList = customerDao.findAllByGenderAndActive(EnumGenderStatus.MALE.getValue(), EnumAvaibleStatus.ACTIVE.getValue());
            if(customerList.isEmpty()) {
                response.setStatus(new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND, "Male Customer not found"));
                return response;
            }for (Customer customer: customerList){
                    RespCustomer respCustomer = getCustomerById(customer.getCustomerId());
                    respCustomerList.add(respCustomer);
                }
            return response;
            }
            else if (gender == 0){
            List<Customer> customerList = customerDao.findAllByGenderAndActive(EnumGenderStatus.FEMALE.getValue(), EnumAvaibleStatus.ACTIVE.getValue());
            if (customerList.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND,"Female Customer not found"));
                return response;
            }for (Customer customer: customerList){
                    RespCustomer respCustomer = getCustomerById(customer.getCustomerId());
                    respCustomerList.add(respCustomer);
                }
                return response;
            }else{
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request Data"));
                return response;
            }

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            return response;
        }
    }

    @Override
    public RespStatus addCustomer(ReqCustomer reqCustomer) {
        RespStatus status = null;
        try{
            String customerName = reqCustomer.getReqCustomerName();
            String customerSurname = reqCustomer.getReqCustomerSurname();
            Integer customerGender = reqCustomer.getReqGender();
            if (customerSurname == null || customerName == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request Data");
            }
            Customer customer = new Customer();
            customer.setCustomerName(customerName);
            customer.setCustomerSurname(customerSurname);
            customer.setGender(customerGender);
            customerDao.save(customer);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus updateCustomer(ReqCustomer reqCustomer) {
        RespStatus status = null;
        try{
            Long id = reqCustomer.getReqCustomerId();
            String customerName = reqCustomer.getReqCustomerName();
            String customerSurname = reqCustomer.getReqCustomerSurname();
            Integer customerGender = reqCustomer.getReqGender();
            if (id == null || customerName == null  ){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Ivalid Request Data");
            }
            Customer customer = customerDao.findCustomerByCustomerIdAndActive(id,EnumAvaibleStatus.ACTIVE.getValue());
            if (customer == null){
                return new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND,"Customer not found");
            }
            customer.setCustomerId(id);
            customer.setCustomerName(customerName);
            customer.setCustomerSurname(customerSurname);
            customer.setGender(customerGender);
            customerDao.save(customer);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus deleteCustomer(Long customerId) {
        RespStatus status = null;
        try{
            if (customerId == null){
                return new RespStatus(ExceptionConstants.INVALID_REQUEST_EXCEPTION,"Invalid Request Exception");
            }
            Customer customer =customerDao.findCustomerByCustomerIdAndActive(customerId,EnumAvaibleStatus.ACTIVE.getValue());
            if (customer == null){
                return new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND,"Customer not found");
            }
            customer.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            customerDao.save(customer);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

}