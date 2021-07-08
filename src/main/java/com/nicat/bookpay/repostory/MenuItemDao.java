package com.nicat.bookpay.repostory;

import com.nicat.bookpay.entity.MenuItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemDao extends CrudRepository<MenuItem,Long > {
    List<MenuItem> findAllByActive(Integer active);
    MenuItem findMenuItemByItemIdAndActive(Long id,Integer active);
}
