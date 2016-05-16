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

import java.time.LocalDateTime;

/**
 * Created by Janne K. Olesen on 15.05.2016.
 */
public class IsoLocalDateTimeConverterTest extends ConverterTestBase<LocalDateTime> {

	public IsoLocalDateTimeConverterTest() {
		super(IsoLocalDateTimeConverter.INSTANCE);
	}


	@Override
	protected Object[] fromStringParameters() {
		return new Object[]{
				createTestCase("2016-05-16T14:15", LocalDateTime.of(2016, 5, 16, 14, 15)),
				createTestCase("2016-05-16T14:15:16", LocalDateTime.of(2016, 5, 16, 14, 15, 16)),
				createTestCase("2016-05-16T14:15:16.17", LocalDateTime.of(2016, 5, 16, 14, 15, 16, 170000000)),
				createTestCaseFromStringException("2016-05-16T14:15+05:00"),
				createTestCaseFromStringException("2016-05-32T14:15")
		};
	}


	@Override
	protected Object[] toStringParameters() {
		return new Object[]{
				createTestCase("2016-05-16T14:15:00", LocalDateTime.of(2016, 5, 16, 14, 15)),
				createTestCase("2016-05-16T14:15:16", LocalDateTime.of(2016, 5, 16, 14, 15, 16)),
				createTestCase("2016-05-16T14:15:16.17", LocalDateTime.of(2016, 5, 16, 14, 15, 16, 170000000))
		};
	}
}