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

import java.io.File;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class FileConverter extends AbstractConverter<File> {

	public static final FileConverter INSTANCE = new FileConverter();


	private FileConverter() {
		super(File.class);
	}


	@Override
	protected File doFromString(String value) throws Exception {
		if (StringUtils.isEmpty(value)) {
			throw new ParseException("file can't be create");
		}
		return new File(value);
	}
}
