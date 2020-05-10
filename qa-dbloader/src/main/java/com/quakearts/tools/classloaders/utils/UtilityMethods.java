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
package com.quakearts.tools.classloaders.utils;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UtilityMethods {
	
	private UtilityMethods() {}
	
	public static ZipEntry findZipEntry(String entryFileName, ZipInputStream jarStream) throws IOException {
		ZipEntry zipEntry=null;
		while((zipEntry = jarStream.getNextEntry())!=null){
			if(!zipEntry.isDirectory() 
					&& zipEntry.getName().equals(entryFileName)){
				break;
			}
		}
		return zipEntry;
	}
}
