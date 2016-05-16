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

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class BooleanConverterTest extends ConverterTestBase<Boolean> {

	public BooleanConverterTest() {
		super(BooleanConverter.INSTANCE);
	}


	@Override
	protected Object[] fromStringParameters() {
		return new Object[]{
				createTestCase("trUe", true),
				createTestCase("YeS", true),
				createTestCase("oN", true),
				createTestCase("1", true),
				createTestCase("falSe", false),
				createTestCase("nO", false),
				createTestCase("oFf", false),
				createTestCase("0", false),
				createTestCaseFromStringException("noBoolean"),
				createTestCaseFromStringException(null)
		};
	}


	@Override
	protected Object[] toStringParameters() {

		return new Object[]{
				createTestCase("true", true),
				createTestCase("false", false),
				createTestCase(null, null)

		};
	}
}