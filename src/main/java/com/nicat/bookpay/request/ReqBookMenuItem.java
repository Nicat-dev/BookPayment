package com.nicat.bookpay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class ReqBookMenuItem {
    private Long reqBookMenuItemId;
    private Long reqBookId;
    private Long reqMenuCourseId;
    private Integer active;
}
