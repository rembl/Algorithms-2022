package lesson5;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class OpenAddressingSet<T> extends AbstractSet<T> {

    private final int bits;

    private final int capacity;

    private final Object[] storage;

    private final Object dummy = new Object();

    private int size = 0;

    private int startingIndex(Object element) {
        return element.hashCode() & (0x7FFFFFFF >> (31 - bits));
    }

    public OpenAddressingSet(int bits) {
        if (bits < 2 || bits > 31) {
            throw new IllegalArgumentException();
        }
        this.bits = bits;
        capacity = 1 << bits;
        storage = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка, входит ли данный элемент в таблицу
     */
    @Override
    public boolean contains(Object o) {
        int index = startingIndex(o);
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                return true;
            }
            index = (index + 1) % capacity;
            current = storage[index];
        }
        return false;
    }

    /**
     * Добавление элемента в таблицу.
     *
     * Не делает ничего и возвращает false, если такой же элемент уже есть в таблице.
     * В противном случае вставляет элемент в таблицу и возвращает true.
     *
     * Бросает исключение (IllegalStateException) в случае переполнения таблицы.
     * Обычно Set не предполагает ограничения на размер и подобных контрактов,
     * но в данном случае это было введено для упрощения кода.
     */
    @Override
    public boolean add(T t) {
        int startingIndex = startingIndex(t);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null && current != dummy) {
            if (current.equals(t)) {
                return false;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) {
                throw new IllegalStateException("Table is full");
            }
            current = storage[index];
        }
        storage[index] = t;
        size++;
        return true;
    }

    /**
     * Удаление элемента из таблицы
     *
     * Если элемент есть в таблице, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     *
     * Спецификация: {@link Set#remove(Object)} (Ctrl+Click по remove)
     *
     * Средняя
     */
    @Override
    public boolean remove(Object o) {

        //сложность O(N)
        //память O(1)

        int startingIndex = startingIndex(o);
        int index = startingIndex;
        Object current = storage[index];

        while (current != null) {
            if (current.equals(o)) {
                storage[index] = dummy;
                size--;
                return true;
            }

            index = (index + 1) % capacity;
            if (index == startingIndex) return false;
            current = storage[index];

        }

        return false;

    }

    /**
     * Создание итератора для обхода таблицы
     *
     * Не забываем, что итератор должен поддерживать функции next(), hasNext(),
     * и опционально функцию remove()
     *
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     *
     * Средняя (сложная, если поддержан и remove тоже)
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new OpenAddressingIterator();
    }

    public class OpenAddressingIterator implements Iterator<T> {

        private int currentIndex;
        private int currentElement;
        private final int amountOfElements;
        private Object node;

        OpenAddressingIterator() {

            currentIndex = -1;
            currentElement = 0;
            amountOfElements = size();

        }

        @Override
        public boolean hasNext() {

            //сложность O(1)
            //память O(1)

            return currentElement < amountOfElements;
        }

        @Override
        public T next() {

            //сложность O(N)
            //память O(1)

            if (!hasNext()) throw new NoSuchElementException();
            else {
                node = null;
                while (node == null || node == dummy) {
                    currentIndex++;
                    node = storage[currentIndex];
                }

                currentElement++;
                return (T) node;
            }
        }

        @Override
        public void remove() {

            //сложность O(1)
            //память O(1)

            if (node == null) throw new IllegalStateException();

            storage[currentIndex] = dummy;
            node = null;
            size--;
        }
    }
}
