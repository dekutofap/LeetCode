public class Solution {
    public boolean isPowerOfTwo(int n) {
        //return Integer.toString(n, 2).matches("10*");
        
        
        //Key:matches("10*") *��ζ��ƥ��ǰ���0�����
        //toString(n,3)  ��nΪbase����n���ƣ�������Ϊ���û����ʾ������ʽ
        //Ч�ʷǳ���....
        //return Integer.toString(n,3).matches("10*");
        //���Ի��������������
        return n>0 && (n==1 || (n%2==0 && isPowerOfTwo(n/2)));
    }
}