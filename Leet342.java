public class Solution {
    public boolean isPowerOfFour(int num) {
        //return Integer.toString(num,4).matches("10*");
        //Key:matches("10*") *��ζ��ƥ��ǰ���0�����
        //toString(n,3)  ��nΪbase����n���ƣ�������Ϊ���û����ʾ������ʽ
        //Ч�ʷǳ���....
        //return Integer.toString(n,3).matches("10*");
        //���Ի��������������
        return num>0 && (num==1 || (num%4==0 && isPowerOfFour(num/4)));
    }
}