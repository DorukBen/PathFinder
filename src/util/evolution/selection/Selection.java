package util.evolution.selection;

import data.Individual;
import data.Population;

public interface Selection {
    Individual select(Population population);
}
