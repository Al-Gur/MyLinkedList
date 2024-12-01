package telran.list.model;

import telran.list.interfaces.IList;

import java.util.Iterator;

public class MyLinkedList<E> implements IList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    @Override  // O(1)
    public int size() {
        return size;
    }

    @Override  // O(n) (причем O(1) для частного случая index == size)
    public boolean add(int index, E element) {
        if (index == size) {
            Node<E> newNode = new Node<>(last, element, null);
            if (last != null) {
                last.next = newNode;
            } else {
                first = newNode;
            }
            last = newNode;
        } else {
            Node<E> node = getNodeByIndex(index);
            Node<E> newNode = new Node<>(node.prev, element, node);
            node.prev = newNode;
            if (index != 0) {
                newNode.prev.next = newNode;
            } else {
                first = newNode;
            }
        }
        size++;
        return true;
    }

    // O(n)
    private Node<E> getNodeByIndex(int index) {
        checkIndex(index);
        Node<E> node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    // O(1)
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override  // O(n)
    public E get(int index) {
        return getNodeByIndex(index).payload;
    }

    @Override  // O(n)
    public int indexOf(Object o) {
        int index = 0;
        if (o != null) {
            for (Node<E> node = first; node != null; node = node.next, index++) {
                if (o.equals(node.payload)) {
                    return index;
                }
            }
        } else {
            for (Node<E> node = first; node != null; node = node.next, index++) {
                if (null == node.payload) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override  // O(n)
    public int lastIndexOf(Object o) {
        int index = size - 1;
        if (o != null) {
            for (Node<E> node = last; node != null; node = node.prev, index--) {
                if (o.equals(node.payload)) {
                    return index;
                }
            }
        } else {
            for (Node<E> node = last; node != null; node = node.prev, index--) {
                if (null == node.payload) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override  // O(n) (причем O(1) для частных случаев index == size-1 и index == 0)
    public E remove(int index) {
        Node<E> node = getNodeByIndex(index);
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            first = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            last = node.prev;
        }
        size--;
        return node.payload;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = first;

            @Override  // O(1)
            public boolean hasNext() {
                return node != null;
            }

            @Override  // O(1)
            public E next() {
                E elem = node.payload;
                node = node.next;
                return elem;
            }
        };
    }

    private static class Node<E> {
        E payload;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E payload, Node<E> next) {
            this.payload = payload;
            this.prev = prev;
            this.next = next;
        }
    }
}
