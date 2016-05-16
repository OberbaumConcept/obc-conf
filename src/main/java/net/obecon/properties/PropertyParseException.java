/*
 * © Copyright 2016 Oberbaum Concept UG, Berlin, Germany - http://www.oberbaum-concept.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.obecon.properties;

import javax.annotation.Nonnull;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class PropertyParseException extends RuntimeException {

	public static PropertyParseException deserializeError(String key, Throwable t) {
		return new PropertyParseException(String.format("failed to deserialize property '%s'", key), t);
	}


	public static PropertyParseException parseError(String key, String value, Class<?> targetClass) {
		return new PropertyParseException(
				String.format("failed to parse property '%s'='%s' to %s)", key, value, targetClass.getName()), null);
	}


	public static PropertyParseException parseError(String key, String value, Class<?> targetClass, @Nonnull Throwable cause) {
		return new PropertyParseException(
				String.format("failed to parse property '%s'='%s' to %s: %s", key, value, targetClass.getName(), cause
						.getMessage()), cause);
	}


	private PropertyParseException(String message, Throwable cause) {
		super(message, cause);
	}
}

