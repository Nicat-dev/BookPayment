package com.nicat.bookpay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class ReqMenuItem {
    private Long reqItemId;
    private Long reqChefId;
    private String reqItemName;
    private String reqDescription;
    private Integer reqActive;
}
