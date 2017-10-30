package util.evolution.mutation;

import data.Direction;
import data.Individual;
import data.Population;

public class RandomResettingMutation implements Mutation {

    private static double sDefaultMutationRate = 0.05;

    @Override
    public void mutate(Population population) {
        if (sDefaultMutationRate >= Math.random()){
            for (int i = 0; i < population.size(); i++) {
                Individual individual = population.getIndividuals()[i];
                // Select point which is representing gene which will be mutate
                int point = (int) (Math.random() * individual.size());
                individual.getChromosome()[point].setDirection(Direction.randomDirection());
            }
        }
    }

    public static double getDefaultMutationRate() {
        return sDefaultMutationRate;
    }

    public static void setDefaultMutationRate(double defaultMutationRate) {
        RandomResettingMutation.sDefaultMutationRate = defaultMutationRate;
    }
}
