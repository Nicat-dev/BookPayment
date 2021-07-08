package com.nicat.bookpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_course_item")
@DynamicInsert
public class MenuCourseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseItemId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private MenuCourse course;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private MenuItem menuItem;
    @CreationTimestamp
    private Date dateData;
    @ColumnDefault(value = "1")
    private Integer active;
}
