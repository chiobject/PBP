package brood;

public class human extends brood {
//	3. 인간    - 벨런스        ( 중립기지 먹게 될 시 모든 능력치 30초동안 이동력 1.1배 생산력은 0.9배  공격력은 0.5증가)
//	생산력  2  0.5초당 20  특성 적용시 ->  0.5초당 18
//	이동력  2  행동시간 9초  특성 적용시 -> 8.95초
//	공격력  2  유닛 1당 2 -> 2.5
	
	
    // 인간에게 특화된 특성
    private static final double BASE_PRODUCTION_RATE = 2; // 기본 생산률
    private int maxPopulationCount = 5000;	//최대 인구 수

    
    public human() {
        // 종족,공격력,이동속도,최대인구수
        super(1);
        unitLeft ="images\\unit\\humanLeft.png";
        unitRight = "images\\unit\\humanRight.png";
        speed = 150;
        AD = 60;
        popProdRate = 50;
        popProdSpeed = 800;
    }

}
