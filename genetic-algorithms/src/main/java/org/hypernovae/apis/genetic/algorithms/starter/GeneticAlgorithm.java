package org.hypernovae.apis.genetic.algorithms.starter;

public class GeneticAlgorithm {
	private int populationSize;
	private double mutationRate;
	private double crossoverRate;
	private int elitismCount;
	
	
	public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount) {
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
		this.elitismCount = elitismCount;
	}
	
	public EPopulation initEPopulation(int eChromosomeLength) {
		return new EPopulation(this.populationSize, eChromosomeLength);
		
	}
	
	/**
	 * This method will serve the evaluation stage where each individual android of the population
	 * will have its fitness value calculated and stored for future use. The principle of selection is
	 * used in genetic algorithms to guide the so-called evolutionary process to a better population
	 * This selection heavily relies on the fitness function therefore it should be well designed.
	 * A uniquely developed fitness function corresponds to each specific optimization problem
	 * @param android
	 * @return
	 */
	public double evalFitness(Android android) {
		// Tracks the number of correct eGenes
		int correctEGenes = 0;
		
		for (int eGeneIndex = 0; eGeneIndex < android.getEChromosomeLength(); eGeneIndex++) {
			// Add one fitness point for each "1" found
			if (android.getEGene(eGeneIndex) == 1) {
				correctEGenes += 1;
				
			}
		}
		
		double fitness = (double)correctEGenes / android.getEChromosomeLength();
		
		// Store fitness
		android.setFitness(fitness);
		return fitness;
	}
	
	/**
	 * This helper method loops over the Android's population and add it's fitness
	 * to the population's fitness.
	 * @param ePopulation
	 */
	public void evalEPopulation(EPopulation ePopulation) {
		double ePopulationFitness = 0;
		for (Android android: ePopulation.getAndroids()) {
				ePopulationFitness += evalFitness(android);
		}
		ePopulation.setEPopulationFitness(ePopulationFitness);
	}
	
	
	
	

}
