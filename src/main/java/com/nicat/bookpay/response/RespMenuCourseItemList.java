package com.nicat.bookpay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement
public class RespMenuCourseItemList {
    @JsonProperty(value = "menuCourseItem")
    private List<RespMenuCourseItem> respMenuCourseItemList;
    private RespStatus status;
}
