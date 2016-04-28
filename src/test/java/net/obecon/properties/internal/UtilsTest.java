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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Janne K. Olesen on 29.04.2016.
 */
public class UtilsTest {

	@Test
	public void asMap() throws Exception {

	}


	@Test
	public void addAll_NoNullPointerException() throws Exception {
		Utils.addAll(null, new HashMap<String, String>()); // null as source should be ok
	}


	@Test
	public void addAll() throws Exception {
		Map<String, String> target = new HashMap<>();
		Map<String, String> source = new HashMap<>();
		source.put("a", "a");
		source.put("b", null);
		source.put(null, "c"); // should be missing in target
		Utils.addAll(source, target);
		assertEquals("a", source.get("a"), target.get("a"));
		assertEquals("b", source.get("b"), target.get("b"));
		assertEquals("size", 2, target.size());
	}
}