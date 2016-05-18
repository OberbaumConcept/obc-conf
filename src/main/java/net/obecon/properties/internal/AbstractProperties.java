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

package net.obecon.properties.internal;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.text.StrSubstitutor;
import net.obecon.properties.MissingPropertyException;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public abstract class AbstractProperties implements PropertyGetters {

	protected final Map<String, String> map;


	protected AbstractProperties(@Nonnull Map<String, String> map) {
		this.map = new HashMap<>();
		Utils.addAll(map, this.map);
	}


	@Override
	public boolean hasKey(@Nonnull String key) {
		return map.get(key) != null;
	}


	@Override
	public String getAsString(@Nonnull String key) throws MissingPropertyException {
		if (!hasKey(key)) {
			throw new MissingPropertyException(key);
		}
		return StrSubstitutor.replace(map.get(key), map);
	}


	@Override
	public Map<String, String> asMap() {
		return new HashMap<>(map);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AbstractProperties that = (AbstractProperties) o;
		return map.equals(that.map);
	}


	@Override
	public int hashCode() {
		return map.hashCode();
	}
}
