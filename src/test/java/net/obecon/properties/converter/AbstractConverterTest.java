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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractConverterTest {

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private AbstractConverter<String> mockConverter;

	@Rule
	public ExpectedException exception = ExpectedException.none();


	@Test
	public void fromString() throws Exception {
		final String input = "input";
		final String expected = "myResult";
		when(mockConverter.doFromString(input)).thenReturn(expected);

		assertEquals(expected, mockConverter.fromString(input));
		verify(mockConverter).doFromString(input);
	}


	@Test
	public void fromString_exception() throws Exception {
		String input = "input";
		Throwable cause = new Exception("myEx");
		when(mockConverter.doFromString(input)).thenThrow(cause);

		exception.expect(ParseException.class);
		exception.expectMessage(input);
		exception.expectCause(sameInstance(cause));

		mockConverter.fromString(input);
		verify(mockConverter).doFromString(input);
	}


	@Test
	public void testToString() throws Exception {
		final String input = "input";
		final String expected = "myResult";
		when(mockConverter.doToString(input)).thenReturn(expected);

		assertEquals(expected, mockConverter.toString(input));
		verify(mockConverter).doToString(input);
	}


	@Test
	public void getTargetClass() throws Exception {

		AbstractConverter<String> myConverter = new AbstractConverter<String>(String.class) {
			@Override
			protected String doFromString(String value) throws Exception {
				return null;
			}
		};

		assertEquals(String.class, myConverter.getTargetClass());
	}
}