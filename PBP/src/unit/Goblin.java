package unit;

public class Goblin extends BaseStat {
	
	
//	1. 고블린 - 생산력 특화 ( 중립기지 먹으면 본기지 생산력 일정시간동안 1.2배?) 
//	생산력 3   0.5초당 30  -> 36
//	이동력  2  총 이동시간 9초
//	공격력  1  유닛 1당 1
	
    // 고블린에게 특화된 특성
    private static final double PRODUCTION_RATE = 3; // 생산률
    private static final double PRODUCTION_BONUS_DURATION = 10.0; // 생산 보너스 지속 시간
    private static final double PRODUCTION_BONUS_MULTIPLIER = 1.2; // 생산 보너스 배수
    private static final double MOVEMENT_SPEED = 2; // 이동 속도
    private static final double ATTACK_DAMAGE = 1; // 공격력

    private boolean productionBonusActive; // 중립 기지를 먹었을 때의 생산 보너스 상태
    private double productionBonusTimeRemaining; // 남은 생산 보너스 지속 시간

    public Goblin() {
        // 고블린의 기본값으로 초기화
        super(ATTACK_DAMAGE, MOVEMENT_SPEED, 0, 0);
        productionBonusActive = false;
        productionBonusTimeRemaining = 0.0;
    }

    // 고블린에 특화된 로직을 포함시키기 위해 addNowPopulationCount 메서드를 오버라이드
    @Override
    public void addNowPopulationCount(int addCount) {
        // 고블린에 특화된 로직을 추가하려면 이곳에 작성
        super.addNowPopulationCount(addCount);
    }

    // 중립 기지를 먹었을 때의 생산 보너스를 활성화하는 메서드
    public void activateProductionBonus() {
        productionBonusActive = true;
        productionBonusTimeRemaining = PRODUCTION_BONUS_DURATION;
        System.out.println("중립 기지를 먹어 생산 보너스가 활성화되었습니다.");
    }

    // 중립 기지를 먹었을 때의 생산 보너스 지속 시간을 갱신하고 비활성화하는 메서드
    public void updateProductionBonus(double deltaTime) {
        if (productionBonusActive) {
            productionBonusTimeRemaining -= deltaTime;
            if (productionBonusTimeRemaining <= 0.0) {
                productionBonusActive = false;
                System.out.println("생산 보너스가 비활성화되었습니다.");
            }
        }
    }

    // 고블린의 총 생산 시간을 계산하는 메서드
    public double getTotalProductionTime() {
        double productionTime = PRODUCTION_RATE / UnitProduction.buildcounter;
        if (productionBonusActive) {
            productionTime /= PRODUCTION_BONUS_MULTIPLIER;
        }
        return productionTime;
    }
}
