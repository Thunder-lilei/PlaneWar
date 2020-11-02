package 飞机大战;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

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
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;

public class PlaneWar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//????????
	public static final int WIDTH = 400;  //游戏窗口大小
	public static final int HEIGHT = 640; 
	
	public static BufferedImage background1,background2,background3;                    //背景图
	public static BufferedImage start;   	                                            //启动图
	public static BufferedImage pause;		                                            //暂停图
	public static BufferedImage gameover;	                                            //游戏结束图
	public static BufferedImage pass;                                                   //通关
	public static BufferedImage EnemyPlane1,EnemyPlane2,EnemyPlane3;	                //敌机
	public static BufferedImage Grift1,Grift2,Grift3;		                            //奖励
	public static BufferedImage bullet6,bullet1,bullet2,bullet3,bullet4,bullet5;		//子弹
	public static BufferedImage Me;			                                            //角色
	public static BufferedImage BOOM1,BOOM2,BOOM3;                                      //爆炸效果
	
	int Door=1;              //关卡数
	int little_time=10;      //时间间隔
	
	static{
		try{
			background1 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\background\\background_1.png"));
			background2 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\background\\background_2.png"));
			background3 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\background\\background_3.png"));
			
			BOOM1 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\blast\\blast_1.png"));
			BOOM2 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\blast\\blast_2.png"));
			BOOM3 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\blast\\blast_3.png"));
			
			pass = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\GameInterface\\通关.png"));
			start = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\\\lilei\\\\src\\\\飞机大战\\images\\GameInterface\\interface_1.png"));
			pause = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\\\lilei\\\\src\\\\飞机大战\\images\\GameInterface\\游戏暂停.png"));
			gameover = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\\\lilei\\\\src\\\\飞机大战\\images\\GameInterface\\jeimian_2.png"));
			
			EnemyPlane1 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\\\lilei\\\\src\\\\飞机大战\\images\\BossPlane\\plane_5.png"));
			EnemyPlane2 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\\\lilei\\\\src\\\\飞机大战\\images\\BossPlane\\plane_4.png"));
			EnemyPlane3 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\\\lilei\\\\src\\\\飞机大战\\images\\BossPlane\\plane_6.png"));
			
			Grift1 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\\\lilei\\\\src\\\\飞机大战\\images\\Grift\\award_1.png"));   //生命
			Grift2 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\\\lilei\\\\src\\\\飞机大战\\images\\Grift\\award_2.png"));   //子弹
			Grift3 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\\\lilei\\\\src\\\\飞机大战\\images\\Grift\\award_3.png"));   //特殊子弹
			
			bullet1 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\bullet\\bullet_1.png"));   //普通子弹
			bullet2 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\bullet\\bullet_2.png"));   //波浪
			bullet3 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\bullet\\bullet_3.png"));   //激光
			bullet4 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\bullet\\bullet_4.png"));   //闪电
			bullet5 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\bullet\\bullet_5.png"));   //闪电球
			bullet6 = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\bullet\\bullet_6.png"));   //火焰
			
			int type = new Random().nextInt(3);
			if(type==1)
			Me = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\MEPlane\\plane_1.png"));
			else if(type==2)
			Me = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\MEPlane\\plane_2.png"));
			else
			Me = ImageIO.read(new File("C:\\Users\\lilei\\eclipse-workspace\\lilei\\src\\飞机大战\\images\\MEPlane\\plane_3.png"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private ME me = new ME(); //初始玩家
	private FlyingObject[] flyings = {}; //敌人
	private Bullet[] bullets = {}; //弹药
	private Boom[] booms= {}; //爆炸效果
	
	public static final int START = 0;     //启动状态
	public static final int RUNNING = 1;   //运行状态
	public static final int PAUSE = 2;     //暂停状态
	public static final int GAME_OVER = 3; //游戏结束状态
	public static final int GAME_PASS = 4; //游戏结束状态
	private int state = START; //默认启动状态
	
	//敌人入场
	public FlyingObject nextit(int Door){
		int type=50;
		
		switch(Door) 
		{
		case 1:
			type = new Random().nextInt(50);
			break;
		case 2:
			type = new Random().nextInt(100);
			break;
		case 3:
			type = new Random().nextInt(150);
			break;			
		}
		
		if(type<2){ //奖励
			return new Grift();
		}else{ //敌机
			switch(Door) 
			{
			case 1:
				return new Enemy_plane(Door);
			case 2:
				return new Enemy_plane(Door);
			case 3:
				return new Enemy_plane(Door);
			}
			return new Enemy_plane(Door);
		}
	}
	
	int flyIndex = 0; 
	//加载敌人/奖励
	public void enterAction(){
		flyIndex++;
		switch(Door) 
		{
			case 1:
				if(flyIndex%20==0){
					FlyingObject obj = nextit(Door);
					flyings = Arrays.copyOf(flyings,flyings.length+1); //扩容
					flyings[flyings.length-1] = obj; //添加敌人
				}
				break;
			case 2:
				if(flyIndex%15==0){
					FlyingObject obj = nextit(Door);
					flyings = Arrays.copyOf(flyings,flyings.length+1); //扩容
					flyings[flyings.length-1] = obj; //添加敌人
				}
				break;
			case 3:
				if(flyIndex%10==0){
					FlyingObject obj = nextit(Door);
					flyings = Arrays.copyOf(flyings,flyings.length+1); //扩容
					flyings[flyings.length-1] = obj; //添加敌人
				}
				break;
		}
	}
	
	//全部移动
	public void stepAction(){ 
		//me.step(); //角色走一步
		for(int i=0;i<flyings.length;i++){ //遍历所有敌人
			flyings[i].step(); //敌人走一步
		}
		for(int i=0;i<bullets.length;i++){ //遍历所有子弹
			bullets[i].step(); //子弹走一步
		}
	}
	
	int shootIndex = 0; 
	//开火
	public void shootAction(){ 
		shootIndex++; 
		if(shootIndex%30==0){ 
			Bullet[] bs = me.shoot(); 
			bullets = Arrays.copyOf(bullets,bullets.length+bs.length); //扩容(bs有几个元素就扩大几个容量)
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length); //数组的追加
		}
	}
	
	//飞出画面的
	public void outOfBoundsAction(){
		int index = 0; //没有越界个数
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; //不越界敌人数组
		for(int i=0;i<flyings.length;i++){
			if(!flyings[i].outOfBounds()){ //没有越界
				flyingLives[index] =flyings[i];//没有越界的保留
				index++;
			}
			else 
			{
				if(flyings[i] instanceof Enemy_plane)
				me.subtractLife();//敌机飞出画面减命
			}
		}
		//返还敌人
		flyings = Arrays.copyOf(flyingLives,index); 
		
		index = 0; //没有越界个数归零
		//没有越界子弹
		Bullet[] bulletLives = new Bullet[bullets.length]; 
		for(int i=0;i<bullets.length;i++){
			if(!bullets[i].outOfBounds()){ 
				bulletLives[index] = bullets[i];
				index++;
			}
		}
		bullets = Arrays.copyOf(bulletLives,index); 
		
	}
	
	public void bangAction() throws MalformedURLException{ 
		for(int i=0;i<bullets.length;i++){ 
			@SuppressWarnings("unused")
			boolean if_bang=false;
			
			if(if_bang=bang(bullets[i])==true && (bullets[i].image==bullet1 || bullets[i].image==bullet2|| bullets[i].image==bullet5|| bullets[i].image==bullet6)) //击中
			{
				for(int j=i;j<bullets.length-1;j++) //清除这枚子弹
				{
					bullets[j]=bullets[j+1];
				}
				bullets=Arrays.copyOf(bullets, bullets.length-1);
				i--;
			}
		}
	}
	
//	public void playMusic()
//	{
//		try {
//			FileInputStream fileau=new FileInputStream("music/music.wav" );
//			AudioStream as=new AudioStream(fileau);
//			AudioPlayer.player.start(as);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}
	
	int score = 0; //玩家的得分
	//子弹击中
	public boolean bang(Bullet b) throws MalformedURLException{
		int index = -1; 
		
		for(int i=0;i<flyings.length;i++){ 
			if(flyings[i].shootBy(b)){ //判断是否击中
				index = i; //记录被击中敌人
				break;
			}
		}
		
		if(index!=-1){ //有被击中的
			FlyingObject it = flyings[index]; 
			
			//判断类型
			if(it instanceof Enemy_plane){  //敌人
				Enemy_plane e = (Enemy_plane)it;  //强转为敌人
				e.LowLive();//减少血量
				
				if(e.GetLive()==0)//敌机血量为0
				{
					score += e.getScore(); //得分
					
					FlyingObject obj = new Boom(e.x,e.y,b.get_bullettype());
					booms = Arrays.copyOf(booms,booms.length+1); //扩容
					booms[booms.length-1] = (Boom) obj; //添加爆炸效果
					    
//					      File f = new File("Taylor Swift-Sparks Fly.wav"); 
//					      URI uri = f.toURI();
					
//					      URL url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/爆炸音效.wav");  //解析地址
//					      AudioClip aau; 
//					      aau = Applet.newAudioClip(url);
//					      aau.loop();  //循环播放
					
					//删除被击中的敌人
					FlyingObject t = flyings[index];
					flyings[index] = flyings[flyings.length-1];
					flyings[flyings.length-1] = t;
					flyings = Arrays.copyOf(flyings,flyings.length-1);
					return true;//被击中删除子弹和敌人
				}
				return false;//击中但是为满足血量为0不删除敌人和子弹
				//删除击中对方的子弹
//				Bullet bullet=b;
//				b = bullets[bullets.length-1];
//				bullets[bullets.length-1] = bullet;
//				bullets = Arrays.copyOf(bullets,bullets.length-1);
			}
			
			else{   //奖励
					//无效果
				return false;//射击到奖励不删除子弹和奖励
				
				}
			}
		
		return false;
			

		}
	
	//碰撞事件
	public void hitAction(){
		for(int i=0;i<flyings.length;i++){
			if(me.hit(flyings[i])){ //相撞
				FlyingObject it = flyings[i]; 
				
				//判断类型
				if(it instanceof Enemy_plane){  //敌人
					Enemy_plane e = (Enemy_plane)it;  //强转为敌人
					score += e.getScore(); //得分
					me.subtractLife(); //角色减少生命
					//me.clearDoubleFire(); //去除双倍火力
				}
				
				else { //奖励
					Grift a = (Grift)it;   //强转为奖励
					int type = a.getType(); //判断奖励等级
					
					if(type==Grift_type.LIFE)//加命
						me.addLife();
					else //更改弹药
						me.change_bullet_type(type);
				}
				
				//删除被撞敌人
				FlyingObject t = flyings[i];
				flyings[i] = flyings[flyings.length-1];
				flyings[flyings.length-1] = t;
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
		}
	}
	
	//判定游戏结束
	public void checkGameOverAction(){
		if(me.getLife()<=0){ //生命为0
			state=GAME_OVER;   
			Door=1;
		}
	}
	
	//判定游戏通关
	public void checkGamePassAction(){
		if(Door==4){ 
			state=GAME_PASS;   
		}
	}
	
	//是否进入下一关
	public boolean check_next_Action(int Door) 
	{
		switch(Door) 
		{
		case 1:
			if(score==500)
				return true;
			break;
		case 2:
			if(score==1500)
				return true;
			break;
		case 3:
			if(score==3000)
				return true;
			break;
		}
		
		return false;
	}
	
	//爆炸效果出现次数增加
	public void EveryBoomAdd() 
	{
		for(int i=0;i<booms.length;i++) 
		{
			booms[i].CountAdd();
		}
	}
	
	//开始游戏
	public void action() throws MalformedURLException{
		//鼠标适配器
		//移出 移回 点击 移动
		MouseAdapter l = new MouseAdapter(){
			
			public void mouseMoved(MouseEvent e){
				if(state==RUNNING){ //运行状态下执行
					int x = e.getX(); //获取鼠标坐标
					int y = e.getY(); 
					me.moveTo(x, y); //移动飞机
				}
			}
			
			public void mouseClicked(MouseEvent e){
				switch(state){ 		//判定游戏现行状态
				case START:         //开始游戏-》运行游戏
					state=RUNNING; 
					break;
				case GAME_OVER:  //游戏结束-》分数归零-》新建角色-》新建敌人-》重新启动
					score = 0;   
					me = new ME();
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					state=START; 
					break;
				}
			}
			
			//鼠标移除游戏暂停
			public void mouseExited(MouseEvent e){
				if(state==RUNNING){ 
					state=PAUSE;    
				}
			}
			//鼠标移回游戏继续
			public void mouseEntered(MouseEvent e){
				if(state==PAUSE){  
					state=RUNNING; 
				}
			}
			
		};
		
		this.addMouseListener(l); //处理鼠标操作事件
		this.addMouseMotionListener(l); //处理鼠标滑动事件
		
//		URL url=null;
//		switch(this.state) 
//		{
//		case START:
//			url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/菜单音乐.wav");
//			break;
//		case GAME_PASS:
//			url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/战斗胜利.wav");
//			break;
//		case GAME_OVER:
//			url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/游戏失败.wav");
//			break;
//		case RUNNING:
//			url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/战斗音乐.wav");
//			break;
//		case PAUSE:
//			url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/菜单音乐.wav");
//			break;
//		}
//		AudioClip aau; //开场音乐
//	    aau = Applet.newAudioClip(url);
//	    aau.loop();  //循环播放
	
		Timer timer = new Timer(); //定时器		
		
		//schedule（task，time）在时间等于或者超过time的时候执行且仅执行一次
		//schedule（task，time，period）时间等于或者超过time首次执行task，之后每隔period毫秒重复执行一次任务
		timer.schedule(new TimerTask(){
			boolean if_RUNNING_music=false;
			boolean if_GAME_PASS_music=false;
			boolean if_GAME_OVER_music=false;
			boolean if_START_music=false;
			boolean if_PAUSE_music=false;
			AudioClip aau; //开场音乐
			
			public void run(){ 
				URL url=null;
				
				if(state==RUNNING){ //运行状态时执行
					enterAction(); //加载敌人
					stepAction();  //加载飞行物（所有会飞的）
					shootAction(); //开火
					outOfBoundsAction(); //删除越界的飞行物
					try {
						bangAction();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  //子弹命中
					hitAction();   //撞机
					checkGameOverAction(); //判断游戏结束
					checkGamePassAction(); //判断游戏通关
					EveryBoomAdd(); //爆炸效果出现次数增加
					boolean next=check_next_Action(Door); //判断进入下一关
					if(next==true) 
					{
						Door++;
						//change_time(Door);
					}
				}
				
				switch(state) 
				{
				case START:
					try {
						url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/菜单音乐.wav");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(if_START_music==false) 
					{
						if(aau!=null)
						aau.stop();
					    aau = Applet.newAudioClip(url);
					    aau.loop();  //循环播放
					    if_RUNNING_music=false;
						if_GAME_PASS_music=false;
						if_GAME_OVER_music=false;
						if_START_music=true;
						if_PAUSE_music=false;
//						System.out.println("播放菜单音乐！");
					}
					break;
				case GAME_PASS:
					try {
						url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/战斗胜利.wav");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(if_GAME_PASS_music==false) 
					{
						aau.stop();
					    aau = Applet.newAudioClip(url);
					    aau.loop();  //循环播放
					    if_RUNNING_music=false;
						if_GAME_PASS_music=true;
						if_GAME_OVER_music=false;
						if_START_music=false;
						if_PAUSE_music=false;
//						System.out.println("播放战斗胜利！");
					}
					break;
				case GAME_OVER:
					try {
						url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/游戏失败.wav");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(if_GAME_OVER_music==false) 
					{
						aau.stop();
					    aau = Applet.newAudioClip(url);
					    aau.loop();  //循环播放
					    if_RUNNING_music=false;
						if_GAME_PASS_music=false;
						if_GAME_OVER_music=true;
						if_START_music=false;
						if_PAUSE_music=false;
//						System.out.println("播放游戏失败！");
					}
					break;
				case RUNNING:
					try {
						url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/战斗音乐.wav");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(if_RUNNING_music==false) 
					{
						aau.stop();
					    aau = Applet.newAudioClip(url);
					    aau.loop();  //循环播放
					    if_RUNNING_music=true;
						if_GAME_PASS_music=false;
						if_GAME_OVER_music=false;
						if_START_music=false;
						if_PAUSE_music=false;
//						System.out.println("播放战斗音乐！");
					}
					break;
				case PAUSE:
					try {
						url = new URL("file:///C:/Users/lilei/eclipse-workspace/lilei/src/飞机大战/images/音效/菜单音乐.wav");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(if_PAUSE_music==false) 
					{
						aau.stop();
					    aau = Applet.newAudioClip(url);
					    aau.loop();  //循环播放
					    if_RUNNING_music=false;
						if_GAME_PASS_music=false;
						if_GAME_OVER_music=false;
						if_START_music=false;
						if_PAUSE_music=true;
//						System.out.println("播放菜单音乐！");
					}
					break;
				}
				repaint(); //重画
			}
		},0,little_time); //0毫秒开始 每隔10毫秒刷新
	}
	
//	public void change_time(int Door) //更改时间间隔
//	{
//		switch(Door) 
//		{
//		case 2:
//			break;
//		case 3:
//			break;
//		}
//	}
	
	//画
	public void paint(Graphics g){
		switch(Door) 
		{
		case 1:
			g.drawImage(background1,0,0,null); //背景break;
			break;
		case 2:
			g.drawImage(background2,0,0,null); //背景break;
			break;
		case 3:
			g.drawImage(background3,0,0,null); //背景break;
			break;
		}
		//g.drawImage(background,0,0,null); //背景
		
		painthero(g);                  //角色
		paintFlyingObjects(g);         //飞行物
		paintBullets(g);               //子弹
		paintBooms(g);                 //画爆炸效果
		paintScoreAndLife(g);          //分数 命
		paintState(g);                 //状态
	}
	//画角色
	public void painthero(Graphics g){
		g.drawImage(me.image,me.x,me.y,null); 
	}
	//画飞行物
	public void paintFlyingObjects(Graphics g){
		for(int i=0;i<flyings.length;i++){ 
			g.drawImage(flyings[i].image,flyings[i].x,flyings[i].y,null);//这个Null无伤大雅
		}
	}
	//画子弹
	public void paintBullets(Graphics g){
		for(int i=0;i<bullets.length;i++){ 
			g.drawImage(bullets[i].image,bullets[i].x,bullets[i].y,null);//这个Null无伤大雅
		}
	}
	//画爆炸效果
	public void paintBooms(Graphics g) 
	{
		for(int i=0;i<booms.length;i++) 
		{
			if(booms[i].Get_IfBeDraw()==true && booms[i].IfCanDel()==true) //删除已经展示的爆炸效果
			{
				FlyingObject t = booms[i];
				booms[i] = booms[booms.length-1];
				booms[booms.length-1] = (Boom) t;
				booms = Arrays.copyOf(booms,booms.length-1);
				i--;
			}
		}
		for(int i=0;i<booms.length;i++)
		{
			g.drawImage(booms[i].image,booms[i].x,booms[i].y,null);//这个Null无伤大雅
			booms[i].change_ifbedraw();//修改爆炸效果状态
		}
	}
	//画 分 命
	public void paintScoreAndLife(Graphics g){
		g.setColor(new Color(0,0,255)); //蓝色
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24)); //字体
		g.drawString("SCORE: "+score,10,25);//位置
		g.drawString("LIFE: "+me.getLife(),10,45);
	}
	//画状态
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
		case GAME_PASS:
			g.drawImage(pass,0,0,null);
			break;
		}
	}
	
	public static void main(String[] args) throws MalformedURLException {
		JFrame frame = new JFrame("飞机大战-内测版本"); 
		PlaneWar game = new PlaneWar(); //载入游戏
		frame.add(game);
		//窗口设定
		frame.setSize(WIDTH, HEIGHT); 
		frame.setAlwaysOnTop(true); //窗口保持在所有程序上方
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置窗口默认关闭操作(关闭窗口时退出程序)
		frame.setLocationRelativeTo(null); //窗口居于屏幕中央
		frame.setVisible(true); 
		
		game.action(); //开始游戏
	}
}
