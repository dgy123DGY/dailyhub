package com.markerhub.base.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

	public static final int SUCCESS = 0;
	public static final int ERROR = -1;

	private int code;
	private String mess;
	private T data;

	public Result(int code, String mess, T data) {
		this.code = code;
		this.mess = mess;
		this.data = data;
	}

	public static <T> Result<T> success(T data) {
		return new Result(SUCCESS, "操作成功", data);
	}

	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> failure(String mess) {
		return new Result(ERROR, mess, null);
	}

}
