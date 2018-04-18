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
package com.quakearts.webapp.security.jwt.impl;

import java.io.IOException;

import com.quakearts.webapp.security.jwt.internal.json.Json;
import com.quakearts.webapp.security.jwt.internal.json.JsonObject;
import com.quakearts.webapp.security.util.UtilityMethods;

public abstract class JWTJsonObjectBase {
	protected JsonObject jsonObject = new JsonObject();

	public String compact() {
		try {
			return UtilityMethods.base64EncodeWithoutPadding(jsonObject.toString().getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Unable to encode");
		}
	}

	public void unCompact(String compacted) {
		String jsonString;
		try {
			jsonString = new String(UtilityMethods.base64DecodeMissingPadding(compacted));
		} catch (IOException e) {
			throw new RuntimeException("Something went wrong with the decoder");
		}

		jsonObject = Json.parse(jsonString).asObject();
	}
}
