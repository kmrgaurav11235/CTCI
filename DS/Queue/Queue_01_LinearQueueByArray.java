/*
https://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/
*/
class Queue_01_LinearQueueByArray {
    static class LinearQueueByArray {
        private int [] a;
        private int capacity;
        private int front;
        private int rear;

        LinearQueueByArray(int capacity) {
            this.capacity = capacity;
            a = new int [capacity];
            front = -1;
            rear = -1;
        }

        public boolean isEmpty() {
            return front == -1;
        }

        public boolean isFull() {
            return rear == (capacity - 1);
        }

        public void enqueue(int data) {
            if (isFull()) {
                System.out.println("Queue Overflow! Cannot enqueue new element.");
                return;
            }
            a[++rear] = data;
            if (front == -1) {
                front++;
            }
        }

        public int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue Underflow! Cannot dequeue element.");
                return 0;
            }
            int data = a[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            return data;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue Underflow! Cannot peek element.");
                return 0;
            }
            return a[front];
        }
        @Override
        public String toString() {
            if (isEmpty()) {
                return "[]";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Front -> [ ");
            for (int i = front; i <= rear; i++) {
                stringBuilder.append(a[i] + " ");
            }
            stringBuilder.append("] <- Rear\n");
            return stringBuilder.toString();
        }
    }
    public static void main(String[] args) {
        LinearQueueByArray queue = new LinearQueueByArray(3); 
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
        
        System.out.println(queue.dequeue() +  " dequeued from queue\n"); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println(queue.dequeue() +  " dequeued from queue\n"); 
        System.out.println("Current Queue: " + queue);
        
        System.out.println(queue.dequeue() +  " dequeued from queue\n"); 
        System.out.println("Current Queue: " + queue);
    }
}