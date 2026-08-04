// Problem Link: https://leetcode.com/problems/lru-cache?envType=problem-list-v2&envId=wravg9od

/*

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.

int get(int key) Return the value of the key if the key exists, otherwise return -1.

void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.

The functions get and put must each run in O(1) average time complexity.

Example:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4

Approach: Using HashMap and Doubly Linked List.

🧠 Intuition:

The Least Recently Used (LRU) Cache is a data structure that removes the "oldest" (least recently accessed) item when it reaches its capacity.

To achieve O(1) time complexity for both get and put, we combine two data structures:

HashMap: Provides O(1) lookups for any key.

Doubly Linked List: Maintains the order of access. We move "fresh" items to the front and "stale" items stay at the back.

By using Dummy Head and Dummy Tail nodes, we avoid null pointer checks during node removal or insertion, making the code much cleaner.

🚀 Approach:

1. Data Structure Design: Each node in the list stores both the key and value. The key is necessary so that when we evict from the list, we know which entry to remove from the HashMap.

2. The get(key) Operation:
If the key doesn't exist in the HashMap, return -1.
If it exists, the item is now "recently used." Remove it from its current position in the list and Insert it at the front (right after the dummy head).

3. The put(key, value) Operation:
If key exists: Update the value, remove the node, and move it to the front.
If key is new: * Check if the cache is at capacity. If so, delete the node right before the tail (the Least Recently Used item) and remove it from the HashMap.

4. Create a new node and insert it at the front.

Helper Methods:

remove(node): Unlinks a node from its neighbors.

insertAtFront(node): Places a node between the head and the original head.next.

📈 Complexity:

Time complexity: O(1) for both get and put operations.

Space complexity: O(C) where C is the capacity of the cache, as we store at most C nodes and HashMap entries.


Code:

class Node {
    int key, value;
    Node prev, next;
    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        insertAtFront(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertAtFront(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            insertAtFront(newNode);
            map.put(key, newNode);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}



*/