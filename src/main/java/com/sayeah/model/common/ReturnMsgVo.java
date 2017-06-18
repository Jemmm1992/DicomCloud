package com.sayeah.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class ReturnMsgVo<T> implements Serializable {

	private boolean ret; // 是否执行成功
	private T data; // 数据
	private String msgInfo;// 消息内容

}
