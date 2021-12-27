/* Fermin Ramos
 * University of New Mexico, 2021
 *
 * This program merges multiple singly linked lists into 1 singly
 * linked list, organized by ascending order. Each linked lists will
 * contain integers, which may be or may not be organized. The program
 * strips the data from each node in each linked list to figure out the
 * correct ordering of nodes. The program uses Priority Queues (PQ) to
 * handle the logic of ascending order. Once a correct order is
 * established, a new merged linked list is made out of the data passed
 * by the multiple linked lists. Linked lists can be made under the
 * Main function, as it's easier to read and modify each linked list
 * for the user's needs.
 *
 * Input: None. (Linked Lists can be initialized in Main function)
 * Output: Linked List in ascending order
 *
 * */

import java.util.PriorityQueue;

public class LinkedList {
    /*Defines a Node for Singly Linked List*/
    ListNode head;
    public class ListNode {
        int val;
        ListNode next;

        /*Constructor*/
        ListNode(int val) {
            this.val = val;
        }
    }



    /*Inserting Nodes*/
    public void add(LinkedList list, int val){
        // Create New Node
        ListNode node = new ListNode(val);
        node.next = null;

        /*If head is empty, initialize*/
        if(list.head == null){
            list.head = node;
        }else{
            /*list already initialized, find last node to insert new*/
            ListNode nextNode = list.head;
            while(nextNode.next != null){
                nextNode = nextNode.next;
            }
            nextNode.next = node;
        }
    }


    /*Printing Nodes*/
    public static void printLinkedList(LinkedList list){
        ListNode currentNode = list.head;
        while(currentNode != null){
            System.out.print(currentNode.val + " ");
            currentNode = currentNode.next;
        }
    }


    public static PriorityQueue<Integer> addToPQ(LinkedList[] megaList){
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();

        /*Pull out values from queue to print new merged linked-list*/
        int curMegaList = 0;
        int totalMegaLists = megaList.length;


        //Iterates through each list in megaList
        while(curMegaList < totalMegaLists) {
            //Inserts each node of our list into our priority queue
            ListNode currentNode = megaList[curMegaList].head;
            while (currentNode != null) {
                pQueue.add(currentNode.val);
                currentNode = currentNode.next;
            }
            curMegaList++;
        }

        return pQueue;
    }


    /*Main*/
    public static void main(String[] args){
        /*Linked List(s)*/
        LinkedList list1 = new LinkedList();
        list1.add(list1, 1);
        list1.add(list1, 2);
        list1.add(list1, 3);
        list1.add(list1, 2);
        list1.add(list1, 4);


        LinkedList list2 = new LinkedList();
        list2.add(list2, 11);
        list2.add(list2, 22);
        list2.add(list2, 33);
        list2.add(list2, 22);
        list2.add(list2, 44);



        /*Mega List containing ALL linked lists, used for iterating to PQ*/
        LinkedList[] megaList = new LinkedList[2];  //<-- Must Change Value!
        megaList[0] = list1;
        megaList[1] = list2;
        // <-- Add new linked lists here!
        
        /*Add values in megaList to Priority Queue (PQ), to organize*/
        PriorityQueue<Integer> pQueue = addToPQ(megaList);



        /*Create New Merged Linked List from Priority Queue Values*/
        LinkedList mergedList = new LinkedList();

        int mergeSize = pQueue.size();
        for(int j = 0; j < mergeSize; j++){
            mergedList.add(mergedList, pQueue.poll());
        }



        /*Print New Merged Linked List*/
        printLinkedList(mergedList);
    }
}
