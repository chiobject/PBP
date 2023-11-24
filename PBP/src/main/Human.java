package main;

public class Human extends BaseStat {
	
//	3. 인간    - 벨런스        ( 중립기지 먹게 될 시 모든 능력치 30초동안 이동력 1.1배 생산력은 0.9배  공격력은 0.5증가)
//	생산력  2  0.5초당 20  특성 적용시 ->  0.5초당 18
//	이동력  2  행동시간 9초  특성 적용시 -> 8.95초
//	공격력  2  유닛 1당 2 -> 2.5
	
	
    // 인간에게 특화된 특성
    private static final double BASE_PRODUCTION_RATE = 2; // 기본 생산률
    private static final double BASE_MOVEMENT_SPEED = 2; // 기본 이동 속도
    private static final double BASE_ATTACK_DAMAGE = 2; // 기본 공격력

    private static final double BONUS_DURATION = 30.0; // 특성 적용 지속 시간
    private static final double MOVEMENT_SPEED_BONUS = 1.1; // 이동력 보너스 배수
    private static final double PRODUCTION_RATE_BONUS = 0.9; // 생산력 감소 배수
    private static final double ATTACK_DAMAGE_BONUS = 0.5; // 공격력 증가 값

    private boolean bonusActive; // 중립 기지를 먹었을 때의 특성 보너스 상태
    private double bonusTimeRemaining; // 남은 특성 보너스 지속 시간

    public Human() {
        // 인간의 기본값으로 초기화
        super(BASE_ATTACK_DAMAGE, BASE_MOVEMENT_SPEED, 0, 0);
        bonusActive = false;
        bonusTimeRemaining = 0.0;
    }

    // 인간에게 특화된 로직을 포함시키기 위해 addNowPopulationCount 메서드를 오버라이드
    @Override
    public void addNowPopulationCount(int addCount) {
        // 인간에 특화된 로직을 추가하려면 이곳에 작성
        super.addNowPopulationCount(addCount);
    }

    // 중립 기지를 먹었을 때의 특성 보너스를 활성화하는 메서드
    public void activateBonus() {
        bonusActive = true;
        bonusTimeRemaining = BONUS_DURATION;
        System.out.println("중립 기지를 먹어 특성 보너스가 활성화되었습니다.");
    }

    // 중립 기지를 먹었을 때의 특성 보너스 지속 시간을 갱신하고 비활성화하는 메서드
    public void updateBonus(double deltaTime) {
        if (bonusActive) {
            bonusTimeRemaining -= deltaTime;
            if (bonusTimeRemaining <= 0.0) {
                bonusActive = false;
                System.out.println("특성 보너스가 비활성화되었습니다.");
            }
        }
    }

    // 인간의 총 생산 시간을 계산하는 메서드
    public double getTotalProductionTime() {
        double productionRate = BASE_PRODUCTION_RATE / UnitProduction.buildcounter;
        if (bonusActive) {
            productionRate *= PRODUCTION_RATE_BONUS;
        }
        return productionRate;
    }

    // 인간의 총 이동 시간을 계산하는 메서드
    public double getTotalMovementTime() {
        double movementSpeed = BASE_MOVEMENT_SPEED;
        if (bonusActive) {
            movementSpeed *= MOVEMENT_SPEED_BONUS;
        }
        return UnitProduction.create + movementSpeed;
    }

    // 인간의 총 공격력을 계산하는 메서드
    public double getTotalAttackDamage() {
    	double attackDamage = BASE_ATTACK_DAMAGE;
        if (bonusActive) {
            attackDamage += ATTACK_DAMAGE_BONUS;
        }
        return attackDamage;
    }
}
