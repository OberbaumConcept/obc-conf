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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 * @author Janne K. Olesen &lt;janne.olesen@oberbaum-concept.com&gt;
 */
public class TestUtils {

	private TestUtils() {
	}


	public static <T extends Serializable> String serializeToBase64(T object) throws IOException {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (final ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(object);
			oos.flush();
			return Base64.getEncoder().encodeToString(bos.toByteArray());
		}
	}


	public static class SerializableObject implements Serializable {

		private final String value;


		public SerializableObject(String value) {
			this.value = value;
		}


		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			SerializableObject serializableObject = (SerializableObject) o;
			return value.equals(serializableObject.value);
		}


		@Override
		public int hashCode() {
			return value.hashCode();
		}
	}
}
