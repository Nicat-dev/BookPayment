package com.nicat.bookpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_menu_item")
@DynamicInsert
public class BookMenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuItemId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Booking booking;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_item_id")
    private MenuCourseItem menuCourseItem;
    @ColumnDefault(value = "1")
    private Integer active;
}
