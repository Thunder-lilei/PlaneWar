package 飞机大战;

public class Boom extends FlyingObject
{
	private boolean if_bedraw=false;
	private int count=0;
	
	public Boom(int x,int y,int bullet_type){//子弹初始位置为角色现在位置
		
		image=PlaneWar.BOOM3;
		
		switch(bullet_type) 
		{
		case Grift_type.wave:
			image=PlaneWar.BOOM1;
			break;
		case Grift_type.laser:
			image=PlaneWar.BOOM2;
			break;
		case Grift_type.electric:
			image=PlaneWar.BOOM2;
			break;
		case Grift_type.Blitzball:
			image=PlaneWar.BOOM1;
			break;
		case Grift_type.fire:
			image=PlaneWar.BOOM3;
			break;
		}
		
		width = image.getWidth();
		height = image.getHeight();
		
		this.x = x;
		this.y = y;
	}

	//增加爆炸效果出现的次数
	public void CountAdd() 
	{
		this.count++;
	}
	//是否可以删除爆炸效果
	public boolean IfCanDel() 
	{
		if(this.count==20)
			return true;
		return false;
	}
	//修改被画状态
	public void change_ifbedraw() 
	{
		this.if_bedraw=true;
	}
	//是否被画
	public boolean Get_IfBeDraw() 
	{
		return this.if_bedraw;
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean outOfBounds() {
		return this.y<=-this.height; //子弹飞出画面
	}
}
