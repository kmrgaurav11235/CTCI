/*
https://www.geeksforgeeks.org/queue-linked-list-implementation/
*/
class Queue_03_QueueByLL {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            next = null;
        }
        Node() {
            this(0);
        }
    }
    static class QueueByLL {
        private Node front;
        private Node rear;

        QueueByLL() {
            front = null;
            rear = null;
        }

        public boolean isEmpty() {
            return front == null;
        }

        public void enqueue(int data) {
            Node temp = new Node(data);
            if (isEmpty()) {
                front = temp;
                rear = temp;
            } else {
                rear.next = temp;
                rear = temp;
            }
        }

        public int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue Underflow! Cannot Dequeue.");
                return 0;
            }
            int temp = front.data;
            if (front == rear) {
                rear = null;
            }
            front = front.next;
            return temp;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue Underflow! Cannot Peek.");
                return 0;
            }
            return front.data;
        }

        @Override
        public String toString() {
            if (isEmpty()) {
                return "[]";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Front -> [ ");
            Node p = front;
            while (p != null) {
                stringBuilder.append(p.data + " ");
                p = p.next;
            }
            stringBuilder.append(" ] -> Rear");
            return stringBuilder.toString();
        }
    }
    public static void main(String[] args) {
        QueueByLL queue = new QueueByLL(); 
        System.out.println("Initial Queue: " + queue);

        System.out.println("Inserting 10 into queue."); 
        queue.enqueue(10); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println("Inserting 20 into queue."); 
        queue.enqueue(20); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println("Inserting 30 into queue."); 
        queue.enqueue(30); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println("Inserting 40 into queue."); 
        queue.enqueue(40); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println(queue.dequeue() +  " dequeued from queue\n"); 
        System.out.println("Current Queue: " + queue);

        System.out.println("Inserting 40 into queue."); 
        queue.enqueue(40); 
        System.out.println("Current Queue: " + queue);

        System.out.println("Inserting 50 into queue."); 
        queue.enqueue(50); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println(queue.dequeue() +  " dequeued from queue\n"); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println(queue.dequeue() +  " dequeued from queue\n"); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println(queue.dequeue() +  " dequeued from queue\n"); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println(queue.dequeue() +  " dequeued from queue\n"); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println(queue.dequeue() +  " dequeued from queue\n"); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println(queue.dequeue() +  " dequeued from queue\n"); 
        System.out.println("Current Queue: " + queue);
        
    }
}