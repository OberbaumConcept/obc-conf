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

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class IsoZonedDateTimeConverterTest extends ConverterTestBase<ZonedDateTime> {

	private static final ZoneId zone = ZoneId.of("Europe/Berlin");


	public IsoZonedDateTimeConverterTest() {
		super(IsoZonedDateTimeConverter.INSTANCE);
	}


	@Override
	protected Object[] fromStringParameters() {
		return new Object[]{
				createTestCase("2016-05-16T14:15+02:00[Europe/Berlin]",
						ZonedDateTime.of(2016, 5, 16, 14, 15, 0, 0, zone)),
				createTestCase("2016-05-16T14:15:16+02:00[Europe/Berlin]",
						ZonedDateTime.of(2016, 5, 16, 14, 15, 16, 0, zone)),
				createTestCase("2016-05-16T14:15:16.17+02:00[Europe/Berlin]",
						ZonedDateTime.of(2016, 5, 16, 14, 15, 16, 170000000, zone)),
				createTestCaseFromStringException("2016-05-16"),
				createTestCaseFromStringException("2016-05-16+02:00[Europe/Berlin]"),
				createTestCaseFromStringException("14:15"),
				createTestCaseFromStringException("14:15+02:00[Europe/Berlin]"),
				createTestCaseFromStringException("2016-05-32T14:15+02:00[Europe/Berlin]")
		};
	}


	@Override
	protected Object[] toStringParameters() {
		return new Object[]{
				createTestCase("2016-05-16T14:15:00+02:00[Europe/Berlin]",
						ZonedDateTime.of(2016, 5, 16, 14, 15, 0, 0, zone)),
				createTestCase("2016-05-16T14:15:16+02:00[Europe/Berlin]",
						ZonedDateTime.of(2016, 5, 16, 14, 15, 16, 0, zone)),
				createTestCase("2016-05-16T14:15:16.17+02:00[Europe/Berlin]",
						ZonedDateTime.of(2016, 5, 16, 14, 15, 16, 170000000, zone))
		};
	}
}