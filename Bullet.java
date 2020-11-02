package �ɻ���ս;
 
public class Bullet extends FlyingObject {
	private int speed = 3; //�ƶ����ٶ�
	
	
	/*
	public Bullet(int x,int y){//�ӵ���ʼλ��Ϊ��ɫ����λ��
		image = PlaneWar.bullet1; //��ʼ�ӵ�Ϊ��ͨ�ӵ�
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	}*/
	
	public Bullet(int x,int y,int bullet_type){//�ӵ���ʼλ��Ϊ��ɫ����λ��
		
		image=PlaneWar.bullet1;
		
		switch(bullet_type) 
		{
		case Grift_type.wave:
			image=PlaneWar.bullet2;
			speed=7; //�����ӵ��ٶ�
			break;
		case Grift_type.laser:
			image=PlaneWar.bullet3;
			speed=15; //�����ӵ��ٶ�
			break;
		case Grift_type.electric:
			image=PlaneWar.bullet4;
			speed=15; //�����ӵ��ٶ�
			break;
		case Grift_type.Blitzball:
			image=PlaneWar.bullet5;
			speed=7; //�����ӵ��ٶ�
			break;
		case Grift_type.fire:
			image=PlaneWar.bullet6;
			speed=7; //�����ӵ��ٶ�
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
		return this.y<=-this.height; //�ӵ��ɳ�����
	}
	
	//�ӵ�����
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