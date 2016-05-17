package net.obecon.properties.converter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Janne K. Olesen &lt;janne.olesen@unbelievable-machine.com&gt;
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractConverterTest {

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private AbstractConverter<String> mockConverter;

	@Rule
	public ExpectedException exception = ExpectedException.none();


	@Test
	public void fromString() throws Exception {
		final String input = "input";
		final String expected = "myResult";
		when(mockConverter.doFromString(input)).thenReturn(expected);

		assertEquals(expected, mockConverter.fromString(input));
		verify(mockConverter).doFromString(input);
	}


	@Test
	public void fromString_exception() throws Exception {
		String input = "input";
		Throwable cause = new Exception("myEx");
		when(mockConverter.doFromString(input)).thenThrow(cause);

		exception.expect(ParseException.class);
		exception.expectMessage(input);
		exception.expectCause(sameInstance(cause));

		mockConverter.fromString(input);
		verify(mockConverter).doFromString(input);
	}


	@Test
	public void testToString() throws Exception {
		final String input = "input";
		final String expected = "myResult";
		when(mockConverter.doToString(input)).thenReturn(expected);

		assertEquals(expected, mockConverter.toString(input));
		verify(mockConverter).doToString(input);
	}


	@Test
	public void getTargetClass() throws Exception {

		AbstractConverter<String> myConverter = new AbstractConverter<String>(String.class) {
			@Override
			protected String doFromString(String value) throws Exception {
				return null;
			}
		};

		assertEquals(String.class, myConverter.getTargetClass());
	}
}