package LinkList;

interface ILink{
    boolean add(Object data);//增加
    boolean remove(Object data);//删除
    boolean set(int index,Object newData);//根据指定下标修改元素内容
    Object get(int index);//根据指定下标返回节点内容
    int contains(Object data);//根据指定内容判断链表是否存在
    void clear();//清空链表
    int size();//链表长度
    void printLink();//遍历链表
}

class LinkImpl implements ILink{
    private Node head;
    private Node last;
    private int size;
    class Node{
        private Node next;
        private Object data;
        private Node(Node next,Object data){
            this.data = data;
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    @Override
    public boolean add(Object data) {
        Node newNode = new Node(null,data);
        if(head == null){
            head = newNode;
            last = head;
        }
        else {
            last.next = newNode;
            last = last.next;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object data) {
        Node pre = head;
        if(data == null){
           for (Node temp = head; temp != null; temp = temp.next){
               if (temp.data == null){
                   if(temp == head){
                       head = temp.next;
                   }else {
                       pre = temp.next;
                       if(temp.next == null){
                           last = pre;
                       }
                   }
                   temp.data = null;
                   this.size--;
                   return true;
               }
               pre = temp;
           }
        }else {
            for (Node temp = head; temp != null;temp = temp.next){
                if(temp.data.equals(data)){
                    if(temp == head){
                        head = temp.next;
                    }else {
                        pre = temp.next;
                        if(temp.next == null){
                            last = pre;
                        }
                    }
                    temp.data = null;
                    this.size--;
                    return true;
                }
                pre = temp;
            }
        }
        return false;
    }


    @Override
    public boolean set(int index, Object newData) {
        Node temp = head;
        if(index - 1 < 0 || index - 1 > size)
            return false;
        for(int i = 0; i < index;i++){
            temp = temp.next;
        }
        temp.data = newData;
        return true;
    }

    @Override
    public Object get(int index) {
        Node temp = head;
        if(index - 1 < 0 || index - 1 > size)
            return false;
        for(int i = 1; i < index;i++){
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public int contains(Object data) {
        Node temp = head;
        int i = 1;
        if(data == null){
            for(;temp != null;temp = temp.next){
                if (temp.data == data)
                    return i;
                else i++;
            }
        }else {
            for(;temp != null;temp = temp.next){
                if (temp.data.equals(data))
                    return i;
                else i++;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        Node temp = head;
        Node node;
        for (;temp != null;temp = node){
            temp.data = null;
            node = temp.next;
            temp.next = null;
            this.size--;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printLink() {
        Node temp = head;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}
