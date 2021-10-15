package Stack_;

public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        initializationMap(map);
        setObstacle(map, 3, 1);
        setObstacle(map, 3, 2);
        setObstacle(map, 3, 3);
        setObstacle(map, 3, 4);
        setObstacle(map, 3, 5);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==================");
        setWay(map, 1, 1);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void initializationMap(int[][] map) {
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[map.length - 1][i] = 1;
        }
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][map[0].length - 1] = 1;
        }
    }

    public static boolean setObstacle(int[][] map, int i, int j) {
        if (i >= map.length || j >= map[0].length) return false;
        map[i][j] = 1;
        return true;
    }

    //    map表示地图
//    i，j表示初始位置坐标
//    如果小球能到map[6][5]位置，则说明通路找到
//    约定：当map[i][j]为0表示该点没有走过，1表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
//    走迷宫策略为：下->右->上->左
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) return true;
        if (map[i][j] == 0) {
            map[i][j] = 2;

            if (setWay(map, i + 1, j)) {
                return true;
            } else if (setWay(map, i, j + 1)) {
                return true;
            } else if (setWay(map, i - 1, j)) {
                return true;
            }
            if (setWay(map, i, j - 1)) {
                return true;
            } else {
                //说明该点走不通，是死路
                map[i][j] = 3;
                return false;
            }
        } else {
            return false;
        }
    }
}
