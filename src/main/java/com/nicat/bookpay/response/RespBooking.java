package com.nicat.bookpay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.nicat.bookpay.entity.Chef;
import com.nicat.bookpay.entity.Customer;
import com.nicat.bookpay.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespBooking {
    @JsonProperty(value = "id")
    private Long respBookingId;
    @JsonProperty(value = "Data")
    private Date respDataDate;
    @JsonProperty(value = "price")
    private Long respTotalPrice;
    @JsonProperty(value = "chef")
    private Chef respChef;
    @JsonProperty(value = "menu")
    private Menu respMenu;
    @JsonProperty(value = "customer")
    private Customer respCustomer;
    @JsonProperty(value = "active")
    private Integer respActive;

    private RespStatus status;
}
