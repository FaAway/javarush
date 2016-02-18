package com.javarush.test.level22.lesson18.big01

import org.junit.Assert

/**
 * Created by FarAway on 16.02.2016.
 */
class FieldTest extends GroovyTestCase {


    //матрица поля: 1 - клетка занята, 0 - свободна
    private int[][] matrix = [
            [0,0,0,0],
            [0,1,0,0],
            [0,1,0,0],
            [1,1,1,1],
            [1,1,1,1],
            [1,0,0,1],
    ];

    private int[][] matrixResult = [
            [0,0,0,0],
            [0,0,0,0],
            [0,0,0,0],
            [0,1,0,0],
            [0,1,0,0],
            [1,0,0,1],
    ];

    //ширина и высота
    private int width = matrix[0].length;
    private int height = matrix.length;

    void setUp() {
        super.setUp()
    }

    void tearDown() {

    }

    void testRemoveFullLines() {
        //Например так:
        //Создаем список для хранения линий
        //Копируем все непустые линии в список.
        //Добавляем недостающие строки в начало списка.
        //Преобразуем список обратно в матрицу
        ArrayList<int[]> lines = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            int xCount = 0;
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] != 0) xCount++;
            }
            if (xCount != 0 && xCount != width)
                lines.add(matrix[i]);
        }
        int diff = height - lines.size();
        for (int i = 0; i < diff; i++) {
            lines.add(0, new int[width]);
        }
        matrix = lines.toArray(new int[height][width]);
        println(matrixResult);
        println(matrix);
        Assert.assertArrayEquals(matrixResult, matrix);
    }
}
