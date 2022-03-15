package BankPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Nozdryuhin Aleksandr
 * @version 4.0.0
 * class of bank
 * in that class we run the program and
 * test this system
 */
public class Bank {
    /**
     * constants
     */
    public static final int NUMBER_OF_EMPLOYEES= 5;
    public static final int SERVICE_TIME=       2000;
    public static final int CLIENTS_PER_MIN=    300;
    public static CashRegister cashRegister= new CashRegister(10000);

    public static void main(String[] args) throws InterruptedException {
        List<Employee> employees=new ArrayList<>();
        for(int i=1;i<=NUMBER_OF_EMPLOYEES;i++){
            employees.add(new Employee(i,cashRegister));
        }
        for(Employee employee:employees){
            employee.start();
        }
        while (true){
            //if queue so long, we stop accept the clients during 20 sec
                if(employees.get(0).getClients().size()>5)
                    Thread.sleep(20000);
                Client client =clientGenerator(CLIENTS_PER_MIN);
                employees.get(searchMinClientQueueEmployee(employees)).addClient(client);
        }

    }

    /**
     * method to get employee
     * with minimal clients queue
     * @param employees, list of threads
     * @return thread id with min queue
     */
    public static int searchMinClientQueueEmployee(List<Employee> employees){
        int minId=0;
        for(int i=0;i<employees.size();i++){
            if(employees.get(i).getClients().size()<employees.get(minId).getClients().size())
                minId=i;
        }
        return minId;
    }

    /**
     * method to generate clients
     * @param time, thread sleeping time
     * @return new client
     */
    public static Client clientGenerator(int time) throws InterruptedException {
        Thread.sleep(60000/time);
        return new Client(SERVICE_TIME);
    }
}
