package PlaneWar;
import java.awt.image.BufferedImage;
import java.util.Random;
 
public class ME extends FlyingObject {
	private int doubleFire; //����ֵ Ϊ��ʱ����ʹ��˫������
	private int life; //��
	private BufferedImage[] images; //���л���ͼƬ����
	private int index; 
	
	public ME(){
		image = PlaneWar.Me;
		
		width = image.getWidth();   
		height = image.getHeight(); 
		x = 150; 
		y = 400; 
		doubleFire = 10000; 
		life = 3; 
		images = new BufferedImage[]{PlaneWar.Me,PlaneWar.Me}; 
		index = 0; 
	}
	
	//�ƶ�
	public void step(){ 
		image = images[index++/10%images.length];
	}
	
	//����
	public Bullet[] shoot(){
		int xStep = this.width/4; //�ɻ�Խ�� �ӵ�Խ��
		int yStep = 20; 
		if(doubleFire>0){ //�Ƿ�˫������
			Bullet[] bs = new Bullet[2]; //2���ӵ�
			bs[0] = new Bullet(this.x+1*xStep,this.y-yStep); 
			bs[1] = new Bullet(this.x+3*xStep,this.y-yStep); 
			doubleFire-=2; //����һ��˫�������������ֵ��2
			return bs;
		}else{ //����
			Bullet[] bs = new Bullet[1]; //1���ӵ�
			bs[0] = new Bullet(this.x+2*xStep,this.y-yStep); 
			return bs;
		}
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
	public void addDoubleFire(){
		doubleFire+=40; 
	}
	
	//��ջ���
	public void clearDoubleFire(){
		doubleFire = 0;
	}
	
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
 
 
 
 
 
 
 
 
 
 
 
 
 