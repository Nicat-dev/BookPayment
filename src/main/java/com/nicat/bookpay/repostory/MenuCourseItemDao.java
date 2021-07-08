package com.nicat.bookpay.repostory;

import com.nicat.bookpay.entity.MenuCourseItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuCourseItemDao extends CrudRepository<MenuCourseItem,Long> {
    List<MenuCourseItem> findAllByActive(Integer active);
    MenuCourseItem findMenuCourseItemByCourseItemIdAndActive(Long id,Integer active);
}
