package util.evolution.mutation;

import data.Gene;
import data.Individual;
import data.Population;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScrambleMutation implements Mutation {

    private static double sDefaultMutationRate = 0.05;

    @Override
    public void mutate(Population population) {
        if (sDefaultMutationRate >= Math.random()){
            for (int i = 0; i < population.size(); i++) {
                Individual individual = population.getIndividuals()[i];
                // Select two points from chromosome and create a subset
                int firstPoint = (int) (Math.random() * individual.size());
                int secondPoint = (int) (Math.random() * individual.size());

                Gene[] subset;
                if (firstPoint > secondPoint) {
                    int template = firstPoint;
                    firstPoint = secondPoint;
                    secondPoint = template;
                }
                subset = Arrays.copyOfRange(individual.getChromosome(), firstPoint, secondPoint);

                // Scramble the subset and place back in to the chromosome
                List subList = Arrays.asList(subset);
                Collections.shuffle(subList);

                int j = 0;
                for (Object gene : subList) {
                    individual.getChromosome()[firstPoint + j].setDirection(((Gene) gene).getDirection());
                    j++;
                }
            }
        }
    }

    public static double getDefaultMutationRate() {
        return sDefaultMutationRate;
    }

    public static void setDefaultMutationRate(double defaultMutationRate) {
        ScrambleMutation.sDefaultMutationRate = defaultMutationRate;
    }
}
