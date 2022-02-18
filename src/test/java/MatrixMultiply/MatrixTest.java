package MatrixMultiply;

import org.junit.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * class for testing multithreading matrix multiply
 * @Author Nozdryuhin Aleksandr
 * @version 4.0.0
 */
public class MatrixTest {

    private int[][] first= {
            {1,2,5},
            {2,7,9},
            {4,9,7},
            {2,8,1}
    };

    private int[][] second= {
            {2,5,6},
            {2,9,8},
            {7,1,8}
    };

    private int[][] rez={
            {41,28,62},
            {81,82,140},
            {75,108,152},
            {27,83,84}
    };

    @Test
    public void multiply() throws InterruptedException {
        Matrix matrix=new Matrix(first,second);
        List<Thread> threads=matrix.createThreads();
        for(Thread thread:threads){
            thread.start();
        }
        Thread.sleep(100);
        for(int i=0;i<rez.length;i++){
            for(int j=0;j<rez[i].length;j++){
                assertEquals(rez[i][j],matrix.getRez()[i][j]);
            }
        }
    }
}