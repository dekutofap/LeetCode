public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if(matrix.length == 0) return list;
        int x1 = 0,y1 = 0,x2 = matrix.length-1,y2 = matrix[0].length-1;
        int index1 = 0,index2 = 0;
        
        //Key point:������жϴ���
        //Conrer case:[[2,3]] �����������жϾͻ����
        // while(list.size()< matrix[0].length*matrix.length){
            
            
        //���ǳ�һ�������ڲ�ѹ����״̬��Ȼ��ÿ�ζ������Ͻ�ѭ��һ��
        while(x1<=x2 && y1<=y2){    
            for(int i = y1;i<=y2;i++) list.add(matrix[x1][i]);
            x1++;
            for(int i = x1;i<=x2;i++) list.add(matrix[i][y2]);
            y2--;
            //Key point:��������϶���Ҫ������x1,x2��ϵ��y1,y2��ϵ
            //��ֹ������matrix��Ӱ��
            if(x1<=x2) for(int i = y2;i>=y1;i--) list.add(matrix[x2][i]);
            x2--;
            if(y1<=y2) for(int i = x2;i>=x1;i--) list.add(matrix[i][y1]);
            y1++;
        }
        
        return list;
    }
}