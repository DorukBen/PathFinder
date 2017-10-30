package util.evolution.selection;

import data.Individual;
import data.Population;
import util.factory.PopulationFactory;

public class TournamentSelection implements Selection {

    private static int sDefaultTournamentSize = 5;

    @Override
    public Individual select(Population population) {
        // Create a new population with default value
        Population tournament = PopulationFactory.newInstance(sDefaultTournamentSize);
        double fitness = 0;
        int index = 0;
        // Select random individuals from argument population
        for (int i = 0; i < sDefaultTournamentSize; i++) {
            int point = (int) (Math.random() * PopulationFactory.getPopulationSize());
            tournament.getIndividuals()[i] = population.getIndividuals()[point].clone();
            if (population.getIndividuals()[point].getFitness() > fitness) {
                fitness = population.getIndividuals()[point].getFitness();
                index = i;
            }
        }

        // Return the most adaptive one in selected individuals
        return tournament.getIndividuals()[index];
    }

    public static int getDefaultTournamentSize() {
        return sDefaultTournamentSize;
    }

    public static void setDefaultTournamentSize(int tournamentSize) {
        TournamentSelection.sDefaultTournamentSize = tournamentSize;
    }
}
