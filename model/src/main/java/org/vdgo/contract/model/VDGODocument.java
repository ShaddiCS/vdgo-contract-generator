package org.vdgo.contract.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class VDGODocument {
    private String clientName;
    private String clientShort;
    private String organization;
    private String address;
    private String month;
    private String fullDate;
    private String actDate;
    private String actNum;
    private String phone;
    private String email;
    private Integer day;
}
