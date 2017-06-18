package com.sayeah.model.gen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
@Getter
@Setter
@ToString
public class StudyFile {
    private int id;
    int userId;
    int bigFileId;
    String folderName;
    String url;
    Date studyDate;
    String modality;

    String studyUid;
    String patientUid;
    String studyFileSize;
    Date createDate;
    int ishdfs;
}
