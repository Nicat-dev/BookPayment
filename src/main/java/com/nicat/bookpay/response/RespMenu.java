package com.nicat.bookpay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespMenu {
    @JsonProperty(value = "id")
    private Long respMenuId;
    @JsonProperty(value = "price")
    private Long respBasePrice;
    @JsonProperty(value = "date")
    private Date respDataDate;
    @JsonProperty(value = "active")
    private Integer respActive;

    private RespStatus status;
}
