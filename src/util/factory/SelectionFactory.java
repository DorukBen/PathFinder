package util.factory;

import util.evolution.selection.*;

public class SelectionFactory {
    public static Selection newInstance(SelectionType type) {
        switch (type) {
            case FITNESS_PROPORTIONATE_SELECTION:
                return new FitnessProportionateSelection();
            case TOURNAMENT_SELECTION:
                return new TournamentSelection();
            case RANK_SELECTION:
                return new RankSelection();
            case RANDOM_SELECTION:
                return new RandomSelection();
            default:
                return new FitnessProportionateSelection();
        }
    }

    public enum SelectionType {
        FITNESS_PROPORTIONATE_SELECTION,
        TOURNAMENT_SELECTION,
        RANK_SELECTION,
        RANDOM_SELECTION;
    }
}

