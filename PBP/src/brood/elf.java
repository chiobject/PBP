package brood;

public class elf extends brood {
//	2. 엘프    - 이동력 특화 ( 상대방을 만나지 10초 이상 만나지 않으면 이동력 1.2배)
//	생산력  1  
//	이동력  3  총 이동시간이 8.5초인데 시작하자마자 8.2초가 디폴트 그러면 상대방보다 2초의 여유시간 턴을 더 많이 쓸수 있다.
//	공격력  2  유닛 1당 2
	
    // 엘프에게 특화된 특성
    private int maxPopulationCount = 5000;	//최대 인구 수
    

    public elf() {
        // 종족,공격력,이동속도,최대인구수
        super(3);
        unitLeft ="images\\unit\\elfLeft.png";
        unitRight = "images\\unit\\elfRight.png";
        AD = 50;
        speed = 200;
        popProdRate = 50;
        popProdSpeed = 800;
    }
}
