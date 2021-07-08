package com.nicat.bookpay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.sql.Date;

@Data
@JacksonXmlRootElement
public class ReqCustomer {
    private Long reqCustomerId;
    private String reqCustomerName;
    private String reqCustomerSurname;
    private Date reqDataDate;
    private Integer reqGender;
    private Integer reqActive;
}
