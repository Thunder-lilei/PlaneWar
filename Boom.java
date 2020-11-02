package �ɻ���ս;

public class Boom extends FlyingObject
{
	private boolean if_bedraw=false;
	private int count=0;
	
	public Boom(int x,int y,int bullet_type){//�ӵ���ʼλ��Ϊ��ɫ����λ��
		
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

	//���ӱ�ըЧ�����ֵĴ���
	public void CountAdd() 
	{
		this.count++;
	}
	//�Ƿ����ɾ����ըЧ��
	public boolean IfCanDel() 
	{
		if(this.count==20)
			return true;
		return false;
	}
	//�޸ı���״̬
	public void change_ifbedraw() 
	{
		this.if_bedraw=true;
	}
	//�Ƿ񱻻�
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
		return this.y<=-this.height; //�ӵ��ɳ�����
	}
}
