package BankPackage;

import lombok.*;

/**
 * @Author Nozdryuhin Aleksandr
 * @version 4.0.0
 * class of bank client
 */
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    /**
     * 1-put money
     * 2-take off money
     */
    @Getter
    @Setter
    private int operationType;

    @Getter
    @Setter
    private int amountOfMoney;

    @Getter
    @Setter
    private int serviceTime;

    public Client(int time) {
        this.operationType = (int)(Math.random()*2+1);
        this.amountOfMoney = (int)(Math.random()*5000+500);
        this.serviceTime = time;
    }
}
