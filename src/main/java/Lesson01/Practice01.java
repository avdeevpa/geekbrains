package Lesson01;

import java.util.Arrays;

public class Practice01 {
    public static void main(String[] args) {

    }

    // Case_01
    public static boolean isBeetween10n20(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    // Case_02
    public static void printNumSign(int a) {
        if (a < 0) {
            System.out.println("Negative");
            return;
        }
        System.out.println("Positive");
    }

    // Case_03
    public static void isLeapYear(int y) {
        if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {
            System.out.println("Leap year!");
            return;
        }
        System.out.println("Ordinary year...");
    }

    // Case_04
    public static int sumOfArray(int[] arr) {
        int sum = 0;
        for (int n: arr){
            sum += n;
        }
        return sum;
    }

    // Case_05
    // For test: System.out.println(Arrays.toString(makeArr(8,3)));
    public static int[] makeArr(int size, int step){
        int[] arr = new int[size];
        arr[0] = 1;
        for (int i = 1; i < size; i++) {
            arr[i] = arr[i-1] + step;
        }
        return arr;
    }

    // Case_06
    // For test: printMatrix(makeDiagMatrix(5));
    public static int[][] makeDiagMatrix(int size){
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            arr[i][i] = 1;
        }
        return arr;
    }

    // Case_06_1:
    public static void printMatrix(int[][] arr) {
        for (int[] line: arr){
            for (int rec: line) {
                System.out.print(rec + " ");
            }
            System.out.println();
        }
    }

    // Case_07
    public static int maxOfArr(int[] arr) {
        int[] tmp = arr.clone();
        Arrays.sort(tmp);
        return tmp[tmp.length - 1];
    }

}
