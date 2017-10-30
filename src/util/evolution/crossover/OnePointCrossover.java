package util.evolution.crossover;

import data.Individual;
import util.factory.IndividualFactory;

public class OnePointCrossover implements Crossover{

    @Override
    public Individual crossover(Individual first, Individual second) {
        // Create new individual instance from factory
        Individual result = IndividualFactory.newInstance();
        // Find a point which is representing crossing point of the chromosome
        int point = (int) (Math.random() * first.size());

        for (int i = 0; i < result.size(); i++) {
            if (i <= point) {
                result.getChromosome()[i].setDirection(first.getChromosome()[i].getDirection());
            } else {
                result.getChromosome()[i].setDirection(second.getChromosome()[i].getDirection());
            }
        }
        return result;
    }
}
