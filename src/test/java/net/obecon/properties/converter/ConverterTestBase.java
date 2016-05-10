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
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeThat;

/**
 * @author Janne K. Olesen &lt;janne.olesen@unbelievable-machine.com&gt;
 */
@RunWith(JUnitParamsRunner.class)
public abstract class ConverterTestBase<T> {

	private final Converter<T> converter;


	protected ConverterTestBase(Converter<T> converter) {
		this.converter = converter;
	}


	@Rule
	public ExpectedException thrown = ExpectedException.none();


	protected abstract Object[] toStringParameters();


	protected Object[] fromStringParameters() {
		return toStringParameters();
	}


	@Parameters(method = "toStringParameters")
	@Test
	public void toString(TestCase<T> testCase) {
		assumeFalse(testCase.fromStringException);
		assertEquals(testCase.string, converter.toString(testCase.value));
	}


	@Parameters(method = "fromStringParameters")
	@Test
	public void fromString(TestCase<T> testCase) {
		if (testCase.fromStringException) {
			thrown.expect(ParseException.class);
		}
		assertEquals(testCase.value, converter.fromString(testCase.string));
	}


	public TestCase<T> createTestCase(String string, T value) {
		return new TestCase<T>(string, value, false, false);
	}


	public TestCase<T> createTestCaseFromStringException(String string) {
		return new TestCase<T>(string, null, false, true);
	}


	protected class TestCase<T> {

		final String string;
		final T value;
		final boolean fromStringException;


		private TestCase(String string, T value, boolean toStringException, boolean fromStringException) {
			this.string = string;
			this.value = value;
			this.fromStringException = fromStringException;
		}
	}
}
