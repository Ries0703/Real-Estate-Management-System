package com.javaweb.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static boolean isEmpty(Object str) {
		return str == null || StringUtils.isBlank(str.toString());
	}
}
