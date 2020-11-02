package 飞机大战;
import java.util.Random;
 
public class Enemy_plane extends FlyingObject implements Enemy {
	private int speed = 2; //移动的速度
	private int live=5; //血量
	
	public Enemy_plane(int Door){
		switch(Door) 
		{
		case 1:
			image = PlaneWar.EnemyPlane1; //图片
			break;
		case 2:
			image = PlaneWar.EnemyPlane2; //图片
			break;
		case 3:
			image = PlaneWar.EnemyPlane3; //图片
			break;
		}
		//image = PlaneWar.EnemyPlane1; //图片
		width = image.getWidth();   //宽
		height = image.getHeight(); //高
		Random rand = new Random(); //随机数对象
		x = rand.nextInt(PlaneWar.WIDTH-this.width); //x:0到(窗口宽-敌机宽)之间的随机数
		y = -this.height; //y:负的敌机的高
		ChangeLive(Door);
	}
	
	//得分
	public int getScore(){
		return 5; //打掉一个敌机得5分
	}
	
	//更改血量
	public void ChangeLive(int live) 
	{
		this.live*=live;
	}
	
	//减少血量
	public void LowLive() 
	{
		live--;
	}
	
	//查看血量
	public int GetLive() 
	{
		return live;
	}
	
	//移动
	public void step(){
		y+=speed; 
	}
	
	//是否越界
	public boolean outOfBounds(){
		return this.y>=PlaneWar.HEIGHT; //敌机飞出界面
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 