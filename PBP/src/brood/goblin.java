package brood;

public class goblin extends brood {
	
//	1. 고블린 - 생산력 특화 ( 중립기지 먹으면 본기지 생산력 일정시간동안 1.2배?) 
//	생산력 3   0.5초당 30  -> 36
//	이동력  2  총 이동시간 9초
//	공격력  1  유닛 1당 1

    private static final double BASE_PRODUCTION_RATE = 3; // 기본 생산률
    private int maxPopulationCount = 5000;	//최대 인구 수
    
    public goblin() {
        // 종족,공격력,이동속도,최대인구수
        super(2);
        unitLeft ="images\\unit\\goblinLeft.png";
        unitRight = "images\\unit\\goblinRight.png";
        speed = 150;
        AD = 35;
        popProdRate = 80;
        popProdSpeed = 600;
    }

}
