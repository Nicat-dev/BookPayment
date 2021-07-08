package com.nicat.bookpay.repostory;

import com.nicat.bookpay.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerDao extends CrudRepository<Customer,Long> {
    List<Customer> findAllByActive(Integer active);
    Customer findCustomerByCustomerIdAndActive(Long id,Integer active);
    List<Customer> findAllByGenderAndActive(Integer gender,Integer active);
}
