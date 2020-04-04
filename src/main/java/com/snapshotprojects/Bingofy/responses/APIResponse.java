package com.snapshotprojects.Bingofy.responses;

public class APIResponse<T> {

	public int code;

	T data;

	public APIResponse(int code, T data) {
		super();
		this.code = code;
		this.data = data;
	}

	public APIResponse(int code) {
		super();
		this.code = code;
	}

	public static <T> APIResponse response(int code, T data) {
		return new APIResponse<T>(code, data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "APIResponse [code=" + code + ", data=" + data + "]";
	}

}
