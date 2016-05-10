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
 * Parses <code>true, yes, on, 1</code> as <code>true</code>,
 * <code>false, no, off, 0</code> as <code>false</code>. Case is ignored.
 *
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class BooleanConverter extends AbstractConverter<Boolean> {

	public static final BooleanConverter INSTANCE = new BooleanConverter();


	private BooleanConverter() {
		super(Boolean.class);
	}


	@Override
	protected Boolean doFromString(String value) throws Exception {
		if (value.equalsIgnoreCase("true") ||
				value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("on")
				|| value.equalsIgnoreCase("1")) {
			return true;
		}
		if (value.equalsIgnoreCase("false") ||
				value.equalsIgnoreCase("no") || value.equalsIgnoreCase("off")
				|| value.equalsIgnoreCase("0")) {
			return false;
		}
		throw new ParseException(String.format("invalid value: %s. Expected true|false|on|off|yes|no|1|0", value));
	}
}
