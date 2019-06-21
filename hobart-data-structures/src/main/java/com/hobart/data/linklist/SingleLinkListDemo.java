package com.hobart.data.linklist;

/**
 * 单向链表
 */
public class SingleLinkListDemo {
    public static void main(String[] args) {
        HeroNode node1=new HeroNode(1, "宋江", "及时雨");
        HeroNode node2=new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3=new HeroNode(3, "吴用", "智多星");
        HeroNode node4=new HeroNode(4, "林冲", "豹子头");
        
        SingleLinkList singleLinkList = new SingleLinkList();
        singleLinkList.addNode(node1);
        singleLinkList.addNode(node2);
        singleLinkList.addNode(node3);
        singleLinkList.addNode(node4);

        singleLinkList.list();
    }
    
    private static class SingleLinkList{
        private HeroNode head = new HeroNode(0, "", "");
        
        //添加节点到单向链表
        //思路，不考虑编号顺序
        //1.找到当前链表的最后节点
        //2、将最后这个节点的next 指向新的节点
        public void addNode(HeroNode node){
            //因为head节点不能动，因此我们需要一个临时节点
            HeroNode temp = this.head;
            while (true) {
                //找到链表的最后
                if (temp.getNext() == null){
                    break;
                }
                //如果没找到后移
                temp = temp.next;
            }
            //当退出循环时，temp就执行链表的最后
            //将最后这个节点的next指向新节点
            temp.setNext(node);
        }
        
        //遍历链表
        public void list(){
            
            if (this.head.getNext() == null){
                System.out.println("链表为空");
                return;
            }
            //因为头节点不能动
            HeroNode temp = this.head.getNext();
            while (true){
                //判断链表是否到最后
                if (temp == null) {
                    break;
                }
                System.out.println(temp);
                temp = temp.getNext();
            }
        }
    }
    
    private static class HeroNode{
        private int no;
        private String name;
        private String nickName;
        private HeroNode next;//指向下一个节点

        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public HeroNode getNext() {
            return next;
        }

        public void setNext(HeroNode next) {
            this.next = next;
        }
    }
}
