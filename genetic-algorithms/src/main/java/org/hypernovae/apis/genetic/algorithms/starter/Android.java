package org.hypernovae.apis.genetic.algorithms.starter;

public class Android {
	private int[] eChromosome;
	private double fitness = -1;
	
	public Android(int[] eChromosome) {
		// creates the android's eChromosome
		this.eChromosome = eChromosome;
	}
	
	public Android(int eChromosomeLength) {
		this.eChromosome = new int[eChromosomeLength];
		for (int eGene = 0; eGene < eChromosomeLength; eGene ++) {
			if (0.5 < Math.random()) {
				this.setEGene(eGene, 1);				
			} else {
				this.setEGene(eGene, 0);
			}
		}
	}
	
	public int[] getEChromosome() {
		return eChromosome;
	}
	
	public int getEChromosomeLength () {
		return this.eChromosome.length;
	}
	public void setEGene(int offset, int eGene) {
		this.eChromosome[offset] = eGene;
	}
	
	public int getEGene(int offset) {
		return this.eChromosome[offset];
	}
	
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public double getFitness() {
		return this.fitness;
	}
	
	public String toString() {
		String output = "";
		for (int eGene = 0; eGene < this.eChromosome.length; eGene++) {
			output += this.eChromosome[eGene];
		}
		return output;
	}
}
