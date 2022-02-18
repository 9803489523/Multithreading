package MatrixMultiply;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Nozdryuhin Aleksandr
 * @version 4.0.0
 * this class need to multiply matrix
 */
@ToString
@EqualsAndHashCode
public class Matrix {

    /**
     * first matrix
     */
    @Getter
    @Setter
    private int[][] first;
    /**
     * second matrix
     */
    @Getter
    @Setter
    private int[][] second;

    /**
     * result of multiply first and second matrix
     */
    @Getter
    @Setter
    private int[][] rez;

    /**
     * quantity of threads
     */
    @Getter
    @Setter
    private int threadQuantity;
    /**
     * constructor with params
     * @param first, first matrix
     * @param second, second matrix
     */
    public Matrix(int[][] first, int[][] second) {
        if(first[0].length!=second.length)
            System.out.println("Размерности матриц не согласуются, умножение невозможно!");
        else {
            this.first = first;
            this.second = second;
            this.rez = new int[first.length][second[0].length];
            if(first.length<10000)
                this.threadQuantity=2;
            else
                this.threadQuantity=3;
        }
    }

    /**
     * static method to generate matrix by row and column dimensions
     * @param row, row dimension
     * @param col, column dimension
     * @return new matrix
     */
    public static int[][] generateMatrix(int row,int col){
        int[][] matrix=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                matrix[i][j]=(int)(Math.random()*10);
            }
        }
        return matrix;
    }

    /**
     * print matrix to console
     * @param matrix, which we want to output on console
     */
    public static void printMatrix(int[][] matrix){
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    /**
     * method to create threads list
     * @return threads list
     */
    public List<Thread> createThreads(){
        List<Thread> threads=new ArrayList<>();
        for(int i=0;i<threadQuantity;i++){
            threads.add(new Thread(new MultiplyThread(i,this)));
        }
        return threads;
    }

    /**
     * method to multithreading multiple first and second matrix
     * @param i, number of thread
     */
    public void multiply(int i){
        for(int j=0;j< second.length;j++){
            for(int k=0;k< second[0].length;k++){
                rez[i][k]+= first[i][j]* second[j][k];
            }
        }
    }

}
