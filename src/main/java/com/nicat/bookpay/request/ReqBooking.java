package com.nicat.bookpay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class ReqBooking {
    private Long reqBookingId;
    private Long reqTotalPrice;
    private Long reqMenuId;
    private Long reqChefId;
    private Long reqCustomerId;
    private Integer active;
}
