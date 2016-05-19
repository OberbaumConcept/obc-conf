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

package net.obecon.properties.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class Utils {

	private Utils() {
	}


	/**
	 * Returns all keys from {@linkplain Properties#stringPropertyNames()}
	 * with their values as map
	 *
	 * @param properties properties to convert
	 * @return result map
	 */
	@Nonnull
	public static Map<String, String> asMap(@Nonnull Properties properties) {
		Map<String, String> map = new HashMap<>();
		properties.stringPropertyNames().forEach(k -> map.put(k, properties.getProperty(k)));
		return map;
	}


	/**
	 * Copies all entries from <code>source</code> to <code>target</code>, except <code>null</code> keys
	 *
	 * @param source entries to copy
	 * @param target target to copy entries to
	 */
	public static <K, V> void addAll(@Nullable Map<K, V> source, @Nonnull Map<K, V> target) {
		if (source != null) {
			source.entrySet().stream().filter(e -> e.getKey() != null)
					.forEach(e -> target.put(e.getKey(), e.getValue()));
		}
	}
}
