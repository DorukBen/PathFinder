package util.factory;

import data.Direction;
import data.Gene;

public class GeneFactory implements RandomizedInstance{

    public static Gene newInstance() {
        return new Gene(Direction.NONE);
    }

    @Override
    public Gene randomInstance() {
        return new Gene(Direction.randomDirection());
    }
}
