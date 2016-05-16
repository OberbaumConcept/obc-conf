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

import java.math.BigInteger;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class LongConverterTest extends ConverterTestBase<Long> {

	public LongConverterTest() {
		super(LongConverter.INSTANCE);
	}


	@Override
	protected Object[] fromStringParameters() {
		return new Object[]{
				createTestCase(Long.toString(Long.MIN_VALUE), Long.MIN_VALUE),
				createTestCase(Long.toString(Long.MAX_VALUE), Long.MAX_VALUE),
				createTestCaseFromStringException(null),
				createTestCaseFromStringException(""),
				createTestCaseFromStringException(BigInteger.valueOf(Long.MIN_VALUE)
						.subtract(BigInteger.ONE)
						.toString()),
				createTestCaseFromStringException(BigInteger.valueOf(Long.MAX_VALUE)
						.add(BigInteger.ONE)
						.toString()),
				createTestCaseFromStringException("1.0")
		};
	}


	@Override
	protected Object[] toStringParameters() {
		return new Object[]{
				createTestCase(Long.toString(Long.MIN_VALUE), Long.MIN_VALUE),
				createTestCase(Long.toString(Long.MAX_VALUE), Long.MAX_VALUE),
				createTestCase("0", -0L)
		};
	}
}