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
        //无序保存
//        singleLinkList.addNode(node3);
//        singleLinkList.addNode(node2);
//        singleLinkList.addNode(node1);
//        singleLinkList.addNode(node4);
        
        //加入按编号的顺序
        singleLinkList.addNodeByOrder(node3);
        singleLinkList.addNodeByOrder(node2);
        singleLinkList.addNodeByOrder(node1);
        singleLinkList.addNodeByOrder(node4);
        singleLinkList.list();
        
        //修改某个节点的属性
        HeroNode node6=new HeroNode(2, "小卢", "玉麒麟111");
        singleLinkList.update(node6);
        
        //删除某个节点
        singleLinkList.delete(4);
        System.out.println("修改后的数据");
        singleLinkList.list();
        
        //测试获取链表有效长度
        System.out.printf("单链表的有效长度：%d\n",getLength(singleLinkList.getHead()));
        
        //测试查找倒数第k个节点
        System.out.printf("查找的单向链表倒数第k个节点%s",findLastIndexNode(singleLinkList.getHead(),2));
        
    }

    /**
     * 查找单向链表的倒数第k个节点 【新浪面试题】
     * 思路
     * 1、编写方法，接收head节点，同时接收一个index
     * 2、index 表示是倒数第index个节点
     * 3、先把链表从头到尾遍历，得到链表的总长度 getLength
     * 4、得到size后，我们从链表的第一个开始遍历(size-index)个，就可以得到
     * 5、如果找到了，则返回该节点，否则返回null
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.getNext() == null){
            return null;
        }
        
        int size = getLength(head);
        if (index <=0 || index > size){
            return null;
        }
        
        HeroNode current= head.getNext();
        for (int i=0;i<size-index;i++){
            current = current.getNext();
        }
        return current;
    }

    /**
     * 计算单向链表的有效长度
     * @param head
     * @return
     */
    public static int getLength(HeroNode head){
        if (head.getNext() ==null){
            return 0;
        }
        int length= 0;
        //定义一个辅助变量
        HeroNode current= head.getNext();
        while (current != null){
            length++;
            current = current.getNext();
        }
        return length;
    }
    
    private static class SingleLinkList{
        private HeroNode head = new HeroNode(0, "", "");


        public HeroNode getHead() {
            return head;
        }

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

        /**
         * 添加节点 按编号排序
         * @param node
         */
        public void addNodeByOrder(HeroNode node){
            HeroNode temp = head;
            boolean flag = false;//标志添加的编号是否存在
            while (true){
                //链表的最后
                if (temp.next == null){
                    break;
                }
                //位置找到了，插入在temp的后面
                if (temp.next.getNo() > node.getNo()){
                    break;
                }else if (temp.next.getNo() == node.getNo()){
                    //添加的节点编号已经存在
                    flag = true;//说明编号存在
                    break;
                }
                temp = temp.next;//后移，遍历当前链表
            }
            //判断flag的值
            if (flag){
                System.out.printf("准备插入的英雄的编号 %d 已经存在，不能加入\n",node.no);
            }else{
                node.next = temp.next;
                temp.next = node;
            }
        }
        
        //修改链表的某个节点的属性值
        public void update(HeroNode node){
            if (head.getNext() == null){
                System.err.println("链表为空，不能修改");
                return;
            }
            HeroNode temp = head.getNext();
            boolean flag = false;//找到该节点的标识
            while (true) {
                if (temp.getNext() == null){
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
        
        //删除某个节点
        public void delete(int no){
            HeroNode temp=head;
            boolean flag= false;//是否找到这个删除的节点
            while (true){
                if (temp.getNext() == null){
                    break;
                }
                if (temp.getNext().getNo() == no){
                    flag = true;
                    break;
                }
                temp = temp.getNext();
            }
            //找到了
            if (flag){
                temp.setNext(temp.getNext().getNext());
            }else {
                System.err.printf("要删除的节点%d不存在\n",no);
            }
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
