package com.example.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public final class JacocoCeshi {
    public static void main(final String[] args)  {
    	System.out.println("sdf");
    	try {
			 Socket socket = new Socket(InetAddress.getByName("39.106.188.246"), 8763);
			OutputStream outputStream = socket.getOutputStream();
			System.out.println(outputStream);
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}