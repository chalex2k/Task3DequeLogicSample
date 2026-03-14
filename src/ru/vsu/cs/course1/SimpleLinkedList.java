package ru.vsu.cs.course1;

import java.util.Iterator;

public class SimpleLinkedList<T> implements Iterable<T> {

    protected class SimpleLinkedListItem<T> {
        public T value;
        public SimpleLinkedListItem<T> next;

        public SimpleLinkedListItem(T value, SimpleLinkedListItem<T> next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListItem(T value) {
            this(value, null);
        }

        public SimpleLinkedListItem() {
            this(null, null);
        }
    }



    protected SimpleLinkedListItem<T> head = null;
    protected SimpleLinkedListItem<T> tail = null;
    protected int count = 0;

    protected void checkEmpty() throws SimpleLinkedListException {
        if (isEmpty()) {
            throw new SimpleLinkedListException("List is empty");
        }
    }

    // O(1)
    public T getFirst() throws SimpleLinkedListException {
        checkEmpty();
        return head.value;
    }

    // O(1)
    public T getLast() throws SimpleLinkedListException {
        checkEmpty();
        return tail.value;
    }

    // O(index)
    protected SimpleLinkedListItem<T> getItem(int index) throws SimpleLinkedListException {
        if (index < 0 || index >= size()) {
            throw new SimpleLinkedListException(String.format("Incorrect index [%d]", index));
        }
        SimpleLinkedListItem<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    // O(index)
    public T get(int index) throws SimpleLinkedListException {
        return getItem(index).value;
    }

    // O(1)
    public void addFirst(T value) {
        head = new SimpleLinkedListItem<>(value, head);
        count++;
        if (count == 1) {
            tail = head;
        }
    }

    // O(1)
    public void addLast(T value) {
        SimpleLinkedListItem<T> tmp = new SimpleLinkedListItem<>(value);
        if (count == 0) {
            head = tail = tmp;
        } else {
            tail = tail.next = tmp;
        }
        count++;
    }

    // O(index)
    public void insert(int index, T value) throws SimpleLinkedListException {
        if (index == 0) {
            addFirst(value);
        } else {
            if (index < 0 || index > size()) {
                throw new SimpleLinkedListException(String.format("Incorrect index [%d]", index));
            }
            SimpleLinkedListItem<T> prev = getItem(index - 1);
            SimpleLinkedListItem<T> tmp = new SimpleLinkedListItem<>(value, prev.next);
            prev.next = tmp;
            if (count == index) {
                tail = tmp;
            }
            count++;
        }
    }

    // O(1)
    public T removeFirst() throws SimpleLinkedListException {
        checkEmpty();
        T value = head.value;
        head = head.next;
        count--;
        if (count == 0) {
            tail = null;
        }
        return value;
    }

    // O(size)
    public T removeLast() throws SimpleLinkedListException {
        checkEmpty();
        if (count == 1) {
            return removeFirst();
        } else {
            tail = getItem(count - 2);
            T value = tail.next.value;
            tail.next = null;
            count--;
            return value;
        }
    }

    // O(index)
    public T remove(int index) throws SimpleLinkedListException {
        if (index < 0 || index >= size()) {
            throw new SimpleLinkedListException(String.format("Incorrect index [%d]", index));
        }
        if (index == 0) {
            return removeFirst();
        } else {
            SimpleLinkedListItem<T> prev = getItem(index - 1);
            T value = prev.next.value;
            prev.next = prev.next.next;
            count--;
            return value;
        }
    }

    // O(1)
    public void clear() {
        head = tail = null;
        count = 0;
    }

    // O(1)
    public int size() {
        return count;
    }

    // O(1)
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
//        class SimpleLinkedListIterator implements Iterator<T> {
//            SimpleLinkedListItem<T> curr = head;
//
//            @Override
//            public boolean hasNext() {
//                return curr != null;
//            }
//
//            @Override
//            public T next() {
//                T value = curr.value;
//                curr = curr.next;
//                return value;
//            }
//        }
//
//        return new SimpleLinkedListIterator();

        return new Iterator<T>() {
            SimpleLinkedListItem<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        };
    }
}


class SimpleLinkedListException extends Exception {
    public SimpleLinkedListException(String error) {
        super(error);
    }
}
