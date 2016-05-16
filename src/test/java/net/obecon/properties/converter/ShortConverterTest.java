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
 * Created by Janne K. Olesen on 10.05.2016.
 */
public class ShortConverterTest extends ConverterTestBase<Short> {

	public ShortConverterTest() {
		super(ShortConverter.INSTANCE);
	}


	@Override
	protected Object[] fromStringParameters() {
		return new Object[]{
				createTestCase(Short.toString(Short.MIN_VALUE), Short.MIN_VALUE),
				createTestCase(Short.toString(Short.MAX_VALUE), Short.MAX_VALUE),
				createTestCaseFromStringException(null),
				createTestCaseFromStringException(""),
				createTestCaseFromStringException(Integer.toString(Short.MIN_VALUE - 1)),
				createTestCaseFromStringException(Integer.toString(Short.MAX_VALUE + 1)),
				createTestCaseFromStringException("1.0")
		};
	}


	@Override
	protected Object[] toStringParameters() {
		return new Object[]{
				createTestCase(Short.toString(Short.MIN_VALUE), Short.MIN_VALUE),
				createTestCase(Short.toString(Short.MAX_VALUE), Short.MAX_VALUE),
				createTestCase("0", (short) -0)
		};
	}
}