public class Solution {
    public boolean isPowerOfThree(int n) {
        //Key:matches("10*") *��ζ��ƥ��ǰ���0�����
        //toString(n,3)  ��nΪbase����n���ƣ�������Ϊ���û����ʾ������ʽ
        //Ч�ʷǳ���....
        //return Integer.toString(n,3).matches("10*");
        //���Ի��������������
        return n>0 && (n==1 || (n%3==0 && isPowerOfThree(n/3)));
    }
}