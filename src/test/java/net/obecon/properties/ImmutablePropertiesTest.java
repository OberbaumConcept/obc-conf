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

package net.obecon.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class ImmutablePropertiesTest {

	@Test
	public void of_Map() {
		Map<String, String> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		ImmutableProperties immutableProperties = ImmutableProperties.of(map);
		assertEquals("key1", "value1", immutableProperties.getAsString("key1"));
		assertEquals("key2", "value2", immutableProperties.getAsString("key2"));
	}


	@Test
	public void of_Properties() {
		java.util.Properties properties = new Properties();
		properties.put("key1", "value1");
		properties.put("key2", "value2");
		ImmutableProperties immutableProperties = ImmutableProperties.of(properties);
		assertEquals("key1", "value1", immutableProperties.getAsString("key1"));
		assertEquals("key2", "value2", immutableProperties.getAsString("key2"));
	}
}