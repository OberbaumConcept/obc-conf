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

import java.time.OffsetTime;
import java.time.ZoneOffset;

/**
 * Created by Janne K. Olesen on 15.05.2016.
 */
public class IsoOffsetTimeConverterTest extends ConverterTestBase<OffsetTime> {

	private static final ZoneOffset utcPlus5 = ZoneOffset.ofHours(5);
	private static final ZoneOffset utcMinus5 = ZoneOffset.ofHours(-5);


	public IsoOffsetTimeConverterTest() {
		super(IsoOffsetTimeConverter.INSTANCE);
	}


	@Override
	protected Object[] fromStringParameters() {
		return new Object[]{
				createTestCase("14:15+05:00", OffsetTime.of(14, 15, 0, 0, utcPlus5)),
				createTestCase("14:15-05:00", OffsetTime.of(14, 15, 0, 0, utcMinus5)),
				createTestCase("14:15:16+05:00", OffsetTime.of(14, 15, 16, 0, utcPlus5)),
				createTestCase("14:15:16-05:00", OffsetTime.of(14, 15, 16, 0, utcMinus5)),
				createTestCase("14:15:16.17+05:00", OffsetTime.of(14, 15, 16, 170000000, utcPlus5)),
				createTestCase("14:15:16.17-05:00", OffsetTime.of(14, 15, 16, 170000000, utcMinus5)),
				createTestCaseFromStringException("14:15:16"),
				createTestCaseFromStringException("25:00+05:00")

		};
	}


	@Override
	protected Object[] toStringParameters() {
		return new Object[]{
				createTestCase("14:15:00+05:00", OffsetTime.of(14, 15, 0, 0, utcPlus5)),
				createTestCase("14:15:00-05:00", OffsetTime.of(14, 15, 0, 0, utcMinus5)),
				createTestCase("14:15:16+05:00", OffsetTime.of(14, 15, 16, 0, utcPlus5)),
				createTestCase("14:15:16-05:00", OffsetTime.of(14, 15, 16, 0, utcMinus5)),
				createTestCase("14:15:16.17+05:00", OffsetTime.of(14, 15, 16, 170000000, utcPlus5)),
				createTestCase("14:15:16.17-05:00", OffsetTime.of(14, 15, 16, 170000000, utcMinus5))
		};
	}
}