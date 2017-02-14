public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if(strs.length == 0) return 0;
        //Key:������01pack��value����1������weight���Ʊ����һ����ά���顣��ϸ���룬��Щ�ƣ����������
        //
        int[][] arr = new int[m+1][n+1];
        //Key:fillֻ�����һά����
        /**
        public static void fill(int[] a,int val)
        Assigns the specified int value to each element of the specified array of ints.
        
        ****/
        //Arrays.fill(arr,0);
        //Key:Ϊ�˷�ֹ���ֱ��ظ�ʹ�ø��ţ�����index��Ҫ��end��ʼ
        for(int k = 0;k<=strs.length-1;k++){
            int count0 = 0,count1 = 0;
            for(int i = 0;i<=strs[k].length()-1;i++){
                if(strs[k].charAt(i) == '0') count0++;
                else count1++;
            }
            //Log:System.out.println(count0+"+"+count1);
            //Key:Corner case:["10","0","1"] 1 1
            //��Ϊ��������ֵ����֣����Գ���[0][0],�������⡣[0][1],[1][0]����Ҳ������
            for(int i = m;i>=0;i--){
                for(int j =n;j>=0;j--){
                    if(i>=count0 && j >= count1){
                        if(i-count0 >= 0 && j-count1 >= 0)arr[i][j] = Math.max(arr[i][j],arr[i-count0][j-count1]+1);
                    }
                }
            }      
        }
        return arr[m][n];
    }
}