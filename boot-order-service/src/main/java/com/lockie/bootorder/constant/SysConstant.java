package com.lockie.bootorder.constant;

public enum SysConstant {

	SUCCEED("200", "请求成功"), 
	ERROR500("500", "系统内部错误"),
	ERROR404("404", "请求不存在"),
	ERROR1000("1000", "令牌不可为空"),
	ERROR1001("1001", "令牌验证不通过"),
	ERROR1002("1002", "参数验证不通过"),
	ERROR1003("1003", "请求方式错误POST/GET"),
	ERROR1004("1004", "用户未登录"),
	ERROR1005("1005", "账号或密码不可为空"),
	ERROR1006("1006", "账号不存在"),
	ERROR1007("1007", "该账号已禁用"),
	ERROR1008("1008", "账号或密码错误"),
	ERROR1009("1009", "签名错误"),
	ERROR1010("1010", "上传加签失败"),
	ERROR1016("1016", "密码错误"),
	ERROR1020("1020", "签名校验不通过"),
	ERROR1021("1021", "业务逻辑错误"),
	ERROR1046("1046", "分类编码不能重复");
	private String code;
	private String msg;

	private SysConstant(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
