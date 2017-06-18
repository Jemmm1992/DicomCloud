package com.sayeah.model.gen;

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
    private int studyFileId;

    private String dicomName;
    private String mappigName;
    private long fileSize;


    private String patientUid;
    private String studyUid;
    private String seriesUid;
    private String sopUid;

    private Date studyDate;
    private String modality;
    private int bigFileId;

    private Date createdDate;

    private int ishdfs;
}
