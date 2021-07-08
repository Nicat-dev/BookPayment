package com.nicat.bookpay.repostory;

import com.nicat.bookpay.entity.MenuCourse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuCourseDao extends CrudRepository<MenuCourse,Long> {
    List<MenuCourse> findAllByActive(Integer active);
    MenuCourse findMenuCourseByCourseIdAndActive(Long id,Integer active);
}
