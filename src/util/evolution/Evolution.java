package util.evolution;

import data.Individual;
import data.Population;
import util.factory.*;

public class Evolution {

    public static Population evolve(Population population,
                                    SelectionFactory.SelectionType selection,
                                    CrossoverFactory.CrossoverType crossover,
                                    MutationFactory.MutationType mutation,
                                    boolean elitism) {
        Population newPopulation = PopulationFactory.newInstance();

        if (elitism) {
            newPopulation.getIndividuals()[0] = mostAdaptive(population);
        }

        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        for (int i = elitismOffset; i < population.size(); i++) {
            Individual first = SelectionFactory.newInstance(selection).select(population);
            Individual second = SelectionFactory.newInstance(selection).select(population);
            Individual newIndividual = CrossoverFactory.newInstance(crossover).crossover(first, second);
            newPopulation.getIndividuals()[i] =  newIndividual.clone();
        }

        MutationFactory.newInstance(mutation).mutate(newPopulation);

        newPopulation.setGeneration(population.getGeneration() + 1);

        return newPopulation;
    }

    public static Individual mostAdaptive(Population population) {
        double fitness = 0;
        Individual adaptive = IndividualFactory.newInstance();
        for (Individual individual : population.getIndividuals()) {
            if (individual.getFitness() > fitness) {
                adaptive = individual.clone();
            }
        }
        return adaptive;
    }
}
