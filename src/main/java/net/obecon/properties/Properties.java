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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nonnull;
import net.obecon.properties.internal.PropertiesImpl;
import net.obecon.properties.internal.PropertyGetters;
import net.obecon.properties.internal.PropertySetters;
import net.obecon.properties.internal.Utils;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public interface Properties extends Serializable, PropertyGetters, PropertySetters {

	/**
	 * Creates an immutable copy
	 *
	 * @return ImmutableProperties
	 */
	@Nonnull
	default ImmutableProperties immutable() {
		return ImmutableProperties.of(asMap());
	}

	/**
	 * Creates Properties from the given <code>map</code>.
	 *
	 * @param map source
	 * @return Properties
	 */
	@Nonnull
	static Properties of(@Nonnull Map<String, String> map) {
		return new PropertiesImpl(map);
	}

	/**
	 * Creates Properties from the given <code>properties</code>.
	 * Only {@link java.util.Properties#stringPropertyNames()} are copied.
	 *
	 * @param properties source
	 * @return Properties
	 */
	@Nonnull
	static Properties of(@Nonnull java.util.Properties properties) {
		return new PropertiesImpl(Utils.asMap(properties));
	}


	/**
	 * Reads properties from the given properties file using <code>UTF-8</code> as file encoding.
	 *
	 * @param filePath path to properties file
	 * @return Properties
	 * @throws IOException
	 */
	@Nonnull
	static Properties readProperties(@Nonnull String filePath) throws IOException {
		return readProperties(new File(filePath));
	}


	/**
	 * Reads properties from the given properties file using <code>UTF-8</code> as file encoding.
	 *
	 * @param file path to properties file
	 * @return Properties
	 * @throws IOException
	 */
	@Nonnull
	static Properties readProperties(@Nonnull File file) throws IOException {
		return readProperties(file, "UTF-8");
	}


	/**
	 * Reads properties from the given file using the given encoding.
	 *
	 * @param file     path to properties file
	 * @param encoding file encoding to use
	 * @return Properties
	 * @throws IOException
	 */
	@Nonnull
	static Properties readProperties(@Nonnull File file, @Nonnull String encoding) throws IOException {
		return readProperties(new FileInputStream(file), encoding);
	}

	/**
	 * Reads properties from the given inputstream using the given encoding.
	 *
	 * @param inputStream data source
	 * @param encoding    encoding to use
	 * @return Properties
	 * @throws IOException
	 */
	static Properties readProperties(@Nonnull InputStream inputStream, @Nonnull String encoding) throws IOException {
		java.util.Properties javaProps = new java.util.Properties();
		try (Reader reader = new InputStreamReader(inputStream, encoding)) {
			javaProps.load(reader);
		}
		return Properties.of(javaProps);
	}
}
