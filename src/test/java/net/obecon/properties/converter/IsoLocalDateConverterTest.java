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

/**
 * Created by Janne K. Olesen on 15.05.2016.
 */
public class IsoLocalDateConverterTest extends ConverterTestBase<LocalDate> {

	public IsoLocalDateConverterTest() {
		super(IsoLocalDateConverter.INSTANCE);
	}


	@Override
	protected Object[] fromStringParameters() {
		return new Object[]{
				createTestCase("2016-05-15", LocalDate.of(2016, 5, 15)),
				createTestCaseFromStringException("2016-05-15+05:00"),
				createTestCaseFromStringException("2016-05-32")
		};
	}


	@Override
	protected Object[] toStringParameters() {
		return new Object[]{
				createTestCase("2016-05-15", LocalDate.of(2016, 5, 15))
		};
	}
}