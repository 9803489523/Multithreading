package MatrixMultiply;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Nozdryuhin Aleksandr
 * @version 4.0.0
 * class to multithreading matrix multiply
 */
@EqualsAndHashCode
@ToString
public class MultiplyThread implements Runnable{

    /**
     * number of thread
     */
    @Getter
    @Setter
    private int threadNumber;

    /**
     * reference to destination matrix
     */
    @Getter
    @Setter
    private Matrix matrix;

    /**
     * constructor with params
     * @param threadNumber, number of thread
     * @param matrix, reference to destination matrix
     */
    public MultiplyThread(int threadNumber,Matrix matrix) {
        this.threadNumber = threadNumber;
        this.matrix=matrix;

    }

    /**
     * thread method
     */
    @Override
    public void run() {
        System.out.println(threadNumber);
        if(threadNumber==matrix.getThreadQuantity()-1) {
            for (int i = threadNumber * (matrix.getFirst().length / matrix.getThreadQuantity());i<matrix.getFirst().length;i++)
                matrix.multiply(i);

        }
        else{
            if(threadNumber==0) {
                for (int i = 0; i < (matrix.getFirst().length / matrix.getThreadQuantity()); i++)
                    matrix.multiply(i);
            }
            else{
                for (int i =matrix.getFirst().length / matrix.getThreadQuantity(); i < 2*matrix.getFirst().length / matrix.getThreadQuantity(); i++)
                    matrix.multiply(i);
            }

        }
    }
}
