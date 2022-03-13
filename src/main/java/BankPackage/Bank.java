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
    public static final int TIME_WORKING=10000;
    public static CashRegister cashRegister= new CashRegister(10000);

    public static void main(String[] args) throws InterruptedException {
        List<Employee> employees=new ArrayList<>();
        for(int i=1;i<=NUMBER_OF_EMPLOYEES;i++){
            employees.add(new Employee(i,cashRegister));
        }
        for(Employee employee:employees){
            employee.start();
        }
        Long start=System.currentTimeMillis();
        Long time;
        int counter=0;
        while (true){
            time=System.currentTimeMillis();
            if(time-start<TIME_WORKING) {
                Client client = new Client(SERVICE_TIME);
                employees.get(searchMinClientQueueEmployee(employees)).addClient(client);
            }
            if(counter==5&&(time-start>TIME_WORKING)){
            System.out.println("Все клиенты обслужены, банк закрывается!");
                for(Employee employee:employees){
                    employee.setActive(false);
                }
                return;
            }
            else
                counter=0;
           Thread.sleep(60000/CLIENTS_PER_MIN);
           for(Employee employee:employees){
               if(employee.getClients().size()==0)
                   counter++;
           }
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
}
