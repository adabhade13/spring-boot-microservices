package com.tatamotors.dealers.cb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {
	public static void main(String[] args) {
		String input =  "I like coding. Coding makes me happy. I am doing coding in java";
		List<String> list = new ArrayList<>();
		String[] words=input.toLowerCase().split(" ");
		Map<String, Long> collect = Stream.of(words).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(collect);
		/*
		 * System.out.println(words[0]); for (String lines : words) { String[] word
		 * =lines.split(" "); for(String str : word) { list.add(str); } }
		 */
		System.out.println(list);
		
		List<Integer> l = new ArrayList<Integer>();
		l.add(23);
		l.add(13);
		l.add(2);
		l.add(78);
		l.add(73);
		
		Integer integer = l.stream().max((i1,i2) -> i1.compareTo(i2)).get();
		
		System.out.println(integer);
		
		
		
		
		
		
		
	}

}
