package util;

import data.Direction;
import data.Map;
import data.Population;
import data.Section;

public class SectionUtils {

    public static Section move(Map map, Section section, Direction direction){
        int position;
        switch (direction) {
            case NONE:
                position = (section.getRow()) + (section.getColumn() * map.getRowLength());
                break;
            case LEFT:
                if (section.getRow() - 1 >= 0) {
                    position = (section.getRow() - 1) + (section.getColumn() * map.getRowLength());
                } else {
                    position = -1;
                }
                break;
            case UP:
                if (section.getColumn() - 1 >= 0) {
                    position = section.getRow() + ((section.getColumn() - 1) * map.getRowLength());
                } else {
                    position = -1;
                }
                break;
            case RIGHT:
                if (section.getRow() + 1 < map.getRowLength()) {
                    position = (section.getRow() + 1) + (section.getColumn() * map.getRowLength());
                } else {
                    position = -1;
                }
                break;
            case DOWN:
                if (section.getColumn() + 1 < map.getColumnLength()) {
                    position = section.getRow() + ((section.getColumn() + 1) * map.getRowLength());
                } else {
                    position = -1;
                }
                break;
            default:
                position = -1;
        }
        if (position >= 0) {
            return map.getBoard()[position].clone();
        } else {
            Section newSection = section.clone();
            newSection.setBlocked(true);
            return newSection;
        }
    }

    public static Section getStartSection(Map map){
        int totalSectionCount = map.getRowLength() * map.getColumnLength();
        return map.getBoard()[totalSectionCount / 2].clone();
    }

    public static double[] getStartPoint(Map map) {
        int totalSectionCount = map.getRowLength() * map.getColumnLength();
        Section start = map.getBoard()[totalSectionCount / 2];
        return new double[]{start.getRow(), start.getColumn()};
    }

    public static double[] getTargetPoint(Map map) {
        Section target = map.getBoard()[0];
        return new double[]{target.getRow(), target.getColumn()};
    }

    public static double getDistance(Section first, Section second) {
        int xAxisDistance = Math.abs(first.getRow() - second.getRow());
        int yAxisDistance = Math.abs(first.getColumn() - second.getColumn());

        return Math.sqrt(Math.pow(xAxisDistance, 2) + Math.pow(yAxisDistance, 2));
    }

    public static double getMaximumDistance(Map map) {
        return Math.sqrt(Math.pow(map.getRowLength(), 2) + Math.pow(map.getColumnLength(), 2));
    }

    public static double[][] getWritableData(Map map) {
        int dataSize = map.getRowLength() * map.getColumnLength();
        double[][] data = new double[dataSize][2];
        for (int i = 0; i < dataSize; i++) {
            if (map.getBoard()[i].isBlocked()) {
                data[i][0] = map.getBoard()[i].getRow();
                data[i][1] = map.getBoard()[i].getColumn();
            }
        }
        return data;
    }

    public static double[][][] getWritablePathData(Map map, Population population){
        int dataSize = population.size();
        int pathSize = population.getIndividuals()[0].size();
        double[][][] data = new double[dataSize][pathSize][2];
        for (int i = 0; i < dataSize; i++) {
            Section current = SectionUtils.getStartSection(map);
            for (int j = 0; j < pathSize; j++) {
                Direction direction = population.getIndividuals()[i].getChromosome()[j].getDirection();
                current = move(map, current, direction);
                if (map.getBoard()[current.getRow() + (map.getRowLength() * current.getColumn())].isBlocked()){
                    break;
                }
                data[i][j][0] = current.getRow();
                data[i][j][1] = current.getColumn();
            }
        }
        return data;
    }
}
