package ru.vsu.cs.course1;

import java.util.*;

// Класс-адаптер, вызывать методы SimpleLinkedList, но при этом он релизовывал стандартный интерфейс Deque
public class SimpleDeque<T> implements Deque<T> {
    private final SimpleLinkedList<T> list = new SimpleLinkedList<T>();

    @Override
    public void addFirst(T t) {
        list.addFirst(t);
    }

    @Override
    public void addLast(T t) {
        list.addLast(t);
    }

    @Override
    public boolean offerFirst(T t) {
        try {
            list.addFirst(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean offerLast(T t) {
        try {
            list.addLast(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public T removeFirst() {
        try {
            return list.removeFirst();
        } catch (SimpleLinkedListException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T removeLast() {
        try {
            return list.removeLast();
        } catch (SimpleLinkedListException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T pollFirst() {
        try {
            return list.removeFirst();
        } catch (SimpleLinkedListException e) {
            return null;
        }
    }

    @Override
    public T pollLast() {
        try {
            return list.removeLast();
        } catch (SimpleLinkedListException e) {
            return null;
        }
    }

    @Override
    public T getFirst() {
        try {
            return list.getFirst();
        } catch (SimpleLinkedListException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getLast() {
        try {
            return list.getLast();
        } catch (SimpleLinkedListException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T peekFirst() {
        try {
            return list.getFirst();
        } catch (SimpleLinkedListException e) {
            return null;
        }
    }

    @Override
    public T peekLast() {
        try {
            return list.getLast();
        } catch (SimpleLinkedListException e) {
            return null;
        }
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        Iterator<T> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (Objects.equals(o, element)) {
                try {
                    list.remove(index);
                    return true;
                } catch (SimpleLinkedListException e) {
                    return false;
                }
            }
            index++;
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        // Проходим с конца, поэтому создаем временный массив или проходим дважды
        // Проще найти последнее вхождение, пройдя сначала весь список
        int lastIndex = -1;
        Iterator<T> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (o == null ? element == null : o.equals(element)) {
                lastIndex = index;
            }
            index++;
        }

        if (lastIndex >= 0) {
            try {
                list.remove(lastIndex);
                return true;
            } catch (SimpleLinkedListException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean add(T t) {
        list.addLast(t);
        return true;
    }

    @Override
    public boolean offer(T t) {
        try {
            list.addLast(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public T remove() {
        try {
            return list.removeFirst();
        } catch (SimpleLinkedListException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T poll() {
        try {
            return list.removeFirst();
        } catch (SimpleLinkedListException e) {
            return null;
        }
    }

    @Override
    public T element() {
        try {
            return list.getFirst();
        } catch (SimpleLinkedListException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T peek() {
        try {
            return list.getFirst();
        } catch (SimpleLinkedListException e) {
            return null;
        }
    }

    @Override
    public void push(T t) {
        list.addFirst(t);
    }

    @Override
    public T pop() {
        try {
            return list.removeFirst();
        } catch (SimpleLinkedListException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            while (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<T> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (!c.contains(element)) {
                try {
                    list.remove(index);
                    modified = true;
                    // Не увеличиваем index, так как элементы сдвинулись
                } catch (SimpleLinkedListException e) {
                    // Игнорируем
                }
            } else {
                index++;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(Object o) {
        for (T element : list) {
            if (Objects.equals(o, element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Iterator<T> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            array[index++] = iterator.next();
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        int i = 0;
        for (T element : list) {
            a[i++] = (T1) element;
        }
        return a;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            private int currentIndex = size() - 1;

            @Override
            public boolean hasNext() {
                return currentIndex >= 0;
            }

            @Override
            public T next() { // O(n)
                if (!hasNext()) throw new NoSuchElementException();
                try {
                    return list.get(currentIndex--);
                } catch (SimpleLinkedListException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}