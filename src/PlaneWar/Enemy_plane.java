package PlaneWar;
import java.util.Random;
 
public class Enemy_plane extends FlyingObject implements Enemy {
	private int speed = 2; //�ƶ����ٶ�
	
	public Enemy_plane(){
		image = PlaneWar.airplane; //ͼƬ
		width = image.getWidth();   //��
		height = image.getHeight(); //��
		Random rand = new Random(); //���������
		x = rand.nextInt(PlaneWar.WIDTH-this.width); //x:0��(���ڿ�-�л���)֮��������
		y = -this.height; //y:���ĵл��ĸ�
	}
	
	//�÷�
	public int getScore(){
		return 5; //���һ���л���5��
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
 
 
 
 
 
 
 
 
 
 
 
 
 
 