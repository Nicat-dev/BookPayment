package com.nicat.bookpay.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespCustomerList {
    private List<RespCustomer> respCustomerList;
    private List<RespCustomer> respGenders;
    private RespStatus status;
}
