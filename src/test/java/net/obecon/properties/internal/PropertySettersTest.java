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

import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import junitparams.JUnitParamsRunner;
import net.obecon.properties.Properties;
import net.obecon.properties.TestUtils.SerializableObject;
import net.obecon.properties.converter.BooleanConverter;
import net.obecon.properties.converter.CharConverter;
import net.obecon.properties.converter.DoubleConverter;
import net.obecon.properties.converter.FloatConverter;
import net.obecon.properties.converter.IntConverter;
import net.obecon.properties.converter.LongConverter;
import net.obecon.properties.converter.ShortConverter;
import static net.obecon.properties.TestUtils.serializeToBase64;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
@RunWith(JUnitParamsRunner.class)
public class PropertySettersTest {

	private static final String KEY = "key";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private PropertySetters propertySetters;
	private ArgumentCaptor<String> capturedKey;
	private ArgumentCaptor<String> capturedValue;
	private ArgumentCaptor<Map<String, String>> capturedMap;


	@Before
	public void setUp() throws Exception {
		capturedKey = ArgumentCaptor.forClass(String.class);
		capturedValue = ArgumentCaptor.forClass(String.class);
		capturedMap = ArgumentCaptor.forClass(Map.class);

		propertySetters = mock(PropertySetters.class, withSettings()
				.defaultAnswer(invocation -> invocation.getMethod().isDefault() ? invocation
						.callRealMethod() : RETURNS_DEFAULTS.answer(invocation)));
		doNothing().when(propertySetters).set(capturedKey.capture(), capturedValue.capture());
		doNothing().when(propertySetters).addAll(capturedMap.capture());
	}


	@After
	public void tearDown() throws Exception {

	}


	@Test
	public void addAll_javaProperties() throws Exception {
		java.util.Properties properties = new java.util.Properties();
		properties.setProperty("key1", "val1");
		properties.setProperty("key2", "val2");
		propertySetters.addAll(properties);
		assertNotNull("captured", capturedMap.getValue());
		assertEquals("size", 2, capturedMap.getValue().size());
		assertTrue("contains key1", capturedMap.getValue().containsKey("key1"));
		assertEquals("value for key1", "val1", capturedMap.getValue().get("key1"));
		assertTrue("contains key2", capturedMap.getValue().containsKey("key2"));
		assertEquals("value for key2", "val2", capturedMap.getValue().get("key2"));
	}


	@Test
	public void addAll_Properties() throws Exception {
		Properties properties = Properties.create();
		properties.set("key1", "val1");
		properties.set("key2", "val2");
		propertySetters.addAll(properties);
		assertNotNull("captured", capturedMap.getValue());
		assertEquals("size", 2, capturedMap.getValue().size());
		assertTrue("contains key1", capturedMap.getValue().containsKey("key1"));
		assertEquals("value for key1", "val1", capturedMap.getValue().get("key1"));
		assertTrue("contains key2", capturedMap.getValue().containsKey("key2"));
		assertEquals("value for key2", "val2", capturedMap.getValue().get("key2"));
	}


	@Test
	public void set() throws Exception {
		final SerializableObject object = new SerializableObject("object");
		propertySetters.set(KEY, object);
		assertEquals("key", KEY, capturedKey.getValue());
		assertEquals("value", serializeToBase64(object), capturedValue.getValue());
	}


	@Test
	public void set_null() throws Exception {
		propertySetters.set(KEY, (SerializableObject) null);
		assertEquals("key", KEY, capturedKey.getValue());
		assertNull("value", capturedValue.getValue());
	}


	@Test
	public void set_Boolean() throws Throwable {
		final boolean value = true;
		propertySetters.set(KEY, value);
		assertEquals("key", KEY, capturedKey.getValue());
		assertEquals("value", BooleanConverter.INSTANCE.toString(value), capturedValue.getValue());
	}


	@Test
	public void set_Char() throws Throwable {
		final char value = 'a';
		propertySetters.set(KEY, value);
		assertEquals("key", KEY, capturedKey.getValue());
		assertEquals("value", CharConverter.INSTANCE.toString(value), capturedValue.getValue());
	}


	@Test
	public void set_Short() throws Throwable {
		final short value = 1;
		propertySetters.set(KEY, value);
		assertEquals("key", KEY, capturedKey.getValue());
		assertEquals("value", ShortConverter.INSTANCE.toString(value), capturedValue.getValue());
	}


	@Test
	public void set_Int() throws Throwable {
		final int value = 1;
		propertySetters.set(KEY, value);
		assertEquals("key", KEY, capturedKey.getValue());
		assertEquals("value", IntConverter.INSTANCE.toString(value), capturedValue.getValue());
	}


	@Test
	public void set_Long() throws Throwable {
		final long value = 1;
		propertySetters.set(KEY, value);
		assertEquals("key", KEY, capturedKey.getValue());
		assertEquals("value", LongConverter.INSTANCE.toString(value), capturedValue.getValue());
	}


	@Test
	public void set_Float() throws Throwable {
		final float value = 1;
		propertySetters.set(KEY, value);
		assertEquals("key", KEY, capturedKey.getValue());
		assertEquals("value", FloatConverter.INSTANCE.toString(value), capturedValue.getValue());
	}


	@Test
	public void set_Double() throws Throwable {
		final double value = 1;
		propertySetters.set(KEY, value);
		assertEquals("key", KEY, capturedKey.getValue());
		assertEquals("value", DoubleConverter.INSTANCE.toString(value), capturedValue.getValue());
	}
}