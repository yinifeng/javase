package com.hobart.data.linklist;

/**
 * 单向环形列表
 * 
 * 约瑟夫问题(丢手帕)：
 * 设编号为1,2,3,....,n的n个人围坐一圈，约定编号为k(1<=k<=n)的人从1开始报数，
 * 数到m的那个人出列，它的下一位又从1开始报数，数到m那个人又出列，依次类推，
 * 直到所有人出列为止，由此产生一个出队编号的序列
 * 
 * 例如：
 * n=5,即有5个人
 * k=1,从第一个人开始报数
 * m=2 数2下
 * 出队顺序：
 * 2->4->1->5->3
 * 
 * 单向环形列表解决
 * 
 */
public class CircleSingleLinkedListDemo {

    public static void main(String[] args) {
        //构建环形链表，遍历
        CircleSingleLinkedList circleSingleLinkedList=new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(10);
        circleSingleLinkedList.listBoy();
        
        //测试出圈顺序
        System.out.println("-----测试出圈------");
        //circleSingleLinkedList.countBoy(1, 2, 5);//2->4->1->5->3
        circleSingleLinkedList.countBoy(1, 3, 5);
    }
    
    private static class CircleSingleLinkedList{
        private Boy first = new Boy(-1);


        /**
         * 根据用户输入，计算小孩出圈的顺序
         * 
         * 
         * 
         * 根据用户的输入，生成一个小孩出圈的顺序
         * n = 5 , 即有5个人 
         * k = 1, 从第一个人开始报数
         * m = 2, 数2下
         *
         *
         * 1.  需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点.
         * 补充： 小孩报数前，先让 first 和  helper 移动 k - 1次
         * 2.  当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次
         * 3.  这时就可以将first 指向的小孩节点 出圈
         * first = first .next 
         * helper.next = first  
         * 原来first 指向的节点就没有任何引用，就会被回收
         *
         * 出圈的顺序
         * 2->4->1->5->3
         * 
         * @param startNo 表示从第几个小孩开始
         * @param countNum 表示数几下
         * @param nums 表示最初有多少小孩在圈中
         */
        public void countBoy(int startNo, int countNum, int nums) {
            //先对数据校验
            if (first == null || startNo < 1 || startNo > nums) {
                System.err.println("参数输入有误，重新输入");
                return;
            }

            //创建要给辅助指针，帮助完成小孩出圈
            Boy helper = first;
            //需求创建一个辅助指针(变量)helper，事先应该指向环形链表的最后这个节点
            while (true) {
                if (helper.getNext() == first) {
                    break;
                }
                helper = helper.getNext();
            }
            //小孩报数前，先让first和helper移动k-1次
            for (int i=0;i<startNo-1;i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
            //这里是一个循环操作，知道圈中只有一个节点
            while (true){
                //说明圈中只剩一个小孩
                if (helper == first){
                    break;
                }
                //让first和helper指针同时的移动countNum-1
                for (int i=0;i<countNum-1;i++){
                    first = first.getNext();
                    helper = helper.getNext();
                }
                //这时候first指向的节点就是要出圈的小孩
                System.out.printf("小孩%d出圈\n",first.getNo());
                //这时将first指向的小孩节点出圈
                first = first.getNext();
                helper.setNext(first);
            }
            System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());
        }

        //添加小孩构成环形链表
        public void addBoy(int nums){
            if (nums <= 0){
                System.err.printf("nums的值不正确%d\n",nums);
                return;
            }
            Boy curBoy = null;//辅助指针,帮助构建环形链表
            //使用for循环
            for (int i=1;i<=nums;i++){
                //根据编号创建小孩节点
                Boy boy=new Boy(i);
                //如果是第一小孩
                if(i == 1){
                    first = boy;
                    first.setNext(first);//指向本身
                    curBoy = first;
                }else{
                    curBoy.setNext(boy);
                    boy.setNext(first);
                    curBoy = boy;
                }
            }
        }
        
        public void listBoy(){
            if (first.getNext() == null){
                System.out.println("没有任何一个小孩！");
                return;
            }
            Boy curBoy =first;
            while (true){
                System.out.printf("小孩的编号:%d\n",curBoy.getNo());
                if (curBoy.getNext() == first){
                    break;
                }
                curBoy = curBoy.getNext();
            }
        }
    }
    
    //创建一个boy类
    private static class Boy{
        private int no;//编号
        
        private Boy next;//指向下一个节点，默认为null
        
        public Boy(int no){
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }
}
