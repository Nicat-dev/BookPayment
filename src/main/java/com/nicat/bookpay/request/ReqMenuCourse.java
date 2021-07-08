package com.nicat.bookpay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.nicat.bookpay.entity.Menu;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class ReqMenuCourse {
    private Long reqMenuCourseId;
    private Long reqMenu;
    private String reqName;
    private Integer acticve;
    private Long reqPrice;
}
