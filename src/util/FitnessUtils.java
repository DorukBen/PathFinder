package util;

import data.*;

public class FitnessUtils {

    public static void calculateFitness(Map map, Population population) {
        for (int i = 0; i < population.size(); i++) {
            calculateIndividualFitness(map, population.getIndividuals()[i]);
        }
    }

    private static void calculateIndividualFitness(Map map, Individual individual) {
        Gene[] chromosome = individual.getChromosome();

        // Start point
        Section next, current = SectionUtils.getStartSection(map);
        for (int i = 0; i < chromosome.length; i++) {
            Gene gene = chromosome[i];
            next = SectionUtils.move(map, current, gene.getDirection());
            // Control intersection to block or target
            if (next.isBlocked()) {
                for (int j = i + 1; j < chromosome.length; j++){
                    individual.getChromosome()[j].setDirection(Direction.NONE);
                }
                break;
            } else if (SectionUtils.getDistance(map.getBoard()[0], next) == 0) {
                for (int j = i + 1; j < chromosome.length; j++){
                    individual.getChromosome()[j].setDirection(Direction.NONE);
                }
                current = next;
                break;
            }
            current = next;
        }

        double distance = SectionUtils.getDistance(map.getBoard()[0], current);
        double fitness = SectionUtils.getMaximumDistance(map) / ((distance != 0) ? distance: 1);
        individual.setFitness(fitness);
    }
}
