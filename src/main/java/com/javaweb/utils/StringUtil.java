package com.javaweb.utils;

import com.javaweb.enums.TypeCode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class StringUtil {
	public static boolean usableTypeCode(List<TypeCode> typeCodes) {
		if (typeCodes == null)
			return false;
		return typeCodes.stream().anyMatch(str -> !StringUtil.isEmpty(str));

	}

	public static boolean isEmpty(Object str) {
		return str == null || StringUtils.isBlank(str.toString());
	}
}
