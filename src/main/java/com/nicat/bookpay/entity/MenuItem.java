package com.nicat.bookpay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item")
@DynamicInsert
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chef_id")
    private Chef chef;
    private String itemName;
    private String description;
    @CreationTimestamp
    private Date date;
    @ColumnDefault(value = "1")
    private Integer active;
}
