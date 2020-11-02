package �ɻ���ս;
import java.util.Random;
 
public class Enemy_plane extends FlyingObject implements Enemy {
	private int speed = 2; //�ƶ����ٶ�
	private int live=5; //Ѫ��
	
	public Enemy_plane(int Door){
		switch(Door) 
		{
		case 1:
			image = PlaneWar.EnemyPlane1; //ͼƬ
			break;
		case 2:
			image = PlaneWar.EnemyPlane2; //ͼƬ
			break;
		case 3:
			image = PlaneWar.EnemyPlane3; //ͼƬ
			break;
		}
		//image = PlaneWar.EnemyPlane1; //ͼƬ
		width = image.getWidth();   //��
		height = image.getHeight(); //��
		Random rand = new Random(); //���������
		x = rand.nextInt(PlaneWar.WIDTH-this.width); //x:0��(���ڿ�-�л���)֮��������
		y = -this.height; //y:���ĵл��ĸ�
		ChangeLive(Door);
	}
	
	//�÷�
	public int getScore(){
		return 5; //���һ���л���5��
	}
	
	//����Ѫ��
	public void ChangeLive(int live) 
	{
		this.live*=live;
	}
	
	//����Ѫ��
	public void LowLive() 
	{
		live--;
	}
	
	//�鿴Ѫ��
	public int GetLive() 
	{
		return live;
	}
	
	//�ƶ�
	public void step(){
		y+=speed; 
	}
	
	//�Ƿ�Խ��
	public boolean outOfBounds(){
		return this.y>=PlaneWar.HEIGHT; //�л��ɳ�����
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 