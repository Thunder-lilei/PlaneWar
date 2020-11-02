package 飞机大战;
 
public class Bullet extends FlyingObject {
	private int speed = 3; //移动的速度
	
	
	/*
	public Bullet(int x,int y){//子弹初始位置为角色现在位置
		image = PlaneWar.bullet1; //初始子弹为普通子弹
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	}*/
	
	public Bullet(int x,int y,int bullet_type){//子弹初始位置为角色现在位置
		
		image=PlaneWar.bullet1;
		
		switch(bullet_type) 
		{
		case Grift_type.wave:
			image=PlaneWar.bullet2;
			speed=7; //增加子弹速度
			break;
		case Grift_type.laser:
			image=PlaneWar.bullet3;
			speed=15; //增加子弹速度
			break;
		case Grift_type.electric:
			image=PlaneWar.bullet4;
			speed=15; //增加子弹速度
			break;
		case Grift_type.Blitzball:
			image=PlaneWar.bullet5;
			speed=7; //增加子弹速度
			break;
		case Grift_type.fire:
			image=PlaneWar.bullet6;
			speed=7; //增加子弹速度
			break;
		}
		
		width = image.getWidth();
		height = image.getHeight();
		
		this.x = x;
		this.y = y;
	}
	
	public void step(){
		y-=speed; 
	}
	
	public boolean outOfBounds(){
		return this.y<=-this.height; //子弹飞出画面
	}
	
	//子弹类型
	public int get_bullettype() 
	{
		if(this.image==PlaneWar.bullet1)
			return 1;
		else if(this.image==PlaneWar.bullet2)
			return 2;
		else if(this.image==PlaneWar.bullet3)
			return 3;
		else if(this.image==PlaneWar.bullet4)
			return 4;
		else if(this.image==PlaneWar.bullet5)
			return 5;
		else
			return 6;
	}
	
}