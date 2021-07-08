package com.nicat.bookpay.repostory;

import com.nicat.bookpay.entity.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuDao extends CrudRepository<Menu,Long> {
    List<Menu> findAllByActive(Integer active);
    Menu findMenuByMenuIdAndActive(Long id,Integer active);
}
