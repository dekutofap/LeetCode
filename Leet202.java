public class Solution {
    public boolean isHappy(int n) {
        // //input 4 �����
        // //�������brute force���ǵĵط�̫��
        // //Math.sqrt() sqrt=>square root ������Math.pow(a,b) ��a��b�η�
        // int count = 0;
        // //2��20�������ͬ�Ľ�������ǵ�count:2*2��2*2+0*0��ͬ�����Բ�Ҫ�ж�n������ѭ����,�ж���ʼ����2��20��count�Ƿ���ͬ��
        // //int tmp = n;
        // int tmp = -1;
        // boolean flag = true;
        // //˫��while���ܴ���ܻ� Time Limit Exceeded
        // while(n != 1 && flag){
        //     while(n/10 != 0){
        //         count += Math.pow(n%10,2);
        //         n = n/10;
        //     }
        //     count += Math.pow(n%10,2);
            
        //     //key point
        //     if(tmp<0)tmp = count;
           
        //     //2��20�������ͬ�Ľ�������ǵ�count:2*2��2*2+0*0��ͬ�����Բ�Ҫ�ж�n������ѭ����,�ж���ʼ����2��20��count�Ƿ���ͬ��
        //     if(n == tmp){
        //         flag = false;
        //     }
        //     n = count;
        //      //count need to reset 0
        //     count = 0;
        // }
        // return flag;
        
        if(n == 1) return true;
        //Key:�ӵ�֮�����ڣ���ʱ���Ǵ��м��ĳһ������ʼѭ���������Ǵӿ�ͷ��n��ʼѭ��������ֻ���ж��Ա�����������.....
        //int start = n,tmp = 0;
        int tmp = 0;
        Set<Integer> set = new HashSet<>();
        /*****
        while(n != 0){
            tmp += (int)Math.pow(n%10,2);
            n = n/10;
        }
        n = tmp;
        if(n == 1) return true;
        
        *******/
        while(set.add(n) && n!= 1){
            tmp = 0;
            while(n != 0){
                tmp += (int)Math.pow(n%10,2);
                n = n/10;
            }
            if(tmp == 1) return true;
            n = tmp;
        }
        return false;
    }
}