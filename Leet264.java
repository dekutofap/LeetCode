public class Solution {
    public int nthUglyNumber(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        int index2 = 0,index3 = 0,index5 = 0;
        //Key point:��ôһ��������ȷ��DP
        //�൱����2,3,5�ֱ����Сֵ�ϳ���2��3��5.Ȼ��Ƚ�
        for(int i=1;i<=n-1;i++){
            arr[i] = Math.min(arr[index2]*2,Math.min(arr[index3]*3,arr[index5]*5));
            if(arr[i] == arr[index2]*2) index2++;
            if(arr[i] == arr[index3]*3) index3++;
            if(arr[i] == arr[index5]*5) index5++;
        }
        return arr[n-1];
    }
}