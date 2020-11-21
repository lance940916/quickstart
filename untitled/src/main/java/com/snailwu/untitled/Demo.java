package com.snailwu.untitled;

/**
 * @author 吴庆龙
 * @date 2020/4/10 1:44 下午
 */
public class Demo {
    public static void main(String[] args) {

        // 时间复杂度为O(n)，空间复杂度为O(1)
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);

        List list = new List();
        list.head = node1;
        list.tail = node5;

        Node p, q, m;
        p = list.head;
        q = list.head.next;
        m = q;
        while (m != null) {
            m = q.next;
            q.next = p;
            p = q;
            q = m;
        }
        list.tail = list.head;
        list.tail.next = null;
        list.head = p;

        Node i = list.head;
        while (i != null) {
            System.out.println(i.val);
            i = i.next;
        }
    }

    static class List {
        Node head;

        Node tail;
    }

    static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

}
