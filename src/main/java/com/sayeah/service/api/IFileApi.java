package com.sayeah.service.api;

import com.sayeah.model.common.ReturnMsgVo;

import java.io.InputStream;

/**
 * Created by BIG-JIAN on 2017/6/17.
 */
public interface IFileApi {
    /**
     * 上传文件的API
     * @param inputStream
     * @param name
     * @return
     */
    ReturnMsgVo<Object> uploadFile(InputStream inputStream,String name);
}
