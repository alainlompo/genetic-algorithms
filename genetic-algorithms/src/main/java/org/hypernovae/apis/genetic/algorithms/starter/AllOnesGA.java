package org.hypernovae.apis.genetic.algorithms.starter;

public class AllOnesGA {
	public static void main(String[] args) {
		// Creates a GA object
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.95, 0);
		
		// Init the population
		EPopulation ePopulation = ga.initEPopulation(50);
		
		ga.evalEPopulation(ePopulation);
		int generation = 1;
		
		// Evolution loop (Androids may evolve, humans won't)
		while (ga.isTerminationConditionMet(ePopulation) == false) {
			
			// Displays fittest android from the population
			System.out.println("Best solution so far: " + ePopulation.getFittest(0));
			
			// TODO: Crossover
			ePopulation = ga.crossoverEPopulation(ePopulation);
			
			// TODO: Mutation
			
			// TODO: evaluate population
			ga.evalEPopulation(ePopulation);
			
			// TODO: Go to the next generation
			generation ++;
		}
		
		System.out.println(String.format("Found solution in %d generations ", generation));
		System.out.println( String.format("Best solution : %s", ePopulation.getFittest(0).toString()));
	}

}
