package util.factory;

import data.Map;
import data.Section;

public class MapFactory implements RandomizedInstance<Map>{

    private static int sDefaultRowLength = 15;
    private static int sDefaultColumnLength = 15;
    private static double sDefaultBlockChance = 0.3;

    public static Map newInstance() {
        int capacity = sDefaultRowLength * sDefaultColumnLength;
        Section[] sections = new Section[capacity];
        for (int i = 0; i < capacity; i++) {
            sections[i] = new Section(i % sDefaultRowLength, i / sDefaultRowLength, false);
        }
        return new Map(sections, sDefaultRowLength, sDefaultColumnLength);
    }

    @Override
    public Map randomInstance() {
        int capacity = sDefaultRowLength * sDefaultColumnLength;
        Section[] sections = new Section[capacity];
        for (int i = 0; i < capacity; i++) {
            if (Math.random() >= sDefaultBlockChance || i == 0 || i == capacity / 2) {
                sections[i] = new Section(i % sDefaultRowLength, i / sDefaultRowLength, false);
            } else {
                sections[i] = new Section(i % sDefaultRowLength, i / sDefaultRowLength, true);
            }
        }
        return new Map(sections, sDefaultRowLength, sDefaultColumnLength);
    }

    public static int getDefaultRowLength() {
        return sDefaultRowLength;
    }

    public static void setDefaultRowLength(int rowLength) {
        MapFactory.sDefaultRowLength = rowLength;
    }

    public static int getDefaultColumnLength() {
        return sDefaultColumnLength;
    }

    public static void setDefaultColumnLength(int columnLength) {
        MapFactory.sDefaultColumnLength = columnLength;
    }

    public static double getDefaultBlockChance() {
        return sDefaultBlockChance;
    }

    public static void setDefaultBlockChance(double blockChance) {
        MapFactory.sDefaultBlockChance = blockChance;
    }
}
