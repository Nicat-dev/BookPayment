package com.nicat.bookpay.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespStatus {
    private Integer statusCode;
    private String statusMessage;

    private static final Integer SUCCES_CODE = 1;
    private static final String SUCCES_MESSAGE = "Success";

    public static RespStatus getSuccesMessage(){
        return new RespStatus(SUCCES_CODE,SUCCES_MESSAGE);
    }
}
