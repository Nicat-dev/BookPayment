package com.nicat.bookpay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.nicat.bookpay.entity.MenuCourse;
import com.nicat.bookpay.entity.MenuItem;
import lombok.Data;

import java.sql.Date;

@Data
@JacksonXmlRootElement
public class RespMenuCourseItem {
    @JsonProperty(value = "courseItemId")
    private Long respCourseItemId;
    @JsonProperty(value = "course")
    private MenuCourse respCourse;
    @JsonProperty(value = "item")
    private MenuItem respMenuItem;
    @JsonProperty(value = "date")
    private Date respDataDate;
    @JsonProperty("active")
    private Integer respActive;
    private RespStatus status;
}
