package com.sayeah.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
@Getter
@Setter
@ToString
public class SOPInstanceModel {
    private String patientID;
    private String studyInstaceUID;
    private String seriesInstanceUID;
    private String sopInstanceUID;
    private String studyDate;
    private String modality;

    private String fileName;
    private long fileSize;
}
