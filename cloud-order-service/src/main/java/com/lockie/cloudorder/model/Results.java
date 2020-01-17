package com.lockie.cloudorder.model;

import com.lockie.cloudorder.constant.SysConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Results {

	/** 返回状态 true成功 false失败 **/
	private boolean status = false;

	/** 状态码 **/
	private String code = SysConstant.ERROR500.getCode();

	/** 返回消息 **/
	private Object msg = SysConstant.ERROR500.getMsg();

	/** 返回数据 **/
	private Object data = new ArrayList<Object>();

	public Results(String code, Object msg) {
		this.code = code;
		this.msg = msg;
	}

	public Results(boolean status, String code, Object msg) {
		this.status = status;
		this.code = code;
		this.msg = msg;
	}

	public Results(SysConstant sysConstant) {
		this.status = false;
		this.code = sysConstant.getCode();
		this.msg = sysConstant.getMsg();
	}

	public Results(SysConstant sysConstant, String msg) {
		this.status = false;
		this.code = sysConstant.getCode();
		this.msg = msg;
	}

	public Results results() {
		return new Results();
	}

	public Results results(SysConstant sysConstant) {
		return new Results(sysConstant);
	}

	public Results succeed() {
		return new Results(true, SysConstant.SUCCEED.getCode(), SysConstant.SUCCEED.getMsg());
	}

	public Results succeed(Object object) {
		Results results = succeed();
		results.setData(object != null ? object : new ArrayList<Object>());
		return results;
	}

	public Results failure(String code, Object msg) {
		return new Results(false, code, msg);
	}

	public Results failure(Object msg) {
		return new Results(false, SysConstant.ERROR1021.getCode(), msg);
	}

	public Results failure(SysConstant sysConstant) {
		return failure(sysConstant.getCode(), sysConstant.getMsg());
	}

}
