package pos;

public class Pos {
    private int money;

    public void addMoney(int money) {
        /* DONE: 시재를 추가하는 메서드 구현
         * - 입력받은 금액만큼 시재를 추가
         */

        this.money += money;
    }

    public void subtractMoney(int money) {
        /* DONE: 시재를 빼는 메서드 구현
         * - 입력받은 금액만큼 시재를 차감
         * - 현재 시재보다 더 큰 금액을 차감하려는 경우, 실패 처리
         */

        int result = this.money - money;

        if (result < 0) {
            throw new IllegalArgumentException("시재를 초과한 금액");
        }

        this.money = result;
    }

    public void printMoney() {
        /* DONE: 시재를 출력하는 메서드 구현
         * - 현재 시재를 출력
         */

        System.out.println("Money = " + money);
    }
}