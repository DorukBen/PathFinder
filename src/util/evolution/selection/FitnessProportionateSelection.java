package util.evolution.selection;

import data.Individual;
import data.Population;

public class FitnessProportionateSelection implements Selection {

    @Override
    public Individual select(Population population) {
        // Calculate total sum of fitness'
        double totalSum = 0;
        for (Individual individual : population.getIndividuals()) {
            totalSum += individual.getFitness();
        }

        // Select a point and find index of the corresponding individual
        double selectionPoint = Math.random() * totalSum;

        double currentSum = 0;
        int index = 0;
        while (selectionPoint > currentSum) {
            currentSum += population.getIndividuals()[index].getFitness();
            index++;
        }
        return population.getIndividuals()[index - 1].clone();
    }
}
