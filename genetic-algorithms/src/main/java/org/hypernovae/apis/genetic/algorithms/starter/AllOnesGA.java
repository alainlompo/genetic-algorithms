package org.hypernovae.apis.genetic.algorithms.starter;

public class AllOnesGA {
	public static void main(String[] args) {
		// Creates a GA object
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.95, 0);
		
		// Init the population
		EPopulation ePopulation = ga.initEPopulation(50);
	}

}
