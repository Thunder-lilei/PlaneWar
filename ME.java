package �ɻ���ս;
import java.awt.image.BufferedImage;
 

public class ME extends FlyingObject {
	private int life; //��
	BufferedImage image; //���л���ͼƬ����
	private int bullet_type;
	
	public ME(){
		image = PlaneWar.Me;
		
		width = image.getWidth();   
		height = image.getHeight(); 
		x = 150; //��ʼλ��
		y = 400;  
		life = 5; 
		//images = new BufferedImage[]{PlaneWar.Me,PlaneWar.Me}; 
		//index = 0; 
		bullet_type=Grift_type.DOUBLE_FIRE;//��ʼ�ӵ�
	}
	
	//�ƶ�
	public void step(){ 
		//image = images[index++/10%images.length];
	}
	
	//�޸��ӵ�����
	public void change_bullet_type(int bullet_type) 
	{
		this.bullet_type=bullet_type;
	}
	
	//����
	public Bullet[] shoot(){
		int xStep = this.width/4; //��ö�ӵ��������
		//int yStep = 20; 
		
		Bullet[] bs = new Bullet[2]; //2���ӵ�
		bs[0] = new Bullet(this.x+1*xStep,this.y,bullet_type); 
		bs[1] = new Bullet(this.x+3*xStep,this.y,bullet_type);
		return bs;
	}
	
	//�ɻ�������ƶ�
	public void moveTo(int x,int y){
		this.x = x-this.width/2; //�������������
		this.y = y-this.height/2; 
	}
	
	public boolean outOfBounds(){
		return false; //����Խ��
	}
 
	//����
	public void addLife(){
		life++; //������1
	}
	
	//����
	public int getLife(){
		return life; //��������
	}
	
	//����
	public void subtractLife(){
		life--; //������1
	}
	
	//���ӻ���ֵ
//	public void addDoubleFire(){
//		doubleFire+=40; 
//	}
	
	//��ջ���
//	public void clearDoubleFire(){
//		doubleFire = 0;
//	}
	
	//�Ƿ���ײ
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
 
 
 
 
 
 
 
 
 
 
 
 
 