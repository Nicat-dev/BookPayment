package com.nicat.bookpay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespBookMenuItemList {
    @JsonProperty(value = "BookMenuItemList")
    private List<RespBookMenuItem> respBookMenuItemList;
    private RespStatus status;
}
