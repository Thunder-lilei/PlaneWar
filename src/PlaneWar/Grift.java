package PlaneWar;
 
import java.util.Random;
 
//����
public class Grift extends FlyingObject implements Grift_type {
	private int xSpeed = 1; 
	private int ySpeed = 2; 
	private int awardType;  //��������

	public Grift(){
		image = PlaneWar.Grift; 
		width = image.getWidth();   
		height = image.getHeight(); 
		x = new Random().nextInt(PlaneWar.WIDTH-this.width); 
		y = -this.height; 
		awardType = new Random().nextInt(2); //�������
	}
	
	//�����ȼ�
	public int getType(){
		return awardType; 
	}
	
	//�ƶ�
	public void step(){
		x+=xSpeed; 
		y+=ySpeed; 
		if(x>=PlaneWar.WIDTH-this.width){ 
			xSpeed=-1;
		}
		if(x<=0){ 
			xSpeed=1;
		}
	}
	
	//�Ƿ�Խ��
	public boolean outOfBounds(){
		return this.y>=PlaneWar.HEIGHT; 
	}
	}