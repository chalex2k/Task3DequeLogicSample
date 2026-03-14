package ru.vsu.cs.course1;

import java.util.*;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) throws Exception {
        // стандартные стек и очередь
        Deque<Integer> standardStack = new ArrayDeque<Integer>();
        Deque<Integer> standardQueue = new ArrayDeque<Integer>();

        // самописные стек и очередь
        Deque<Integer> myStack = new SimpleDeque<Integer>();
        Deque<Integer> myQueue = new SimpleDeque<Integer>();

        // Теперь можем передавать в один и тот же метод и стандартные и свои реализации. Метод не знает что именно мы ему передали, т.к. работает через интерфейс
        TaskLogic.solve(standardStack, standardQueue);
        TaskLogic.solve(myStack, myQueue);
    }
}
