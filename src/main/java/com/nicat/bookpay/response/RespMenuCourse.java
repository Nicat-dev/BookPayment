package com.nicat.bookpay.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.nicat.bookpay.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespMenuCourse {
    @JsonProperty(value = "id")
    private Long respCourseId;
    @JsonProperty(value = "menu")
    private Menu respMenu;
    @JsonProperty(value = "name")
    private String respName;
    @JsonProperty(value = "price")
    private Long respPrice;
    @JsonProperty(value = "date")
    private Date respDataDate;
    @JsonProperty(value = "active")
    private Integer active;
    private RespStatus status;
}
