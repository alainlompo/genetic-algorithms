package org.hypernovae.apis.genetic.algorithms.starter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EpopulationTest {

	@Test
	public void size_is_ok_Test() {
		EPopulation ePop = new EPopulation(150);
		assertEquals(150, ePop.size());
	}
	
}
