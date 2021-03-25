package KMeans;

import java.util.Random;
import java.util.Scanner;

import Bean.pointArray;

public class Kcluster {
	pointArray[] ypo;// 点集
	pointArray[] pacore = null;// old聚类中心
	pointArray[] pacoren = null;// new聚类中心
	// 初试聚类中心，点集

	public void productpoint(pointArray[] point,int x) {
		int num = point.length;
		ypo = point;//点集
		// 初始化聚类中心位置
		int core = x;
		this.pacore = new pointArray[core];// 存放聚类中心
		this.pacoren = new pointArray[core];

		Random rand = new Random();
		int temp[] = new int[core];
		temp[0] = rand.nextInt(num);//随机选取一个点作为聚类中心
		pacore[0] = new pointArray();
		pacore[0].setLng(ypo[temp[0]].getLng());
		pacore[0].setLat(ypo[temp[0]].getLat());
		pacore[0].flage = 0;
		// 避免产生重复的中心
		for (int i = 1; i < core; i++) {
			int flage = 0;
			int thistemp = rand.nextInt(num);
			for (int j = 0; j < i; j++) {
				if (temp[j] == thistemp) {
					flage = 1;// 有重复
					break;
				}
			}
			if (flage == 1) {
				i--;
			} else {
				pacore[i] = new pointArray();
				pacore[i].setLng(ypo[thistemp].getLng());
				pacore[i].setLat(ypo[thistemp].getLat());
				pacore[i].flage = 0;// 0表示聚类中心
			}
		}
		System.out.println("初始聚类中心：");
		for (int i = 0; i < pacore.length; i++) {
			System.out.println(pacore[i].getLng() + " " + pacore[i].getLat());
		}
	}
	// ///找出每个点属于哪个聚类中心
    public void searchbelong()// 找出每个点属于哪个聚类中心
    {

        for (int i = 0; i < ypo.length; i++) {
            double dist = 999;
            int lable = -1;
            for (int j = 0; j < pacore.length; j++) {
               double distance = distpoint(ypo[i], pacore[j]);
                if (distance < dist) {
                    dist = distance;
                    lable = j;
                    // po[i].flage = j + 1;// 1,2,3......

                }
            }
            ypo[i].flage = lable + 1;
       }
    }
    // 更新聚类中心
    public void calaverage() {

        for (int i = 0; i < pacore.length; i++) {
            System.out.println("以<" + pacore[i].getLng() + "," + pacore[i].getLat()
                    + ">为中心的点：");
            int numc = 0;
            pointArray newcore = new pointArray();
            for (int j = 0; j < ypo.length; j++) {

                if (ypo[j].flage == (i + 1)) {
                    System.out.println(ypo[j].getId());
                    numc += 1;
                    newcore.setLng(newcore.getLng()+ypo[j].getLng()) ;
                    newcore.setLat(newcore.getLat()+ypo[j].getLat()) ;
                    
                }
            }
            // 新的聚类中心
            pacoren[i] = new pointArray();
            pacoren[i].setLng(newcore.getLng() / numc);
            pacoren[i].setLat(newcore.getLat() / numc);
            pacoren[i].flage = 0;
            System.out.println("新的聚类中心：" + pacoren[i].getLng() + "," + pacoren[i].getLat());

        }
    }
    public double distpoint(pointArray px, pointArray py) {

        return Math.sqrt(Math.pow((px.getLng() - py.getLng()), 2)
                + Math.pow((px.getLat() - py.getLat()), 2));

    }
    public void change_oldtonew(pointArray[] old, pointArray[] news) {
        for (int i = 0; i < old.length; i++) {
            old[i].setLng(news[i].getLng());
            old[i].setLat(news[i].getLat());
            old[i].flage = 0;// 表示为聚类中心的标志。
        }
    }
    public void movecore(pointArray[] center) {
        // this.productpoint();//初始化，样本集，聚类中心，
        this.searchbelong();
        this.calaverage();//
        double movedistance = 0;
        int biao = -1;//标志，聚类中心点的移动是否符合最小距离
        for (int i = 0; i < pacore.length; i++) {
            movedistance = distpoint(pacore[i], pacoren[i]);
            System.out.println("distcore:" + movedistance);//聚类中心的移动距离
            if (movedistance < 0.01) {
                biao = 0;

            } else {                
                biao=1;//需要继续迭代，
                break;
            }
        }
        if (biao == 0) {
            System.out.println("迭代完毕！！！！！");
            System.out.println("聚类中心:");
            for(int i=0;i<pacoren.length;i++){
            	System.out.println(pacoren[i].getLng() + "," + pacoren[i].getLat());
            	center[i]=pacoren[i];
            }
            
        } else {
            change_oldtonew(pacore, pacoren);
            movecore(center);
        }
    }
}
