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
	
	/**
	 * If the fitness of any android in the population is 1 we are good!
	 * @param ePopulation
	 * @return
	 */
	public boolean isTerminationConditionMet(EPopulation ePopulation) {
		for (Android android: ePopulation.getAndroids()) {
			if (android.getFitness() == 1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * We spin a roulette's wheel (ran domly, no cheating) and find out what android is luckily picked by thee (e) nature
	 * @param ePopulation
	 * @return
	 */
	public Android selectParent(EPopulation ePopulation) {
		// Gets the androids
		Android[] androids = ePopulation.getAndroids();
		
		// Spin the roulette's wheel
		double ePopulationFitness = ePopulation.getEPopulationFitness();
		double rouletteWheelPosition = Math.random() * ePopulationFitness;
		
		// Parent where are you?
		double spinWheel = 0;
		for (Android android: androids) {
			spinWheel += android.getFitness();
			if (spinWheel >= rouletteWheelPosition) {
				return android;
			}
		}
		
		// We did not find any suitable parent: the last one will do.
		return androids[ePopulation.size() -1];
	}
	
	/**
	 * Simple crossover's implementation approach
	 * @param ePopulation
	 * @return
	 */
	public EPopulation crossoverEPopulation(EPopulation ePopulation) {
		// Creates the new EPopulation
		EPopulation newEPopulation = new EPopulation(ePopulation.size());
		
		// Fitnessly loop over current ePopulation
		for (int ePopulationIndex = 0; ePopulationIndex < ePopulation.size(); ePopulationIndex++) {
			Android parent1 = ePopulation.getFittest(ePopulationIndex);
			
			// Is this android good for crossover?
			if (this.crossoverRate > Math.random() && ePopulationIndex >  this.elitismCount) {
				
				// Initializing the offspring
				Android offspring = new Android (parent1.getEChromosomeLength());
				
				Android parent2 = selectParent(ePopulation);
				
				// Loop over the eGenome
				for (int eGeneIndex = 0; eGeneIndex < parent1.getEChromosomeLength(); eGeneIndex++) {
					// Using half of each parent's eGenes
					// Meaning that each parent has 50% chances to set the given gene
					// This is done using Math.random and comparing the value to 50%
					if (0.5 > Math.random()) {
						offspring.setEGene(eGeneIndex, parent1.getEGene(eGeneIndex));
						
					} else {
						offspring.setEGene(eGeneIndex, parent2.getEGene(eGeneIndex));
					}
				}
				
				// This offspring will now be part of the new ePopulation
				newEPopulation.setAndroid(ePopulationIndex, offspring);	
			}
			
			// In case the crossoverrate condition is not met we do not apply crossover and 
			//  the android's parent is replicated in the new population as is
			else {
				newEPopulation.setAndroid(ePopulationIndex, parent1);
			}
		}
		
		return newEPopulation;
	}
	
	public EPopulation mutateEPopulation(EPopulation ePopulation) {
		EPopulation newEPopulation = new EPopulation(this.populationSize);
		
		// Fitnessly loop over the actual ePopulation
		for (int ePopulationIndex = 0; ePopulationIndex < ePopulation.size(); ePopulationIndex++) {
			Android android = ePopulation.getFittest(ePopulationIndex);
			
			// Loop over an android's eGenes
			for (int eGeneIndex = 0; eGeneIndex < android.getEChromosomeLength(); eGeneIndex++) {
				// is this an MVA (Most Valuable Android)? Is this android part of the elite?
				// If this is an elite android then we skeep the mutation he will reach the next generation
				// as is.
				if (ePopulationIndex >= this.elitismCount) {
					// Will this eGene (E)naturally mutate?
					if (this.mutationRate > Math.random()) {
						int newEGene = 1;
						if (android.getEGene(eGeneIndex) == 1) {
							newEGene = 0;
						}
						
						// E-mutation
						android.setEGene(eGeneIndex, newEGene);
					}
				}
			}
			
			// Add the android to the new EPopulation
			newEPopulation.setAndroid(ePopulationIndex, android);
		}
		
		return newEPopulation;
		
	}
	
	
}
