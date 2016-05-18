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

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Nonnull;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public interface PropertySetters {

	void set(@Nonnull String key, String value);

	void addAll(@Nonnull Map<String, String> map);

	default void addAll(@Nonnull Properties properties) {
		addAll(Utils.asMap(properties));
	}

	default void set(@Nonnull String key, char value) {
		set(key, Character.toString(value));
	}

	default void set(@Nonnull String key, short value) {
		set(key, Short.toString(value));
	}

	default void set(@Nonnull String key, int value) {
		set(key, Integer.toString(value));
	}

	default void set(@Nonnull String key, long value) {
		set(key, Long.toString(value));
	}

	default void set(@Nonnull String key, float value) {
		set(key, Float.toString(value));
	}

	default void set(@Nonnull String key, double value) {
		set(key, Double.toString(value));
	}

	default void set(@Nonnull String key, boolean value) {
		set(key, Boolean.toString(value));
	}

	default <T extends Serializable> void set(@Nonnull String key, T value) {
		if (value == null) {
			set(key, (String) null);
		} else {
			// serialize the object
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
				oos.writeObject(value);
				oos.flush();
				set(key, Base64.getEncoder().encodeToString(bos.toByteArray()));
			} catch (Exception e) {
				throw new RuntimeException("Should never had happened", e);
			}
		}
	}
}
