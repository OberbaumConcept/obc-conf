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
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.annotation.Nonnull;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class IsoLocalDateTimeConverter extends AbstractConverter<LocalDateTime> {


	public static final IsoLocalDateTimeConverter INSTANCE = new IsoLocalDateTimeConverter(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	private final DateTimeFormatter formatter;


	protected IsoLocalDateTimeConverter(@Nonnull DateTimeFormatter formatter) {
		super(LocalDateTime.class);
		this.formatter = Objects.requireNonNull(formatter);
	}

	@Override
	protected LocalDateTime doFromString(String value) throws Exception {
		return LocalDateTime.parse(value, formatter);
	}


	@Override
	protected String doToString(LocalDateTime object) {
		return formatter.format(object);
	}
}
