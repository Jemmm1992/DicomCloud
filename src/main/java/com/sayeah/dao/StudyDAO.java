package com.sayeah.dao;

import com.sayeah.model.gen.StudyFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by BIG-JIAN on 2017/6/18.
 */
@Mapper
public interface StudyDAO {
    String TABLE_NAME = "studyfile";
    String INSET_FIELDS = " user_id, big_file_id, folder_name, url, study_date, modality, study_uid, patient_uid, study_file_size, create_date, ishdfs";
    String SELECT_FIELDS = " id," + INSET_FIELDS;

    String SELECT_FIELDS2 = "user_id, dicom_name, study_date, modality, bigfile_id, study_uid ";


    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{userId},#{bigFileId},#{folderName},#{url},#{studyDate},#{modality},#{studyUid},#{patientUid},#{studyFileSize},#{createDate},#{ishdfs})"})
    int addStudyFile(StudyFile studyFile);


    // 通过用户的user_id来获取
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where user_id=#{userId}"})
    StudyFile selectStudyFileById(int id);

//
//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where dicom_name=#{dicomName}"})
//    DicomFile selectByName(String dicomName);
//
//
//    // 通过StudyUid,来找到属于同一个StudyUid的所有dicomFile
//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where study_uid=#{studyUid}"})
//    List<DicomFile> selectByStudyUid(String dicomName);
//
//
//    //根据小文件的名称(diocm_name）得到对应的大文件地址(study_date,modality,bigfile_id,study_uid)
//    @Select({"select ", SELECT_FIELDS2, " from ", TABLE_NAME, " where dicom_name=#{dicomName}"})
//    DicomFile selectByName2(String dicomName);

}
