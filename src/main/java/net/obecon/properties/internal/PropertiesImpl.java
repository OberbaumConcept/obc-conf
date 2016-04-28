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

import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import net.obecon.properties.ImmutableProperties;
import net.obecon.properties.Properties;

/**
 * @author Janne K. Olesen &lt;janne.olesen@unbelievable-machine.com&gt;
 */
public class PropertiesImpl extends AbstractProperties implements Properties {

	public PropertiesImpl(@Nonnull Map<String, String> map) {
		super(map);
	}


	@Override
	public void addAll(@Nonnull Map<String, String> map) {
		Utils.addAll(map, this.map);
	}


	@Nonnull
	@Override
	public ImmutableProperties immutable() {
		return ImmutableProperties.of(map);
	}


	@Override
	public void set(@Nonnull String key, String value) {
		map.put(Objects.requireNonNull(key, "null is not permitted as key"), value);
	}
}
