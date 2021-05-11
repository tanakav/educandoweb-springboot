package com.educandoweb.api.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class URL {
	
	public static List<Integer> decoreIntList(String s){
		String[] integers = s.split(",");
		
		List<Integer> list = Arrays.asList(integers)
				.stream()
				.map(number -> Integer.parseInt(number))
				.collect(Collectors.toList());
		
		return list;
	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
}
