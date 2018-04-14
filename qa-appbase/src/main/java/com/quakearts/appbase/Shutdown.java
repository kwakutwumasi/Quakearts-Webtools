/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.appbase;

import java.io.IOException;
import java.net.Socket;

import javax.enterprise.inject.Vetoed;
import javax.net.SocketFactory;

@Vetoed
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
