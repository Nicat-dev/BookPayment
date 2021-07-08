package com.nicat.bookpay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.sql.Date;

@Data
@JacksonXmlRootElement
public class ReqMenuCourseItem {
    private Long reqCourseItemId;
    private Long reqCourseId;
    private Long reqMenuItem;
    private Date dataDate;
    private Integer active;
}
