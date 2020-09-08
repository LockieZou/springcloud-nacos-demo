package com.lockie.bootorder.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;

import java.util.*;

/**
 * @author
 * @date 创建时间：2017年11月16日 下午6:18:33
 * @version 1.0
 */
public class JsonUtil {

	public final static ObjectMapper mapper = new ObjectMapper();

	static {
		// 多余的值不解析
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 解析器支持解析单引号
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		// 解析器支持解析结束符
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		// mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
		// Include.NON_NULL 属性为NULL 不序列化
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	public static String toJson(Object obj) {
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * JSONArray 转换成 Map
	 * @param dataObject
	 * @return
	 */
	public static ArrayList<Map<String, Object>> jsonArraytoMap(JSONArray dataObject) {
		ArrayList<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
		//循环JSONArray对象数据
		for (Object object : dataObject) {
			JSONObject jsonObject = (JSONObject) object;
			HashMap map = new HashMap<String, String>();
			//循环JSONArray中某一json串，并将json转换为map
			// entrySet()方法返回一个set视图（Set<Map.Entry<K,V>>），遍历set视图中的元素；
			for (Map.Entry entry : jsonObject.entrySet()) {
				if (entry.getValue() instanceof JSONArray) {
					map.put((String) entry.getKey(), jsonArraytoMap((JSONArray) entry.getValue()));
				} else {
					map.put((String) entry.getKey(), entry.getValue());
				}
				map.put((String) entry.getKey(), entry.getValue());
			}
			//将map加入到list中
			datalist.add(map);
		}
		return datalist;
	}

	public static <T> T toBean(String jsonStr, Class<T> objClass) {
		if (jsonStr == null) {
			return null;
		}
		T t = null;
		try {
			t = mapper.readValue(jsonStr, objClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	private static <T> T toBean(String jsonStr, JavaType type) {
		if (jsonStr == null) {
			return null;
		}
		T t = null;
		try {
			t = mapper.readValue(jsonStr, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> T toBean(String jsonStr, Class<?> c1, Class<?> c2, Class<?> c3) {
		return toBean(jsonStr, mapper.getTypeFactory().constructParametricType(c1,
				mapper.getTypeFactory().constructParametricType(c2, c3)));
	}

	public static <T> T toBean(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) {
		return toBean(jsonStr, mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses));
	}

	private static <T> T toCollection(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) {
		return toBean(jsonStr, mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses));
	}

	public static <T> T toSet(String jsonStr, Class<?>... elementClasses) {
		return toCollection(jsonStr, Set.class, elementClasses);
	}

	public static <T> T toList(String jsonStr, Class<?>... elementClasses) {
		return toCollection(jsonStr, List.class, elementClasses);
	}

	public static <T> T toMap(String jsonStr) {
		return toBean(jsonStr, mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class));
	}

	public static <T> T toMap(String jsonStr, Class<?> c1, Class<?> c2, Class<?> c3) {
		JavaType keyType = SimpleType.constructUnsafe(c1);
		JavaType valueType = mapper.getTypeFactory().constructParametricType(c2, c3);
		return toBean(jsonStr, mapper.getTypeFactory().constructMapType(Map.class, keyType, valueType));
	}

}
