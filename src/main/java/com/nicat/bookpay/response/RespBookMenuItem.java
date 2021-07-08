package com.nicat.bookpay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.nicat.bookpay.entity.Booking;
import com.nicat.bookpay.entity.MenuCourseItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespBookMenuItem {
    @JsonProperty(value = "bookMenuId")
    private Long RespBookMenuId;
    @JsonProperty(value = "booking")
    private Booking Respbooking;
    @JsonProperty(value = "menuCourseItem")
    private MenuCourseItem respMenuCourseItem;
    @JsonProperty(value = "active")
    private Integer active;
    private RespStatus status;
}
