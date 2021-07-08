package com.nicat.bookpay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class ReqMenu {
    private Long reqMenuId;
    private Long reqBasePrice;
    private Integer reqActive;
}
