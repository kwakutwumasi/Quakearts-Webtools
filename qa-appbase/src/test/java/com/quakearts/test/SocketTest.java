/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.test;

import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;

public class SocketTest {
	
	public static void main(String[] args) {
		new Thread(()->{
			try (Socket socket = SocketFactory.getDefault().createSocket("127.0.0.1", 9999)) {
				socket.getOutputStream().write(0x01b);
				System.out.println("Done");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
		new Thread(()->{
			try (ServerSocket socket = ServerSocketFactory.getDefault().createServerSocket(9999)) {
				while (true) {
					Socket incomingSocket = socket.accept();
					int read = incomingSocket.getInputStream().read();
					System.out.println("Done "+read);	
				}
			} catch (Exception e) {
			}
		}).start();
	}
	
}
