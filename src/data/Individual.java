package data;

public class Individual {
    private Gene[] mChromosome;
    private double mFitness;

    public Individual(Gene[] chromosome) {
        this.mChromosome = chromosome;
        mFitness = 0;
    }

    public Gene[] getChromosome() {
        return mChromosome;
    }

    public void setChromosome(Gene[] chromosome) {
        this.mChromosome = chromosome;
    }

    public double getFitness() {
        return mFitness;
    }

    public void setFitness(double fitness) {
        this.mFitness = fitness;
    }

    public int size(){
        return mChromosome.length;
    }

    @Override
    public Individual clone(){
        Individual individual = new Individual(mChromosome.clone());
        individual.setFitness(mFitness);
        return individual;
    }

    @Override
    public String toString() {
        StringBuilder individualString = new StringBuilder();
        individualString.append("Chromosome: ");
        for (Gene gene : mChromosome) {
            individualString.append(gene);
        }
        individualString.append(" Fitness: ");
        individualString.append(mFitness);
        return individualString.toString();
    }
}
