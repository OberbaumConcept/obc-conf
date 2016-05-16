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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class IsoOffsetDateConverterTest extends ConverterTestBase<OffsetDateTime> {

	private static final ZoneOffset defaultZoneOffset = ZoneOffset.ofHours(1);
	private static final ZoneOffset utcPlus5 = ZoneOffset.ofHours(5);
	private static final ZoneOffset utcMinus5 = ZoneOffset.ofHours(-5);


	public IsoOffsetDateConverterTest() {
		super(IsoOffsetDateConverter.INSTANCE);
	}


	@Override
	protected Object[] fromStringParameters() {
		return new Object[]{
				createTestCase("2016-05-16+05:00",
						OffsetDateTime.of(LocalDate.of(2016, 5, 16), LocalTime.MIDNIGHT, utcPlus5)),
				createTestCase("2016-05-16-05:00",
						OffsetDateTime.of(LocalDate.of(2016, 5, 16), LocalTime.MIDNIGHT, utcMinus5)),
				createTestCaseFromStringException("2016-05-32+05:00"),
				createTestCaseFromStringException("2016-05-16")
		};
	}


	@Override
	protected Object[] toStringParameters() {
		return new Object[]{
				createTestCase("2016-05-16+05:00",
						OffsetDateTime.of(LocalDate.of(2016, 5, 16), LocalTime.MIDNIGHT, utcPlus5)),
				createTestCase("2016-05-16-05:00",
						OffsetDateTime.of(LocalDate.of(2016, 5, 16), LocalTime.MIDNIGHT, utcMinus5))
		};
	}
}