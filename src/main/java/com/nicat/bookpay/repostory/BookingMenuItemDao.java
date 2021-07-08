package com.nicat.bookpay.repostory;

import com.nicat.bookpay.entity.BookMenuItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingMenuItemDao extends CrudRepository<BookMenuItem,Long> {
    List<BookMenuItem> findAllByActive(Integer active);
    BookMenuItem findBookMenuItemByMenuItemIdAndActive(Long id,Integer active);
}
