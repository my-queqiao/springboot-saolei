package com.example.demo;

import java.util.stream.Stream;

public class TestStackTrace {

	    public void aa(){
	        bb();
	    }

	    public void bb(){
	        cc();
	    }

	    public void cc(){
	        Thread.getAllStackTraces().forEach(
	                (k,v)->{

	                    Stream.of(v)
	                            .filter(trace->trace.getClassName().equals(this.getClass().getName()))
	                            .map(StackTraceElement::getMethodName)
	                            .forEach(System.out::println);
	                }
	        );

	    }
	    public static void main(String[] args) {
			
		}
}
