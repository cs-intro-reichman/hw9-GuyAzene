/**
 * Represents a list of Nodes.
 */
public class LinkedList {

    private Node first; // pointer to the first element of this list
    private Node last;  // pointer to the last element of this list
    private int size;   // number of elements in this list

    /**
     * Constructs a new list.
     */
    public LinkedList() {
        first = null;
        last = first;
        size = 0;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.addLast(new MemoryBlock(0, 1000));
        ll.addLast(new MemoryBlock(5, 6));
        ll.add(1, new MemoryBlock(3, 4));
        ll.addLast(new MemoryBlock(4444, 6));
        ll.addLast(new MemoryBlock(5, 6));


        System.out.println(ll);
        System.out.println(ll.indexOf(new MemoryBlock(5, 6)));
        ll.remove(3);
        System.out.println(ll);
    }

    /**
     * Gets the first node of the list
     *
     * @return The first node of the list.
     */
    public Node getFirst() {
        return this.first;
    }

    /**
     * Gets the last node of the list
     *
     * @return The last node of the list.
     */
    public Node getLast() {
        return this.last;
    }

    /**
     * Gets the current size of the list
     *
     * @return The size of the list.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets the node located at the given index in this list.
     *
     * @param index the index of the node to retrieve, between 0 and size
     * @return the node at the given index
     * @throws IllegalArgumentException if index is negative or greater than the list's size
     */
    public Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(
                    "index must be between 0 and size");
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Creates a new Node object that points to the given memory block,
     * and inserts the node at the given index in this list.
     * <p>
     * If the given index is 0, the new node becomes the first node in this list.
     * <p>
     * If the given index equals the list's size, the new node becomes the last
     * node in this list.
     * <p>
     * The method implementation is optimized, as follows: if the given
     * index is either 0 or the list's size, the addition time is O(1).
     *
     * @param block the memory block to be inserted into the list
     * @param index the index before which the memory block should be inserted
     * @throws IllegalArgumentException if index is negative or greater than the list's size
     */
    public void add(int index, MemoryBlock block) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(
                    "index must be between 0 and size");
        }
        Node newNode = new Node(block);
        if (index == 0) { // Insert the node first
            newNode.next = first;
            first = newNode;
            if (size == 0) // Edge Case: List is Empty
                last = newNode;
        }
        else if (index == size) { // Insert the node last
            if (last != null)
                last.next = newNode;
            last = newNode;
            if (size == 0) // Edge Case: List is empty
                first = newNode;
        }

        else {
            Node current = first;
            int counter = 0;
            while (counter < index-1) {
                current = current.next;
                counter++;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    /**
     * Creates a new node that points to the given memory block, and adds it
     * to the end of this list (the node will become the list's last element).
     *
     * @param block the given memory block
     */
    public void addLast(MemoryBlock block) {
        //// Write your code here
        Node newNode = new Node(block);
        if (size == 0) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    /**
     * Creates a new node that points to the given memory block, and adds it
     * to the beginning of this list (the node will become the list's first element).
     *
     * @param block the given memory block
     */
    public void addFirst(MemoryBlock block) {
        //// Write your code here
        Node newNode = new Node(block);
        newNode.next = first;
        first = newNode;
        if (size == 0) {
            last = newNode;
        }

        size++;
    }

    /**
     * Gets the memory block located at the given index in this list.
     *
     * @param index the index of the retrieved memory block
     * @return the memory block at the given index
     * @throws IllegalArgumentException if index is negative or greater than or equal to size
     */
    public MemoryBlock getBlock(int index) {
        //// Replace the following statement with your code
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(
                    "index must be between 0 and size");
        }
        return getNode(index).block;
    }

    /**
     * Gets the index of the node pointing to the given memory block.
     *
     * @param block the given memory block
     * @return the index of the block, or -1 if the block is not in this list
     */
    public int indexOf(MemoryBlock block) {
        //// Replace the following statement with your code
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.block.equals(block)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    /**
     * Removes the given node from this list.
     *
     * @param node the node that will be removed from this list
     */
    public void remove(Node node) {
        if (node == null)
            throw new NullPointerException("node must not be null");
        if (size == 0)
            return;

        int index = indexOf(node.block);
        if (index == -1)
            return;
        if (index == 0) {
            first = first.next;
            if (size == 1)
                last = null;
        } else {
            int counter = 0;
            Node current = first;
            while (counter < index - 1) {
                current = current.next;
                counter++;
            }
            current.next = node.next;
            if (node == last) {
                last = current;
            }
        }
        size--;
    }

    /**
     * Removes from this list the node which is located at the given index.
     *
     * @param index the location of the node that has to be removed.
     * @throws IllegalArgumentException if index is negative or greater than or equal to size
     */
    public void remove(int index) {
        //// Write your code here
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(
                    "index must be between 0 and size");
        }
        remove(getNode(index));
    }

    /**
     * Removes from this list the node pointing to the given memory block.
     *
     * @param block the memory block that should be removed from the list
     * @throws IllegalArgumentException if the given memory block is not in this list
     */
    public void remove(MemoryBlock block) {
        //// Write your code here
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.block.equals(block)) {
                remove(i);
                return;
            }
            current = current.next;
        }
        throw new IllegalArgumentException("The given memory block is not in this list");
    }

    /**
     * Returns an iterator over this list, starting with the first element.
     */
    public ListIterator iterator() {
        return new ListIterator(first);
    }

    /**
     * A textual representation of this list, for debugging.
     */
    public String toString() {
        //// Replace the following statement with your code
        String text = "";
        Node current = this.first;
        while (current != null) {
            String block = current.block.toString();
            text += "(" + block.substring(1, block.length() - 1) + ") ";
            current = current.next;
        }
        return text;
    }
}