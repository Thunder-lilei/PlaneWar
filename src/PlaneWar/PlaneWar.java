package PlaneWar;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
 
public class PlaneWar extends JPanel {
	public static final int WIDTH = 400;  //娓告垙绐楀彛澶у皬
	public static final int HEIGHT = 645; 
	
	public static BufferedImage background; //鑳屾櫙鍥�
	public static BufferedImage start;   	//鍚姩鍥�
	public static BufferedImage pause;		//鏆傚仠鍥�
	public static BufferedImage gameover;	//娓告垙缁撴潫鍥�
	public static BufferedImage airplane;	//鏁屾満
	public static BufferedImage Grift;		//濂栧姳
	public static BufferedImage bullet;		//瀛愬脊
	public static BufferedImage Me;			//瑙掕壊
	static{
		try{
			
			background = ImageIO.read(new File("C:\\Users\\lilei\\Desktop\\images\\background\\background_1.png"));
			start = ImageIO.read(new File("C:\\Users\\lilei\\Desktop\\images\\GameInterface\\interface_1.png"));
			pause = ImageIO.read(new File("C:\\Users\\lilei\\Desktop\\images\\GameInterface\\pause.png"));
			gameover = ImageIO.read(new File("C:\\Users\\lilei\\Desktop\\images\\GameInterface\\jeimian_2.png"));
			airplane = ImageIO.read(new File("C:\\Users\\lilei\\Desktop\\images\\BossPlane\\plane_5.png"));
			Grift = ImageIO.read(new File("C:\\Users\\lilei\\Desktop\\images\\Grift\\award_2.png"));
			bullet = ImageIO.read(new File("C:\\Users\\lilei\\Desktop\\images\\bullet\\bullet_1.png"));
			int type = new Random().nextInt(3);
			if(type==1)
			Me = ImageIO.read(new File("C:\\Users\\lilei\\Desktop\\images\\MEPlane\\plane_1.png"));
			else if(type==2)
			Me = ImageIO.read(new File("C:\\Users\\lilei\\Desktop\\images\\MEPlane\\plane_2.png"));
			else
			Me = ImageIO.read(new File("C:\\Users\\lilei\\Desktop\\images\\MEPlane\\plane_3.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private ME me = new ME(); //鍒濆鐜╁
	private FlyingObject[] flyings = {}; //鏁屼汉
	private Bullet[] bullets = {}; //寮硅嵂
	
	public static final int START = 0;     //鍚姩鐘舵��
	public static final int RUNNING = 1;   //杩愯鐘舵��
	public static final int PAUSE = 2;     //鏆傚仠鐘舵��
	public static final int GAME_OVER = 3; //娓告垙缁撴潫鐘舵��
	private int state = START; //榛樿鍚姩鐘舵��
	
	//鏁屼汉鍏ュ満
	public FlyingObject nextit(){
		int type = new Random().nextInt(100);
		
		if(type==1){ //濂栧姳
			return new Grift();
		}else{ //鏁屾満
			return new Enemy_plane();
		}
	}
	
	int flyIndex = 0; 
	//鍔犺浇鏁屼汉/濂栧姳
	public void enterAction(){
		flyIndex++;
		if(flyIndex%40==0){
			FlyingObject obj = nextit();
			flyings = Arrays.copyOf(flyings,flyings.length+1); //鎵╁
			flyings[flyings.length-1] = obj; //娣诲姞鏁屼汉
		}
	}
	
	//鍏ㄩ儴绉诲姩
	public void stepAction(){ 
		me.step(); //鑻遍泟鏈鸿蛋涓�姝�
		for(int i=0;i<flyings.length;i++){ //閬嶅巻鎵�鏈夋晫浜�
			flyings[i].step(); //鏁屼汉璧颁竴姝�
		}
		for(int i=0;i<bullets.length;i++){ //閬嶅巻鎵�鏈夊瓙寮�
			bullets[i].step(); //瀛愬脊璧颁竴姝�
		}
	}
	
	int shootIndex = 0; 
	//寮�鐏�
	public void shootAction(){ 
		shootIndex++; 
		if(shootIndex%30==0){ 
			Bullet[] bs = me.shoot(); 
			bullets = Arrays.copyOf(bullets,bullets.length+bs.length); //鎵╁(bs鏈夊嚑涓厓绱犲氨鎵╁ぇ鍑犱釜瀹归噺)
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length); //鏁扮粍鐨勮拷鍔�
		}
	}
	
	//椋炲嚭鐢婚潰鐨�
	public void outOfBoundsAction(){
		int index = 0; //娌℃湁瓒婄晫涓暟
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; //涓嶈秺鐣屾晫浜烘暟缁�
		for(int i=0;i<flyings.length;i++){
			if(!flyings[i].outOfBounds()){ //娌℃湁瓒婄晫
				flyingLives[index] =flyings[i];//娌℃湁瓒婄晫鐨勪繚鐣�
				index++;
			}
		}
		//杩旇繕鏁屼汉
		flyings = Arrays.copyOf(flyingLives,index); 
		
		index = 0; //娌℃湁瓒婄晫涓暟褰掗浂
		//娌℃湁瓒婄晫瀛愬脊
		Bullet[] bulletLives = new Bullet[bullets.length]; 
		for(int i=0;i<bullets.length;i++){
			Bullet b = bullets[i]; 
			if(!bullets[i].outOfBounds()){ 
				bulletLives[index] = bullets[i];
				index++;
			}
		}
		bullets = Arrays.copyOf(bulletLives,index); 
		
	}
	
	public void bangAction(){ 
		for(int i=0;i<bullets.length;i++){ 
			bang(bullets[i]); //鍑讳腑
		}
	}
	
	int score = 0; //鐜╁鐨勫緱鍒�
	//瀛愬脊鍑讳腑
	public void bang(Bullet b){
		int index = -1; 
		
		for(int i=0;i<flyings.length;i++){ 
			if(flyings[i].shootBy(b)){ ////鍒ゆ柇鏄惁鍑讳腑
				index = i; //璁板綍琚嚮涓晫浜�
				break;
			}
		}
		
		if(index!=-1){ //鏈夎鍑讳腑鐨�
			FlyingObject it = flyings[index]; 
			
			//鍒ゆ柇绫诲瀷
			if(it instanceof Enemy_plane){  //鏁屼汉
				Enemy_plane e = (Enemy_plane)it;  //寮鸿浆涓烘晫浜�
				score += e.getScore(); //寰楀垎
				
				//鍒犻櫎琚嚮涓殑鏁屼汉
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
			
			else{   //濂栧姳
					//鏃犳晥鏋�
				}
			}
			

		}
	
	//纰版挒浜嬩欢
	public void hitAction(){
		for(int i=0;i<flyings.length;i++){
			if(me.hit(flyings[i])){ //鐩告挒
				FlyingObject it = flyings[i]; 
				
				//鍒ゆ柇绫诲瀷
				if(it instanceof Enemy_plane){  //鏁屼汉
					Enemy_plane e = (Enemy_plane)it;  //寮鸿浆涓烘晫浜�
					score += e.getScore(); //寰楀垎
					me.subtractLife(); //瑙掕壊鍑忓皯鐢熷懡
					me.clearDoubleFire(); //鍘婚櫎鍙屽�嶇伀鍔�
				}
				
				else { //濂栧姳
					Grift a = (Grift)it;   //寮鸿浆涓哄鍔�
					int type = a.getType(); //鍒ゆ柇濂栧姳绛夌骇
					
					switch(type){
					case Grift_type.DOUBLE_FIRE:   //鍙屽�嶇伀鍔�
						me.addDoubleFire(); 
						break;
					case Grift_type.LIFE:    //鍔犲懡
						me.addLife(); 
						break;
					}
				}
				
				//鍒犻櫎琚挒鏁屼汉
				FlyingObject t = flyings[i];
				flyings[i] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
		}
	}
	
	//鍒ゅ畾娓告垙缁撴潫
	public void checkGameOverAction(){
		if(me.getLife()<=0){ //鐢熷懡涓�0
			state=GAME_OVER;   
		}
	}
	
	//寮�濮嬫父鎴�
	public void action(){
		//榧犳爣閫傞厤鍣�
		//绉诲嚭 绉诲洖 鐐瑰嚮 绉诲姩
		MouseAdapter l = new MouseAdapter(){
			
			public void mouseMoved(MouseEvent e){
				if(state==RUNNING){ //杩愯鐘舵�佷笅鎵ц
					int x = e.getX(); //鑾峰彇榧犳爣鍧愭爣
					int y = e.getY(); 
					me.moveTo(x, y); //绉诲姩椋炴満
				}
			}
			
			public void mouseClicked(MouseEvent e){
				switch(state){ 		//鍒ゅ畾娓告垙鐜拌鐘舵��
				case START:         //寮�濮嬫父鎴�-銆嬭繍琛屾父鎴�
					state=RUNNING; 
					break;
				case GAME_OVER:  //娓告垙缁撴潫-銆嬪垎鏁板綊闆�-銆嬫柊寤鸿鑹�-銆嬫柊寤烘晫浜�-銆嬮噸鏂板惎鍔�
					score = 0;   
					me = new ME();
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					state=START; 
					break;
				}
			}
			
			//榧犳爣绉婚櫎娓告垙鏆傚仠
			public void mouseExited(MouseEvent e){
				if(state==RUNNING){ 
					state=PAUSE;    
				}
			}
			//榧犳爣绉诲洖娓告垙缁х画
			public void mouseEntered(MouseEvent e){
				if(state==PAUSE){  
					state=RUNNING; 
				}
			}
			
		};
		
		this.addMouseListener(l); //澶勭悊榧犳爣鎿嶄綔浜嬩欢
		this.addMouseMotionListener(l); //澶勭悊榧犳爣婊戝姩浜嬩欢
		
		Timer timer = new Timer(); //瀹氭椂鍣�
		int little_time = 10; //鏃堕棿闂撮殧
		
		//schedule锛坱ask锛宼ime锛夊湪鏃堕棿绛変簬鎴栬�呰秴杩噒ime鐨勬椂鍊欐墽琛屼笖浠呮墽琛屼竴娆�
		//schedule锛坱ask锛宼ime锛宲eriod锛夋椂闂寸瓑浜庢垨鑰呰秴杩噒ime棣栨鎵цtask锛屼箣鍚庢瘡闅攑eriod姣閲嶅鎵ц涓�娆′换鍔�
		timer.schedule(new TimerTask(){
			public void run(){ 
				if(state==RUNNING){ //杩愯鐘舵�佹椂鎵ц
					enterAction(); //鍔犺浇鏁屼汉
					stepAction();  //鍔犺浇椋炶鐗╋紙鎵�鏈変細椋炵殑锛�
					shootAction(); //寮�鐏�
					outOfBoundsAction(); //鍒犻櫎瓒婄晫鐨勯琛岀墿
					bangAction();  //瀛愬脊鍛戒腑
					hitAction();   //鎾炴満
					checkGameOverAction(); //鍒ゆ柇娓告垙缁撴潫
				}
				repaint(); //閲嶇敾
			}
		},0,little_time); //0姣寮�濮� 姣忛殧10姣鍒锋柊
	}
	
	//鐢�
	public void paint(Graphics g){
		g.drawImage(background,0,0,null); //鑳屾櫙
		painthero(g); //瑙掕壊
		paintFlyingObjects(g); //椋炶鐗�
		paintBullets(g); //瀛愬脊
		paintScoreAndLife(g); //鍒嗘暟 鍛�
		paintState(g); //鐘舵��
	}
	//鐢昏鑹�
	public void painthero(Graphics g){
		g.drawImage(me.image,me.x,me.y,null); 
	}
	//鐢婚琛岀墿
	public void paintFlyingObjects(Graphics g){
		for(int i=0;i<flyings.length;i++){ 
			g.drawImage(flyings[i].image,flyings[i].x,flyings[i].y,null);//杩欎釜Null鏃犱激澶ч泤
		}
	}
	//鐢诲瓙寮�
	public void paintBullets(Graphics g){
		for(int i=0;i<bullets.length;i++){ 
			g.drawImage(bullets[i].image,bullets[i].x,bullets[i].y,null);//杩欎釜Null鏃犱激澶ч泤
		}
	}
	//鐢� 鍒� 鍛�
	public void paintScoreAndLife(Graphics g){
		g.setColor(new Color(0,0,255)); //钃濊壊
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24)); //瀛椾綋
		g.drawString("SCORE: "+score,10,25);//浣嶇疆
		g.drawString("LIFE: "+me.getLife(),10,45);
	}
	//鐢荤姸鎬�
	public void paintState(Graphics g){
		switch(state){ 
		case START:
			g.drawImage(start,0,0,null);
			break;
		case PAUSE:
			g.drawImage(pause,0,0,null);
			break;
		case GAME_OVER:
			g.drawImage(gameover,0,0,null);
			break;
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("椋炴満澶ф垬DEMO"); 
		PlaneWar game = new PlaneWar(); //杞藉叆娓告垙
		frame.add(game); 
		frame.setSize(WIDTH, HEIGHT); 
		frame.setAlwaysOnTop(true); //绐楀彛淇濇寔鍦ㄦ墍鏈夌▼搴忎笂鏂�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //璁剧疆绐楀彛榛樿鍏抽棴鎿嶄綔(鍏抽棴绐楀彛鏃堕��鍑虹▼搴�)
		frame.setLocationRelativeTo(null); //绐楀彛灞呬簬灞忓箷涓ぎ
		frame.setVisible(true); 
		
		game.action(); //寮�濮嬫父鎴�
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 