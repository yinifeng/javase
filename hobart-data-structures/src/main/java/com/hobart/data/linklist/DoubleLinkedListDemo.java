package com.hobart.data.linklist;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {

    static DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
    static HeroNode2 node1=new HeroNode2(1, "宋江", "及时雨");
    static HeroNode2 node2=new HeroNode2(2, "卢俊义", "玉麒麟");
    static HeroNode2 node3=new HeroNode2(3, "吴用", "智多星");
    static HeroNode2 node4=new HeroNode2(4, "林冲", "豹子头");

    public static void main(String[] args) {
        //双向链表的测试
        System.out.println("双向链表的测试");
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);
        doubleLinkedList.list();

        System.out.println("双向链表修改");
        HeroNode2 node5=new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(node5);
        doubleLinkedList.list();

        System.out.println("双向链表删除");
        doubleLinkedList.delete(4);
        doubleLinkedList.list();
        
    }

    /**
     * 双向链表
     */
    private static class DoubleLinkedList{
        private HeroNode2 head= new HeroNode2(0,"","");

        public HeroNode2 getHead() {
            return head;
        }
        
        //顺序添加
        public void addByOrder(HeroNode2 node){
            
        }

        //删除某个节点
        public void delete(int no){
            if (head.getNext() == null){
                System.err.println("要删除的链表为空,不能删除");
            }
            HeroNode2 temp=head.getNext();
            boolean flag= false;//是否找到这个删除的节点
            while (true){
                if (temp == null){
                    break;
                }
                if (temp.getNo() == no){
                    flag = true;
                    break;
                }
                temp = temp.getNext();
            }
            //找到了
            if (flag){
                temp.getPre().setNext(temp.getNext());
                //要可能删除的是最后一个节点
                if (temp.getNext() !=null){
                    temp.getNext().setPre(temp.getPre());
                }
            }else {
                System.err.printf("要删除的节点%d不存在\n",no);
            }
        }

        //修改链表的某个节点的属性值
        //双向链表的节点内容修改 和 单向链表基本一样
        public void update(HeroNode2 node){
            if (head.getNext() == null){
                System.err.println("链表为空，不能修改");
                return;
            }
            HeroNode2 temp = head.getNext();
            boolean flag = false;//找到该节点的标识
            while (true) {
                if (temp == null){
                    break;
                }
                if (temp.getNo() == node.getNo()){
                    flag = true;
                    break;
                }
                temp = temp.getNext();
            }
            if (flag){
                temp.setName(node.getName());
                temp.setNickName(node.getNickName());
            }else{
                System.err.printf("没有找到编号%d的节点，不能修改\n",node.getNo());
            }
        }

        public void add(HeroNode2 node){
            //因为head节点不能动，因此我们需要一个临时节点
            HeroNode2 temp = this.head;
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
            
            //形成一个双向链表
            temp.setNext(node);
            node.setPre(temp);
        }

        //遍历链表
        public void list(){

            if (this.head.getNext() == null){
                System.out.println("链表为空");
                return;
            }
            //因为头节点不能动
            HeroNode2 temp = this.head.getNext();
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
    
    private static class HeroNode2{
        private int no;
        private String name;
        private String nickName;
        private HeroNode2 next;//指向下一个节点，默认为null
        private HeroNode2 pre;//指向前一个节点，默认为null

        public HeroNode2(int no, String name, String nickName) {
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

        public HeroNode2 getNext() {
            return next;
        }

        public void setNext(HeroNode2 next) {
            this.next = next;
        }

        public HeroNode2 getPre() {
            return pre;
        }

        public void setPre(HeroNode2 pre) {
            this.pre = pre;
        }
    }
}
