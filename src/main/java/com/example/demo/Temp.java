package com.example.demo;

import java.util.HashSet;
import java.util.Set;

public class Temp {
	public static void main(String[] args) {
		System.out.println(111);
		Set<O> o = new HashSet<>();
		O o1 = new O("zhangsan");
		O o2 = new O("zhangsan");
		o.add(o1);
		o.add(o2);
		System.out.println(o);
	}
}
class O {
	String name;
	public O(String name) {
		
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}