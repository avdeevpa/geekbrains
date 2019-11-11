package com.geekbrains.learning.tasktracker.excersise.arrExcersise;

class ArrClass {
    private static int ARR_SIZE = 4;

    static class MyArrayException extends RuntimeException {
        MyArrayException(String message) {
            super(message);
        }
    }

    static class MyArraySizeException extends MyArrayException {
        MyArraySizeException(String msg) {
            super(msg);
        }
    }

    static class MyArrayDataException extends MyArrayException {
        MyArrayDataException(String msg) {
            super(msg);
        }
    }

    private static int ArrayInput(String[][] arr) {
        if (arr.length != 4) {
            throw new MyArraySizeException("Количество строк несоответствует. Требуется массив 4 х 4");
        }
        for (String[] line : arr) {
            if (line.length != 4) {
                throw new MyArraySizeException("Количество столбцов несоответствует. Требуется массив 4 х 4");
            }
        }

        int sum = 0;
        for (int i = 0; i < ARR_SIZE; i++) {
            for (int j = 0; j < ARR_SIZE; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("Нечисловое значение в ячейке [%d][%d]", i, j));
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String[][] myArr = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        int sum;
        try {
            sum = ArrayInput(myArr);
        } catch (MyArrayException e) {
            System.out.println("Ошибка обработки массива: " + e.getMessage());
            return;
        }
        System.out.println("Сумма числовых элементов массива: " + sum);
    }

}
