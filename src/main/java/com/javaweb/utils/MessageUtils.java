package com.javaweb.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageUtils {

	public static Map<String, String> getMessage(String message) {
		Map<String, String> result = new HashMap<>();
        switch (message) {
            case "update_success":
                result.put("message", "Update success");
                result.put("alert", "success");
                break;
            case "insert_success":
                result.put("message", "Insert success");
                result.put("alert", "success");
                break;
            case "delete_success":
                result.put("message", "Delete success");
                result.put("alert", "success");
                break;
            case "error_system":
                result.put("message", "Error system");
                result.put("alert", "danger");
                break;
        }
		return result;
	}
}
