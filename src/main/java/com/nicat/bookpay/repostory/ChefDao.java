package com.nicat.bookpay.repostory;

import com.nicat.bookpay.entity.Chef;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChefDao extends CrudRepository<Chef,Long> {
    List<Chef> findAllByActive(Integer active);
    Chef findChefByChefIdAndActive(Long id,Integer active);
}
