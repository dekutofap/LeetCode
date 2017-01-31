public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        //2pointe+����??
        if(g.length == 0|| s.length == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0,index1 = 0,index2 = 0;
        while(index1 <= g.length-1 && index2 <= s.length-1){
            //Key point:�����д����ȫ�棬index2������Ҳ���ܲ����㣬��result��Ȼ��++.Corner case:[1,2,3]��[1,1]�᷵�ش����2
            //if(index2 <= s.length-1 && s[index2] < g[index1]) index2++;
            //��д�������ͺö��ˣ�����Ҳ�����ж�index2 <= s.length-1�ˣ�continue֮����while����ж���
            
            //Key point:
            //if(s[index2++] < g[index1]) continue; ������ôд����ΪֻҪ�жϾͻ�index2++,����if�Ƕ��Ǵ�...
            if(s[index2] < g[index1]){
                index2++;
                continue;
            }
            index1++;
            index2++;
            result++;
        }
        return result;
    }
}