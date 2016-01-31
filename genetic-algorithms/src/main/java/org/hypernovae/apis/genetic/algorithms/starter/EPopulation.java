package org.hypernovae.apis.genetic.algorithms.starter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class EPopulation {
	private Android[] ePopulation;
	private double ePopulationFitness = -1;
	
	public EPopulation(int ePopulationSize) {
		this.ePopulation = new Android[ePopulationSize];
	}
	
	public EPopulation(int ePopulationSize, int eChromosomeLength) {
		this.ePopulation = new Android[ePopulationSize];
		
		for (int androidCount = 0; androidCount <  ePopulationSize; androidCount++) {
			Android android = new Android(eChromosomeLength);
			this.ePopulation[androidCount] = android;
		}
	}
	
	public Android[] getAndroids() {
		return this.ePopulation;
	}
	
	public Android getFittest(int offset) {
		Arrays.sort(this.ePopulation, new Comparator<Android>() {
			public int compare(Android a1, Android a2) {
				if (a1.getFitness() > a2.getFitness()) {
					return -1;
				} else if( a1.getFitness() < a2.getFitness()) {
					return +1;
				} else {
					return 0;
				}
			}
			
		});
		return this.ePopulation[offset];
	}
	
	public void setEPopulationFitness(double fitness) {
		this.ePopulationFitness = fitness;
	}
	
	public int size() {
		return this.ePopulation.length;
	}
	
	public Android setAndroid(int offset, Android android) {
		return ePopulation[offset] = android;
	}
	
	public Android getAndroid(int offset) {
		return ePopulation[offset];
	}
	
	public void shuffle() {
		Random rnd = new Random();
		for (int i = ePopulation.length -1;  i >0; i--) {
			int index = rnd.nextInt(i+1);
			Android a = ePopulation[index];
			ePopulation[index] = ePopulation[i];
			ePopulation[i] = a;
		}
		
	}

}
