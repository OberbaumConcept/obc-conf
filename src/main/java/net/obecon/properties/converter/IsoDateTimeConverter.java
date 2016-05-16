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

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import javax.annotation.Nonnull;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class IsoDateTimeConverter extends AbstractConverter<OffsetDateTime> {

	private final DateTimeFormatter formatter;


	public IsoDateTimeConverter(@Nonnull ZoneOffset defaultZoneOffset) {
		super(OffsetDateTime.class);
		this.formatter = new DateTimeFormatterBuilder()
				.append(DateTimeFormatter.ISO_DATE_TIME)
				.parseDefaulting(ChronoField.OFFSET_SECONDS, defaultZoneOffset.getTotalSeconds())
				.toFormatter();
	}


	@Override
	protected OffsetDateTime doFromString(String value) throws Exception {
		return OffsetDateTime.parse(value, formatter);
	}


	@Override
	protected String doToString(OffsetDateTime object) {
		return formatter.format(object);
	}
}
