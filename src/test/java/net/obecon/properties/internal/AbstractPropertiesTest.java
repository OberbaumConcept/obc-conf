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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.junit.Assert.*;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
@RunWith(JUnitParamsRunner.class)
public class AbstractPropertiesTest {

	class MyProperties extends AbstractProperties {

		public MyProperties(@Nonnull Map<String, String> map) {
			super(map);
		}
	}


	private MyProperties properties;


	@Before
	public void setUp() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("a", null);
		map.put("b", "b");
		map.put("c", "a${b}c");
		map.put("d", "${c}d${c}d");
		properties = new MyProperties(map);

	}


	@Parameters({
			"a, false",
			"b, true",
			"z, false"
	})
	@Test
	public void hasKey(String key, boolean expected) throws Exception {
		assertEquals(expected, properties.hasKey(key));
	}


	@Parameters({
			"b, b",
			"c, abc",
			"d, abcdabcd"
	})
	@Test
	public void testVariableSubstitution(String key, String expected) throws Exception {
		assertEquals(expected, properties.getAsString(key));
	}


	@Test
	public void asMap_isCopy() throws Exception {
		Map<String, String> map = properties.asMap();
		map.put("new", "new");
		assertFalse(properties.hasKey("new"));
	}


	@Test
	public void testEquals() throws Exception {
		MyProperties properties2 = new MyProperties(properties.asMap());
		assertTrue(properties.equals(properties2));
	}


	@Test
	public void testHashCode() throws Exception {
		MyProperties properties2 = new MyProperties(properties.asMap());
		assertEquals(properties.hashCode(), properties2.hashCode());
	}
}