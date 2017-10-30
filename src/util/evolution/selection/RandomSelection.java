package util.evolution.selection;

import data.Individual;
import data.Population;

public class RandomSelection implements Selection {

    @Override
    public Individual select(Population population) {
        // Select a random point and return the corresponding individual
        int point = (int) (Math.random() * population.size());
        return population.getIndividuals()[point].clone();
    }
}
