import java.util.*;
class Test{
	//public static int A = 10;
	/*
		public static void main(String[] s){
			//System.out.println(A+"");
			Solution.printB();
		}
	*/
	
	//java.lang.StackOverflowError
	//�ݹ���� stack������󣬼�ʹû�б������ߴ��Σ��ݹ�ʱҲ����һЩ���ص�ַ��ִ�������ĵȿ������Ĳ��������ÿ��ջ���ǻ�����
	//���������Ϊ11417
	public static void StackOverflowTest(int count){
		System.out.println(count);
		StackOverflowTest(++count);
	}
	
	//Mark:OutOfMemoryError��ԭ���п��ܲ�ͬ
	//�ڴ��������Ϊ����Ҫ���ڴ����������˿����ڴ����������ܽϺ����Щ
	public static void outOfMemory(){
		//Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit	
		//int[] arr = new int[Integer.MAX_VALUE];
		//���� heap outOFMemory 
		//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
		List<int[]> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		while(true){
			//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space  
			//������Ϊint[1000]��Ҫ��mem�㹻�ࡣ���������list.add(new String("abc")),��ΪString��Ҫ��mem���٣������ҵ��˰���Ҳû����....
			list.add(new int[1000]);
			
			//list.add(new String("abc")),��ΪString��Ҫ��mem���٣������ҵ��˰���Ҳû����....
			//list2.add(new String("abc"));
		}
			
	}
	public static void main(String[] s){
		//StackOverflow test
		//wrong -> �±ߵ���2�в���stackOverflow
		//int i = 2147483641;
		//int[] arr = new int[2];   i = arr[2];
		
		
		//StackOverflowTest(0);
		outOfMemory();
		
		/*
				
			
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
		*/
	}
}
class Solution{
	public static int B = 9;
	public static void printB(){
		System.out.println(B+"");
	}
}