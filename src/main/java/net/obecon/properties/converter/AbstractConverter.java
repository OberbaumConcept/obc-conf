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

package net.obecon.properties.converter;

import javax.annotation.Nonnull;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public abstract class AbstractConverter<T> implements Converter<T> {

	private final Class<T> targetClass;


	protected AbstractConverter(Class<T> targetClass) {
		this.targetClass = targetClass;
	}


	@Override
	public T fromString(@Nonnull String value) throws ParseException {
		try {
			return doFromString(value);
		} catch (ParseException e) {
			throw e;
		} catch (Exception e) {
			throw new ParseException(value, e);
		}
	}


	@Override
	public String toString(T object) {
		return object != null ? doToString(object) : null;
	}


	@Nonnull
	@Override
	public Class<T> getTargetClass() {
		return targetClass;
	}


	protected abstract T doFromString(String value) throws Exception;

	protected String doToString(T object) {
		return object.toString();
	}
}
