package PlaneWar;
import java.awt.image.BufferedImage;
 
public abstract class FlyingObject {
	protected BufferedImage image; 
	protected int width;  
	protected int height; 
	protected int x; 
	protected int y; 
	//�ƶ�
	public abstract void step();
	//�Ƿ����
	public abstract boolean outOfBounds();
	
	//�Ƿ񱻻���
	public boolean shootBy(Bullet bullet){//�������ӵ�
		int x1 = this.x;             
		int x2 = this.x+this.width;  
		int y1 = this.y;             
		int y2 = this.y+this.height; 
		int x = bullet.x;            
		int y = bullet.y;            
		
		return x>=x1 && x<=x2//�ӵ��������
			   &&
			   y>=y1 && y<=y2; //�ӵ��������
	}
	
}