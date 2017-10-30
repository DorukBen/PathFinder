package data;

public class Population {
    private Individual[] mIndividuals;
    private int mGeneration;

    public Population(Individual[] individuals) {
        this.mIndividuals = individuals;
        mGeneration = 0;
    }

    public Population(Individual[] individuals, int generation) {
        this.mIndividuals = individuals;
        this.mGeneration = generation;
    }

    public Individual[] getIndividuals() {
        return mIndividuals;
    }

    public void setIndividuals(Individual[] individuals) {
        this.mIndividuals = individuals;
    }

    public int getGeneration() {
        return mGeneration;
    }

    public void setGeneration(int generation) {
        this.mGeneration = generation;
    }

    public int size() {
        return this.mIndividuals.length;
    }
}
