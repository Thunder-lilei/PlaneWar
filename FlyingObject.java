package �ɻ���ս;
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
		int x = this.x;             
		int x_width = this.x+this.width;  
		int y = this.y;             
		int y_height = this.y+this.height; //�л��������� ���򳤶� ���򳤶� 
		
		int x_bullet = bullet.x;
		int x_bullet_width=bullet.x+bullet.width;
		int x_bullet_mid_width=bullet.x+(bullet.width/2);
		int y_bullet = bullet.y;      
		int y_bullet_height=bullet.y+bullet.height;
		int y_bullet_mid_height=bullet.y+(bullet.height/2); //�ӵ��������� ���򳤶� ���г��� ����һ�㳤�� ����һ�볤��
		
		return (x_bullet>=x && x_bullet<=x_width && y_bullet>=y && y_bullet<=y_height) //�ӵ����ϽǴ��ڵл���Χ��
				|| 
			   (x_bullet_width>=x && x_bullet_width<=x_width && y_bullet_height>=y && y_bullet_height<=y_height) //�ӵ����ϽǴ��ڵл���Χ��
			   ||
			   (x_bullet_mid_width>=x && x_bullet_mid_width<=x_width && y_bullet_mid_height>=y && y_bullet_mid_height<=y_height) //�ӵ��������Ĵ��ڵл���Χ��
			   ; 
	}
	
}