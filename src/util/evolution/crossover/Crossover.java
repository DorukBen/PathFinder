package util.evolution.crossover;

import data.Individual;

public interface Crossover {
    Individual crossover(Individual first, Individual second);
}
