package util.evolution.crossover;

import data.Individual;
import util.factory.IndividualFactory;

public class UniformCrossover implements Crossover {

    private static double sDefaultUniformRate = 0.01;

    @Override
    public Individual crossover(Individual first, Individual second) {
        // Create new individual instance from factory
        Individual result = IndividualFactory.newInstance();

        // Perform possible crossover over all genes
        for (int i = 0; i < result.size(); i++) {
            if (Math.random() <= sDefaultUniformRate) {
                result.getChromosome()[i].setDirection(first.getChromosome()[i].getDirection());
            } else {
                result.getChromosome()[i].setDirection(second.getChromosome()[i].getDirection());
            }
        }
        return result;
    }

    public static double getDefaultUniformRate() {
        return sDefaultUniformRate;
    }

    public static void setDefaultUniformRate(double uniformRate) {
        UniformCrossover.sDefaultUniformRate = uniformRate;
    }
}
