public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        //Key:
        //�����ĵݹ��˼�룬��ǰ�������������ÿһ������������������ַ����ֳ������֣�Ȼ��������ߵĸ��ֲ�ͬ�Ľ������Ȼ�����һ�����м䲻���ǲ����ڼ����ŵ���ͬ��������ġ�
        return helper(input);
    }
    public List<Integer> helper(String input){
        //Key:����Ĭ���˶��ǺϷ������㣬���Բ��������������������Ҳ�Ͳ����ж�input == ""��
        List<Integer> list = new ArrayList<>();
        if(input == null || input.equals("")) return list;
        for(int i = 0;i<=input.length()-1;i++){
            if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*'){
                String a = input.substring(0,i);
                String b = input.substring(i+1);
                List<Integer> left = helper(a);
                List<Integer>  right = helper(b);
                //Key:List<Integer> for eachʱ������int
                for(int l:left){
                    for(int r:right){
                        if(input.charAt(i) == '+') list.add(l+r);
                        if(input.charAt(i) == '-') list.add(l-r);
                        if(input.charAt(i) == '*') list.add(l*r);
                    }
                }
            }
        }
        if(list.size() == 0) list.add(Integer.parseInt(input));
        return list;
    }
}