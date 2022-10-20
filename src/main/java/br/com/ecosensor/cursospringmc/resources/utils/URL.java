package br.com.ecosensor.cursospringmc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static List<Integer> decodeIntList(String s) {
		
		// Exemplo com for
		String[] vector = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vector.length; i++) {
			list.add(Integer.parseInt(vector[i].trim()));
		}
		// return list
		
		// Exemplo com lambda.
		return Arrays.asList(s.split(",")).stream().map(Integer::parseInt)
				.collect(Collectors.toList());
	}
	
	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
}
