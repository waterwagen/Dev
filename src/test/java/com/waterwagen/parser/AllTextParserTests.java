package com.waterwagen.parser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestIntegerParser.class, 
				TestIntegerTranslator.class,
				TestTextParser.class })
public class AllTextParserTests
{

}
