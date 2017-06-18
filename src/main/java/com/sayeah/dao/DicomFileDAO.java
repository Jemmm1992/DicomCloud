package com.sayeah.dao;

import com.sayeah.model.DicomFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by BIG-JIAN on 2017/6/1.
 */

@Mapper
public interface DicomFileDAO {

    String TABLE_NAME = "dicomfile";
    String INSET_FIELDS = " user_id, dicom_name, study_date, modality, bigfile_id, file_size, sop_uid, series_uid, study_uid, patient_uid, created_date ";
    String SELECT_FIELDS = " id, user_id, dicom_name, study_date, modality, bigfile_id, file_size, sop_uid, series_uid, study_uid, patient_uid, created_date ";

    String SELECT_FIELDS2 = "user_id, dicom_name, study_date, modality, bigfile_id, study_uid ";


    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{userId},#{dicomName},#{studyDate},#{modality},#{bigFileId},#{fileSize},#{sopUid},#{seriesUid},#{studyUid},#{patientUid},#{createdDate})"})
    int addDicomFile(DicomFile dicomFile);


    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    DicomFile selectById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where dicom_name=#{dicomName}"})
    DicomFile selectByName(String dicomName);


    // 通过StudyUid,来找到属于同一个StudyUid的所有dicomFile
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where study_uid=#{studyUid}"})
    List<DicomFile> selectByStudyUid(String dicomName);


    //根据小文件的名称(diocm_name）得到对应的大文件地址(study_date,modality,bigfile_id,study_uid)
    @Select({"select ", SELECT_FIELDS2, " from ", TABLE_NAME, " where dicom_name=#{dicomName}"})
    DicomFile selectByName2(String dicomName);

}
