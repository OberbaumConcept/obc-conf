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

import java.lang.reflect.Method;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.obecon.properties.PropertyParseException;
import net.obecon.properties.converter.BooleanConverter;
import net.obecon.properties.converter.CharConverter;
import net.obecon.properties.converter.Converter;
import net.obecon.properties.converter.DoubleConverter;
import net.obecon.properties.converter.FloatConverter;
import net.obecon.properties.converter.IntConverter;
import net.obecon.properties.converter.LongConverter;
import net.obecon.properties.converter.ShortConverter;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
@RunWith(JUnitParamsRunner.class)
public class PropertyGettersTest {

	private static final String KEY = "key";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private PropertyGetters propertyGetters;


	@Before
	public void setUp() throws Exception {
		propertyGetters = mock(PropertyGetters.class, withSettings()
				.defaultAnswer(invocation -> invocation.getMethod().isDefault() ? invocation
						.callRealMethod() : RETURNS_DEFAULTS.answer(invocation)));
	}


	@After
	public void tearDown() throws Exception {

	}


	private Object[] parameters() {
		return new Object[]{
				new GetAsTestCase("getAsChar", CharConverter.INSTANCE, "c", 'c', 'a', char.class),
				new GetAsTestCase("getAsBoolean", BooleanConverter.INSTANCE, "true", true, false, boolean.class),
				new GetAsTestCase("getAsShort", ShortConverter.INSTANCE, "1", (short) 1, (short) 0, short.class),
				new GetAsTestCase("getAsInt", IntConverter.INSTANCE, "1", 1, 0, int.class),
				new GetAsTestCase("getAsLong", LongConverter.INSTANCE, "1", 1L, 0L, long.class),
				new GetAsTestCase("getAsFloat", FloatConverter.INSTANCE, "1", 1f, 0f, float.class),
				new GetAsTestCase("getAsDouble", DoubleConverter.INSTANCE, "1", 1.0, 1.0, double.class),
		};
	}


	@Test
	public void getAsString_withDefault_getProperty() throws Throwable {
		final String value = "string";
		when(propertyGetters.hasKey(KEY)).thenReturn(true);
		when(propertyGetters.getAsString(KEY)).thenReturn(value);
		assertEquals(value, propertyGetters.getAsString(KEY, ""));
	}


	@Test
	public void getAsString_withDefault_getDefault() throws Throwable {
		final String value = "string";
		when(propertyGetters.hasKey(KEY)).thenReturn(false);
		assertEquals(value, propertyGetters.getAsString(KEY, value));
	}


	@Test
	public void get_ParseException() throws Throwable {
		thrown.expect(PropertyParseException.class);
		thrown.expectMessage(startsWith("failed to parse property 'key'='string' to java.lang.Boolean"));
		when(propertyGetters.getAsString(KEY)).thenReturn("string");
		propertyGetters.get(KEY, BooleanConverter.INSTANCE);
	}


	@Parameters(method = "parameters")
	@Test
	public void get(GetAsTestCase testCase) throws Throwable {
		when(propertyGetters.getAsString(KEY)).thenReturn(testCase.value);
		Object result = propertyGetters.get(KEY, testCase.converter);
		assertEquals(testCase.expected, result);
	}


	@Parameters(method = "parameters")
	@Test
	public void get_withDefault_getProperty(GetAsTestCase testCase) throws Throwable {
		when(propertyGetters.getAsString(KEY)).thenReturn(testCase.value);
		Object result = propertyGetters.get(KEY, testCase.converter);
		assertEquals(testCase.expected, result);
	}


	@Parameters(method = "parameters")
	@Test
	public void get_withDefault_getDefault(GetAsTestCase testCase) throws Throwable {
		when(propertyGetters.hasKey(KEY)).thenReturn(false);
		Object result = propertyGetters.get(KEY, testCase.converter, testCase.defaultValue);
		verify(propertyGetters).hasKey(eq(KEY));
		assertEquals(testCase.defaultValue, result);
	}


	@Parameters(method = "parameters")
	@Test
	public void getAs(GetAsTestCase testCase) throws Throwable {
		when(propertyGetters.getAsString(KEY)).thenReturn(testCase.value);
		Method method = PropertyGetters.class.getDeclaredMethod(testCase.methodName, String.class);
		Object result = method.invoke(propertyGetters, KEY);
		verify(propertyGetters).get(eq(KEY), eq(testCase.converter));
		assertEquals(testCase.expected, result);
	}


	@Parameters(method = "parameters")
	@Test
	public void getAs_withDefault_getProperty(GetAsTestCase testCase) throws Throwable {
		when(propertyGetters.hasKey(KEY)).thenReturn(true);
		when(propertyGetters.getAsString(KEY)).thenReturn(testCase.value);
		Method method = PropertyGetters.class
				.getDeclaredMethod(testCase.methodName, String.class, testCase.resultClass);
		Object result = method.invoke(propertyGetters, KEY, testCase.expected);
		verify(propertyGetters).hasKey(eq(KEY));
		verify(propertyGetters).get(eq(KEY), eq(testCase.converter));
		assertEquals(testCase.expected, result);
	}


	@Parameters(method = "parameters")
	@Test
	public void getAs_withDefault_getDefault(GetAsTestCase testCase) throws Throwable {
		when(propertyGetters.hasKey(KEY)).thenReturn(false);
		Method method = PropertyGetters.class
				.getDeclaredMethod(testCase.methodName, String.class, testCase.resultClass);
		Object result = method.invoke(propertyGetters, KEY, testCase.defaultValue);
		verify(propertyGetters).hasKey(eq(KEY));
		assertEquals(testCase.defaultValue, result);
	}


	static class GetAsTestCase<T> {

		final String methodName;
		final Converter<T> converter;
		final String value;
		final T expected;
		final T defaultValue;
		final Class<?> resultClass;


		public GetAsTestCase(String methodName, Converter<T> converter, String value, T expected, T defaultValue, Class<?> resultClass) {
			this.methodName = methodName;
			this.converter = converter;
			this.value = value;
			this.expected = expected;
			this.defaultValue = defaultValue;
			this.resultClass = resultClass;
		}


		@Override
		public String toString() {
			return methodName;
		}
	}
}