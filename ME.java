package 飞机大战;
import java.awt.image.BufferedImage;
 

public class ME extends FlyingObject {
	private int life; //命
	BufferedImage image; //可切换的图片数组
	private int bullet_type;
	
	public ME(){
		image = PlaneWar.Me;
		
		width = image.getWidth();   
		height = image.getHeight(); 
		x = 150; //初始位置
		y = 400;  
		life = 5; 
		//images = new BufferedImage[]{PlaneWar.Me,PlaneWar.Me}; 
		//index = 0; 
		bullet_type=Grift_type.DOUBLE_FIRE;//初始子弹
	}
	
	//移动
	public void step(){ 
		//image = images[index++/10%images.length];
	}
	
	//修改子弹类型
	public void change_bullet_type(int bullet_type) 
	{
		this.bullet_type=bullet_type;
	}
	
	//开火
	public Bullet[] shoot(){
		int xStep = this.width/4; //两枚子弹相隔距离
		//int yStep = 20; 
		
		Bullet[] bs = new Bullet[2]; //2发子弹
		bs[0] = new Bullet(this.x+1*xStep,this.y,bullet_type); 
		bs[1] = new Bullet(this.x+3*xStep,this.y,bullet_type);
		return bs;
	}
	
	//飞机随鼠标移动
	public void moveTo(int x,int y){
		this.x = x-this.width/2; //不减会略有误差
		this.y = y-this.height/2; 
	}
	
	public boolean outOfBounds(){
		return false; //永不越界
	}
 
	//加命
	public void addLife(){
		life++; //命数增1
	}
	
	//命数
	public int getLife(){
		return life; //返回命数
	}
	
	//减命
	public void subtractLife(){
		life--; //命数减1
	}
	
	//增加火力值
//	public void addDoubleFire(){
//		doubleFire+=40; 
//	}
	
	//清空火力
//	public void clearDoubleFire(){
//		doubleFire = 0;
//	}
	
	//是否相撞
	public boolean hit(FlyingObject other){
		int x1 = other.x-this.width/2;               
		int x2 = other.x+other.width+this.width/2;   
		int y1 = other.y-this.height/2; 			 
		int y2 = other.y+other.height+this.height/2; 
		int x = this.x+this.width/2; 				 
		int y = this.y+this.height/2; 				 
		
		return x>=x1 && x<=x2
			   &&
			   y>=y1 && y<=y2; 
	}
	
}
 
 
 
 
 
 
 
 
 
 
 
 
 