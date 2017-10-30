package util.evolution.selection;

import data.Individual;
import data.Population;

import java.util.Arrays;
import java.util.Comparator;

public class RankSelection implements Selection {

    @Override
    public Individual select(Population population) {
        // Sort array for finding the correct ranks
        Arrays.sort(population.getIndividuals(), new Comparator<Individual>() {
            @Override
            public int compare(Individual first, Individual second) {
                return Double.compare(first.getFitness() ,second.getFitness());
            }
        });

        // Calculate sum of the ranks and select a point
        int totalRank = (population.size() * (population.size() - 1)) / 2;
        int point = (int) (Math.random() * totalRank);

        // Find the individual which is corresponding to point
        int currentRank = 0, index = 0;
        while (currentRank < point) {
            index++;
            currentRank += index + 1;
        }

        return population.getIndividuals()[index].clone();
    }
}
