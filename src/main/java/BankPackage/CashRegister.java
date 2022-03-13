package BankPackage;

import lombok.*;

/**
 * @Author Nozdryuhin Aleksandr
 * @version 4.0.0
 * class bank cash register
 */
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CashRegister {

    @Setter
    @Getter
    private int money;

    public CashRegister(int money) {
        this.money = money;
    }

    public void putMoney(int money){
        this.money+=money;
    }

    public void takeOffMoney(int money){
        this.money-=money;
    }
}
