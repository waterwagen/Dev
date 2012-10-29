package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.junit.Test;

public abstract class EqualsHashcodeContractTester
{
	@Test
	public void testDifferentInstancesWithSameValuesSatisfyEqualsMethod()
	{
		InstancePair pair = provideDifferentInstancesWithTheSameValues();
		assertThat("Expected different instances with the same values to be equivalent through the equals() method.", 
				pair.instance1, is(equalTo(pair.instance2)));
	}
	
	@Test
	public void testDistinctInstancesWithDifferentValuesDoNotSatisfyEqualsMethod()
	{
		InstancePair pair = provideDifferentInstancesWithDifferentValues();
		assertThat("Expected different instances with different values to not be equivalent through the equals() method.", 
					pair.instance1, is(not(equalTo(pair.instance2))));
	}

	@Test
	public void testHashCodeIsTheSameInInstancesWhichSatisfyTheEqualsMethod()
	{
		InstancePair pair = provideDifferentInstancesWithTheSameValues();
		assertThat("Expected different instances with the same values to produce the same hashCode.", 
				pair.instance1.hashCode(), is(equalTo(pair.instance2.hashCode())));
	}

	@Test
	public void testHashCodeIsDifferentInInstancesWhichDoNotSatisfyTheEqualsMethod()
	{
		InstancePair pair = provideDifferentInstancesWithDifferentValues();
		assertThat("Expected different instances with different values to produce different hashCodes.", 
				pair.instance1.hashCode(), is(not(equalTo(pair.instance2.hashCode()))));
	}

	@Test
	public void testThatTheSameHashCodeIsProducedForEachInvocationOfTheMethod()
	{
		Object instance = provideInstance(); 
		int previous_hashcode = instance.hashCode();
		int INVOCATION_COUNT = 100;
		for(int i = 0; i < INVOCATION_COUNT; i++)
		{
			int hashcode = instance.hashCode();
			assertThat("Expected the same hashCode to be returned for each invocation of the hashCode method.", 
					 hashcode, is(equalTo(previous_hashcode)));
			previous_hashcode = hashcode; 
		}
	}

	////////////////////////
	/// Abstract Methods ///
	////////////////////////
	
	public abstract Object provideInstance();

	public abstract InstancePair provideDifferentInstancesWithTheSameValues();

	public abstract InstancePair provideDifferentInstancesWithDifferentValues();

	
	public class InstancePair
	{
		private Object instance1 = null;
		private Object instance2 = null;

		public InstancePair(Object instance1, Object instance2)
		{
			super();
			this.instance1 = instance1;
			this.instance2 = instance2;
		}
	}
}
