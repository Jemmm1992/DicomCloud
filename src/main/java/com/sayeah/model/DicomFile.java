package com.sayeah.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by BIG-JIAN on 2017/6/1.
 */
@Setter
@Getter
@ToString

public class DicomFile {

    private int id;
    private int userId;
    private String dicomName;
    private Date studyDate;
    private String modality;
    private int bigFileId;
    private long fileSize;
    private String sopUid;
    private String seriesUid;
    private String studyUid;
    private String patientUid;
    private Date createdDate;
}
