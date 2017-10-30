package util.evolution.mutation;

import data.Direction;
import data.Individual;
import data.Population;

public class SwapMutation implements Mutation {

    private static double sDefaultMutationRate = 0.05;

    @Override
    public void mutate(Population population) {
        if (sDefaultMutationRate >= Math.random()){
            for (int i = 0; i < population.size(); i++) {
                Individual individual = population.getIndividuals()[i];
                // Select two points from chromosome and swap them
                int firstPoint = (int) (Math.random() * individual.size());
                int secondPoint = (int) (Math.random() * individual.size());

                Direction template = individual.getChromosome()[firstPoint].getDirection();
                individual.getChromosome()[firstPoint].setDirection(individual.getChromosome()[secondPoint].getDirection());
                individual.getChromosome()[secondPoint].setDirection(template);
            }
        }
    }

    public static double getDefaultMutationRate() {
        return sDefaultMutationRate;
    }

    public static void setDefaultMutationRate(double defaultMutationRate) {
        SwapMutation.sDefaultMutationRate = defaultMutationRate;
    }
}
