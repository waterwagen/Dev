package com.waterwagen.mris;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestEmailAddress.class, 
				TestFirstName.class, 
				TestId.class,
				TestLastName.class, 
				TestPhoneNumber.class })
public class ValueTypeTests {}
