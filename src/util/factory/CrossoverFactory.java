package util.factory;

import util.evolution.crossover.Crossover;
import util.evolution.crossover.MultiPointCrossover;
import util.evolution.crossover.OnePointCrossover;
import util.evolution.crossover.UniformCrossover;

public class CrossoverFactory {

    public static Crossover newInstance(CrossoverType type) {
        switch (type) {
            case ONE_POINT_CROSSOVER:
                return new OnePointCrossover();
            case MULTI_POINT_CROSSOVER:
                return new MultiPointCrossover();
            case UNIFORM_CROSSOVER:
                return new UniformCrossover();
            default:
                return new OnePointCrossover();
        }
    }

    public enum CrossoverType {
        ONE_POINT_CROSSOVER,
        MULTI_POINT_CROSSOVER,
        UNIFORM_CROSSOVER;
    }
}



