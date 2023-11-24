package main;

public class NeutralZone {
    private static final int PRODUCTION_RATE = 10; // 생산률 (1초당)
    private int unitCount; // 현재 생산된 유닛 수

    public NeutralZone() {
        unitCount = 0;
    }

    public int produceUnits(int deltaTimeInSeconds) {
        int producedUnits = PRODUCTION_RATE * deltaTimeInSeconds;
        unitCount += producedUnits;
        return producedUnits;
    }

    public int getUnitCount() {
        return unitCount;
    }

    public void resetUnitCount() {
        unitCount = 0;
    }
}
