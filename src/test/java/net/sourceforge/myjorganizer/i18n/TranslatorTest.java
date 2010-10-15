package net.sourceforge.myjorganizer.i18n;

import static org.junit.Assert.*;

import org.junit.Test;

public class TranslatorTest {

	@Test
	public void testUnsupportedTranslation()
	{
		assertEquals("@Smurfs", Translator._("Smurfs"));
	}
	
	@Test
	public void testPresentTranslation()
	{
		assertEquals("File", Translator._("FILE_MENU"));
	}
}
