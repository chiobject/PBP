package brood;

public class elf extends brood {
//	2. 엘프    - 이동력 특화 ( 상대방을 만나지 10초 이상 만나지 않으면 이동력 1.2배)
//	생산력  1  
//	이동력  3  총 이동시간이 8.5초인데 시작하자마자 8.2초가 디폴트 그러면 상대방보다 2초의 여유시간 턴을 더 많이 쓸수 있다.
//	공격력  2  유닛 1당 2
	
    // 엘프에게 특화된 특성
    private static final double MOVEMENT_TIME_THRESHOLD = 10.0; // 이동 시간 보너스 임계값
    private static final double MOVEMENT_TIME_BONUS = 1.2; // 이동 시간 보너스 배수
    private static final double PRODUCTION_RATE = 1; // 생산률
    private static final double MOVEMENT_SPEED = 3; // 이동 속도
    private static final double ATTACK_DAMAGE = 2; // 공격력

    public elf() {
        // 엘프의 기본값으로 초기화
        super(3,ATTACK_DAMAGE, MOVEMENT_SPEED, 0, 0);
    }

    // Elf 특유의 로직을 포함시키기 위해 addNowPopulationCount 메서드를 오버라이드
    @Override
    public void addNowPopulationCount(int addCount) {
        // Elf에 특화된 로직을 추가하려면 이곳에 작성
        super.addNowPopulationCount(addCount);
    }

    // Elf의 효과적인 이동 시간을 계산하는 메서드
    public double getEffectiveMovementTime() {
        // 만남 시간이 임계값보다 낮으면 이동 시간 보너스를 적용
        if (getspeed() > MOVEMENT_TIME_THRESHOLD) {
            return MOVEMENT_TIME_BONUS * MOVEMENT_TIME_THRESHOLD;
        } else {
            return MOVEMENT_TIME_BONUS * getspeed();
        }
    }

    // 이동에 필요한 총 시간을 계산하는 메서드
//    public double getTotalMovementTime() {
//        return UnitProduction.create + getEffectiveMovementTime();
//    }
//
//    // Elf의 총 생산 시간을 계산하는 메서드
//    public double getTotalProductionTime() {
//        return PRODUCTION_RATE * UnitProduction.create;
//    }
}
