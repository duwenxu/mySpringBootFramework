import java.util.concurrent.ConcurrentHashMap;

/**
 * ["LRUCache","put","put","get","put","put","get"]
 * [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
 */
class LRUCache {

    /**
     * 定义链中的Node
     */
    static class DLinkedNode {
        int key, value;
        DLinkedNode prev, next;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    ConcurrentHashMap<Integer, DLinkedNode> map = new ConcurrentHashMap<>();
    DLinkedNode head, tail;
    private int size;
    private int capacity;

    public LRUCache(int cap) {
        this.capacity = cap;
        this.size = 0;
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        //连接首尾节点
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }else {
            DLinkedNode node = map.get(key);
            //更新到头部
            moveToHead(node);
            return node.value;
        }
    }

    /**
     * 添加值
     * @param key
     * @param val
     */
    public void put(int key,int val){
        DLinkedNode node = new DLinkedNode(key, val);
        //当前键值对存在，直接更新
        if (map.containsKey(key)){
            map.put(key,node);
            moveToHead(node);
        }else {
            //容量已满
            if (size==capacity){
                DLinkedNode tail = removeTail();
                map.remove(tail.key);
                size--;
            }
            map.put(key,node);
            addToHead(node);
            size++;
        }
    }

    /**
     * 添加到头结点（相当于一次插入节点 o(1)）
     *
     * @param node
     */
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 删除节点（断开链）
     *
     * @param node
     */
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 移动到头节点处
     *
     * @param node
     */
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 删除尾结点(删除虚拟尾结点的前一个节点)
     *
     * @return
     */
    private DLinkedNode removeTail() {
        DLinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2,1);
        lruCache.put(2,2);
        lruCache.get(2);
        lruCache.put(1,1);
        lruCache.put(4,1);
        lruCache.get(2);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

