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

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Map;
import javax.annotation.Nonnull;
import net.obecon.properties.MissingPropertyException;
import net.obecon.properties.PropertyParseException;
import net.obecon.properties.converter.BooleanConverter;
import net.obecon.properties.converter.CharConverter;
import net.obecon.properties.converter.Converter;
import net.obecon.properties.converter.DoubleConverter;
import net.obecon.properties.converter.FloatConverter;
import net.obecon.properties.converter.IntConverter;
import net.obecon.properties.converter.LongConverter;
import net.obecon.properties.converter.ParseException;
import net.obecon.properties.converter.ShortConverter;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public interface PropertyGetters {


	boolean hasKey(@Nonnull String key);

	default <T extends Serializable> T get(@Nonnull String key) throws MissingPropertyException {
		try {
			final byte[] bytes = Base64.getDecoder().decode(getAsString(key));
			final ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			try (final ObjectInputStream ois = new ObjectInputStream(bis)) {
				//noinspection unchecked,unchecked
				return (T) ois.readObject();
			}
		} catch (MissingPropertyException e) {
			throw e;
		} catch (Exception e) {
			throw PropertyParseException.deserializeError(key, e);
		}
	}

	default <T extends Serializable> T get(@Nonnull String key, T defaultValue) throws PropertyParseException {
		return hasKey(key) ? get(key) : defaultValue;
	}

	default <T> T get(@Nonnull String key, @Nonnull Converter<T> converter) throws MissingPropertyException, PropertyParseException {
		final String value = getAsString(key);
		try {
			return converter.fromString(value);
		} catch (ParseException e) {
			throw PropertyParseException.parseError(key, value, converter.getTargetClass(), e);
		}
	}


	default <T> T get(@Nonnull String key, @Nonnull Converter<T> converter, T defaultValue) throws PropertyParseException {
		return hasKey(key) ? get(key, converter) : defaultValue;
	}

	default char getAsChar(@Nonnull String key) throws MissingPropertyException, PropertyParseException {
		return get(key, CharConverter.INSTANCE);
	}

	default char getAsChar(@Nonnull String key, char defaultValue) throws PropertyParseException {
		return hasKey(key) ? getAsChar(key) : defaultValue;
	}

	String getAsString(@Nonnull String key) throws MissingPropertyException;

	default String getAsString(@Nonnull String key, String defaultValue) {
		return hasKey(key) ? getAsString(key) : defaultValue;
	}

	default boolean getAsBoolean(@Nonnull String key) throws MissingPropertyException, PropertyParseException {
		return get(key, BooleanConverter.INSTANCE);
	}

	default boolean getAsBoolean(@Nonnull String key, boolean defaultValue) throws PropertyParseException {
		return hasKey(key) ? getAsBoolean(key) : defaultValue;
	}

	default short getAsShort(@Nonnull String key) throws MissingPropertyException, PropertyParseException {
		return get(key, ShortConverter.INSTANCE);
	}

	default short getAsShort(@Nonnull String key, short defaultValue) throws PropertyParseException {
		return hasKey(key) ? getAsShort(key) : defaultValue;
	}

	default int getAsInt(@Nonnull String key) throws MissingPropertyException, PropertyParseException {
		return get(key, IntConverter.INSTANCE);
	}

	default int getAsInt(@Nonnull String key, int defaultValue) throws PropertyParseException {
		return hasKey(key) ? getAsInt(key) : defaultValue;
	}

	default long getAsLong(@Nonnull String key) throws MissingPropertyException, PropertyParseException {
		return get(key, LongConverter.INSTANCE);
	}

	default long getAsLong(@Nonnull String key, long defaultValue) throws PropertyParseException {
		return hasKey(key) ? getAsLong(key) : defaultValue;
	}

	default float getAsFloat(@Nonnull String key) throws MissingPropertyException, PropertyParseException {
		return get(key, FloatConverter.INSTANCE);
	}

	default float getAsFloat(@Nonnull String key, float defaultValue) throws PropertyParseException {
		return hasKey(key) ? getAsFloat(key) : defaultValue;
	}

	default double getAsDouble(@Nonnull String key) throws MissingPropertyException, PropertyParseException {
		return get(key, DoubleConverter.INSTANCE);
	}

	default double getAsDouble(@Nonnull String key, double defaultValue) throws PropertyParseException {
		return hasKey(key) ? getAsDouble(key) : defaultValue;
	}

	Map<String, String> asMap();
}
