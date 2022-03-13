package BankPackage;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Nozdryuhin Aleksandr
 * @version 4.0.0
 * class of bank employee
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Thread{

    /**
     * bank clients
     */
    @Setter
    @Getter
    private List<Client> clients;

    /**
     * field of bank cash register
     */
    @Getter
    @Setter
    private CashRegister cashRegister;

    /**
     * field of employees id
     */
    @Getter
    @Setter
    private int idb;

    /**
     * field, that need to disable this thread
     */
    @Getter
    @Setter
    private boolean isActive;

    /**
     * constructor
     */
    public Employee(int id,CashRegister cashRegister) {
        clients=new ArrayList<>();
        this.idb=id;
        this.cashRegister=cashRegister;
        isActive=true;
    }

    /**
     * method to add client to list
     * @param client, that add to list
     */
    public void addClient(Client client){
        clients.add(client);
    }

    /**
     * method, that put money to cash register
     * and take off to client
     */
    public synchronized void workWithClient() throws InterruptedException {
        if(clients.size()>0) {
            Client client = clients.get(0);
            System.out.println("Операционист " + idb + " начал работу с клиентом, в очереди " + (clients.size()-1)+"\n");
            if (client.getOperationType() == 1) {
                cashRegister.putMoney(client.getAmountOfMoney());
                System.out.printf("+%s $, баланс банка: %s $, операционист %s\n\n", client.getAmountOfMoney(), cashRegister.getMoney(), idb);
                clients.remove(client);
                Thread.sleep(client.getServiceTime());
            } else {
                if (client.getAmountOfMoney() > cashRegister.getMoney()) {
                    System.out.println(client.getAmountOfMoney()+", в кассе недостаточно средств, операционист " + idb+"\n");
                    clients.remove(client);
                } else {
                    cashRegister.takeOffMoney(client.getAmountOfMoney());
                    System.out.printf("-%s $, баланс банка: %s $, операционист %s\n\n", client.getAmountOfMoney(), cashRegister.getMoney(), idb);
                    clients.remove(client);
                    Thread.sleep(client.getServiceTime());
                }
            }
        }
    }

    /**
     * thread method
     */
    @SneakyThrows
    @Override
    public void run() {
        while (isActive) {
            workWithClient();
            try{
                Thread.sleep(400);
            }
            catch(InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
        }
    }
}
