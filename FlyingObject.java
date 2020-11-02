package 飞机大战;
import java.awt.image.BufferedImage;
 
public abstract class FlyingObject {
	protected BufferedImage image; 
	protected int width;  
	protected int height; 
	protected int x;
	protected int y;
	//移动
	public abstract void step();
	//是否出界
	public abstract boolean outOfBounds();
	
	//是否被击中
	public boolean shootBy(Bullet bullet){//参数是子弹
		int x = this.x;             
		int x_width = this.x+this.width;  
		int y = this.y;             
		int y_height = this.y+this.height; //敌机横纵坐标 横向长度 纵向长度 
		
		int x_bullet = bullet.x;
		int x_bullet_width=bullet.x+bullet.width;
		int x_bullet_mid_width=bullet.x+(bullet.width/2);
		int y_bullet = bullet.y;      
		int y_bullet_height=bullet.y+bullet.height;
		int y_bullet_mid_height=bullet.y+(bullet.height/2); //子弹横纵坐标 横向长度 纵行长度 横向一般长度 纵向一半长度
		
		return (x_bullet>=x && x_bullet<=x_width && y_bullet>=y && y_bullet<=y_height) //子弹左上角处于敌机范围内
				|| 
			   (x_bullet_width>=x && x_bullet_width<=x_width && y_bullet_height>=y && y_bullet_height<=y_height) //子弹右上角处于敌机范围内
			   ||
			   (x_bullet_mid_width>=x && x_bullet_mid_width<=x_width && y_bullet_mid_height>=y && y_bullet_mid_height<=y_height) //子弹正面中心处于敌机范围内
			   ; 
	}
	
}