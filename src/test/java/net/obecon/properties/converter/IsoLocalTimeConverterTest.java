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

import java.time.LocalTime;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class IsoLocalTimeConverterTest extends ConverterTestBase<LocalTime> {

	public IsoLocalTimeConverterTest() {
		super(IsoLocalTimeConverter.INSTANCE);
	}


	@Override
	protected Object[] fromStringParameters() {
		return new Object[]{
				createTestCase("14:15", LocalTime.of(14, 15)),
				createTestCase("14:15:16", LocalTime.of(14, 15, 16)),
				createTestCase("14:15:16.17", LocalTime.of(14, 15, 16, 170000000)),
				createTestCaseFromStringException("14:15+05:00"),
				createTestCaseFromStringException("25:00")
		};
	}


	@Override
	protected Object[] toStringParameters() {
		return new Object[]{
				createTestCase("14:15:00", LocalTime.of(14, 15)),
				createTestCase("14:15:16", LocalTime.of(14, 15, 16)),
				createTestCase("14:15:16.17", LocalTime.of(14, 15, 16, 170000000))
		};
	}
}