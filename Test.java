class Test{
	//public static int A = 10;
	/*
		public static void main(String[] s){
			//System.out.println(A+"");
			Solution.printB();
		}
	*/
	public static void main(String[] s){
		Integer i1 = 128;
		int i2 = 128;
		//true
		//Integer��int�Ƚ�ʱ���Զ�����Ϊint
		System.out.println(i1 == i2);
		
		Integer a = Integer.valueOf(3);
		Integer b = Integer.valueOf(3);
		//true
		//��Ϊ-128<3<127,����valueOf�ڲ����ص���ͬһ��IntegerCache
		System.out.println(a == b);
		++a;
		//4 
		//Integer a�ȱ���Ϊint����������ͨ���Զ����䣬����IntegerCache[4] -> ��Ϊ��-128~127�ڣ������Զ�����Cache
		System.out.println(a);
		//false
		System.out.println(a == b);
		//��a++�෴
		--a;
		//true 
		System.out.println(a == b);
		
		//ֱ��new �¶�����
		Integer c = new Integer(3);
		Integer d = new Integer(3);
		//false
		//��ַ��ͬ
		System.out.println(c == d);
		++c;
		++d;
		//true
		//c,d�������Զ�װ�� return Cache
		System.out.println(c == d);
	}
}
class Solution{
	public static int B = 9;
	public static void printB(){
		System.out.println(B+"");
	}
}