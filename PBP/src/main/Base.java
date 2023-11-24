package main;

public class Base {
    private int unitCapacity; // 기지의 유닛 수용량
    private int unitCount; // 현재 기지에 있는 유닛 수

    public Base(int unitCapacity) {
        this.unitCapacity = unitCapacity;
        this.unitCount = 0;
    }

    public boolean addUnits(int unitsToAdd) {
        if (unitCount + unitsToAdd <= unitCapacity) {
            unitCount += unitsToAdd;
            return true;
        } else {
            return false;
        }
    }

    public boolean removeUnits(int unitsToRemove) {
        if (unitCount >= unitsToRemove) {
            unitCount -= unitsToRemove;
            return true;
        } else {
            return false;
        }
    }

    public int getUnitCount() {
        return unitCount;
    }

    public int getUnitCapacity() {
        return unitCapacity;
    }

    public void setUnitCapacity(int unitCapacity) {
        this.unitCapacity = unitCapacity;
    }
}
