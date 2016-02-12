package org.hypernovae.apis.genetic.algorithms.starter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class EpopulationTest {

	@Test
	public void size_is_ok_Test() {
		EPopulation ePop = new EPopulation(150);
		assertEquals(150, ePop.size());
	}
	
	@Test
	public void construction_with_one_param_is_ok_Test() {
		EPopulation ePop = new EPopulation(5);
		Android[] androids = ePop.getAndroids();
		assertEquals(5, androids.length);
		for (int i = 0; i < androids.length; i++) {
			assertNull(androids[i]);
		}
	}
	
	@Test(expected= NegativeArraySizeException.class)
	public void construction_with_one_param_is_not_ok_case_1_Test() {
		EPopulation ePop = new EPopulation(-5);
		assertEquals(-5, ePop.getAndroids().length);
	}
	
	@Test
	public void construction_with_two_params_is_ok_Test() {
		EPopulation ePop = new EPopulation(5,3);
		Android[] androids = ePop.getAndroids();
		assertEquals(5, androids.length);
		for (int i = 0; i < androids.length; i++) {
			assertNotNull(androids[i]);
			assertEquals(3, androids[i].getEChromosomeLength());
			
		}
	}
	
	@Test
	public void getFittest_ok_Test() {
		EPopulation ePop = new EPopulation(3,2);
		ePop.getAndroids()[0].setFitness(0.25);
		ePop.getAndroids()[1].setFitness(0.11);
		ePop.getAndroids()[2].setFitness(0.86);
		assertEquals(Double.valueOf( 0.11), Double.valueOf(ePop.getFittest(2).getFitness()));
		assertEquals(Double.valueOf(0.25), Double.valueOf(ePop.getFittest(1).getFitness()));
		assertEquals(Double.valueOf(0.86), Double.valueOf(ePop.getFittest(0).getFitness()));
	}
	
	/**
	 * This test will check that the shuffle method doesn"='t change or add any other android
	 */
	@Test
	public void shuffle_keeps_same_droids_ok_Test() {
		EPopulation ePop = new EPopulation(3,2);
		List<Android> droids = new ArrayList<Android>();
		for (Android a: ePop.getAndroids()) {
			droids.add(a);
		}
		
		ePop.shuffle();
		for (Android a: ePop.getAndroids()) {
			assertTrue(isInList(a, droids));
		}
		
	}
	
	private boolean isInList(Android a, List<Android> list) {
		boolean result = false;
		if (list.contains(a)) {
			result = true;
			list.remove(a);
		}
		return result;
	}
	
	
}
