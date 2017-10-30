package util.evolution.crossover;

import data.Individual;
import util.factory.IndividualFactory;

import java.util.Arrays;

public class MultiPointCrossover implements Crossover{

    private static int sDefaultCrossoverCount = 2;

    @Override
    public Individual crossover(Individual first, Individual second) {
        // Create new individual instance from factory
        Individual result = IndividualFactory.newInstance();
        // Find points which are representing crossing points of the chromosome
        int[] points = new int[sDefaultCrossoverCount];
        for (int i = 0; i < sDefaultCrossoverCount; i++) {
            points[i] = (int) (Math.random() * first.size());
        }
        // Sort point array for reduce any confusion
        Arrays.sort(points);

        // Specify a queue for tracking individuals' order
        int queue = 0;
        for (int i = 0; i < result.size(); i++) {
            if (i <= points[0]) {
                if (queue % 2 == 0) {
                    result.getChromosome()[i].setDirection(first.getChromosome()[i].getDirection());
                } else {
                    result.getChromosome()[i].setDirection(second.getChromosome()[i].getDirection());
                }
            } else {
                queue++;
            }
        }
        return result;
    }

    public static int getDefaultCrossoverCount() {
        return sDefaultCrossoverCount;
    }

    public static void setDefaultCrossoverCount(int crossoverCount) {
        MultiPointCrossover.sDefaultCrossoverCount = crossoverCount;
    }
}
