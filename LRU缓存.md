***
* #### LRU缓存是什么?
&emsp;&emsp;1. LRU算法就是一种缓存淘汰策略，其他常见的缓存淘汰策略有这几种：
   * FIFO(First in first out): 先进先出式的淘汰机制，队列式的
   * LRU(Least recently used):最近最少使用的,将很长时间没有使用过的淘汰掉
   * LFU(Least frequently used):计算每个信息的访问次数，踢走访问次数最少的那个；如果访问次数一样，就踢走好久没用过的那个
   
&emsp;&emsp;2. 缓存是什么以及为什么要使用缓存
   * 缓存的目的：
        * 1.提升速度。将一些需要频繁访问的数据存放在高速缓存中，可以在需要的时候快速获取到
        * 2.通过缓存一些变化频率较小的数据，来减少对数据请求方的访问次数，减少服务器的请求压力。
   * 为什么需要上述的LRU等缓存策略?   
        &emsp;&emsp;因为现实中各个服务的资源都是有限的，不断堆积的数据缓存会占用大量的服务器空间，因此需要采取一些策略来释放资源以用来承载新到来的需要缓存的数据，以支持服务长期稳定的运行
   * 有哪些方式实现数据缓存?
        * 1.使用JDK或者其他框架所提供的容器类，在服务器内存中缓存数据
        * 2.通过一些NOSql数据库服务进行缓存，不会占用当前服务器的资源。如：redis,elastic等
        * 3.可以使用一些开源的已经封装好的缓存框架帮助我们更好的使用缓存。如：Spring的springCache,Alibaba开源的JetCache等
***
* #### 实现LRU缓存需要实现哪些功能?

1. 作为缓存最基本的操作就是能够将数据写入和读取数据，最好这两种操作都能在O(1)的时间复杂度完成；
2. get()操作读取数据没什么好说的：当前key存在则返回数据，不存在则返回-1；
3. 写入数据的时候：put()--存在key  --更新value并将其置为最新的数据
                       --不存在key--是否有空间写入---有---添加KV并置为最新
                                                      ---无---清除最老的数据并加入最新的数据
***
* #### 实现上述功能需要什么样的数据结构支撑?
* 在O(1)的时间复杂度内实现get: 毫无疑问使用HashMap来存储KV数据
* 上面的功能分析中put()操作需要对加入的value标记其加入的顺序，且需要在O(1)的复杂度内完成数据的删除，那么DoubleLinkedList双向链表可以满足这样的需求
######&emsp;因此，结合上面的两个特点的数据结构就是：LinkedHashMap
***
* #### 具体实现（3种实现思路）
* 使用LinkedList和HashMap实现：
```
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LRU缓存实现
 */
class LRUCache {

    static class Node {
       public int key, val;
       public Node prev,next;
       public Node(int k,int v){
           this.key = k;
           this.val = v;
       }
    }

    private LinkedList<Node> cache;
    private ConcurrentHashMap<Integer, Node> map;
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.cache = new LinkedList<>();
        this.map = new ConcurrentHashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }else {
            Node node = map.get(key);
            //移除该节点并将其插到头部
            cache.remove(node);
            cache.addFirst(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)){
            //更新值，并前置到头结点
            cache.remove(map.get(key));
            cache.addFirst(node);
            map.put(key, node);
        }else {
            //不存在，先判断容量
            if (cap == cache.size()){
                //删除最后一个节点
                Node lastNode = cache.removeLast();
                map.remove(lastNode.key);
            }
            cache.addFirst(node);
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```
* 自己定义双向链表的实现(推荐的写法)
```
import java.util.concurrent.ConcurrentHashMap;

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
            //删除旧值再添加，而不是直接移动
            removeNode(map.get(key));
            addToHead(node);
            map.put(key,node);
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
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```
* 直接继承java封装好的LinkedHashMap类，重写其中的关于清理最旧数据的条件即可
```
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
}
```