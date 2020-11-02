package PlaneWar;
 
public class Bullet extends FlyingObject {
	private int speed = 3; //�ƶ����ٶ�
	
	public Bullet(int x,int y){//�ӵ���ʼλ��Ϊ��ɫ����λ��
		image = PlaneWar.bullet; 
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
}