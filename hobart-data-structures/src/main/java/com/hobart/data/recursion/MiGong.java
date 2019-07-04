package com.hobart.data.recursion;

/**
 * 迷宫回溯问题
 * 
 * 
 */
public class MiGong {

    public static void main(String[] args) {
        //定义一个地图
        int[][] map=new int[8][7];
        
        //1:代表地图挡板，2：行进轨迹，3：死路
        
        //设置横向挡板
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        
        //设置竖向挡板
        for (int i = 0; i < 8; i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("初始一张地图：");
        for (int i = 0; i < map.length ; i++){
            for (int j=0; j < map[i].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        //小球开始行走
        //setWay(map,1,1);
        setWay2(map,1,1);
        
        System.out.println("小球行走，并标识地图的轨迹：");
        for (int i = 0; i < map.length ; i++){
            for (int j=0; j < map[i].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    
    //使用递归回溯给小球找路

    /**
     * 说明：
     * 1、map表示地图
     * 2、x，y表示地图的哪个位置开始出发(1，1)
     * 3、如果小球能到map[6][5]位置，则说明通路找到
     * 4、约定：当map[i][j]为0表示该点没有走过 1:表示挡板，2：行驶轨迹
     * @param map 表示地图
     * @param x 从哪个位置开始
     * @param y
     * @return 如果走到同路就返回true，否则false
     */
    public static boolean setWay(int[][] map,int x,int y){
        if (map[6][5] == 2){//终点已经找到
            return true;
        }else{
            if (map[x][y] == 0){//当前点还未走
                //按照策略 下->右->上->左 行走
                map[x][y] = 2;
                if (setWay(map,x+1, y)){//向下走
                    return true;
                }else if(setWay(map,x, y+1)){
                    return true;
                }else if(setWay(map,x-1, y)){
                    return true;
                }else if(setWay(map, x, y-1)){
                    return true;
                }else{
                    //该路是死路
                    map[x][y] = 3;
                    return false;
                }
            }else{//如果map[x][y] != 0 ,可能是 1，2，3
                return false;
            }
            
        }
    }

    /**
     * 小球行走的轨迹和小球行走策略有关
     * 上->右->下->左
     * @param map
     * @param x
     * @param y
     * @return
     */
    public static boolean setWay2(int[][] map,int x,int y){
        if (map[6][5] == 2){//终点已经找到
            return true;
        }else{
            if (map[x][y] == 0){//当前点还未走
                //按照策略 上->右->下->左 行走
                map[x][y] = 2;
                if (setWay2(map,x-1, y)){//向下走
                    return true;
                }else if(setWay2(map,x, y+1)){//右
                    return true;
                }else if(setWay2(map,x+1, y)){//下
                    return true;
                }else if(setWay2(map, x, y-1)){//左
                    return true;
                }else{
                    //该路是死路
                    map[x][y] = 3;
                    return false;
                }
            }else{//如果map[x][y] != 0 ,可能是 1，2，3
                return false;
            }

        }
    }
}
