package com.quakearts.appbase;

import java.io.IOException;
import java.net.Socket;

import javax.net.SocketFactory;

public class Shutdown {

	public static void main(String[] args) {
		int port = 9999;
		if(args.length>0)
			try {
				port = Integer.parseInt(args[0]);				
			} catch (NumberFormatException e) {
				System.out.println("Could not parse port:"+args[0]);
				return;
			}
		
		try(Socket socket = SocketFactory.getDefault().createSocket("127.0.0.1", port);) {
			socket.getOutputStream().write(0xFA);
		} catch (IOException e) {
			System.out.println("Could not connect to local process:"+e.getMessage());
		}
	}

}
