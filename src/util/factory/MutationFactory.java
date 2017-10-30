package util.factory;

import util.evolution.mutation.*;

public class MutationFactory {

    public static Mutation newInstance(MutationType type) {
        switch (type) {
            case RANDOM_RESETTING_MUTATION:
                return new RandomResettingMutation();
            case SCRAMBLE_MUTATION:
                return new ScrambleMutation();
            case SWAP_MUTATION:
                return new SwapMutation();
            case INVERSION_MUTATION:
                return new InversionMutation();
            default:
                return new RandomResettingMutation();
        }
    }

    public enum MutationType {
        RANDOM_RESETTING_MUTATION,
        SCRAMBLE_MUTATION,
        SWAP_MUTATION,
        INVERSION_MUTATION;
    }
}



