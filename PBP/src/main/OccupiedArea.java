package main;

public class OccupiedArea {
    private static final int MIN_UNIT_COUNT_FOR_OCCUPATION = 300;
    private int unitCount; // 현재 점령된 칸의 유닛 수

    public OccupiedArea() {
        unitCount = 0;
    }

    public boolean occupyArea(int unitsToOccupy) {
        if (unitsToOccupy >= MIN_UNIT_COUNT_FOR_OCCUPATION) {
            unitCount += unitsToOccupy;
            return true;
        } else {
            return false;
        }
    }

    public int getUnitCount() {
        return unitCount;
    }

    public void resetUnitCount() {
        unitCount = 0;
    }
}
