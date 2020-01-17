package com.lockie.clouduser.controller;

import com.lockie.clouduser.model.Results;
import com.lockie.clouduser.util.JsonUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseController extends Results {

	protected <T> T toBean(String json, Class<T> clz) {
		return JsonUtil.toBean(json, clz);
	}

	protected String toJson(Object obj) {
		return JsonUtil.toJson(obj);
	}

	public boolean isNull(String str) {
		return (str == null || str.trim().isEmpty());
	}

	protected <T> boolean notNull(List<T> list) {
		return (list != null && !list.isEmpty());
	}

}
