package util.factory;

import data.Individual;
import data.Population;

public class PopulationFactory implements RandomizedInstance<Population>{

    private static int sPopulationSize = 64;

    public static Population newInstance(){
        Individual[] individuals = new Individual[sPopulationSize];
        for (int i = 0; i < sPopulationSize; i++) {
            individuals[i] = IndividualFactory.newInstance();
        }
        return new Population(individuals);
    }

    public static Population newInstance(int size){
        Individual[] individuals = new Individual[size];
        for (int i = 0; i < size; i++) {
            individuals[i] = IndividualFactory.newInstance();
        }
        return new Population(individuals);
    }

    @Override
    public Population randomInstance() {
        Individual[] individuals = new Individual[sPopulationSize];
        IndividualFactory individualFactory = new IndividualFactory();
        for (int i = 0; i < sPopulationSize; i++) {
            individuals[i] = individualFactory.randomInstance();
        }
        return new Population(individuals);
    }

    public static int getPopulationSize() {
        return sPopulationSize;
    }

    public static void setPopulationSize(int populationSize) {
        PopulationFactory.sPopulationSize = populationSize;
    }


}
