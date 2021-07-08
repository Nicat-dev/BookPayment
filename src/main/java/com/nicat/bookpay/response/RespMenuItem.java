package com.nicat.bookpay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.nicat.bookpay.entity.Booking;
import com.nicat.bookpay.entity.Chef;
import com.nicat.bookpay.entity.MenuCourseItem;
import com.nicat.bookpay.entity.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespMenuItem {
    @JsonProperty(value = "id")
    private Long respMenuItemId;
    @JsonProperty(value = "chef")
    private Chef respChef;
    @JsonProperty(value = "menuCourseItem")
    private String itemName;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "active")
    private Integer respActive;
    private RespStatus status;
}
