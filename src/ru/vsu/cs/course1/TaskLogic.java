package ru.vsu.cs.course1;

import java.util.Deque;

public class TaskLogic {

    // Метод который решает вашу задачу
    // Демонстрация работы со стеком и очередью
    static void solve(Deque<Integer> stack, Deque<Integer> queue) {
        // Добавляем элементы в стек
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Добавляем элементы в очередь
        queue.add(100);
        queue.add(200);
        queue.add(300);

        // Удаляем и выводим элементы из стека
        System.out.println("Stack pop: " + stack.pop());
        System.out.println("Stack pop: " + stack.pop());

        // Удаляем и выводим элементы из очереди
        System.out.println("Queue poll: " + queue.poll());
        System.out.println("Queue poll: " + queue.poll());
    }
}
