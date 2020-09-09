package com.lockie.common.controller;

import com.lockie.common.model.Results;
import com.lockie.common.util.JsonUtil;
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
