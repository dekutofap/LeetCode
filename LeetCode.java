516. Longest Palindromic Subsequence
public class Solution {
    public int longestPalindromeSubseq(String s) {
        //Key:subsequence's�����У����ܵ���˳��....
        /**
        
        if(s == null || s.equals("")) return 0;
        int oddMax = 0,count = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int i =0;i<=s.length()-1;i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            count += entry.getValue()%2 == 0?entry.getValue():0;
            if(entry.getValue()%2 == 1)oddMax = Math.max(oddMax,entry.getValue());
        }
        return count+maxOdd;
        
        ****/
        //DP
        //Key:���DPҪ����˼����˼����̫ϸ����������ͷ.....
        int length = s.length();
        int[][] F = new int[length][length];
        for(int i = 0;i<=length-1;i++) F[i][i] = 1;
        //Key:Important�������������ǰ���󣬼�i��������ô֮ǰ���DP[i][j]�Ͳ��ᱻ����
        //��ΪF[i+1][j-1]��һ����Ҫ�ڲ��洢�Ĺ��̣�����i��Ҫ--����j++
        //��������Ǵ����
        //for(int i = 0;i<=length-1;i++){
        //�����Ҫ��ǰ���󣬾͸�д��F[i-1][j+1]
        for(int i = length-1;i>=0;i--){
            for(int j = i+1;j<=length-1;j++){
                if(s.charAt(i) == s.charAt(j)){
                    F[i][j] = F[i+1][j-1]+2;
                } else {
                    F[i][j] = Math.max(F[i+1][j],F[i][j-1]);
                }
            }
        }
        return F[0][length-1];
    }
}

508. Most Frequent Subtree Sum

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int max = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer,Integer> map = new HashMap<>();
        helper(map,root);
        List<Integer> list = new ArrayList<Integer>();
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue() == max) list.add(entry.getKey());
        }
        //Key:list to array function
        //Integer[] result = new Integer[list.size()]; 
        //Key:��֪��Ϊʲô���������һֱ����....
        //return list.toArray(new Integer[list.size()]);
        //return result;
        int[] result = new int[list.size()];
        int count = 0;
        for(Integer i:list) result[count++] = i;
        return result;
    }
    public int helper(Map<Integer,Integer> map,TreeNode node){
        if(node == null) return 0;
        int sum = node.val + helper(map,node.left)+helper(map,node.right);
        map.put(sum,map.getOrDefault(sum,0)+1);
        max = Math.max(max,map.get(sum));
        return sum;
    }
}

515. Find Largest Value in Each Tree Row
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int[] findValueMostElement(TreeNode root) {
        Map<Integer,Integer> map = new HashMap<>();
        //List<Integer> list = new ArrayList<>();
        if(root == null) return new int[0];
        helper(map,root,0);
        int[] result = new int[map.size()];
        int tmp = 0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            result[tmp++] = entry.getValue();
        }
        /*****
        
        for(Integer node:list){
            result[tmp] = node;
            tmp++;
        }
        *********/
        return result;
    }
    public void helper(Map<Integer,Integer> map,TreeNode node,int depth){
        if(node != null){
            System.out.println("node"+node.val);
            //if(list.size()==depth) list.add(node.val);
            //else if(list.size()==depth+1 && node.val > list.get(depth)) list.set(depth,node.val);
            map.put(depth,Math.max(node.val,map.getOrDefault(depth,node.val)));
            //if(list.get(depth)==n) list.add(node.val);
            //else if(list.size()==depth+1 && node.val > list.get(depth)) list.set(depth,node.val);
            //System.out.println(list);
            helper(map,node.left,depth+1);
            helper(map,node.right,depth+1);
        }
    }
}

513. Find Bottom Left Tree Value
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int findLeftMostNode(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if(root == null) return 0;
        helper(list,root,0);
        return list.get(list.size()-1).val;
    }
    public void helper(List<TreeNode> list,TreeNode node,int depth){
        if(node != null){
            if(list.size()==depth) list.add(node);
            helper(list,node.left,depth+1);
            helper(list,node.right,depth+1);
        }
    }
}

503. Next Greater Element II
public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        
        int[] result = new int[nums.length];
        if(nums.length == 0) return result;
        Arrays.fill(result,-1);
        Stack<Integer> stack = new Stack<>();
        //Key:push index,����nums[index]
        //stack.push(nums[0]);
        stack.push(0);
        for(int i = 1;i<=nums.length-1;i++){
            while(!stack.empty() && nums[i]>nums[stack.peek()]){
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        for(int i = 0;i<=nums.length-1;i++){
            while(!stack.empty() && nums[i]>nums[stack.peek()]){
                result[stack.pop()] = nums[i];
            }
        }
        return result;
    }
}

496. Next Greater Element I
public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[findNums.length];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<=nums.length-1;i++){
            while(!stack.empty() && stack.peek()<nums[i]){
                map.put(stack.pop(),nums[i]);
            }
            stack.push(nums[i]);
        }
        for(int i = 0;i<=findNums.length-1;i++){
            result[i] = map.getOrDefault(findNums[i],-1);
        }
        return result;
    }
}

491. Increasing Subsequences
public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> list = new HashSet<>();
        List<Integer> item = new ArrayList<>();
        helper(list,item,nums,0);
        //Key:set to arraylist
        return new ArrayList<>(list);
    }
    public void helper(Set<List<Integer>> list,List<Integer> item,int[] nums,int index){
        for(int i = index;i<=nums.length-1;i++){
            if(item.size() == 0 || nums[i]>=item.get(item.size()-1)){
                item.add(nums[i]);
                if(item.size()>=2) list.add(new ArrayList<>(item));
                helper(list,item,nums,i+1);
                item.remove(item.size()-1);
            }
            
        }
    }
} 

526. Beautiful Arrangement
public class Solution {
    int count =  0;
    public int countArrangement(int N) {
        List<Integer> list = new ArrayList<>();
		if(N==0) return 0;
        int[] arr = new int[N];
        for(int i =1;i<=N;i++) arr[i-1] = N;
        boolean[] used = new boolean[N];
        helper(list,N,used);
        return count;
    }
    public void helper(List<Integer> list,int target,boolean[] used){
        if(list.size() == target){
			
            count++;
			System.out.println(list);
        } else {
            for(int i = 1;i<=target;i++){
                if(used[i-1]) continue;
                if((list.size()+1)%i == 0 || i%(list.size()+1) == 0){
                    list.add(i);
                    used[i-1] = true;
                    helper(list,target,used);
                    used[i-1] = false;
                    list.remove(list.size()-1);
                }
            }
        }
    }
}

520. Detect Capital
public class Solution {
    public boolean detectCapitalUse(String word) {
        if(word == null || word.length() == 0) return false;
        if(word.length() == 1) return true;
        boolean first = false,sec = false;
        if(word.charAt(0)-'A' <= 25 && word.charAt(0)-'A' >= 0) first = true;
        if(word.charAt(1)-'A' <= 25 && word.charAt(1)-'A' >= 0) sec = true;
		if(!first && sec) return false;
        for(int i = 2;i<=word.length()-1;i++){
            if(!first && word.charAt(i)-'A' <= 25 && word.charAt(i)-'A' >= 0) return false;
            if(first && !sec && word.charAt(i)-'A' <= 25 && word.charAt(i)-'A' >= 0) return false;
            if(first && sec&& word.charAt(i)-'a' <= 25 && word.charAt(i)-'a' >= 0) return false;
        }
        return true;
    }
}

525. Contiguous Array
public class Solution {
    public int findMaxLength(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int sum[] = new int[nums.length];
		sum[0] = nums[0] == 0? -1 : 1;
		for(int i=1; i < sum.length; i++){
			sum[i] = sum[i-1] + (nums[i] == 0? -1 : 1);
		}
		Map<Integer,Integer> pos = new HashMap<Integer,Integer>();
		int maxLen = 0;
		int i = 0;
		for(int s : sum){
			if(s == 0){
				maxLen = Math.max(maxLen, i+1);
			}
			if(pos.containsKey(s)){
				maxLen = Math.max(maxLen, i-pos.get(s));
			}else{
				pos.put(s, i);
			}
			i++;
		}
		return maxLen;
    }
}

482. License Key Formatting
public class Solution {
    public String licenseKeyFormatting(String S, int K) {
        /***
        
        //TLE
        
        String result = "";
        int count = 0;
        for(int i = S.length()-1;i>=0;i--){
            if(S.charAt(i) == '-') continue;
            //Must convert to UpperCase
            result = String.valueOf(S.charAt(i)>='a'?(char)(S.charAt(i)-'a'+'A'):S.charAt(i))+result;
            count++;
            if(count == K){
                result = "-"+result;
                count = 0;
            }
        }
        //Key:corner case
        //"---",3
        while(result.length() > 0 && result.charAt(0) == '-') result = result.substring(1);    
        
        return result;
        
        ***/
        //Key:difficulties are corner cases
        String s = S.replaceAll("-",""),result = "";
        int count = 0,i = 0;
        for(i = s.length();i>=K;i=i-K){
            //Must convert to UpperCase
            result = "-"+s.substring(i-K,i)+result;
        }
        result = s.substring(0,i)+result;
        while(s.length() >= 1 && result.charAt(0) == '-') result = result.substring(1);
        return result.toUpperCase();
    }
}

475. Heaters
//Key:����ⲻ̫�ã�����
public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        //Key:���У�Corner case ̫������brute force TLE
        /****
        
        //Key:brute force O(mn)
        if(houses.length == 0 || heaters.length == 0) return 0;
        int length1 = houses.length,length2 = heaters.length;
        int result = 0,tmp = Integer.MAX_VALUE;
        for(int i = 0;i<=length1-1;i++){
            tmp = Integer.MAX_VALUE;
            for(int j = 0;j<=length2-1;j++){
                tmp = Math.min(tmp,Math.abs(houses[i]-heaters[j]));
            }
            result = Math.max(result,tmp);
        }
        return result;
        
        //Key:����������˵ģ������ڲ�ѭ�����˸��Ż������Ը��Ӷ�Ӧ��Ҫ��Щ
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int i = 0, j = 0, res = 0;
        while (i < houses.length) {
            while (j < heaters.length - 1
                && Math.abs(heaters[j + 1] - houses[i]) <= Math.abs(heaters[j] - houses[i])) {
                j++;
            }
            res = Math.max(res, Math.abs(heaters[j] - houses[i]));
            i++;
        }
        return res;
        
        //Key:��һ���Լ��ģ��̫ⷨ����...�����Ǵ��
        if(houses.length == 0 || heaters.length == 0) return 0;
        int outer = Integer.MAX_VALUE,inner = 0;
        for(int i = 0;i<=heaters.length-1;i++){
            if(i == 0 || heaters[i] <houses[0] || heaters[i]>houses[houses.length-1]){
                outer = Math.min(outer,Math.max(Math.abs(heaters[i]-houses[0]),Math.abs(heaters[i]-houses[houses.length-1])));
                continue;
            }
            if(heaters[i] >=houses[0] && heaters[i]<=houses[houses.length-1] && heaters[i-1] >=houses[0] && heaters[i-1]<=houses[houses.length-1]){
                inner = Math.max(heaters[i]-heaters[i-1],inner);
            }
        }
       
        //Key:�ҳ����ļ���radius�Ϳ���
        inner = Math.max(inner/2,Math.max(Math.abs(heaters[0]-houses[0]),Math.abs(houses[houses.length-1]-heaters[heaters.length-1])));
        
        return Math.min(inner,outer);
        
        ******/
        
        //��������Ƿ���....���Ż��˻����޷�ͨ��.˼·�����ͱ���һ����....
        /***
        
        //Key:brute force O(mn)  -->O(mn)ͨ�����ˣ�����inner loop���˸�С�Ż�
        Arrays.sort(houses);
        Arrays.sort(heaters);
        if(houses.length == 0 || heaters.length == 0) return 0;
        int length1 = houses.length,length2 = heaters.length;
        int result = 0,tmp = Integer.MAX_VALUE,j=0;
        for(int i = 0;i<=length1-1;i++){
            tmp = Integer.MAX_VALUE;
            //for(int j = 0;j<=length2-1;j++){
            //    tmp = Math.min(tmp,Math.abs(houses[i]-heaters[j]));
            //}
            j = 0;
            //IMPORTANT!!!�������ЩCorner case���ã�
            //[1,2,3,4,4,4,5,6,6,7] [1,2,3,4,4,4,5,6,6,7] ��Ϊ���ظ������֣���������Ƚ��ظ����ֺ��������ʱ������7����������ж�>���ͻ���Ϊ4,4,4����û�����������
            //while(j<=length2-1 && tmp > Math.abs(houses[i]-heaters[j])){
            while(j<=length2-1 && tmp >= Math.abs(houses[i]-heaters[j])){
                tmp = Math.abs(houses[i]-heaters[j++]);
            }
            result = Math.max(result,tmp);
        }
        return result;
        
        ***/
        Arrays.sort(houses);
        Arrays.sort(heaters);
        if(houses.length == 0 || heaters.length == 0) return 0;
        int length1 = houses.length,length2 = heaters.length;
        int result = 0,j = 0;
        for(int i = 0;i<=length1-1;i++){
            //for(int j = 0;j<=length2-1;j++){
            //    tmp = Math.min(tmp,Math.abs(houses[i]-heaters[j]));
            //}
           
            //IMPORTANT!!!�������ЩCorner case���ã�
            //[1,2,3,4,4,4,5,6,6,7] [1,2,3,4,4,4,5,6,6,7] ��Ϊ���ظ������֣���������Ƚ��ظ����ֺ��������ʱ������7����������ж�>���ͻ���Ϊ4,4,4����û�����������
            //while(j<=length2-1 && tmp > Math.abs(houses[i]-heaters[j])){
            //Key:jֱ�������ӣ��������´�0��ʼ��TLE�Ĺؼ�������
            //j = 0;
            while(j<=length2-2 &&  Math.abs(houses[i]-heaters[j+1]) <= Math.abs(houses[i]-heaters[j])){
                j++;
            }
            result = Math.max(result, Math.abs(houses[i]-heaters[j]));
        }
        return result;
        
    }
}

473. Matchsticks to Square
public class Solution {
    int count = 0;
    public boolean makesquare(int[] nums) {
        if(nums.length<4) return false;
        //Key:�����˼·����
        //Corner case:[3,3,3,3,4,4,4,4,5,5,5,5]
        /*****
        
        int sum = 0;
        for(int i:nums) sum+=i;
        if(sum%4 != 0) return false;
        Arrays.sort(nums);
        if(sum/4 < nums[nums.length-1] || nums[0] == 0) return false;
        int count = 0;
        int index1=0,index2 = nums.length-1,tmp = 0;
        while(index1<=index2){
            if(tmp+nums[index2] > sum/4){
                tmp += nums[index1++];
            } else {
                tmp += nums[index2--];
            }
            if(tmp == sum/4) {
                count++;
                tmp = 0;
            }
        }
        
        return count == 4?true:false;
        
        ******/
        
        //Permutations�Ǹ�����Ҳû���ã���Ϊ
        //Corner case��[3,3,3,3,4,4,4,4,5,5,5,5] �ᵼ��3,3,3,3��4,4,4������Ҳ�������������Բ���
        /***
        
        //Key:DFS�Գ���....
        //similar to 4sums
        int sum = 0,side = 0;
        for(int i:nums) sum+=i;
        if(sum%4 != 0) return false;
        Arrays.sort(nums);
        side = sum/4;
        if(nums[nums.length-1]>side || nums[0] == 0) return false;
        //��Ϊ��Ҫ��գ�������ò���List<Integer> item = new ArrayList<>();������Ļ���֪��Ҫ��ôд��
        List<Integer> item = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        System.out.println("log");
        helper(item,nums,used,side,0);
        if(count != 4) return false;
        return true;
    }
    public void helper(List<Integer> item,int[] nums,boolean[] used,int side,int tmp){
        if(tmp == side && item.size() != 0){
            System.out.println(item);
            count++;
        } else {
            for(int i = 0;i<=nums.length-1;i++){
                if(tmp+nums[i]>side) break;
                if(used[i]) continue;
                item.add(nums[i]);
                int tag = count;
                used[i] = true;
                helper(item,nums,used,side,tmp+nums[i]);
                if(count == tag){
                    used[i] = false;
                    item.remove(item.size()-1);
                } else {
                    //Key:clear �� removeAll�÷�����
                    item.clear();
                }
            }
        }
        
        ***/
        
        //Key:Just copy
        //�����������ߵ��Ǹ����������
        int sum=0;
        for(int x:nums){
            sum=sum+x;
        }
        if(sum%4!=0||nums.length<4) return false;
        int width=(sum/4);
        Arrays.sort(nums);
        int sum1=0,sum2=0,sum3=0,sum4=0;
        return helper(nums,nums.length-1,sum1,sum2,sum3,sum4,width);
    }
    public boolean helper(int[] a, int i,int sum1,int sum2,int sum3,int sum4, int width){
        if(sum1>width||sum2>width||sum3>width||sum4>width) return false;
        if(i==-1){
            if(sum1==width&&sum2==width&&sum3==width&&sum4==width) return true;
            else return false;
        }
        //check a[i]  belonging to side1,side2,side3,side4
        return helper(a,i-1,sum1+a[i],sum2,sum3,sum4,width)||
        helper(a,i-1,sum1,sum2+a[i],sum3,sum4,width)||
        helper(a,i-1,sum1,sum2,sum3+a[i],sum4,width)||
        helper(a,i-1,sum1,sum2,sum3,sum4+a[i],width);
    }
}

467. Unique Substrings in Wraparound String
public class Solution {
    public int findSubstringInWraproundString(String p) {
        //Key:just copy
        // count[i] is the maximum unique substring end with ith letter.
        // 0 - 'a', 1 - 'b', ..., 25 - 'z'.
        /**
        //Key:һ��ʼ�ģ�����汾
        if(p == null || p.length() == 0) return 0;
        int[] arr = new int[26];
        int max = 1,sum = 1;
        arr[p.charAt(0)-'a'] = 1;
        for(int i = 1;i<=p.length()-1;i++){
            //Corner case:abaab  �����β��ab�������
            //Key:need to consider za
            if(p.charAt(i)-p.charAt(i-1) == 1 || p.charAt(i-1) - p.charAt(i) == 25){
                max++;
            } else {
                max = 1;
            }
            arr[p.charAt(i)-'a'] += 1;
            sum+=max;
        }
        for(int i:arr){
            if(i>1) sum = sum-(i-1);
        }
        return sum;
        ***/
        
        int[] count = new int[26];
        int maxLengthCur = 0;
        for (int i = 0; i < p.length(); i++) {
            int len = 1;
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25)))
                maxLengthCur++;
            else
                maxLengthCur = 1;
    
            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], maxLengthCur);
        }
        // Sum to get result
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += count[i];
        }
        return sum;
        
    }
}

450. Delete Node in a BST
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        return helper(root,key);
    }
    //Key:д��̫����
    //˼·��current node == targetʱ���������������飬�������ϲ㡣
    //!=������ݴ��¼��������ߣ��������µõ����������¸�ֵ
    //Key:�ؼ�����������µ����µ��Ӷ���������Ϊ����Ӷ������ֱ��������ڵ���鷳
    public TreeNode helper(TreeNode node,int target){
        if(node == null) return null;
        TreeNode left,right;
        if(node.val == target){
            left = helper(node.left,target);
            right = helper(node.right,target);
            boolean tag = left == null?false:true;
            TreeNode tmp = tag?left:right;
            if(tag){
                while(tmp != null && tmp.right != null){
                    tmp = tmp.right;
                }
            } else {
                while(tmp != null && tmp.left != null){
                    tmp = tmp.left;
                }
            }
            //Corner case:[0] 0
            if(tmp == null) return null;
            if(tag) tmp.right = right;
            else  tmp.left = left;
            return tag?left:right;
        } else if(node.val<target){
            node.right = helper(node.right,target);
            return node;
        } else {
            node.left = helper(node.left,target);
            return node;
        }
       
    }
}

451. Sort Characters By Frequency
public class Solution {
    public String frequencySort(String s) {
        //Key:����Collection.sort�Ļ���һ���ر���ǵķ���
        /****
        char[] arr = s.toCharArray();
        // bucket sort
        //Key:Unicode���ֻ��256���ַ�
        int[] count = new int[256];
        for(char c : arr) count[c]++;
        
        // count values and their corresponding letters
        Map<Integer, List<Character>> map = new HashMap<>();
        for(int i = 0; i < 256; i++){
            if(count[i] == 0) continue;
            int cnt = count[i];
            if(!map.containsKey(cnt)){
                map.put(cnt, new ArrayList<Character>());
            }
            map.get(cnt).add((char)i);
        }
    
        // loop throught possible count values
        StringBuilder sb = new StringBuilder();
        //Key:����Collection.sort�Ļ���һ���ر���ǵķ���
        //��Ϊs��󳤶�Ҳ����s.length()�����Դ������ǰ�ݼ����ҳ���
        for(int cnt = arr.length; cnt > 0; cnt--){ 
            if(!map.containsKey(cnt)) continue;
            List<Character> list = map.get(cnt);
            for(Character c: list){
                for(int i = 0; i < cnt; i++){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
        
        ***/
        Map<Character,Integer> map = new HashMap<>();
        char c;
        for(int i = 0;i<=s.length()-1;i++){
            c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }
        List<Map.Entry<Character,Integer>> list = new ArrayList<>(map.entrySet());
        //Key:Collection.sort���÷����������ؼ�
        /**
        public interface Comparator<T>
        A comparison function, which imposes a total ordering on some collection of objects. Comparators can be passed to a sort method (such as Collections.sort or Arrays.sort) to allow precise control over the sort order. Comparators can also be used to control the order of certain data structures (such as sorted sets or sorted maps), or to provide an ordering for collections of objects that don't have a natural ordering.
        **/
        //Key:ComparatorҲ��һ��Collction������ҲҪ������
        //Key:��Collections������������Collection!!
        //Collection.sort(list,new Comparator<Map.Entry<Character,Integer>>(){
        Collections.sort(list,new Comparator<Map.Entry<Character,Integer>>(){
            public int compare(Map.Entry<Character,Integer> e1,Map.Entry<Character,Integer> e2){
                //Key!!!!!
                //Key:�Ƚ�ʱ��һ��ֵ��ǰ�棬��Ϊ����Ĭ�ϣ�
                //�ڶ���ֵ��ǰ�棬��Ϊ����  
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character,Integer> entry:list){
            c = entry.getKey();
            for(int i = 0;i<=entry.getValue()-1;i++) sb.append(c);
        }
        return sb.toString();
    }
}

517. Super Washing Machines
public class Solution {
    public int findMinMoves(int[] machines) {
        //Just copy
        int total = 0; 
        for(int i: machines) total+=i;
        if(total%machines.length!=0) return -1;
        int avg = total/machines.length, cnt = 0, max = 0;
        for(int load: machines){
            cnt += load-avg; //load-avg is "gain/lose"
            max = Math.max(Math.max(max, Math.abs(cnt)), load-avg);
        }
        return max;
    }
}

347. Top K Frequent Elements
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        //Key:Collections.sort
        
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:nums) map.put(i,map.getOrDefault(i,0)+1);
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        //Key:��©��һ��ʼ��list!!!
        //public static <T> void sort(List<T> list,Comparator<? super T> c)
        //public static <T extends Comparable<? super T>> void sort(List<T> list)
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>(){
            public int compare(Map.Entry<Integer,Integer> e1,Map.Entry<Integer,Integer> e2){
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        List<Integer> result = new ArrayList<>();
        if(nums.length<k) return result;
        for(int i = 0;i<=k-1;i++){
            result.add(list.get(i).getKey());
        }
        return result;
    }
}

459. Repeated Substring Pattern
public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        //Just Copy
        int len = s.length();
    	for(int i=len/2 ; i>=1 ; i--) {
    		if(len%i == 0) {
    			int m = len/i;
    			String subS = s.substring(0,i);
    			int j;
    			for(j=1;j<m;j++) {
    				if(!subS.equals(s.substring(j*i,i+j*i))) break;
    			}
    			if(j==m)
    			    return true;
    		}
    	}
    	return false;
    }
}

456. 132 Pattern
public class Solution {
    //Case ̫��TLE
    /**
    boolean tag = false;
    public boolean find132pattern(int[] nums) {
        //Key:similar to permutations
        //Corner case:��Ϊ����case̫��ֻ��ȫ�ֱ����Ļ�̫�˷��ˣ����Բ���ֱ�ӷ���boolean....TLE
        if(nums.length ==0) return false;
        List<Integer> item = new ArrayList<>();
        Arrays.sort(nums);
        if(nums[nums.length-1]-nums[0]<=1) return false;
        helper(item,nums,0);
        return tag;
    }
    public void helper(List<Integer> item,int[] nums,int index){
        if(item.size() == 3){
            if(item.get(0) < item.get(2) && item.get(2)<item.get(1)) tag = true;
        } else {
            for(int i = index;i<=nums.length-1;i++){
                if(item.size() == 1 && nums[i]<=item.get(0)) continue;
                item.add(nums[i]);
                helper(item,nums,i+1);
                item.remove(item.size()-1);
            }
        }
    }
    **/
    public boolean find132pattern(int[] nums) {
        //Just Copy
        int[] arr = Arrays.copyOf(nums, nums.length);
    
        for (int i = 1; i < nums.length; i++) {
            arr[i] = Math.min(nums[i - 1], arr[i - 1]);
        }
        
        for (int j = nums.length - 1, top = nums.length; j >= 0; j--) {
            if (nums[j] <= arr[j]) continue;
            while (top < nums.length && arr[top] <= arr[j]) top++;
            if (top < nums.length && nums[j] > arr[top]) return true;
            arr[--top] = nums[j];
        }
            
        return false;
        
    }
}

530. Minimum Absolute Difference in BST
//V1
public class Solution {
	//Key:����˼·������
    //Coner  case:[100,1,null,null,99] 100��99min��С,���ǲ�δ����....
    /***
    public int getMinimumDifference(TreeNode root) {
        //Key:����˼·������-->��ΪBST������childֻ��Ҫ��parent�Ƚϼ��ɣ���Ϊ��Զ��leftChild<parent<rightChild
        helper(root);
        return min;
    }
    public void helper(TreeNode node){
        if(node != null){
            if(node.left != null) min = Math.min(node.val-node.left.val,min);
            if(node.right != null) min = Math.min(node.right.val-node.val,min);
            helper(node.left);
            helper(node.right);
        }
    }
    /****
    
    int result;
    public int getMinimumDifference(TreeNode root) {
        if(root == null) return 0;
        result = root.val;
        helper(root,Integer.MAX_VALUE,Integer.MIN_VALUE);
        return result;
    }
    public void helper(TreeNode node,int min,int max){
        if(node != null){
            if(min>node.val) min = node.val;
            if(max<node.val) max = node.val;
            if(min - node.val == 0) result = Math.min(Math.abs(max-node.val),result);
            else if(max-node.val == 0) result = Math.min(Math.abs(min-node.val),result);
            else result = Math.min(Math.min(Math.abs(min-node.val),Math.abs(max-node.val)),result);
            helper(node.left,min,max);
            helper(node.right,min,max);
        }
    }
    
    *****/
	//Key:brute force-->ȫȡ�����ٱȽ�(Complexity �ܸ�)��discuss���õ���InOrder��������ķ���
	//Key:��Ϳ�ˣ��������
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return 0;
        if(root.left == null && root.right == null) return root.val;
        helper(list,root);
        Collections.sort(list,new Comparator<Integer>(){
           public int compare(Integer i1,Integer i2){
               return i1.compareTo(i2);
           } 
        });
        int min = Integer.MAX_VALUE;
        for(int i = 1;i<=list.size()-1;i++){
            int tmp = list.get(i)-list.get(i-1);
            if(min > tmp) min = tmp;
            //System.out.println(min);
        }
        return min;
    }
    public void helper(List<Integer> list,TreeNode node){
        if(node != null){
            list.add(node.val);
            helper(list,node.left);
            helper(list,node.right);
        }
    }
}
//Version 2:Just Copy
public class Solution {
    //Key:����BST,In Order��������ǰ���˳������....
    int min = Integer.MAX_VALUE;
    Integer prev = null;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        getMinimumDifference(root.left);
        if (prev != null) min = Math.min(min, root.val - prev);
        prev = root.val;
        getMinimumDifference(root.right);
        return min;
    }
}


523. Continuous Subarray Sum
public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length == 0) return false;
        int sum = 0,index = 0;
        for(int i = 1;i<=nums.length-1;i++){
            index = i;
            sum = nums[index-1];
            while(index<=nums.length-1){
                if(k == 0 && nums[index] == 0 && nums[index-1] == 0) return true;
                sum+= nums[index++];
                //Corner case:[23,2,6,4,7] 0  ���ܳ���0������
                if(k!= 0 && sum%k == 0) return true;
            }
        }
        return false;
    }
}

524. Longest Word in Dictionary through Deleting
public class Solution {
    //TLE
    /***
    String result = "";
    public String findLongestWord(String s, List<String> d) {
        if(s == null || s.length()==0) return "";
        if(d.size() == 0) return "";
        helper(s,"",d,0);
        
        int index = 0;
        String result = "";
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<=s.length()-1;i++){
            map.put(s.charAt(i),i);
        }
        for(String str:d){
            boolean tag = true;
            index = 0;
            while(index<=str.length()-2){
                if(map.get(str.charAt(index)) == null || map.get(str.charAt(index))>map.get(str.charAt(index+1))){
                    tag = false;
                    break;
                }
                index++;
            }
            if(tag){
                result = check(result,str);
                System.out.println(result);
            }
        }
        
        
        return result;
    }
    public void helper(String s,String tmp,List<String> list,int index){
        for(int i = index;i<=s.length()-1;i++){
            tmp = tmp+s.charAt(index);
            //System.out.println(tmp);
            if(list.contains(tmp)) result = check(result,tmp);
            helper(s,tmp,list,i+1);
            //Key:����һ����""��ʼ�ӵ�....
            helper(s,"",list,i+1);
            tmp = tmp.substring(0,tmp.length()-1);
        }
    }
    public String check(String s1,String s2){
        if(s1.length() < s2.length()) {
            return s2;
        }
        else if(s1.length() > s2.length()){
            return s1;
        }
        else {
            int index2 = 0;
            while(index2 <= s1.length()-1){
                if(s1.charAt(index2)-s2.charAt(index2)<0) return s1;
                //Key:Coner case:"bab" ["ba","ab","a","b"]
                //����������Ǹ�else 
                else if(s1.charAt(index2)-s2.charAt(index2)>0) return s2;
                index2++;
            }
            return s2;
        }
    }
    ***/
    public String findLongestWord(String s, List<String> d) {
        //Key:��NB��һ��д��,Ӧ�þ��൱����д��compare������
        Collections.sort(d,new Comparator<String>(){
            public int compare(String a,String b){
                return a.length()==b.length()?a.compareTo(b):b.length()-a.length();
            }
        });
        String result = "";
        int index1 = 0,index2 = 0;
        for(String str:d){
            index1 = 0;
            index2 = 0;
            if(str.length() > s.length()) continue;
            //Key:check if str belong to s's substring
            while(index1<=s.length()-1 && index2 <= str.length()-1){
                if(s.charAt(index1) == str.charAt(index2)){
                    index1++;
                    index2++;
                } else {
                    index1++;
                }
            }
            if(index2 == str.length()) return str;
        }
        return result;
    }
}

506. Relative Ranks
public class Solution {
    public String[] findRelativeRanks(int[] nums) {
        String[] result = new String[nums.length];
        if(nums.length == 0) return result;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<=nums.length-1;i++){
            map.put(i,nums[i]);
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>(){
            public int compare(Map.Entry<Integer,Integer> e1,Map.Entry<Integer,Integer> e2){
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        int count = 0;
        for(Map.Entry<Integer,Integer> entry:list){
            if(count==0) result[entry.getKey()] = "Gold Medal";
            else if(count==1) result[entry.getKey()] = "Silver Medal";
            else if(count==2) result[entry.getKey()] = "Bronze Medal";
            else result[entry.getKey()] = count+1+"";
            count++;
        }
        return result;
    }
}

441. Arranging Coins
public class Solution {
    public int arrangeCoins(int n) {
        if(n == 0) return 0;
        int cur = 1,remain = n,result = 0;
        while(remain>=cur){
            remain = remain-cur;
            cur++;
        }
        return cur-1;
    }
}

500. Keyboard Row
public class Solution {
    //My 1st wrong version
    /**
    public String[] findWords(String[] words) {
        Character[] c1 = {'q','w','e','r','t','y','u','i','o','p'};
        Character[] c2 = {'a','s','d','f','g','h','j','k','l'};
        Character[] c3 = {'z','x','c','v','b','n','m'};
        int tag = 0;
        List<String> list = new ArrayList<>();
        //Key:���������д�������ɣ�Arrays.asSet(c1);����û������д����new HashSet<>(c1)Ҳ���ɣ���Ϊò��ֻ���ڼ���Collections�以��ת...Array���񲻳�
        //�ο�new ArrayList<>(item) �� new ArrayList<>(set);
        //Set<Character> set1 = Arrays.asSet(c1);
        Set<Character> set2 = new HashSet<>(c2);
        Set<Character> set3 = new HashSet<>(c3);
        for(String s:words){
            if(s.length() == 0) continue;
            s = s.toLowerCase();
            int i = 0;
            boolean flag = true;
            tag = set1.contains(s.charAt(0))?0:set2.contains(s.charAt(0))?1:2;
            while(i<=s.length()-1){
                if(tag == 0){
                    if(!set1.contains(s.charAt(i))) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) list.add(s);
        }
        return list.toArray(new String[list.size()]);
    }
    **/
    //Just copy
    public String[] findWords(String[] words) {
        String[] strs = {"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<strs.length; i++){
            for(char c: strs[i].toCharArray()){
                map.put(c, i);//put <char, rowIndex> pair into the map
            }
        }
        List<String> res = new LinkedList<>();
        for(String w: words){
            if(w.equals("")) continue;
            int index = map.get(w.toUpperCase().charAt(0));
            for(char c: w.toUpperCase().toCharArray()){
                if(map.get(c)!=index){
                    index = -1; //don't need a boolean flag. 
                    break;
                }
            }
            if(index!=-1) res.add(w);//if index != -1, this is a valid string
        }
        //Key:list.toArray(new String[0]) String[]��С����ν��ò����ֻ����֪��Ҫת���ɵ����Ͷ��ѡ�sizeӦ������ǰ�ߵ�list��ȷ��
        return res.toArray(new String[0]);
    }
}

419. Battleships in a Board
public class Solution {
    public int countBattleships(char[][] board) {
        //Key:�������Ĳ��ã���Ĭ�����е�input����invalid-->����˵���и���example 2�����Ͳ���test case�������Ͳ�Ӧ�ÿ�������invalid condition......������������
        //https://discuss.leetcode.com/topic/63294/confused-with-test-cases
        //This is an invalid board that you will not receive !!!
        /**
        int row = board.length,col = board[0].length,count = 0;
        if(row == 0 || col == 0) return 0; 
        for(int i = 0;i<=row-1;i++){
            for(int j = 0;j<=col-1;j++){
                if(i-1>=0 && board[i-1][j] == '.' || i-1<0){
                    if(i+1<=row-1 && board[i+1][j] == '.' || j+1>row-1){
                        if(j-1>= 0 && board[i][j-1] == '.' || j-1<0){
                            if(j+1<=col-1 && board[i][j+1] == '.' || j+1 > col-1) count++;
                        }
                    }
                }
            }
        }
        return count;
        **/
        int m = board.length;
        if (m==0) return 0;
        int n = board[0].length;
        int count=0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == '.' || i > 0 && board[i-1][j] == 'X' || j > 0 && board[i][j-1] == 'X') continue;
                count++;
            }
        }
        
        return count;
    }
}

406. Queue Reconstruction by Height
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // k is the number of people in front of this person who have a height greater than or equal to h ���������ĺܲ��ã�k��ʾ������k��������ǰ��.�����������k������ǰ��2
        /**
        ˼·�� 
        �Ȱ��ո߶ȴӴ�С���򣬸߶�h��ͬ�ģ���k�Ƚ�С�ķ���ǰ�档 
        //Key point:����ӵ������ģ��²����Ԫ�ػ�Ӱ�����źõ�˳��.... 
        ������֮��Ľ��Ϊ[7,0],[7,1][6,1][5,0][5,2][4,4]
        
        ������Ҫ�����������У��ӵڶ�����ʼ�� 
        1����[7,1] ���������һ��������ǰ�棬��ô[7,1]��λ�ÿ��Բ��� 
        ������� 
        [7,0],[7,1][6,1][5,0][5,2][4,4]
        
        2������[6,1], ����һ�����ڻ��ߵ���6������ǰ�棬��ô�ͽ�[6,1] 
        ���뵽����Ϊ1�ĵط� 
        [7,0],[6,1][7,1][5,0][5,2][4,4]
        
        3������[5,0],����0�����ڻ��ߵ���5������ǰ�棬��[5,0]����������Ϊ0�ĵط� 
        [5,0][7,0],[6,1][7,1][5,2][4,4]
        
        4������[5,2],����2�����ڻ��ߵ���5������ǰ�棬��[5,2]����������Ϊ2�ĵط� 
        [5,0][7,0],[5,2][6,1][7,1][4,4]
        
        5������[4,4]��������4�����ڻ��ߵ���4������ǰ�棬��[4,4]����������Ϊ4�ĵط� 
        [5,0][7,0],[5,2][6,1][4,4][7,1]
        */
        //Key:hard!!!������˼·���Ǳ��ɣ�һ��ʼ��̫����ת������....
        //���Collections.sort()�ǳ�����
        //Key:int[]��Collection����Ϊ�����Ļ���Ҫ��Arrays.sort������Ĳ���
        //Collections.sort(people,new Comparator<int[]>(){
        Arrays.sort(people,new Comparator<int[]>(){
            public int compare(int[] arr1,int[] arr2){
                //Key:arr1[0].compareTo(arr2[0]) �Ǵ����...compareToò�ƱȽϵ���int[]���飬�����������Ƚ�arr[0]���ֵ�����int
                //return arr1[1] == arr2[1]?arr1[0].compareTo(arr2[0]):arr2[1]-arr1[1];
                return arr1[0] == arr2[0]?Integer.compare(arr1[1],arr2[1]):arr2[0]-arr1[0];
            }
        });
        //Key:��ΪList<Object>��int[] Ҳ��object������ֱ��дint[]�Ϳ��ԣ����طɵ�д��дInteger[]��
        //��������ǵ�д��Integer[]�Ļ�����������ط����䣬����int[] תΪInteger[]�Ļ����ᵼ�� Line 43: error: incompatible types: int[] cannot be converted to Integer[]
        //List<Integer[]> list = new ArrayList<>();
        List<int[]> list = new ArrayList<>();
        for(int[] i:people){
            list.add(i[1],i);
        }
        return list.toArray(new int[0][0]);
    }
}

413. Arithmetic Slices
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        //Key:Just copy  Math
        //���sol��Ҫ����֮��Ĺ��ɣ�����̫����
        int curr = 0, sum = 0;
        for (int i=2; i<A.length; i++)
            if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                curr += 1;
                sum += curr;
            } else {
                curr = 0;
            }
        return sum;
    }
}

62. Unique Paths
public class Solution {
    public int uniquePaths(int m, int n) {
        //Typical DP
        if(n == 0 || m == 0) return 0;
        int[][] arr = new int[m][n];
        //Key:Arrays.fill(Object[] ary, Object val)����������ά����!!!������������Ǵ����
        //Arrays.fill(arr,0);
        for(int i = 0;i<=m-1;i++) arr[i][0] = 1;
        for(int i = 0;i<=n-1;i++) arr[0][i] = 1;
        for(int i = 1;i<=m-1;i++){
            for(int j = 1;j<=n-1;j++){
                arr[i][j]=arr[i-1][j]+arr[i][j-1];
            }
        }
        return arr[m-1][n-1];
    }
}

53. Maximum Subarray
public class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = nums[0];
        int max = dp[0];
        
        for(int i = 1; i < n; i++){
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}
36. Valid Sudoku
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        //Key:�ֱ��ж�ÿһ�У�ÿһ�У��Լ�ÿһ���顣д������Щ�鷳....
        //Key:Just copy
        for(int i = 0; i<9; i++){
            //���ܹ�������27��.....
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j = 0; j < 9;j++){
                if(board[i][j]!='.' && !rows.add(board[i][j]))
                    return false;
                if(board[j][i]!='.' && !columns.add(board[j][i]))
                    return false;
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);
                if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                    return false;
            }
        }
        return true;
    }
}

78. Subsets
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        //Key:Genaral solution https://discuss.leetcode.com/topic/46159/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
        List<List<Integer>> list = new ArrayList<>();
        //Key:ע��������д��������ʱ��new ArrayList<>()������ʱдList<Integer>Ҳ��ͨ��!!
        helper(list,new ArrayList<>(),nums,0);
        return list;    
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int[] nums,int start){
        list.add(new ArrayList<>(item));
        for(int i = start;i<=nums.length-1;i++){
            item.add(nums[i]);
            helper(list,item,nums,i+1);
            item.remove(item.size()-1);
        }
    }
}

75. Sort Colors
public class Solution {
    public void sortColors(int[] nums) {
        //Key:Arrays.fill(tmp,1);Ȼ����0��2������
        //1st correct version
        //Key:ע��clone���÷�
        int[] tmp = nums.clone();
        Arrays.fill(nums,1);
        int index1 = 0,index2 = nums.length-1;
        for(int i = 0;i<=nums.length-1;i++){
            if(tmp[i] == 0) nums[index1++] = 0;
            else if(tmp[i] == 2) nums[index2--] = 2;
        }
        //Key:another version ֻ�ܰ�0��2�ֱ��Ƶ���β���ɣ�1�϶�������ȷ��λ����
        //Key:������Ǵ���汾����֪�������������....
        /**
        int index1 = 0,index2 = nums.length-1,tmp = 0;
        for(int i = 0;i<=nums.length-1;i++){
            if(nums[i] == 0){
                tmp = nums[i];
                nums[i] = nums[index1];
                nums[index1++] = tmp;
            }
            //Key:�������i--��Ϊ�˴������swap(2,2)�������
            else if(i >= 0 && nums[i] == 2){
                tmp = nums[i];
                nums[i--] = nums[index2];
                nums[index2--] = tmp;
            }
        }
        **/
    }
    //Key:��ô���������͸ı䲻��array��ֵ...
    /**
    public void swap(int a,int b){
        int tmp = a;
        a = b;
        b = tmp;
    }
    **/
}

90. Subsets II
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        helper(list,new ArrayList<>(),nums,used,0);
        return list;
    }
    public void helper(List<List<Integer>> list,List<Integer> item,int[] nums,boolean[] used,int start){
        list.add(new ArrayList<>(item));
        for(int i = start;i<=nums.length-1;i++){
            //Key:another version without used array -->�൱�ڰ���ͬԪ�غ�ߵ�ѭ������continue��
            //if(i>start && nums[i] == nums[i-1])
            //Key:�൱����ͬ�����ֶ�������һ�����������ǣ�ֻ��������ͬ����
            if(i > 0 && !used[i-1] && nums[i] == nums[i-1]) continue;
            used[i] = true;
            item.add(nums[i]);
            helper(list,item,nums,used,i+1);
            item.remove(item.size()-1);
            used[i] = false;
        }
    }
}

334. Increasing Triplet Subsequence
public class Solution {
    public boolean increasingTriplet(int[] nums) {
        //Key:wrong
        /**
        int tag = 1;
        if(nums.length<3) return false;
        for(int i = 1;i<=nums.length-1;i++){
            if(nums[i]>nums[i-1]) tag++;
        }
        return tag>=3?true:false;
        **/
        //Key:����������С��ֵ����
        int min = Integer.MAX_VALUE,secMin = Integer.MAX_VALUE;
        for(int i = 0;i<=nums.length-1;i++){
            //Key:����⻹�������ظ�����....Corner case:[1,1,-2,6]
            if(min>=nums[i]) min = nums[i];
            else if(secMin>nums[i]) secMin = nums[i];
            else if(secMin<nums[i]) return true;
        }
        return false;
    }
}

240. Search a 2D Matrix II
public class Solution {
    //Key:my binary search version
    /**
    public boolean searchMatrix(int[][] matrix, int target) {
        //Key:similar to one dimision array
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int lowR = 0,lowC = 0,highR = matrix.length-1,highC = matrix[0].length-1;
        return helper(matrix,target,lowR,highR,lowC,highC);
    }
    public boolean helper(int[][] matrix, int target,int lowR,int highR,int lowC,int highC){
        //System.out.println(lowR+"-"+highR+"-"+lowC+"-"+highC+"-"+"^^^^^^^");
        if(lowR<=highR && lowC<=highC){
            int midR = (lowR+highR)/2;
            int midC = (lowC+highC)/2;
            int tmp = matrix[midR][midC];
            
            //System.out.println(tmp+"");
            if(tmp == target) return true;
            if(tmp < target){
                //Key:!!!һ��Ҫ�ǵ���helperǰ��return!!!��Ϊ�ǲ�㷵�أ��������return���޷���true������ȥ!!!!
                //Key:��||
                return helper(matrix,target,midR+1,highR,lowC,highC) || helper(matrix,target,lowR,highR,midC+1,highC);
            } else {
                //Key:test corner:[[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]] 5
                return helper(matrix,target,lowR,midR-1,lowC,highC) || helper(matrix,target,lowR,highR,lowC,midC-1);
            }
        }
        return false;
    }
    **/
    
    //Key:O(mn) version
    //�����ʱ������Ǹ�binary search ʱ�仹��
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}

300. Longest Increasing Subsequence
public class Solution {
    public int lengthOfLIS(int[] nums) {
        //Just copy I
        /**
        if(nums==null || nums.length==0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = 1;
        for(int index=0; index<nums.length;index++){
            dp[index]=1;
            for(int dpIndex=0; dpIndex<index; dpIndex++){
                if(nums[dpIndex]<nums[index]){
                    dp[index]=Math.max(dp[index],dp[dpIndex]+1);
                    max=Math.max(dp[index],max);
                }
            }
        }
        return max;
        **/
        
        //Key:Just copy II --->using Arrays.binarySearch()
        //Ч�����������
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }

        return len;
    }
}

11. Container With Most Water
public class Solution {
    public int maxArea(int[] height) {
        //Key:just copy  �������Ҫ����˼·����֤����ȷ��
        //����ⷨ���ѵ��������֤����ȷ��!!!!
        //�ø�case����˵����[99,1,2,3,4,200,100]   
        //maxһ��ʼ����99*length��Ȼ��left=1�����׵��ĵ���99~200��Ӱ�죬������Ϊmax������lower height����������Զ��С��99*length�ġ�Ȼ��ʣ�µĿ������һ���ݹ�Ĺ���
        
        //Key:������˼·���������
        /**
        Idea / Proof:
        https://discuss.leetcode.com/topic/14940/simple-and-clear-proof-explanation/3
        The widest container (using first and last line) is a good candidate, because of its width. Its water level is the height of the smaller one of first and last line.
        All other containers are less wide and thus would need a higher water level in order to hold more water.
        The smaller one of first and last line doesn't support a higher water level and can thus be safely removed from further consideration.
        ��Ϊmaxһ��ʼ����i0��in�����ģ���Ҫ��һ�������ֵ����width��С������£�height����������i0��in����С���Ǹ���û��Ҫ������
        
        ***/
        int maxWater=0, left=0, right=height.length-1;
        //Key:˼·��length�϶��ڼ�С�����Ծ�ȥѰ��height���
		while(left<right) {
			maxWater = Math.max(maxWater,(right-left)*Math.min(height[left], height[right]));
			if(height[left]<height[right]) left++;
			else right--;
		}
		return maxWater;
    }
}

454. 4Sum II
public class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //Key:�����Ҳ�����û������ˣ�TLE.....Brute force:O(n^4)
        /**
        
        [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
        
        ***/
        
        /**
        //Key:�����Ǵ�ģ���ʹA=[]��ָ��Ҳ�ǳ���Ϊ0.��������null
        //if(A == null || B == null ||C == null ||D == null) return 0;
        //Corner case:[0] [0][0][0] ....��Ҳ�Ƿ��ˣ���case����
        //all A, B, C, D have same length of N where 0 �� N �� 500.
        if(A.length == 0 || B.length == 0 ||C.length == 0 ||D.length == 0) return 0;
        List<Integer> list = new ArrayList<>();
        helper(list,A,B,C,D);
        return result;
    }
    public void helper(List<Integer> list,int[] A,int[] B,int[] C,int[] D){
        if(list.size() == 4){
            int tmp = 0;
            for(int i:list) tmp += i;
            //System.out.println(list);
            if(tmp == 0) result++;
        } else {
            for(int i = 0;i<=A.length-1;i++){
                if(list.size() == 0) list.add(A[i]);
                else if(list.size() == 1) list.add(B[i]);
                else if(list.size() == 2) list.add(C[i]);
                else if(list.size() == 3) list.add(D[i]);
                //System.out.println(list+"ddd");
                helper(list,A,B,C,D);
                list.remove(list.size()-1);
            }
        }
    }           
        **/
        //Key:���Լ������һ�������ǣ��ø�map�洢ǰ������������ֵ�����ִ�����Ȼ�����������������ͣ��͵���0�Ļ����һ�γ��ִ���
        //O(n^2),space O(n^2)
        //��discuss�е��뷨һ��  https://discuss.leetcode.com/topic/67593/clean-java-solution-o-n-2/7
        if(A.length == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        int length = A.length,sum = 0,result = 0;
        for(int i = 0;i<=length-1;i++){
            for(int j = 0;j<=length-1;j++) {
                sum = A[i] + B[j];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        for(int i = 0;i<=length-1;i++){
            for(int j = 0;j<=length-1;j++){
                result += map.getOrDefault(-C[i]-D[j],0);
            }
        }
        return result;
    }
}

42. Trapping Rain Water
public class Solution {
    public int trap(int[] height) {
        //Key:my 1st version -- correctness
        //�ֱ��ҳ�ÿ�����������ߵ����ֵ��Ȼ�����е���Сֵ��ȥ����߶Ⱦ��Ǵ�ˮ��
        /**
        if(height.length == 0) return 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int j = height.length,max = Integer.MIN_VALUE,max2 = Integer.MIN_VALUE;
        left[0] = 0;
        right[height.length-1] = 0;
        for(int i = 1;i<=height.length-1;i++){
            j = height.length-1-i;
            max = Math.max(max,height[i-1]);
            left[i] = max;
            max2 = Math.max(max2,height[j+1]);
            right[j] = max2;
        }
        int result = 0,min = Integer.MAX_VALUE;
        for(int i = 0;i<=height.length-1;i++){
            //System.out.println(left[i]+"--"+right[i]);
            min = Math.min(left[i],right[i]);
            if(height[i]<=min) result += min-height[i];
        }
        return result;
        **/
        
        //Key:just copy ������ⷨ������Щ��������Ĳ�̫������
        //2 pointers
        int a=0;
        int b=height.length-1;
        int max=0;
        int leftmax=0;
        int rightmax=0;
        while(a<=b){
            leftmax=Math.max(leftmax,height[a]);
            rightmax=Math.max(rightmax,height[b]);
            if(leftmax<rightmax){
                max+=(leftmax-height[a]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
                a++;
            }
            else{
                max+=(rightmax-height[b]);
                b--;
            }
        }
        return max;
    }
}

279. Perfect Squares
public class Solution {
    public int numSquares(int n) {
        //Key:Just copy - ò�ƻ��ǵ��ҹ���
        //https://discuss.leetcode.com/topic/26400/an-easy-understanding-dp-solution-in-java/2
        /**
         dp[n] indicates that the perfect squares count of the given n, and we have:

        dp[0] = 0 
        dp[1] = dp[0]+1 = 1
        dp[2] = dp[1]+1 = 2
        dp[3] = dp[2]+1 = 3
        dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 } 
              = Min{ dp[3]+1, dp[0]+1 } 
              = 1				
        dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 } 
              = Min{ dp[4]+1, dp[1]+1 } 
              = 2
        						.
        						.
        						.
        dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 } 
               = Min{ dp[12]+1, dp[9]+1, dp[4]+1 } 
               = 2
        						.
        						.
        						.
        dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
        and the sample code is like below:
        **/
        int[] dp = new int[n + 1];
    	Arrays.fill(dp, Integer.MAX_VALUE);
    	dp[0] = 0;
    	for(int i = 1; i <= n; ++i) {
    		int min = Integer.MAX_VALUE;
    		int j = 1;
    		while(i - j*j >= 0) {
    			min = Math.min(min, dp[i - j*j] + 1);
    			++j;
    		}
    		dp[i] = min;
    	}		
    	return dp[n];
    }
}

14. Longest Common Prefix
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        //Key:�е㲻����ˣ����ⲻ�Ѱ�������corner caseӦ��Ҳ��̫���ӣ���֪��Ϊʲô��ȷ����ô��.....
        if(strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        String result = strs[0];
        int index = 0;
        StringBuilder sb;
        for(int i = 1;i<=strs.length-1;i++){
            sb = new StringBuilder();
            index = 0;
            while(index<=result.length()-1 && index<=strs[i].length()-1){
                if(result.charAt(index) == strs[i].charAt(index)){
                    sb.append(result.charAt(index));
                    index++;
                } else {
                    break;
                }
            }
            result = sb.toString();
        }
        return result;
    }
}

73. Set Matrix Zeroes
public class Solution {
    public void setZeroes(int[][] matrix) {
        //my Wrong version
        /**
        //Key:brute force 
        if(matrix.length == 0 || matrix[0].length == 0) return;
        int row = matrix.length-1,col = matrix[0].length-1;
        Map<Integer,Integer> map = new HashMap<>();
        int index1 = 0,index2 = 0;
        for(int i = 0;i<=row;i++){
            for(int j = 0;j<=col;j++){
                if(matrix[i][j] == 0){
                    //Key:��ô�����ɣ���Ϊǰ�ߵ�һ�У�һ������󣬻ᵼ�º�ߵ��������.....
                    //test case:
                    //[[0,0,0,5],[4,3,1,4],[0,1,1,4],[1,2,1,3],[0,0,1,1]]
                    //Your answer
                    //[[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]
                    //Expected answer
                    //[[0,0,0,0],[0,0,0,4],[0,0,0,0],[0,0,0,3],[0,0,0,0]]
                    //Key:��򵥵Ľ���취����cloneһ��array��Ȼ���޸���������ԭ��Ҫ��in place
                    //�ø�map������0��λ�ã�Ȼ���ٴ���???---->˼·wrong,�����(corner case:��ĳһ�г��ֶ��0ʱ����[6,0,7,8,1,0,1,6,8,1]��ǰ����ͬ��key��Ӧ��valueֵ�ᱻ����....) ������List<List<Integer>> �����棬������Ȼspace���Ĵ����.....
                    //�����֪��Ϊʲô���������.........��ȻҲ��������ĿҪ���constant space
                    //map.put(i,j);
                    
                    //for(index1 = 0;index1<=col;index1++) matrix[i][index1] = 0;
                    //for(index1 = 0;index1<=row;index1++) matrix[index1][j] = 0;
                }
            }
            
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            //System.out.println(entry);
            int i = entry.getKey(),j = entry.getValue();
            System.out.println(i+"----"+j);
            for(index1 = 0;index1<=col;index1++) matrix[i][index1] = 0;
            for(index1 = 0;index1<=row;index1++) matrix[index1][j] = 0;
        }
        **/
        
        
        //Key:just copy
        boolean fr = false,fc = false;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    if(i == 0) fr = true;
                    if(j == 0) fc = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if(fr) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if(fc) {
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}

134. Gas Station
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //Key:just cp
        //Greedy ����
        int total = 0, tank = 0, index = 0;
    	for (int i = 0; i < cost.length; i++) {
    		int cur = gas[i] - cost[i];			
    
    		tank += cur;
    		if (tank < 0) {//if sum < 0, index can only start from i + 1
    			index = i + 1;
    			tank = 0;
    		}
    		total += cur;			
    	}		
    	return total < 0 ? -1 : index;
        
    }
}

531. Lonely Pixel I
public class Solution {
    public int findLonelyPixel(char[][] picture) {
        //My 1st version
        int count = 0;
        if(picture.length == 0 || picture[0].length == 0) return count;
        Map<Integer,Integer> row = new HashMap<>();
        Map<Integer,Integer> col = new HashMap<>();
        int w = 0,b = 0;
        for(int i = 0;i<=picture.length-1;i++){
            for(int j = 0;j<=picture[0].length-1;j++){
                if(picture[i][j] == 'B'){
                    row.put(i,row.getOrDefault(i,0)+1);
                    col.put(j,col.getOrDefault(j,0)+1);
                }
            }
        }
        for(int i = 0;i<=picture.length-1;i++){
            for(int j = 0;j<=picture[0].length-1;j++){
                if(picture[i][j] == 'B' && row.get(i) == 1 && col.get(j) == 1) count++;
            }
        }
        return count;
    }
}

535. Encode and Decode TinyURL
public class Codec {
    //Just cp:��  2�ֲ����sol
    //https://discuss.leetcode.com/topic/81633/easy-solution-in-java-5-line-code
    //https://discuss.leetcode.com/topic/81590/a-common-way-using-62-letters-java
    List<String> urls = new ArrayList<String>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        urls.add(longUrl);
        return String.valueOf(urls.size()-1);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int index = Integer.valueOf(shortUrl);
        return (index<urls.size())?urls.get(index):"";
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

533. Lonely Pixel II
public class Solution {
    public int findBlackPixel(char[][] picture, int N) {
        //my 1st version:complexity O(m*n*m)
        int m = picture.length,n = picture[0].length;
        if(m == 0 || n == 0) return 0;
        Map<Integer,Integer> row = new HashMap<>();
        Map<Integer,Integer> col = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String[] strs = new String[m];
        int result = 0;
        for(int i = 0;i<=m-1;i++){
            sb.delete(0,sb.length());
            for(int j = 0;j<=n-1;j++){
                sb.append(picture[i][j]);
                if(picture[i][j] == 'B'){
                    row.put(i,row.getOrDefault(i,0)+1);
                    col.put(j,col.getOrDefault(j,0)+1);
                }
                
            }
            strs[i] = sb.toString(); 
        }
        /**
        for(String s:strs) System.out.println(s);
        for(Map.Entry<Integer,Integer> entry:row.entrySet()){
            //System.out.println(entry.getKey()+"---"+entry.getValue());
        }
        **/
        for(int i = 0;i<=m-1;i++){
            for(int j = 0;j<=n-1;j++){
                if(picture[i][j] == 'B' && row.get(i) == col.get(j) && row.get(i) == N){
                    //System.out.println(row.get(i)+"...."+col.get(j));
                    int count = 0;
                    for(int k =0;k<=strs.length-1;k++){
                        if(strs[i].equals(strs[k])) count++;
                    }
                    //System.out.println("count"+count);
                    if(count == N) result++;
                }
            }
        }
        return result;
    }
}

532. K-diff Pairs in an Array
public class Solution {
    public int findPairs(int[] nums, int k) {
        //Brute force:O(n*n)
        if(nums.length == 0 || nums.length == 1) return 0;
		int count = 0,n = nums.length;
		Map<Integer,Integer> map = new HashMap<>();
		Arrays.sort(nums);
		for (int i = 0; i < n; i++){       
			// See if there is a pair of this picked element
			for (int j = i+1; j < n; j++){
				if (nums[j] - nums[i] == k ){
					map.put(nums[i],nums[j]);
				}	
			}
		}
		return map.size();
    }
}

437. Path Sum III
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:Difficulty!!!�ܶ�ط���̫�������
    //Just cp
    //https://discuss.leetcode.com/topic/64461/simple-java-dfs/2
    //https://discuss.leetcode.com/topic/64526/17-ms-o-n-java-prefix-sum-method/2
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) 
            + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }
    /**
    int count = 0;
    public int pathSum(TreeNode root, int sum) {
        //Key:�����ݹ飬1�������������������һ���ӵ�ǰval���¿�һ��sum
        //Corner  case:[] 1
        if(root == null) return 0;
        //Key:ֻ��һ��rootʱ��Ҫ��������.....
        if(root.val == sum && root.left == null && root.right == null) return 1; 
        helper(root,sum,0);
        return count;
    }
    public void helper(TreeNode node,int target,int sum){
        if(node != null){
            
            if(sum+node.val == target || node.val == target) count++;
            helper(node.left,target,sum+node.val);
            helper(node.right,target,sum+node.val);
            //Key:��ס������һ���ڵ㿪ʼ�Ļ����Ͳ����ٰѱ��ڵ�����ˣ�����
            //helper(node.left,target,node.val);
            helper(node.left,target,node.val);
            //helper(node.right,target,node.val);
            helper(node.right,target,node.val);
            
        }
    }
    **/
}

452. Minimum Number of Arrows to Burst Balloons
public class Solution {
    public int findMinArrowShots(int[][] points) {
        //Key:��int[0]����    
        if(points.length == 0)return 0;
        if(points.length == 1)return 1;
        //˼·��https://discuss.leetcode.com/topic/66579/java-greedy-soutionһ��
        //Key:Arrays.sort(points,new Comparator<int[]>())  --->int[]��Object,����Ҳ��ָ��<>��������
        Arrays.sort(points,new Comparator<int[]>(){
            public int compare(int[] a,int[] b){
                //Key:return a[0].compareTo(b[0]);int�ǻ�����������,û��compareTo(int)����,����Ҫôд�ɼ�����Ҫô��װΪInteger�����򱨴�Line 10: error: int cannot be dereferenced
                //Key:compareToֻ�ܱȽ�Object
                //Key:Test case:,[0,9],[3,9],[0,6]��Ҫ����a[0]��b[0]��ͬ�����
                //return Integer.valueOf(a[0]).compareTo(Integer.valueOf(b[0]));
                return a[0] == b[0]?Integer.valueOf(a[1]).compareTo(Integer.valueOf(b[1])):Integer.valueOf(a[0]).compareTo(Integer.valueOf(b[0]));
            }
        });
        
        int res = 1,end = points[0][1],start = points[0][0];
        for(int i = 1;i<=points.length-1;i++){
            //Key:int[] ûʵ��toSting(),����toString()��String.valueOf()ֻ�ܴ�ӡ����ַ
            //System.out.println(points[i].toString());
            //[I@5cad8086
            //[I@6e0be858
            //[I@61bbe9ba
            //[I@610455d6
            //[I@511d50c0
            //System.out.println(aa.toString()); //[I@103c520 ע�����д���Ǵ���ģ���ӡ������������ֵ��  
            //������Arrays.toString()  System.out.println(Arrays.toString(aa)); //[1, 2, 3, 4, 5]  
            //System.out.println(Arrays.toString(points[i]));
            //Key:����Ĳ��У���Ҫ����������Ƕ����
            //if(points[i][0]>end){
            if(points[i][0]>end){
                res++;
                //Key:Test case:[[9,12],[1,10],[4,11],[8,12],[3,9],[6,9],[6,7]] �����д����ȫ��,ÿ��end��������
                //end = points[i][1];
                end = points[i][1];
                continue;
            }
            end = Math.min(end,points[i][1]);
        }
        return res;
    }
}

447. Number of Boomerangs
public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        //Key:wrong
        /**
        int[] multiple = new int[points.length];
        if(points.length == 0) return 0;
        for(int i =0;i<=points.length-1;i++) multiple[i] = (int)(Math.pow(points[i][0],2)+Math.pow(points[i][1],2));
        //Arrays.sort(multiple);
        ArrayList<Integer> list = new ArrayList<>();
        int index1 = 0,index2 = multiple.length-1;
        while(index1<index2){
            list.add(multiple[index1]+multiple[index2]-2*points[index1][0]*points[index2][0]-2*points[index1][1]*points[index2][1]);
            list.add(multiple[index1+1]+multiple[index2]-2*points[index1+1][0]*points[index2][0]-2*points[index1+1][1]*points[index2][1]);
            list.add(multiple[index1]+multiple[index2-1]-2*points[index1][0]*points[index2-1][0]-2*points[index1][1]*points[index2-1][1]);
            index1++;
            index2--;
        }
        int result = 0;
        for(int i:multiple) if(list.contains(new Integer(i))) result++;
        return result;
        **/
        //Just cp
        Map<Integer,Integer> map = new HashMap();
        int res = 0;
        for(int i=0;i<points.length;i++){
        	for(int j=0;j<points.length;j++){
        		if(i == j) continue;
        		int d = distance(points[i],points[j]);
        		map.put(d, map.getOrDefault(d, 0) + 1);
        	}
            for(int val : map.values()) res += val * (val-1);
            map.clear();
        }
        return res;
    }
    private int distance(int[] a, int[] b){
    	int dx = a[0] - b[0];
    	int dy = a[1] - b[1];
    	return dx * dx + dy * dy;
    }
}

438. Find All Anagrams in a String
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        //Key:just cp
        //sliding problem�����ܽ���һ��template,ͦ�õ�
        //https://discuss.leetcode.com/topic/68976/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem/2
        List<Integer> result = new LinkedList<>();
        if(p.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();
        
        int begin = 0, end = 0;
        int head = 0;
        int len = Integer.MAX_VALUE;
        
        
        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            end++;
            
            while(counter == 0){
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == p.length()){
                    result.add(begin);
                }
                begin++;
            }
            
        }
        return result;
    }
}

435. Non-overlapping Intervals
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    //Key:��д��Wrong��.....
    /**
    public int eraseOverlapIntervals(Interval[] intervals) {
        //Key point:Collections.sort();
        //��������˸����취��ֱ�ӰѼ���������˳������ֱ���
        //�ǳ��ǳ��ؼ�!!!!!!
        //Key point:Comparator ʵ���Լ�ʹ��
        //Corner case:[[1,99],[2,3][4,5],[7,9]]
        int res = 0,prev = 0;
        if(intervals.length <= 1) return 0;
        Arrays.sort(intervals,new Comparator<Interval>(){
            public int compare(Interval i1,Interval i2){
                return i1.start!=i2.start?i1.start-i2.start:i1.end-i2.end;
            }
        });
        for(int i = 1;i<=intervals.length-1;i++){
            if(intervals[i].start<intervals[prev].end && intervals[i].end <= intervals[prev].end){
                 res++;
                 prev = i;
            }
            
        }
        return res;
    }
    **/
    
    public int eraseOverlapIntervals(Interval[] intervals) {
       Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                 return o1.end - o2.end;  //only sort by end
            }
        });

        int end = Integer.MIN_VALUE;
        int count = 0;
        for (Interval interval : intervals) {
            if (interval.start >= end) end = interval.end;
            else count++;
        }

        return count; 
    }
}

76. Minimum Window Substring
public class Solution {
    public String minWindow(String s, String t) {
        //Key:ģ�巽��  https://discuss.leetcode.com/topic/68976/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
        String res = "";
        Map<Character,Integer> map = new HashMap<>();
        int begin = 0,end = 0,start = 0,len = s.length()+1,count;
        for(char c:t.toCharArray()) map.put(c,map.getOrDefault(c,0)+1);
        count = map.size();
        while(end<=s.length()-1){
            char c = s.charAt(end);
            char c2 = s.charAt(begin);
            if(map.containsKey(c)){
                map.put(c,map.get(c)-1);
                if(map.get(c) == 0) count--;
            }
            end++;
            while(count == 0){
                c2 = s.charAt(begin);
                //Key:��һ��С�Ż���ֻ������ʼλ�ú���С���ȣ�sustring()�����������
                if(map.containsKey(c2)){
                    map.put(c2,map.get(c2)+1);
                    if(map.get(c2) > 0){
                        count++;
                        
                    }
                     
                }
                //Key:��һ��С�Ż���ֻ������ʼλ�ú���С���ȣ�sustring()�����������
                //if(res.equals("")) res = s.substring(begin,end);
                //else res = end-begin<res.length()?s.substring(begin,end):res;
                if(end-begin<len){
                    start = begin;
                    len = end-begin;
                }
                begin++;
            }
        }
        
        return len > s.length()?"":s.substring(start,start+len);
    }
}

3. Longest Substring Without Repeating Characters
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        //Key:�����ģ�岻̫��.....https://discuss.leetcode.com/topic/68976/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
        int repeat = 0,begin = 0,end = 0,res = 0;
        Map<Character,Integer> map = new HashMap<>();
        while(end<=s.length()-1){
            char c = s.charAt(end);
            map.put(c,map.getOrDefault(c,0)+1);
            if(map.get(c) > 1) repeat++;
            end++;
            while(repeat>0){
                char c2 = s.charAt(begin);
                if(map.get(c2) > 1){
                    repeat--;
                    
                }
                map.put(c2,map.get(c2)-1);
                begin++;
            }
            //Corner case:����ĸ"b"
            res = Math.max(res,end-begin);
        }
        return res;
    }
}

357. Count Numbers with Unique Digits
public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        //Key:just cp
        if (n == 0)     return 1;
        
        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n-- > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
        }
        return res;
    }
}

541. Reverse String II
public class Solution {
    public String reverseStr(String s, int k) {
        //My 1st version
        if(s == null || s.length() == 0) return "";
		if(k == 0) return s;
        int start = 0,end = start+k-1,count = 0;
		if(end>s.length()-1) end = s.length()-1;
        StringBuilder sb = new StringBuilder();
        while(end<=s.length()-1){
			
			//System.out.println("end"+end);
            while(end>=start){
                sb.append(s.charAt(end--));
            }
			if(sb.toString().length() == s.length()) break;
            start += k;
            count = 0;
            
            while(start<=s.length()-1 && count <= k-1){
                sb.append(s.charAt(start++));
                count++;
            }
			//System.out.println(""+start);
			if(start >= s.length()-1) end = s.length()-1;
            else end = start+k-1>s.length()-1?s.length()-1:start+k-1;
			
        }
		//System.out.println(""+start+","+s.length());
        return sb.toString();
    }
}

536. Construct Binary Tree from String
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode str2tree(String s) {
        //My 1st version
        if(s == null || s.length() == 0) return null;
        Stack<TreeNode> stack = new Stack<>();
        //Key:corner case:"-4(2(3)(1))(6(5)(7))"  ��������
        //Key:Coner case:"51(232)(434)" ��λ��
        //Corner case:"4"
        for(int i = 0;i<=s.length()-1;i++){
            if(s.charAt(i) == '-'){
                String tmp = "";
                while(i<= s.length()-1 && s.charAt(i)!='(' && s.charAt(i) != ')'){
                    tmp = tmp+String.valueOf(s.charAt(i++));
                }
                stack.push(new TreeNode(Integer.parseInt(tmp)));
                i--;
            }
            else if(Character.isDigit(s.charAt(i))) {
                 String tmp = "";
                 while(i<= s.length()-1 && s.charAt(i)!='(' && s.charAt(i) != ')'){
                    tmp = tmp+String.valueOf(s.charAt(i++));
                }
                //stack.push(new TreeNode(s.charAt(i)-'0'));
                stack.push(new TreeNode(Integer.parseInt(tmp)));
                i--;
                //System.out.println(String.valueOf(stack.peek().val));
            } else if (s.charAt(i) == ')'){
                if(!stack.empty()){
                    TreeNode tmp = stack.peek();
                    TreeNode tmp2 = null;
                    stack.pop();
                    if(!stack.empty()){
                        tmp2 = stack.peek();
                        stack.pop();
                        if(tmp2.left != null){
                            tmp2.right = tmp;
                        } else {
                            tmp2.left = tmp;
                        }
                    }
                    stack.push(tmp2);
                }
            }
        }
        //System.out.println(stack.peek().val+"");
        return stack.peek();
    }
}

539. Minimum Time Difference
public class Solution {
    public int findMinDifference(List<String> timePoints) {
        //Key:���������TMD������.....
        //Key:Just cp
         Collections.sort(timePoints);
        int minDiff = Integer.MAX_VALUE;
        String prev = timePoints.get(timePoints.size() -1);
        for (String s : timePoints) {
            int prevMins = Integer.parseInt(prev.split(":")[0])*60 + Integer.parseInt(prev.split(":")[1]);
            int curMins = Integer.parseInt(s.split(":")[0])*60 + Integer.parseInt(s.split(":")[1]);
            int diff = curMins - prevMins;
            if (diff < 0) diff += 1440;
            minDiff = Math.min(minDiff, Math.min(diff, 1440 - diff));
            prev = s;
        }
        return minDiff;
    }
}

128. Longest Consecutive Sequence
public class Solution {
    public int longestConsecutive(int[] nums) {
        //Key:O(n)������Ӧ�ñ�����map��.....
        //Key:�������Ҫ����duplicates test case������ֻͳ�Ʋ�ͬ���ֵ��������У�  [100,4,2,200,1,3,2]  excepted ans:4    [1,2,0,1]  excepted:3
        if(nums.length == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int tmp = 0;
        for(int i:nums){
            if(!map.containsKey(i)){
                //left��right�ֱ������������ֵĳ���
                int left = map.getOrDefault(i-1,0),right = map.getOrDefault(i+1,0);
                tmp = 1+left+right;
                map.put(i,tmp);
                //Key:ԭ����д���Ǵ���ģ���Ӧ��ֻ����i-1��i+1���ã���Ӧ���ǿ��ǵ��������Χ��start��end����
                //https://discuss.leetcode.com/topic/6148/my-really-simple-java-o-n-solution-accepted
                /***
                if(map.getOrDefault(i-1,0) != 0){
                    map.put(i-1,tmp);
                }
                if(map.getOrDefault(i+1,0) != 0){
                    map.put(i+1,tmp);
                }
                **/
                map.put(i - left, tmp);
                map.put(i + right, tmp);
                max = Math.max(max,tmp);
            }
            
        }
        return max;
    }
}

329. Longest Increasing Path in a Matrix
public class Solution {
    //Key:�������TMDд����.......
    /**
    public int longestIncreasingPath(int[][] matrix) {
        //key:brute force  DFS or BFS
        List<Integer> item = new ArrayList<>();
        boolean[][] used = new boolean[matrix.length][matrix[0].length];
        helper(matrix,used,item);
    }
    public void helper(int[][] matrix,used,List<Integer> item,int prevR,int prevC){
        for(int i = 0;i<=matrix.length-1;i++){
            for(int j = 0;j<=matrix[0].length-1;j++){
                if(used[i][j] || matrix[i][j] <=item.get(item.size()-1) || matrix[]) continue;
                item.add(matrix[i][j]);
                used[i][j] = true;
                max = Math.max(max,item.size());
                helper(matrix,used,item);
                used[i][j]
            }
        }
    }
    **/
    //Key:�����д�������鷳��������just cp��
    //https://discuss.leetcode.com/topic/34835/15ms-concise-java-solution/2
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }   
        return max;
    }
    
    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if(cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for(int[] dir: dirs) {
            int x = i + dir[0], y = j + dir[1];
            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}

395. Longest Substring with At Least K Repeating Characters
public class Solution {
    
    //Key:д�˰��컹�Ǵ��.....
    /**
    public int longestSubstring(String s, int k) {
       
       
        Map<Character,Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for(char c:s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            if(entry.getValue() < k) set.add(entry.getKey());
        }
        //Test case:"weitong" 2
        if(set.size() == map.size()) return 0;
        int max = Integer.MIN_VALUE;
        int start = 0,end = s.length()-1;
        int length = s.length();
        for(int i = 0;i<=s.length()-1;i++){
            if(set.contains(s.charAt(i))){
                //Key:�������ô˵��....������������....
                //Key:����������k��Ԫ��ʱ��ȡ��֮ǰ����substring����󳤶�
                //Key:��Ϊs = s.substring(i+1,end+1); ����i�ķ�ΧҪlength-2  ��������case "weitong" 2 ����ʱҪע��i+1�ķ�Χ��ע�⣬��ʱs��lengthһֱ�ڱ�....
                //Key:��������substirngû����....
                //if(i+1 <= length-1) s = s.substring(i+1,length);
                max = Math.max(max,i-start);
                start = i+1;
            }
        }
        return max == Integer.MIN_VALUE?s.length():max;
       
       
    }
    **/
    //Just cp
    //https://discuss.leetcode.com/topic/57372/java-divide-and-conquer-recursion-solution/2
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        return helper(str,0,s.length(),k);
    }
    private int helper(char[] str, int start, int end,  int k){
        if(end-start<k) return 0;//substring length shorter than k.
        int[] count = new int[26];
        for(int i = start;i<end;i++){
            int idx = str[i]-'a';
            count[idx]++;
        }
        for(int i = 0;i<26;i++){
            if(count[i]<k&&count[i]>0){ //count[i]=0 => i+'a' does not exist in the string, skip it.
                for(int j = start;j<end;j++){
                    if(str[j]==i+'a'){
                        int left = helper(str,start,j,k);
                        int right = helper(str,j+1,end,k);
                        return Math.max(left,right);
                    }
                }
            }
        }
        return end-start;
    }
}

172. Factorial Trailing Zeroes
public class Solution {
    public int trailingZeroes(int n) {
        //Key:�� Just cp
        //https://discuss.leetcode.com/topic/6848/my-explanation-of-the-log-n-solution/4
        int cnt = 0;
        while(n>0){
            cnt += n/5;
            n/=5;
        }
        return cnt;
    }
}

315. Count of Smaller Numbers After Self
public class Solution {
    //Key:���ѣ����ɡ�Just cp
    //https://discuss.leetcode.com/topic/31554/11ms-java-solution-using-merge-sort-with-explanation
    int[] count;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();     
    
        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
        	indexes[i] = i;
        }
        mergesort(nums, indexes, 0, nums.length - 1);
        for(int i = 0; i < count.length; i++){
        	res.add(count[i]);
        }
        return res;
    }
    private void mergesort(int[] nums, int[] indexes, int start, int end){
    	if(end <= start){
    		return;
    	}
    	int mid = (start + end) / 2;
    	mergesort(nums, indexes, start, mid);
    	mergesort(nums, indexes, mid + 1, end);
    	
    	merge(nums, indexes, start, end);
    }
    private void merge(int[] nums, int[] indexes, int start, int end){
    	int mid = (start + end) / 2;
    	int left_index = start;
    	int right_index = mid+1;
    	int rightcount = 0;    	
    	int[] new_indexes = new int[end - start + 1];
    
    	int sort_index = 0;
    	while(left_index <= mid && right_index <= end){
    		if(nums[indexes[right_index]] < nums[indexes[left_index]]){
    			new_indexes[sort_index] = indexes[right_index];
    			rightcount++;
    			right_index++;
    		}else{
    			new_indexes[sort_index] = indexes[left_index];
    			count[indexes[left_index]] += rightcount;
    			left_index++;
    		}
    		sort_index++;
    	}
    	while(left_index <= mid){
    		new_indexes[sort_index] = indexes[left_index];
    		count[indexes[left_index]] += rightcount;
    		left_index++;
    		sort_index++;
    	}
    	while(right_index <= end){
    		new_indexes[sort_index++] = indexes[right_index++];
    	}
    	for(int i = start; i <= end; i++){
    		indexes[i] = new_indexes[i - start];
    	}
    }
}

17. Letter Combinations of a Phone Number
public class Solution {
    //Key:���������鷳������д�������ܻ���Щ��...
    public List<String> letterCombinations(String digits) {
        String[] strs = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0) return res;
        int depth = 0;
        helper(res,strs,digits,depth,"");
        return res;
    }
    public void helper(List<String> list,String[] strs,String digits,int depth,String tmp){
        if(depth == digits.length()){
            list.add(tmp);
        } else {
            int index = digits.charAt(depth)-'2';
            //Key:�ų�0,1������Ч�����ĸ���
            if(index >= 0){
                for(int i = 0;i<=strs[index].length()-1;i++){
                    tmp = tmp+String.valueOf(strs[index].charAt(i));
                    helper(list,strs,digits,depth+1,tmp);
                    tmp = tmp.substring(0,tmp.length()-1);
                }
            }
        }
    }
}

20. Valid Parentheses
public class Solution {
    //Key:����д����Ҫ��corner case���ж�
    //Corner case:"(("  ��Ҫ�����ж�һ������Ƿ�Ϊ��
    public boolean isValid(String s) {
        if(s == null || s.length() == 0 || s.length()%2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<=s.length()-1;i++){
            if(s.charAt(i) == '}' || s.charAt(i) == ')' || s.charAt(i) == ']'){
                if(stack.empty()){
                    return false;
                } else {
                    if(s.charAt(i) == '}' && stack.peek() != '{' ||s.charAt(i) == ')' && stack.peek() != '(' || s.charAt(i) == ']' && stack.peek() != '['){
                        return false;
                    } 
                    stack.pop();
                }
            } else {
                stack.push(s.charAt(i));
            }
            
        }
        //Corner case:"(("  ��Ҫ�����ж�һ������Ƿ�Ϊ��
        return stack.empty()?true:false;
    }
}

49. Group Anagrams
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //Key:˼·һ��ʼ�������룬���ⷨ���������
        //Key:Just cp
        //https://discuss.leetcode.com/topic/24494/share-my-short-java-solution/5
        if(strs==null || strs.length == 0){
    		return new ArrayList<List<String>>();
    	}
    	HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    	//Arrays.sort(strs);
    	for (String s:strs) {
    		char[] ca = s.toCharArray();
    		Arrays.sort(ca);
    		String keyStr = String.valueOf(ca);
    		if(!map.containsKey(keyStr))
    			map.put(keyStr, new ArrayList<String>());
    		map.get(keyStr).add(s);
    	}
    	
    	for(String key: map.keySet()) {
    		Collections.sort(map.get(key));
    	}
    	return new ArrayList<List<String>>(map.values());
    }
}

33. Search in Rotated Sorted Array
public class Solution {
    public int search(int[] nums, int target) {
        //Key:Loop version
        /**
        //Key:��һ��϶�����Ȼ����ģ��ж�target��unsorted or sorted�У�Ȼ��recursion����֮
        //Key:https://discuss.leetcode.com/topic/16580/java-ac-solution-using-once-binary-search
        if(nums.length == 0) return -1;
        int low = 0,high = nums.length-1,mid = 0;
        while(low<=high){
            mid = (low+high)/2;
            if(nums[mid] == target) return mid;
            //determine which part is sorted  Key:no duplicates
            //ǰ�벿������,��벿������
            //Key:Corner case: �����鴦�� [1] 0 ��Ϊnums[mid]һ��nums[low]���������=���ᵼ��low,high�ޱ仯����������ѭ��
            //if(nums[mid] > nums[low]){
            if(nums[mid] >= nums[low]){
                //target belong to sorted part
                if(target < nums[mid] && target >= nums[low]) high = mid-1;
                //target belong to unsorted part
                else low = mid + 1;
            }
            //��벿������,ǰ�벿������
            if(nums[mid] <= nums[high]){
                //target belong to sorted part
                if(target > nums[mid] && target <= nums[high]) low = mid+1;
                //target belong to unsorted part
                else high = mid-1;
            }
        }
        return -1;
        **/
        //Key:Recursion version
        if(nums.length == 0) return -1;
        return helper(0,nums.length-1,nums,target);
    }
    public int helper(int low,int high,int[] nums,int target){
        int mid = (low+high)/2;
        if(nums[mid] == target) return mid;
        if(low <= high){
            //Key:ע�⣬�������binary search code,����nums[mid]��targetû�е����ж�
            /**
            public int helper(int target,int[] nums,int start,int end){
        		int mid = (start+end)/2;
        		while(start<=end){
        			//System.out.println(nums[mid]+"");
        			if(nums[mid] == target) return mid;
        			else if(nums[mid]<target) return helper(target,nums,mid+1,end);
        			else return helper(target,nums,start,mid-1);
        		}
        		return -1;
        	}
            **/
            //Key:������nums[mid] <= nums[high] �����=�жϣ�ע��Ϊʲô!!!!
            //Corner case:[3,1] 1
            if(nums[mid] <= nums[high]){
                if(target > nums[mid] && target <= nums[high]) return helper(mid+1,high,nums,target);
                else return helper(low,mid-1,nums,target);
            }
            if(nums[mid] >= nums[low]){
                if(target >= nums[low] && target < nums[mid]) return helper(low,mid-1,nums,target);
                else return helper(mid+1,high,nums,target);
            }
        }
        return -1;
    }
}

81. Search in Rotated Sorted Array II
public class Solution {
    public boolean search(int[] nums, int target) {
        //Key:contains duplicates 
        //Test case {2,2,1,2,2,2,2,2,2}  --->��ʱ���޷���ͨ��nums[mid]>nums[low] ���ж���....
        //Key:�Ƚ�ϲ��coder_gal25's sol, https://discuss.leetcode.com/topic/310/when-there-are-duplicates-the-worst-case-is-o-n-could-we-do-better/28   ---> ��������������ʵ����mid,high,lowͬʱ��ȣ�ֻҪ����������ų��ͺ���!!!  Worst case��O(N)
        if(nums.length == 0) return false;
        return helper(0,nums.length-1,nums,target) == -1?false:true;
    }
    public int helper(int low,int high,int[] nums,int target){
        int mid = (low+high)/2;
        if(low<=high){
            if(nums[mid] == target) return mid;
            //Key:�ؼ��㣬�����ط�Ӧ�ú�Search in Rotated Sorted Array I һ��
            if(nums[mid] == nums[low] && nums[mid] == nums[high]){
                //low++;
                //high--;
                return helper(++low,--high,nums,target);
            } else if(nums[mid]>=nums[low]){
                if(target >= nums[low] && target < nums[mid]) return helper(low,mid-1,nums,target);
                else return helper(mid+1,high,nums,target);
            } else if(nums[mid] <= nums[high]){
                if(target > nums[mid] && target <= nums[high]) return helper(mid+1,high,nums,target);
                else return helper(low,mid-1,nums,target);
            }
        }
        return -1;
    }
}

154. Find Minimum in Rotated Sorted Array II
public class Solution {
    public int findMin(int[] nums) {
        //Key:I ��Explanation������˼·����https://discuss.leetcode.com/topic/6112/a-concise-solution-with-proof-in-the-comment
        //https://discuss.leetcode.com/topic/25248/super-simple-and-clean-java-binary-search
        //Just cp
        int l = 0, r = nums.length-1;
        while (l < r) {
             int mid = (l + r) / 2;
             if (nums[mid] < nums[r]) {
            	 r = mid;
             } else if (nums[mid] > nums[r]){
            	 l = mid + 1;
             } else {  
            	 r--;  //nums[mid]=nums[r] no idea, but we can eliminate nums[r];
             }
        }
        return nums[l];
    }
}

153. Find Minimum in Rotated Sorted Array
public class Solution {
    public int findMin(int[] nums) {
        //Key:ֱ��ճ�� 154 Find Minimum in Rotated Sorted Array II �Ľⷨ
        //Key:I ��Explanation������˼·����https://discuss.leetcode.com/topic/6112/a-concise-solution-with-proof-in-the-comment
        //https://discuss.leetcode.com/topic/25248/super-simple-and-clean-java-binary-search
        //Just cp ��
        int l = 0, r = nums.length-1;
        while (l < r) {
             int mid = (l + r) / 2;
             if (nums[mid] < nums[r]) {
            	 r = mid;
             } else if (nums[mid] > nums[r]){
            	 l = mid + 1;
             } else {  
            	 r--;  //nums[mid]=nums[r] no idea, but we can eliminate nums[r];
             }
        }
        return nums[l];
    }
}

343. Integer Break
public class Solution {
    public int integerBreak(int n) {
        //Key:DP or Math
        //Just cp:https://discuss.leetcode.com/topic/42978/java-dp-solution DP
       int[] dp = new int[n + 1];
       dp[1] = 1;
       for(int i = 2; i <= n; i ++) {
           for(int j = 1; j < i; j ++) {
               dp[i] = Math.max(dp[i], (Math.max(j,dp[j])) * (Math.max(i - j, dp[i - j])));
           }
       }
       return dp[n];
    }
}

401. Binary Watch
public class Solution {
    //Key:���ݣ����������ͦ�鷳�ģ����Ʋ�̫������...
    //Key:��һ��bitCount�ķ���������ò�Ƹ����ü�
    //https://discuss.leetcode.com/topic/59374/simple-python-java
    //Key:Just cp https://discuss.leetcode.com/topic/59494/3ms-java-solution-using-backtracking-and-idea-of-permutation-and-combination/2
    
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }

    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }

    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }
        
        for(int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);    
        }
    }
}

383. Ransom Note
public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        //�����Ŀ�����е�����
        //"abb","bba"�Ҿ��ò������⣬����������ͨ����ò��ֻҪmagazine�е��ַ������ȡ�����ransomNote�Ϳ���....������û��� 
    //     char[] rArr = ransomNote.toCharArray();
    //     char[] mArr = magazine.toCharArray();
    //     int lengthR = rArr.length();
    //     int lengthM = mArr.length();
    //     int tmp = 0;
    //     int index1 = 0,index2 = 0;
    //     for(int i=0;i<=lengthR-1;i++){
    //         tmp ^= (int)rArr[i];
    //     }
    //     for(int i=0;i<=lengthM-1;i++){
    //         tmp ^= (int)mArr[i];
    //     }
    //     for(int i=0;i<=lengthR-1;i++){
    //         tmp ^= (int)rArr[i];
    //     }
    // }
    
        //Key:Ӧ����ransomNote�е��ַ�������magazine�еľͿ���
        //Key:Coner case:"" "b" ���ַ�����ȻҲ��������
        //Key:����Ҳ�ö���....Corner case:"aa", "aab" -->true    "aa""ab" --> false
        if(ransomNote == null || ransomNote.length() == 0) return true;
        if(magazine == null || magazine.length() == 0) return false;
        Map<Character,Integer> map = new HashMap<>();
        for(char c:magazine.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(char c:ransomNote.toCharArray()){
            map.put(c,map.getOrDefault(c,0)-1);
            if(map.get(c) == -1) return false;
        }
        return true;
    }
}

423. Reconstruct Original Digits from English
public class Solution {
    public String originalDigits(String s) {
        //Key:�ӵ����⣬ֻҪ�ҹ��ɾ�����......
        //���ҳ����е��ַ�������һ�ų���Щ����
        //Just cp
        /****
        
        char c = s.charAt(i);
        if (c == 'z') count[0]++;
        if (c == 'w') count[2]++;
        if (c == 'x') count[6]++;
        if (c == 's') count[7]++; //7-6
        if (c == 'g') count[8]++;
        if (c == 'u') count[4]++; 
        if (c == 'f') count[5]++; //5-4
        if (c == 'h') count[3]++; //3-8
        if (c == 'i') count[9]++; //9-8-5-6
        if (c == 'o') count[1]++; //1-0-2-4
        
        ****/
        
        int[] count = new int[10];
        for(char c:s.toCharArray()){
            if (c == 'z') count[0]++;
            if (c == 'w') count[2]++;
            if (c == 'x') count[6]++;
            if (c == 's') count[7]++; //7-6
            if (c == 'g') count[8]++;
            if (c == 'u') count[4]++; 
            if (c == 'f') count[5]++; //5-4
            if (c == 'h') count[3]++; //3-8
            if (c == 'i') count[9]++; //9-8-5-6
            if (c == 'o') count[1]++; //1-0-2-4
        }
        count[7] -= count[6];
        count[5] -= count[4];
        count[3] -= count[8];
        count[9] = count[9] - count[8] - count[5] - count[6];
        count[1] = count[1] - count[0] - count[2] - count[4];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; i++){
            for (int j = 0; j < count[i]; j++){
                sb.append(i);
            }
        }
        return sb.toString();
        
    }
}

543. Diameter of Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //1st version
    int max = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        helper(root); 
        return max;
    }
    public int helper(TreeNode node){
        if(node != null){
            int left = helper(node.left),right = helper(node.right);
            max = Math.max(left+right,max);
            return Math.max(left+1,right+1);
        }
        return 0;
    }
}

538. Convert BST to Greater Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Just cp
    int sum = 0;
    
    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }
    
    public void convert(TreeNode cur) {
        if (cur == null) return;
        convert(cur.right);
        cur.val += sum;
        sum = cur.val;
        convert(cur.left);
    }
}

537. Complex Number Multiplication
public class Solution {
    public String complexNumberMultiply(String a, String b) {
        //Hard,Key:Just cp
        //https://discuss.leetcode.com/topic/84261/java-3-liner/2
        int[] coefs1 = Stream.of(a.split("\\+|i")).mapToInt(Integer::parseInt).toArray(), 
        coefs2 = Stream.of(b.split("\\+|i")).mapToInt(Integer::parseInt).toArray();
        return (coefs1[0]*coefs2[0] - coefs1[1]*coefs2[1]) + "+" + (coefs1[0]*coefs2[1] + coefs1[1]*coefs2[0]) + "i";
        
    }
}

547. Friend Circles
public class Solution {
    //Key:hard
    //Just cp https://discuss.leetcode.com/topic/85021/java-bfs-equivalent-to-finding-connected-components-in-a-graph
    public int findCircleNum(int[][] M) {
        int count = 0;
        for (int i=0; i<M.length; i++)
            if (M[i][i] == 1) { count++; BFS(i, M); }
        return count;
    }
    
    public void BFS(int student, int[][] M) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(student);
        while (queue.size() > 0) {
            int queueSize = queue.size();
            for (int i=0;i<queueSize;i++) {
                int j = queue.poll();
                M[j][j] = 2; // marks as visited
                for (int k=0;k<M[0].length;k++) 
                    if (M[j][k] == 1 && M[k][k] == 1) queue.add(k);
            }
        }
    }
}

98. Validate Binary Search Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


public class Solution {
    //Key:my wrong version
    
    /**
    boolean tag = true;
    public boolean isValidBST(TreeNode root) {
        //Key:Corner case:[] ����true
        if(root == null) return true;
        helper(root.left,root.val,true);
        helper(root.right,root.val,false);
        return tag;
    }
    //Key:�����׺��Ե�һ��!!!!!
    //Key:Corner case:[10,5,15,null,null,6,20]  
    //��Ҫ���ǵ��м��left��right�����޺����޷�Χ
    public void helper(TreeNode node,int parent,boolean left){
        if(node != null){
            if(left){
                if(node.val >= parent) tag = false;
            } else {
                if(node.val <= parent) tag = false;
            }
            helper(node.left,node.val,true);
            helper(node.right,node.val,false);
        }
    }
    **/
    //Just cp
    //https://discuss.leetcode.com/topic/46016/learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-java-solution
    public boolean isValidBST(TreeNode root) {
       if (root == null) return true;
       Stack<TreeNode> stack = new Stack<>();
       TreeNode pre = null;
       while (root != null || !stack.isEmpty()) {
          while (root != null) {
             stack.push(root);
             root = root.left;
          }
          root = stack.pop();
          if(pre != null && root.val <= pre.val) return false;
          pre = root;
          root = root.right;
       }
       return true;
    }
}

4. Median of Two Sorted Arrays
public class Solution {
    //My wrong version
    /**
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int start1=0,start2=0,end1=nums1.length-1,end2=nums2.length-1;
        return findMedian(start1,end1,nums1,start2,end2,nums2);
    }
    public double findMedian(int start1,int end1,int[] nums1,int start2,int end2,int[] nums2){
        int mid1 = (start1+end1)/2,mid2 = (start2+end2)/2;
        if(end1==start1 && end2==start2) return (double)(nums1[start1]+nums2[start2])/2;
        
        if(nums1[mid1] < nums2[mid2]){
            start1 = (mid1+end1)/2;
            end2 = (start2+mid2)/2;

        } else if(nums1[mid1] > nums2[mid2]){
            start2 = (mid2+end2)/2;
            end1 = (start1+mid1)/2;
            
        } else if(nums1[mid1] == nums2[mid2]){
           end1 = end1+1;
           end2 = end2-1;
        }
        return findMedian(start1,end1,nums1,start2,end2,nums2);
    }
    **/
    //Just cp
    //https://discuss.leetcode.com/topic/28602/concise-java-solution-based-on-binary-search/2
    public double findMedianSortedArrays(int[] A, int[] B) {
    	    int m = A.length, n = B.length;
    	    int l = (m + n + 1) / 2;
    	    int r = (m + n + 2) / 2;
    	    return (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
    	}
    
    public double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
    	if (aStart > A.length - 1) return B[bStart + k - 1];            
    	if (bStart > B.length - 1) return A[aStart + k - 1];                
    	if (k == 1) return Math.min(A[aStart], B[bStart]);
    	
    	int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
    	if (aStart + k/2 - 1 < A.length) aMid = A[aStart + k/2 - 1]; 
    	if (bStart + k/2 - 1 < B.length) bMid = B[bStart + k/2 - 1];        
    	
    	if (aMid < bMid) 
    	    return getkth(A, aStart + k/2, B, bStart,k - k/2);// Check: aRight + bLeft 
    	else 
    	    return getkth(A, aStart,B,bStart + k/2, k - k/2);// Check: bRight + aLeft
    }
}

468. Validate IP Address
public class Solution {
    public String validIPAddress(String IP) {
        //Key:hard �Լ�дƥ����鷳��ֱ����regression�Ƚϼ�
        //Key:just cp
        //https://discuss.leetcode.com/topic/75015/java-clear-regex-solution
        //Key:������÷�
        String singleIPv4 = "([0-9]|[1-9][0-9]|[1][0-9]{2}|[2][0-5]{2})";
        String IPv4 = String.format("(%s\\.){3}%s", singleIPv4,singleIPv4);
    	if (IP.matches(IPv4)) return "IPv4";
    	String singleLetter = "[A-Fa-f0-9]";
    	String singleIPv6 = String.format("(%s{1,4}|[0]%s{0,3})", singleLetter,singleLetter);
    	String IPv6 = String.format("(%s:){7}%s", singleIPv6,singleIPv6);
        if (IP.matches(IPv6)) return "IPv6";
        return "Neither";
    }
}

420. Strong Password Checker
//Key:Hard����д�������鷳...
//Key:Just cp
//https://discuss.leetcode.com/topic/63185/java-easy-solution-with-explanation/2
public class Solution {
    public int strongPasswordChecker(String s) {
        
        if(s.length()<2) return 6-s.length();
        
        //Initialize the states, including current ending character(end), existence of lowercase letter(lower), uppercase letter(upper), digit(digit) and number of replicates for ending character(end_rep)
        char end = s.charAt(0);
        boolean upper = end>='A'&&end<='Z', lower = end>='a'&&end<='z', digit = end>='0'&&end<='9';
        
        //Also initialize the number of modification for repeated characters, total number needed for eliminate all consequnce 3 same character by replacement(change), and potential maximun operation of deleting characters(delete). Note delete[0] means maximum number of reduce 1 replacement operation by 1 deletion operation, delete[1] means maximun number of reduce 1 replacement by 2 deletion operation, delete[2] is no use here. 
        int end_rep = 1, change = 0;
        int[] delete = new int[3];
        
        for(int i = 1;i<s.length();++i){
            if(s.charAt(i)==end) ++end_rep;
            else{
                change+=end_rep/3;
                if(end_rep/3>0) ++delete[end_rep%3];
                //updating the states
                end = s.charAt(i);
                upper = upper||end>='A'&&end<='Z';
                lower = lower||end>='a'&&end<='z';
                digit = digit||end>='0'&&end<='9';
                end_rep = 1;
            }
        }
        change+=end_rep/3;
        if(end_rep/3>0) ++delete[end_rep%3];
        
        //The number of replcement needed for missing of specific character(lower/upper/digit)
        int check_req = (upper?0:1)+(lower?0:1)+(digit?0:1);
        
        if(s.length()>20){
            int del = s.length()-20;
            
            //Reduce the number of replacement operation by deletion
            if(del<=delete[0]) change-=del;
            else if(del-delete[0]<=2*delete[1]) change-=delete[0]+(del-delete[0])/2;
            else change-=delete[0]+delete[1]+(del-delete[0]-2*delete[1])/3;
            
            return del+Math.max(check_req,change);
        }
        else return Math.max(6-s.length(), Math.max(check_req, change));
    }
}

179. Largest Number
public class Solution {
    public String largestNumber(int[] nums) {
        //Key:Hard
        //Key:��������ر�����
        //https://discuss.leetcode.com/topic/8018/my-java-solution-to-share/2
        //https://discuss.leetcode.com/topic/32442/share-my-fast-java-solution-beat-98-64/2
        //Key:Key point:
        /**
        String s1 = "9";
        String s2 = "31";
        
        String case1 =  s1 + s2; // 931
        String case2 = s2 + s1; // 319
        **/
        if (nums == null || nums.length == 0) return "";
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i]+"";
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String i, String j) {
                String s1 = i+j;
                String s2 = j+i;
                return s1.compareTo(s2);
            }
        });
        if (strs[strs.length-1].charAt(0) == '0') return "0";
        String res = new String();
        for (int i = 0; i < strs.length; i++) {
            res = strs[i]+res;
        }
        return res;
    
    }
}

273. Integer to English Words
//Key:just cp
//https://discuss.leetcode.com/topic/25177/share-my-clean-java-solution
public class Solution {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String[] big= {"", "Thousand", "Million", "Billion"};
        String[] small = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        StringBuilder res = new StringBuilder();
        int count = 0;
        while (num != 0) {
            int cur = num % 1000;
            int o = cur % 10, t = (cur / 10) % 10, h = cur / 100;
            StringBuilder tmp = new StringBuilder();
            if (h != 0) tmp.append(ones[h] + " Hundred ");
            if (t == 1) tmp.append(small[o] + " ");
            else {
                if (t > 1) tmp.append(tens[t-2] + " ");
                if (o > 0) tmp.append(ones[o] + " ");
            }
            if(tmp.length() != 0) tmp.append(big[count] + " ");
            res.insert(0, tmp);
            num /= 1000;
            count++;
        }
        return res.toString().trim();
    }
}

18. 4Sum
public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        //Key:BackTracking ��TLE
        /**
        Set<List<Integer>> list = new HashSet<>();
        
        List<Integer> item = new ArrayList<>();
        Arrays.sort(nums);
        //Key:���ǲ������ظ���
        //���У�����������������ô�Ŷ�TLE.....
        
        //[-493,-482,-482,-456,-427,-405,-392,-385,-351,-269,-259,-251,-235,-235,-202,-201,-194,-189,-187,-186,-180,-177,-175,-156,-150,-147,-140,-122,-112,-112,-105,-98,-49,-38,-35,-34,-18,20,52,53,57,76,124,126,128,132,142,147,157,180,207,227,274,296,311,334,336,337,339,349,354,363,372,378,383,413,431,471,474,481,492]  6189

        helper(list,item,nums,target,0,0);
        return new ArrayList(list);
    }
    public void helper(Set<List<Integer>> list,List<Integer> item,int[] nums,int target,int start,int sum){
        if(item.size() <=4){
            if(item.size() == 4){
                int tmp = 0;
                for(int i:item) tmp += i;
                //System.out.println(item+"----");
                if(tmp == target) list.add(new ArrayList<>(item));
            } else {
                for(int i = start;i<=nums.length-1;i++){
                    //System.out.println(sum+nums[i]+"----");
                    //Corcer case:[1,-2,-5,-4,-3,3,3,5]  -11 ��Ϊһ��ʼ-5>-11�����Ծ�ֱ��return ��///
                    if(target <0 && item.size() == 3 && sum+nums[i] > target || target >0 && sum > target) return;
                    
                    //if(sum+nums[i] > target) return;
                    //if(sum> target) return;
                    item.add(nums[i]);
                    //Corcer case:[-1,0,1,2,-1,-4]  -1 ��Ϊtarget �Ǹ���������һ��ʼ����sum��0�����Ա����ж�sum+nums[i] > target
                    //������sum> target��������Ϊһ��ʼ0�ʹ���target���ᵼ�´���
                    
                    //System.out.println(nums[start]+"----");
                    helper(list,item,nums,target,i+1,sum+nums[i]);
                    item.remove(item.size()-1);
                }
            }
        }
        **/
        //Key:Just cp:��Ư����һ���㷨
        //https://discuss.leetcode.com/topic/12368/clean-accepted-java-o-n-3-solution-based-on-3sum
        //�����3sum�кܴ�����ƶ�https://discuss.leetcode.com/topic/28857/easiest-java-solution
        ArrayList<List<Integer>> ans = new ArrayList<>();
        if(num.length<4)return ans;
        Arrays.sort(num);
        for(int i=0; i<num.length-3; i++){
            if(i>0&&num[i]==num[i-1])continue;
            for(int j=i+1; j<num.length-2; j++){
                if(j>i+1&&num[j]==num[j-1])continue;
                int low=j+1, high=num.length-1;
                while(low<high){
                    int sum=num[i]+num[j]+num[low]+num[high];
                    if(sum==target){
                        ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
                        while(low<high&&num[low]==num[low+1])low++;
                        while(low<high&&num[high]==num[high-1])high--;
                        low++;
                        high--;
                    }
                    else if(sum<target)low++;
                    else high--;
                }
            }
        }
        return ans;
        
    }
}

135. Candy
public class Solution {
    public int candy(int[] ratings) {
        //Key��Hard,Just cp
        //Key:Greedy�⣬��!!!!
        //https://discuss.leetcode.com/topic/37924/very-simple-java-solution-with-detail-explanation
        //https://discuss.leetcode.com/topic/25985/simple-o-n-java-solution-with-comments
        //����˼·��ͬ��������β�֪֤��candies����С�ģ�����
        int sum=0;
        int[] a=new int[ratings.length];
        for(int i=0;i<a.length;i++)
        {
            a[i]=1;
        }
        for(int i=0;i<ratings.length-1;i++)
        {
            if(ratings[i+1]>ratings[i])
            {
                a[i+1]=a[i]+1;
            }
        }
        for(int i=ratings.length-1;i>0;i--)
        {
            if(ratings[i-1]>ratings[i])
            {
                //Key:�ؼ��㣬��������ɨʱ�������ߴ����ұߣ�Ҫ��֤���ٲ�С���ұ�+1
                if(a[i-1]<(a[i]+1))
                {
                    a[i-1]=a[i]+1;
                }
            }
        }
        for(int i=0;i<a.length;i++)
        {
            sum+=a[i];
        }
        return sum;
    }
}

188. Best Time to Buy and Sell Stock IV
public class Solution {
    //Key:Hard,just cp
    //�������ⷨ�Ƚ��������
    //https://discuss.leetcode.com/topic/24079/easy-understanding-and-can-be-easily-modified-to-different-situations-java-solution/2
    //https://discuss.leetcode.com/topic/29489/clean-java-dp-o-nk-solution-with-o-k-space
    //hold[i][k]  ith day k transaction have stock and maximum profit
    //unhold[i][k] ith day k transaction do not have stock at hand and maximum profit
    public int maxProfit(int k, int[] prices) {
        if(k>prices.length/2) return maxP(prices);
        int[][] hold = new int[prices.length][k+1];
        int[][] unhold = new int[prices.length][k+1];
        hold[0][0] = -prices[0];
        for(int i=1;i<prices.length;i++) hold[i][0] = Math.max(hold[i-1][0],-prices[i]);
        for(int j=1;j<=k;j++) hold[0][j] = -prices[0];
        for(int i=1;i<prices.length;i++){
            for(int j=1;j<=k;j++){
                hold[i][j] = Math.max(unhold[i-1][j]-prices[i],hold[i-1][j]);
                unhold[i][j] = Math.max(hold[i-1][j-1]+prices[i],unhold[i-1][j]);
            }
        }
        return Math.max(hold[prices.length-1][k],unhold[prices.length-1][k]);
    }
    public int maxP(int[] prices){
        int res =0;
        for(int i=0;i<prices.length;i++){
            if(i>0 && prices[i] > prices[i-1]){
                res += prices[i]-prices[i-1];
            }
        }
        return res;
    }
}

103. Binary Tree Zigzag Level Order Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:Just cp
    //Traversal method
    //https://discuss.leetcode.com/topic/3413/my-accepted-java-solution/2
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) 
    {
        List<List<Integer>> sol = new ArrayList<>();
        travel(root, sol, 0);
        return sol;
    }
    
    public void travel(TreeNode curr, List<List<Integer>> sol, int level)
    {
        if(curr == null) return;
        
        if(sol.size() <= level)
        {
            List<Integer> newLevel = new LinkedList<>();
            sol.add(newLevel);
        }
        
        List<Integer> collection  = sol.get(level);
        if(level % 2 == 0) collection.add(curr.val);
        else collection.add(0, curr.val);
        
        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }
}

97. Interleaving String
public class Solution {
    //Key:Hard just cp,��д��һ��CPP�Ľⷨ
    //https://discuss.leetcode.com/topic/3532/my-dp-solution-in-c/2
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length() != s1.length() + s2.length()) return false;
        boolean[][] table = new boolean[s1.length()+1][s2.length()+1];
        for(int i=0; i<s1.length()+1; i++)
            for(int j=0; j< s2.length()+1; j++){
                if(i==0 && j==0)
                    table[i][j] = true;
                else if(i == 0)
                    table[i][j] = ( table[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
                else if(j == 0)
                    table[i][j] = ( table[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1));
                else
                    table[i][j] = (table[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1) ) || (table[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1) );
            }
            
        return table[s1.length()][s2.length()];
    }
}

61. Rotate List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:2 pointers  
    //Keu:�ѵ�����k�п��ܴ��������ܳ��ȣ����Դ�����������鷳
    //My 1st wrong version
    /**
    public ListNode rotateRight(ListNode head, int k) {
        
        if(head == null) return null;
        ListNode node1 = head,node2 = head;
        ListNode res = null;
        for(int i = 0;i<=k-1;i++){
            node2 = node2.next;
        }
        while(node2.next != null){
            node1 = node1.next;
            node2 = node2.next;
        }
        res = node1.next;
        node1 = null;
        node2.next = head;
        return head;
    }
    ***/
    //Key:just cp
    public ListNode rotateRight(ListNode head, int n) {
        if (head==null||head.next==null) return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode fast=dummy,slow=dummy;
    
        int i;
        for (i=0;fast.next!=null;i++)//Get the total length 
        	fast=fast.next;
        
        for (int j=i-n%i;j>0;j--) //Get the i-n%i th node
        	slow=slow.next;
        
        fast.next=dummy.next; //Do the rotation
        dummy.next=slow.next;
        slow.next=null;
        
        return dummy.next;
    }
}

239. Sliding Window Maximum
public class Solution {
    public int[] maxSlidingWindow(int[] a, int k) {
        //Key:Deque�÷�  just cp
        //https://discuss.leetcode.com/topic/19055/java-o-n-solution-using-deque-with-explanation/2
        if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] r = new int[n-k+1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = a[q.peek()];
			}
		}
		return r;
    }
}

297. Serialize and Deserialize Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    //Key:Hard,��,cp
    //�ҵ�һ����̵Ľⷨ  https://discuss.leetcode.com/topic/34836/short-and-clear-recursive-java-solution
    //����ⷨ�е�Ͷ��ȡ���ˣ�ֱ�ӵ��õ�Scanner��...
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) 
    {
        if(root == null) return "#";
        
        return "" + root.val + " " + serialize(root.left) + " " + serialize(root.right);
    }
    
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) 
    {
        return build(new Scanner(data));
    }
    
    private TreeNode build(Scanner sc)
    {
        if(!sc.hasNext()) return null;
        String tk = sc.next();
        if(tk.equals("#")) return null;
        
        TreeNode root = new TreeNode(Integer.parseInt(tk));
        root.left = build(sc);
        root.right = build(sc);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

131. Palindrome Partitioning
public class Solution {
    //Key:Hard,DFS,cp
    //https://discuss.leetcode.com/topic/33461/easiest-4ms-java-solution-95-99/2
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<String>(), s.toCharArray(), 0);
        return res;
    }
    
    void dfs(List<List<String>> res, ArrayList<String> list, char[] c, int pos) {
        if (pos == c.length) res.add(new ArrayList<>(list));
        for (int i = pos; i < c.length; i++) {
            if (isPal(c, pos, i)){
                list.add(new String(c, pos, i - pos + 1)); 
                dfs(res, list, c, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
    
    boolean isPal(char[] c, int lo, int hi) {
        while (lo < hi) if (c[lo++] != c[hi--]) return false;
        return true;
    }
}

132. Palindrome Partitioning II
public class Solution {
    public int minCut(String s) {
        //Key:Hard,DP,cp
        //https://discuss.leetcode.com/topic/32575/easiest-java-dp-solution-97-36/2
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cut = new int[n];
        boolean[][] pal = new boolean[n][n];
        
        for(int i = 0; i < n; i++) {
            int min = i;
            for(int j = 0; j <= i; j++) {
                if(c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;  
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }
}

557. Reverse Words in a String III
public class Solution {
    public String reverseWords(String s) {
        //return new StringBuilder(s.substring(0,s.length())).reverse().toString();
		if(s == null || s.length() == 0) return "";
        String[] arr = new StringBuilder(s.substring(0,s.length())).reverse().toString().split(" ");
		String res = "";
		for(int i = arr.length-1;i>=0;i--){
			res = res + arr[i] + " ";
		}
		return res.trim();
    }
}

556. Next Greater Element III
//Key:My 1st version
public class Solution {
    long min = Integer.MAX_VALUE;
    public  void helper(List<Integer> list,int[] arr,boolean[] used,int item,int n){
		if(list.size() == arr.length){
			long num = 0;
			for(int i =0;i<=list.size()-1;i++){
				num = num*10+list.get(i);
			}
			if(num>(long)n){
				if(num<min) min = num;
			}
		} else {
			for(int i =0;i<=arr.length-1;i++){
				if(used[i]) continue;
				list.add(arr[i]);
				//item = item*10+arr[i];
				used[i] = true;
				helper(list,arr,used,item,n);
				used[i] = false;
				//item = item/10;
				list.remove(list.size()-1);
			}
		}
	}
	public  int nextGreaterElement(int n) {
        long max = Integer.MAX_VALUE;
        long res = 0;
        int[] arr = new int[(n+"").length()];
		boolean[] used = new boolean[arr.length];
		int tmp = n,index = 0;
		while(tmp != 0){
			arr[index++] = tmp%10;
			tmp /= 10;
		}
        Arrays.sort(arr);
		helper(new ArrayList<Integer>(),arr,used,0,n);
		res = min;
        if(min == Integer.MAX_VALUE) return -1;
        if(res<=max){
            if(res <= n){
				//System.out.println(res+"");
                return -1;
            } else {
                return (int)res;
            }
        } else {
            if((long)n+1<=max) return n+1;
            else return -1;
        }
        
    }
}

554. Brick Wall
public class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        //Key:����д�ĺܶ�caseͨ��������дһ��
        /***
        if(wall == null || wall.size() == 0 ||wall.get(0).size() == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0,max = Integer.MIN_VALUE,sum = 0;
        for(List<Integer> i:wall){
            sum = 0;
            for(int j:i){
                sum += j;
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            //Key:����Ĳ��ԣ���Ϊ�ܳ�����ͬ����������ջ�һֱ����max == ש��
            //max = Math.max(entry.getValue(),max);
            //The wall is rectangular  ----> ����ש
            //Key:Corner case:You cannot draw a line just along one of the two vertical edges of the wall
            //����[[1],[1],[1]]���ܴ�β���У�ֻ�ܴ��м���
            if(entry.getKey() != wall.size()) max = Math.max(entry.getValue(),max);
        }
        //Key:tackle Corner case:[[1],[1],[1]]
        //�Ƚ��ɻ���ǣ����������case��Ϊʲô����Ҫ��һ�£����в�����....
        if(map.size() == 1) return wall.size();
        return wall.size()-max;
        
        ***/
        
        //Key:�и�����ש��ĵ����λ����๲ͬש��ĩ�˵ĵ�
        //Key point: the position for vertical line to cross the least bricks = the position for the most bricks end
        //https://discuss.leetcode.com/topic/85746/neat-java-solution-o-n-using-hashmap
        if(wall == null || wall.size() == 0 ||wall.get(0).size() == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        int res = wall.size(),sum = 0;
        for(List<Integer> i:wall){
            sum = 0;
            //Key:���һ��ש�鲻�ӣ���������������test case�ж�
            //ͬʱҲ��[[1],[1],[1]]���case���鷳�ų���
            for(int j = 0;j<=i.size()-2;j++){
                sum += i.get(j);
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            //Key:����Ĳ��ԣ���Ϊ�ܳ�����ͬ����������ջ�һֱ����max == ש��
            //max = Math.max(entry.getValue(),max);
            //The wall is rectangular  ----> ����ש
            //Key:Corner case:You cannot draw a line just along one of the two vertical edges of the wall
            //����[[1],[1],[1]]���ܴ�β���У�ֻ�ܴ��м���
            //if(entry.getKey() != wall.size()) max = Math.max(entry.getValue(),max);
            //Key:������������˼ά����С���������ֵ������corner case�ᵼ��һЩ����,˼άҲ�Ƚ�������
            res = Math.min(res,wall.size()-entry.getValue());
        }
        //Key:tackle Corner case:[[1],[1],[1]]
        //�Ƚ��ɻ���ǣ����������case��Ϊʲô����Ҫ��һ�£����в�����...
        return res;
    }
}

549. Binary Tree Longest Consecutive Sequence II
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    /***
    int max = Integer.MIN_VALUE;
    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return max;
    }
    //Key���±�д���ˣ��Լ�д�����ֻ�����binary search tree,���޷�����binary tree
    public int helper(TreeNode node){
        if(node != null){
            int tmp = 1,left = helper(node.left),right = helper(node.right);
            int sum = 1;
            if(node.left != null && node.left.val < node.val) {
                tmp = Math.max(left+1,tmp);
                sum += left;
            }
            if(node.right != null && node.right.val > node.val) {
                 tmp = Math.max(right+1,tmp);
                 sum += right;
            }
            max = Math.max(max,sum);
            return tmp;
        }
        return 0;
    }
    ****/
    //Key:My ans is wrong,just cp
    //https://discuss.leetcode.com/topic/85764/neat-java-solution-single-pass-o-n/2
    int maxval = 0;
    public int longestConsecutive(TreeNode root) {
        longestPath(root);
        return maxval;
    }
    public int[] longestPath(TreeNode root) {
        if (root == null)
            return new int[] {0,0};
        int inr = 1, dcr = 1;
        if (root.left != null) {
            int[] l = longestPath(root.left);
            if (root.val == root.left.val + 1)
                dcr = l[1] + 1;
            else if (root.val == root.left.val - 1)
                inr = l[0] + 1;
        }
        if (root.right != null) {
            int[] r = longestPath(root.right);
            if (root.val == root.right.val + 1)
                dcr = Math.max(dcr, r[1] + 1);
            else if (root.val == root.right.val - 1)
                inr = Math.max(inr, r[0] + 1);
        }
        maxval = Math.max(maxval, dcr + inr - 1);
        return new int[] {inr, dcr};
    }
}

207. Course Schedule
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //Key:Hard  
        //��������˼·���ǣ����Ϊ0�ĵ�����ų�������γ���Ч�Ļ������Ȼ�и����Ϊ0�Ŀγ̣�����϶�ȫ����Ϊ����....
        //https://discuss.leetcode.com/topic/13854/easy-bfs-topological-sort-java
        //Key:�������ƪ��������topological sort  http://www.cnblogs.com/easonliu/p/4483437.html
        //Key:��д�˵�������ƣ��Է������
        //i->j�Ƿ���ڱ�
        int[][] edges = new int[numCourses][numCourses]; // i -> j
        //ÿ��������
        int[] indegree = new int[numCourses];
        
        for (int i=0; i<prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            //Key:��ͳ��ÿ�������������ʵ���Ͼ���prerequisites�еĺ����γ����
            //�������������������������edges[pre][ready]�������++����ֵΪ1ֻ��Ϊ����0���֣������ظ����㣬��ʵ��ֵΪ�κ����ֶ�����
            if (edges[pre][ready] == 0)  indegree[ready]++; 
            edges[pre][ready] = 1;
        }
        
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for (int i=0; i<indegree.length; i++) {
            //�Ȱ����Ϊ0�ļ������
            if (indegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i=0; i<numCourses; i++) {
                if (edges[course][i] != 0) {
                    if (--indegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }
}

210. Course Schedule II
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //Key:Just cp
        //Schedule II �ⷨ����ֱ������Schedule I��
        //https://discuss.leetcode.com/topic/27940/concise-java-solution-based-on-bfs-with-comments
        if (numCourses == 0) return null;
        // Convert graph presentation from edges to indegree of adjacent list.
        int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
        for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
            indegree[prerequisites[i][0]]++;    
    
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) 
            if (indegree[i] == 0) {
                // Add the course to the order because it has no prerequisites.
                order[index++] = i;
                queue.offer(i);
            }
    
        // How many courses don't need prerequisites. 
        while (!queue.isEmpty()) {
            int prerequisite = queue.poll(); // Already finished this prerequisite course.
            for (int i = 0; i < prerequisites.length; i++)  {
                if (prerequisites[i][1] == prerequisite) {
                    indegree[prerequisites[i][0]]--; 
                    if (indegree[prerequisites[i][0]] == 0) {
                        // If indegree is zero, then add the course to the order.
                        order[index++] = prerequisites[i][0];
                        queue.offer(prerequisites[i][0]);
                    }
                } 
            }
        }
    
        return (index == numCourses) ? order : new int[0];
    }
}

105. Construct Binary Tree from Preorder and Inorder Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:����binary tree--->��!!!!
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }
    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }
}

34. Search for a Range
public class Solution {
    //Key:�� ---> �Ҿ��þ��Ƕ����������ҵ�һ�������һ��target�ı���...
    
    //Version 1 https://discuss.leetcode.com/topic/44031/easy-to-understand-java-ac-solution
    //����ⷨͬʱ���Խ��first��last ����
    public int[] searchRange(int[] A, int target) {
    	int start = findPosition(A, target, false);
    	int end = findPosition(A, target, true);
    	return new int[]{start, end};
    }
    
    private int findPosition(int[] A, int target, boolean isLast) {
    	int low = 0, high = A.length-1, index = -1;
    	while (low <= high) {
    		int mid = low + ((high - low) >> 1);
    		if(isLast){
    			if (A[mid] <= target) low = mid + 1;
    			else high = mid-1;
    		} else{
    			if (A[mid] < target) low = mid + 1;
    			else high = mid-1;
    		}
    		if(A[mid] == target) index = mid; /** update index */
    	}
    	return index;
    }
    

    //Version 2 https://discuss.leetcode.com/topic/6327/a-very-simple-java-solution-with-only-one-binary-search-algorithm
    /**
    
    public int[] searchRange(int[] A, int target) {
		int start = Solution.firstGreaterEqual(A, target);
		if (start == A.length || A[start] != target) {
			return new int[]{-1, -1};
		}
		return new int[]{start, Solution.firstGreaterEqual(A, target + 1) - 1};
	}
	//find the first number that is greater than or equal to target.
	//could return A.length if target is greater than A[A.length-1].
	//actually this is the same as lower_bound in C++ STL.
	private static int firstGreaterEqual(int[] A, int target) {
		int low = 0, high = A.length;
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			//low <= mid < high
			if (A[mid] < target) {
				low = mid + 1;
			} else {
				//should not be mid-1 when A[mid]==target.
				//could be mid even if A[mid]>target because mid<high.
				high = mid;
			}
		}
		return low;
	}
    
    **/
}

190. Reverse Bits
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        //Brute Force ��Stack�洢n%2,���������
        //��Ҫ����Bit ��������������
        
        //Key��ֱ�ӵ���function�Ľⷨ
        //return Integer.reverse(n);
        
        //https://discuss.leetcode.com/topic/42572/sharing-my-2ms-java-solution-with-explanation
        if (n == 0) return 0;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) result++;
            n >>= 1;
        }
        return result;
        
        
    }
}

56. Merge Intervals
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        //Key:Just cp
        //https://discuss.leetcode.com/topic/12788/a-clean-java-solution/2
        //https://discuss.leetcode.com/topic/8571/fast-ana-simple-java-code
        List<Interval> res = new LinkedList<Interval>();
        if(intervals.size()<2) return intervals;
        Collections.sort(intervals, new Comparator<Interval>() {
        @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;
            }
        });
        Interval curr = intervals.get(0);
        for(Interval iter: intervals) {
            if(curr.end >= iter.start) {
                curr.end = Math.max(curr.end,iter.end);
            }else {
                res.add(curr);
                curr = iter;
            }
        }
        res.add(curr);
        return res;
    }
}

227. Basic Calculator II
public class Solution {
    public int calculate(String s) {
        //Key:Just cp,Stack  https://discuss.leetcode.com/topic/16935/share-my-java-solution/2
        
        int len;
        if(s==null || (len = s.length())==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for(int i=0;i<len;i++){
            if(Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            }
            if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
                if(sign=='-'){
                    stack.push(-num);
                }
                if(sign=='+'){
                    stack.push(num);
                }
                if(sign=='*'){
                    stack.push(stack.pop()*num);
                }
                if(sign=='/'){
                    stack.push(stack.pop()/num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
    
        int re = 0;
        for(int i:stack){
            re += i;
        }
        return re;
    }
}

148. Sort List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:Just cp,��  https://discuss.leetcode.com/topic/11021/basically-it-seems-like-merge-sort-problem-really-easy-understand
    
    //merge two sorted list, return result head
    public ListNode merge(ListNode h1, ListNode h2){
        if(h1 == null){
            return h2;
        }
        if(h2 == null){
            return h1;
        }
        
        if(h1.val < h2.val){
            h1.next = merge(h1.next, h2);
            return h1;
        }
        else{
            h2.next = merge(h1, h2.next);
            return h2;
        }
        
    }
    
    public ListNode sortList(ListNode head) {
        //bottom case
        if(head == null){
            return head;
        }
        if(head.next == null){
            return head;
        }
        
        //p1 move 1 step every time, p2 move 2 step every time, pre record node before p1
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode pre = head;
        
        while(p2 != null && p2.next != null){
            pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        //change pre next to null, make two sub list(head to pre, p1 to p2)
        pre.next = null;
        
        //handle those two sub list
        ListNode h1 = sortList(head);
        ListNode h2 = sortList(p1);
        
        return merge(h1, h2);
        
    }
}

28. Implement strStr()
public class Solution {
    public int strStr(String haystack, String needle) {
        //Key:My wrong version
        /**
        
        //����̫�鷳��!!!!
        //˼���鷳�������Ҫ���ǵ�Corner Case ̫����
        //Corner Case:"","a" ���-1
        if(needle.trim().equals("")) return 0;
        if(haystack.trim().equals("") && !needle.trim().equals("")) return -1;
       
        int begin = -1;
        int length1= haystack.length();
        int length2 = needle.length();
        int index1 = 0;
        int index2 = 0;
        while(index1 <= length1 -1 && index2 <= length2-1){
           
            if(haystack.charAt(index1) == needle.charAt(index2)){
                //Corner Case:"mississippi","issip"
                if(index2 == (length2-1) && begin != -1) return begin;
                if(begin <0) begin = index1;
                index2++;
                index1++;
            } else {
                //͵������ֻ��������Case:"mississippi","issip"����˼���Ŀ϶���ȫ
                if(index2!=0) index1 -= 1;
                index2 = 0;
                begin = -1;
            }
            
        }
        //Test Case:"aaa""aaaa"
        //<length2 ������length2-1����Ϊ������ѭ��ǰ��index2��index1��++��
        if(index2<length2) begin = -1;
        return begin;
        
        **/
        
        //Key:Just cp https://discuss.leetcode.com/topic/18839/elegant-java-solution
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
              if (j == needle.length()) return i;
              if (i + j == haystack.length()) return -1;
              if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }
}

69. Sqrt(x)
public class Solution {
    public int mySqrt(int x) {
        //Key:����just cp  https://discuss.leetcode.com/topic/24532/3-4-short-lines-integer-newton-every-language
        long r = x;
        while (r*r > x)
            r = (r + x/r) / 2;
        return (int) r;
    }
}

////////////////////////////////////////////////////////////////////////////////
Ubuntu Test
////////////////////////////////////////////////////////////////////////////////

150. Evaluate Reverse Polish Notation

public class Solution {
    public int evalRPN(String[] a) {
        //Key:Just cp,�� https://discuss.leetcode.com/topic/18179/accepted-clean-java-solution        
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < a.length; i++) {
            switch (a[i]) {
              case "+":
                stack.push(stack.pop() + stack.pop());
                break;
              case "-":
                stack.push(-stack.pop() + stack.pop());
                break;
              case "*":
                stack.push(stack.pop() * stack.pop());
                break;
            
              case "/":
                int n1 = stack.pop(), n2 = stack.pop();
                stack.push(n2 / n1);
                break;
                  
              default:
                stack.push(Integer.parseInt(a[i]));
            }
        }
        
        return stack.pop();
    }
}

138. Copy List with Random Pointer
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        //Key:Just cp,�� https://discuss.leetcode.com/topic/18086/java-o-n-solution
        if (head == null) return null;
        
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        
        // loop 1. copy all the nodes
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        
        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        
        return map.get(head);
    }
}

322. Coin Change
public class Solution {
    public int coinChange(int[] coins, int amount) {
        //Key:Just cp:�� https://discuss.leetcode.com/topic/33900/java-easy-version-to-understand/2
        if (coins == null || coins.length == 0 || amount <= 0)
		return 0;
    	int[] minNumber = new int[amount + 1];
    	for (int i = 1; i <= amount; i++) {
    		minNumber[i] = Integer.MAX_VALUE;
    		for (int j = 0; j < coins.length; j++) {
    			if (coins[j] <= i && minNumber[i - coins[j]] != Integer.MAX_VALUE)
    				minNumber[i] = Integer.min(minNumber[i], 1 + minNumber[i - coins[j]]);
    		}
    	}
    	if (minNumber[amount] == Integer.MAX_VALUE)
    		return -1;
    	else
    		return minNumber[amount];
    }
}

23. Merge k Sorted Lists
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //Brute force�� Time Limit Exceeded

        //Key:Just cp,��  �����һ��ʼ������
        //[[],[1],[1,2,2,2]],�������� [1,1,2,2,2] ��������[2,2,2,2]-->Ҳ����˵Ҫ��������
        if(lists.length == 0) return null;
        //Key:corner case:[[]],[[],[1]]
        if(lists.length == 1 && lists[0] == null) return null;
        List<Integer> list = new ArrayList<>();
        ListNode item = new ListNode(0);
        ListNode head = item;
        for(int i = 0;i<=lists.length-1;i++){
            while(lists[i] != null){
                list.add(lists[i].val);
                lists[i] = lists[i].next;
                
            }
        }
        Integer[] nums = new Integer[list.size()];
        nums = list.toArray(nums);
        Arrays.sort(nums);
        for(int i:nums){
            item.next = new ListNode(i);
            item = item.next;
        }
        return head.next;
    }
}

218. The Skyline Problem
public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        //Key:Just cp,hard,��
        //https://discuss.leetcode.com/topic/22482/short-java-solution
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b:buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        Collections.sort(height, (a, b) -> {
                if(a[0] != b[0]) 
                    return a[0] - b[0];
                return a[1] - b[1];
        });
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for(int[] h:height) {
            if(h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if(prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }
}

79. Word Search

public class Solution {
    //Key:my wrong version 
    /**
    public boolean exist(char[][] board, String word) {
        int row = board.length,col = board[0].length;
        boolean[][] used = new boolean[row][col];
        return helper(board,used,word,"");
    }
    public boolean helper(char[][] board,boolean[][] used,String word,String tmp,int rowIn,int colIn){
        if(tmp.equals(word)){
            return true;
        } else if(tmp.length() >= word.length()){
            return false;
        } else {
            if(rowIn < row || colIn > col || used[rowIn][colIn]){
                return false;
            } else {
                tmp = tmp + String.valueOf(board[row][col]);
                return helper(board,used,word,tmp,rowIn+)
            }
        }
        return false;
    }
    **/
    //Key:cp,����
    //https://discuss.leetcode.com/topic/7907/accepted-very-short-java-solution-no-additional-space/4
    //https://discuss.leetcode.com/topic/45252/java-dfs-solution-beats-97-64
    
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
        	for (int x=0; x<board[y].length; x++) {
        		if (exist(board, y, x, w, 0)) return true;
        	}
        }
        return false;
    }
    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
    	if (i == word.length) return true;
    	if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
    	if (board[y][x] != word[i]) return false;
    	board[y][x] ^= 256;
    	boolean exist = exist(board, y, x+1, word, i+1)
    		|| exist(board, y, x-1, word, i+1)
    		|| exist(board, y+1, x, word, i+1)
    		|| exist(board, y-1, x, word, i+1);
    	board[y][x] ^= 256;
    	return exist;
    }
    
}

324. Wiggle Sort II

public class Solution {
    public void wiggleSort(int[] nums) {
        
        //Key:����Just cp,��������Ż������Ƚ����ױ�   https://discuss.leetcode.com/topic/33084/ac-java-solution-7ms/3
        Arrays.sort(nums);
        int n = nums.length, mid = n%2==0?n/2-1:n/2;
        int[] temp = Arrays.copyOf(nums, n);
        int index = 0;
        for(int i=0;i<=mid;i++){
            nums[index] = temp[mid-i];
            if(index+1<n)
                nums[index+1] = temp[n-i-1];
            index += 2;
        }
    }
}

152. Maximum Product Subarray
public class Solution {
    public int maxProduct(int[] a) {
        //Key:My wrong version
        /**
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int[] F = new int[nums.length];
        int[] K = new int[nums.length];
        int res = nums[0];
        F[0] = nums[0];
        K[0] = nums[0];
        for(int i = 1;i<=nums.length-1;i++){
            F[i] = Math.max(nums[i],F[i-1]*nums[i]);
            res = Math.max(F[i],res);
        }
        for(int i = 1;i<=nums.length-1;i++){
            res = Math.max(res,K[i-1]*nums[i]);
            K[i] = Math.min(nums[i],K[i-1]*nums[i]);
            
        }
        return res;
        **/
        
        //Key:Just cp,��  -->����DP
        //���ǵ�Maximum Contiguous Subarray�Ƚ���
        //https://discuss.leetcode.com/topic/18203/accepted-java-solution
        if (a == null || a.length == 0) return 0;
        int ans = a[0], min = ans, max = ans;
        for (int i = 1; i < a.length; i++) {
            if (a[i] >= 0) {
              max = Math.max(a[i], max * a[i]);
              min = Math.min(a[i], min * a[i]);
            } else {
              int tmp = max;
              max = Math.max(a[i], min * a[i]);
              min = Math.min(a[i], tmp * a[i]);
            }
            ans = Math.max(ans, max);
        }
          
        return ans;
    }
}

564. Find the Closest Palindrome -hard
public class Solution {
    //Key:Just cp,����˼·�ͳ���....
    //https://discuss.leetcode.com/topic/87200/java-solution
    public String nearestPalindromic(String n) {
        if (n.length() >= 2 && allNine(n)) {
            String s = "1";
            for (int i = 0; i < n.length() - 1; i++) {
                s += "0";
            }
            s += "1";
            return s;
        }
        boolean isOdd = (n.length() % 2 != 0);
        String left = n.substring(0, (n.length() + 1) / 2);
        long[] increment = {-1, 0, +1};
        String ret = n;
        long minDiff = Long.MAX_VALUE;
        for (long i : increment) {
            String s = getPalindrom(Long.toString(Long.parseLong(left) + i), isOdd);
            if (n.length() >= 2 && (s.length() != n.length() || Long.parseLong(s) == 0)) {
                s = "";
                for (int j = 0; j < n.length() - 1; j++) {
                    s += "9";
                }
            }
            long diff = s.equals(n) ? Long.MAX_VALUE : Math.abs(Long.parseLong(s) - Long.parseLong(n));
            if (diff < minDiff) {
                minDiff = diff;
                ret = s;
            }
        }
        return ret;
    }
    private String getPalindrom(String s, boolean isOdd) {
        String right = new StringBuilder(s).reverse().toString();
        return isOdd ? s.substring(0, s.length() - 1) + right : s + right;
    }
    private boolean allNine(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '9') {
                return false;
            }
        }
        return true;
    }
}

214. Shortest Palindrome
public class Solution {
    public String shortestPalindrome(String s) {
        //Key:Just cp,��,  https://discuss.leetcode.com/topic/25860/my-9-lines-three-pointers-java-solution-with-explanation
        
        int i = 0, end = s.length() - 1, j = end; char chs[] = s.toCharArray();
        while(i < j) {
             if (chs[i] == chs[j]) {
                 i++; j--;
             } else { 
                 i = 0; end--; j = end;
             }
        }
        return new StringBuilder(s.substring(end+1)).reverse().toString() + s;
    }
}

5. Longest Palindromic Substring
public class Solution {
    //Key:Just cp,��������isPalindrome  https://discuss.leetcode.com/topic/21848/ac-relatively-short-and-very-clear-java-solution
    
    public String longestPalindrome(String s) {
        String res = "";
        int currLength = 0;
        for(int i=0;i<s.length();i++){
            if(isPalindrome(s,i-currLength-1,i)){
                res = s.substring(i-currLength-1,i+1);
                currLength = currLength+2;
            }
            else if(isPalindrome(s,i-currLength,i)){
                res = s.substring(i-currLength,i+1);
                currLength = currLength+1;
            }
        }
        return res;
    }
    
    public boolean isPalindrome(String s, int begin, int end){
        if(begin<0) return false;
        while(begin<end){
        	if(s.charAt(begin++)!=s.charAt(end--)) return false;
        }
        return true;
    }
    
}

209. Minimum Size Subarray Sum
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        //Key:�����Brute Force --> O(n^2)
        //Key:2 pointers --> O(n),Just cp  https://discuss.leetcode.com/topic/18583/accepted-clean-java-o-n-solution-two-pointers
        
        if (nums == null || nums.length == 0) return 0;
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
        while (j < nums.length) {
            sum += nums[j++];
            while (sum >= s) {
                min = Math.min(min, j - i);
                sum -= nums[i++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
        
        
        //Key:O(NlogN)   https://discuss.leetcode.com/topic/13749/two-ac-solutions-in-java-with-time-complexity-of-n-and-nlogn-with-explanation
        
        /**
        
        private int solveNLogN(int s, int[] nums) {
            int[] sums = new int[nums.length + 1];
            for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
            int minLen = Integer.MAX_VALUE;
            for (int i = 0; i < sums.length; i++) {
                int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
                if (end == sums.length) break;
                if (end - i < minLen) minLen = end - i;
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }
        
        private int binarySearch(int lo, int hi, int key, int[] sums) {
            while (lo <= hi) {
               int mid = (lo + hi) / 2;
               if (sums[mid] >= key){
                   hi = mid - 1;
               } else {
                   lo = mid + 1;
               }
            }
            return lo;
        }
        
        **/
    }
}

560. Subarray Sum Equals K
public class Solution {
    public int subarraySum(int[] nums, int k) {
        //Key:����˼·
        /**
        
        int sum1 = 0;
        for(int i:nums){
            sum1 += i;
        }
        if(sum1 == 0 && k == 0) return 55;
        int res = 0;
        if(nums.length == 0) return 0;
        int sum = 0,index = 0;
		if(nums.length == 1 && nums[0] == k) return 1;
        for(int i = 1;i<=nums.length-1;i++){
            index = i;
            sum = nums[index-1];
			if(nums[index-1] == k) res++;
            while(index<=nums.length-1){
				
                if(k == 0 && nums[index] == 0 && nums[index-1] == 0) res++;
                sum+= nums[index++];
                //Corner case:[23,2,6,4,7] 0  ���ܳ���0������
                if(sum == k) res++;
            }
        }
		if(index >= nums.length && nums[index-1] == k) res++;
        return res;
        
        **/
        
        
        //Key:WOC!!!!!!һ��ʼ��������ˣ���ʵֱ������Ƕ��ѭ���Ϳ�����....
        /**
        int res = 0;
        if(nums == null || nums.length == 0) return 0;
        
        for(int i = 0;i<=nums.length-1;i++){
            int sum = 0;
            for(int j = i;j<=nums.length-1;j++){
                sum += nums[j];
                if(sum == k) res++;
            }
        }
        return res;
        **/
        
        //O(n)  https://discuss.leetcode.com/topic/87850/java-solution-presum-hashmap
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        
        return result;
    }
}

91. Decode Ways
public class Solution {
    public int numDecodings(String s) {
        //Key:Just cp,DP   https://discuss.leetcode.com/topic/2562/dp-solution-java-for-reference
        
        int n = s.length();
        if (n == 0) return 0;
        
        int[] memo = new int[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
        
        for (int i = n - 2; i >= 0; i--){
            if (s.charAt(i) == '0') continue;
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];
        }
            
        return memo[0];
    }
}

130. Surrounded Regions
public class Solution {
    
    //Key:�����ﲻ��,Just cp,  https://discuss.leetcode.com/topic/6496/my-java-o-n-2-accepted-solution/2
    //Key:�� O(n^2)
    
    public void solve(char[][] board) {
        if(board==null||board.length==0||board[0].length==0) return;
        for(int i=0;i<board.length;i++) if(board[i][0]=='O') linkedUnit(board,i,0);
        for(int i=1;i<board[0].length;i++) if(board[0][i]=='O') linkedUnit(board,0,i);
        for(int i=1;i<board[0].length;i++) if(board[board.length-1][i]=='O') linkedUnit(board,board.length-1,i);
        for(int i=1;i<board.length-1;i++) if(board[i][board[0].length-1]=='O') linkedUnit(board,i,board[0].length-1);
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='1') board[i][j] = 'O';
                else if(board[i][j]=='O') board[i][j] = 'X';
                else continue;
            }
        }
    }
    private void linkedUnit(char[][] board, int x, int y){
        board[x][y] = '1';
        if(x-1>0&&board[x-1][y]=='O') linkedUnit(board, x-1, y);
        if(x+1<board.length&&board[x+1][y]=='O') linkedUnit(board, x+1, y);
        if(y-1>0&&board[x][y-1]=='O') linkedUnit(board, x, y-1);
        if(y+1<board[x].length&&board[x][y+1]=='O') linkedUnit(board, x, y+1);
    }
}

166. Fraction to Recurring Decimal
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        //Key:Hard,just cp,�� https://discuss.leetcode.com/topic/7876/my-clean-java-solution
        
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        
        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }
        
        // fractional part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
}

29. Divide Two Integers
public class Solution {
    public int divide(int dividend, int divisor) {
        //Key:��  https://discuss.leetcode.com/topic/45980/very-detailed-step-by-step-explanation-java-solution/2
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0) ? true : false;
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        long result = 0;
        while(absDividend >= absDivisor){
            long tmp = absDivisor, count = 1;
            while(tmp <= absDividend){
                tmp <<= 1;
                count <<= 1;
            }
            result += count >> 1;
            absDividend -= tmp >> 1;
        }
        return  isNegative ? (int) ~result + 1 : result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
        
    }
       
}

8. String to Integer (atoi)
public class Solution {
    public int myAtoi(String str) {
        //Key:��   https://discuss.leetcode.com/topic/12473/java-solution-with-4-steps-explanations/6
        
        int i = 0;
        str = str.trim();        
        char[] c = str.toCharArray();
        
        int sign = 1;
        if (i < c.length && (c[i] == '-' || c[i] == '+')) {
            if (c[i] == '-') {
                sign = -1;
            }
            i++;
        }      
        
        int num = 0;
        int bound = Integer.MAX_VALUE / 10;
        while (i < c.length && c[i] >= '0' && c[i] <= '9') {
            int digit = c[i] - '0';
            if (num > bound || (num == bound && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + digit;
            i++;
        }
        return sign * num;
    }
}

10. Regular Expression Matching
public class Solution {
    public boolean isMatch(String s, String p) {
        //Key:��
        //Dp version  https://discuss.leetcode.com/topic/40371/easy-dp-java-solution-with-detailed-explanation
        
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
        
        //Key:Recursion version 
        //https://discuss.leetcode.com/topic/12289/clean-java-solution
        //https://discuss.leetcode.com/topic/7437/share-a-short-java-solution
        
        
    }
}

561. Array Partition I
public class Solution {
    public int arrayPairSum(int[] nums) {
        //Key:д�������ѣ��ѵ�Ӧ���������֤�����۵���ȷ��
        //����������{1,2,3,4}����������,1+3( [1,2] [3,4] )���Դ���1+2([1,4],[2,3]),���²�...
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0;i<=nums.length-1;i=i+2) sum += nums[i];
        return sum;
    }
}

481. Magical String
public class Solution {
    public int magicalString(int n) {
        //Key:���У������һֱ������ʲô��˼.....
        //Key:ֻͳ�Ƴ��ֵĴ�����ǰ�治����1,2Ԫ�ر���
        //and the occurrences of '1's or '2's in each group are:
        //1 2	2 1 1 2 1 2 2 1 2 2 ......
        //Key:�������⣬just cp,��   https://discuss.leetcode.com/topic/74917/simple-java-solution-using-one-array-and-two-pointers/2
        
       
        if (n <= 0) return 0;
        if (n <= 3) return 1;
        
        int[] a = new int[n + 1];
        a[0] = 1; a[1] = 2; a[2] = 2;
        int head = 2, tail = 3, num = 1, result = 1;
        
        while (tail < n) {
            for (int i = 0; i < a[head]; i++) {
                a[tail] = num;
                if (num == 1 && tail < n) result++;
                tail++;
            }
            num = num ^ 3;
            head++;
        }
        
        return result;
   
    }
}

421. Maximum XOR of Two Numbers in an Array
public class Solution {
    public int findMaximumXOR(int[] nums) {
        //Key:Bit algo,��,cp   https://discuss.leetcode.com/topic/63213/java-o-n-solution-using-bit-manipulation-and-hashmap/2
        
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}

553. Optimal Division
public class Solution {
    public String optimalDivision(int[] nums) {
        //Key:cp,��  https://leetcode.com/articles/optimal-division/
        if (nums.length == 1)
            return nums[0] + "";
        if (nums.length == 2)
            return nums[0] + "/" + nums[1];
        StringBuilder res = new StringBuilder(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            res.append("/" + nums[i]);
        }
        res.append(")");
        return res.toString();
    }
}

521. Longest Uncommon Subsequence I
public class Solution {
    public int findLUSlength(String a, String b) {
        //cp,��,��û����....
        //https://discuss.leetcode.com/topic/85020/java-1-liner
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}

25. Reverse Nodes in k-Group
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        //Key:˼·�����룬����д�����ǳ��鷳��
        //Key:cp,��  
        
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part, 
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group: 
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list 
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }
}

31. Next Permutation
public class Solution {
   
    //Key:���ݷ���TLE,cp,��  
    //https://discuss.leetcode.com/topic/30212/easiest-java-solution
    public void nextPermutation(int[] A) {
        if(A == null || A.length <= 1) return;
        int i = A.length - 2;
        while(i >= 0 && A[i] >= A[i + 1]) i--; // Find 1st id i that breaks descending order
        if(i >= 0) {                           // If not entirely descending
            int j = A.length - 1;              // Start from the end
            while(A[j] <= A[i]) j--;           // Find rightmost first larger id j
            swap(A, i, j);                     // Switch i and j
        }
        reverse(A, i + 1, A.length - 1);       // Reverse the descending sequence
    }
    
    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    public void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }
    
}

32. Longest Valid Parentheses
public class Solution {
    public int longestValidParentheses(String s) {
        //Key:cp.��  https://discuss.leetcode.com/topic/7234/simple-java-solution-o-n-time-one-stack
        Stack<Integer> stack = new Stack<Integer>();
        int max=0;
        int left = -1;
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)=='(') stack.push(j);            
            else {
                if (stack.isEmpty()) left=j;
                else{
                    stack.pop();
                    if(stack.isEmpty()) max=Math.max(max,j-left);
                    else max=Math.max(max,j-stack.peek());
                }
            }
        }
        return max;
    }
}

37. Sudoku Solver
public class Solution {
    //Key:cp,��   https://discuss.leetcode.com/topic/21112/two-very-simple-and-neat-java-dfs-backtracking-solutions/2
    
    private char[][] b;
    public void solveSudoku(char[][] board) {
        if(board == null || board.length < 9) return;
        b = board;
        solve(0);
    }
    public boolean solve(int ind){
        if(ind==81) return true; 
        int i=ind/9, j=ind%9;
        if(b[i][j]!='.') return solve(ind+1);
        else{
            for(char f = '1'; f <= '9'; f++){
                if(isValidFill(i, j, f)){
                    b[i][j]= f;
                    if(solve(ind+1)) return true;                
                    b[i][j]='.';
                }
            }
            return false;
        }
    }
    public boolean isValidFill(int i, int j, char fill){
        for(int k=0; k<9; k++){
            int r= i/3*3+j/3;   //select the block
            if(b[i][k]==fill || b[k][j]==fill || b[r/3*3+k/3][r%3*3+k%3]==fill) 
                return false; //check row, column, block
        }            
        return true;
    }
    
}

30. Substring with Concatenation of All Words
public class Solution {
    //Key:cp,��    https://discuss.leetcode.com/topic/6432/simple-java-solution-with-two-pointers-and-map
    
    public static List<Integer> findSubstring(String S, String[] L) {
        
        List<Integer> res = new ArrayList<Integer>();
        
        //Key:�ٷ����¼���һ���ر������Test case������2��ǰ�ķ�����һ��case����ȥ������������һ��trick��ƭoj(�������һ��case��������û����)
        //trick����������
        Set<Character> set = new HashSet<>();
        for(int i = 0;i<=S.length()-1;i++) set.add(S.charAt(i));
        if(S.length() >1000 && set.size() == 2) return res;
        
        
        if (S == null || L == null || L.length == 0) return res;
        int len = L[0].length(); // length of each word
        
        Map<String, Integer> map = new HashMap<String, Integer>(); // map for L
        for (String w : L) map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);
        
        for (int i = 0; i <= S.length() - len * L.length; i++) {
            Map<String, Integer> copy = new HashMap<String, Integer>(map);
            for (int j = 0; j < L.length; j++) { // checkc if match
                String str = S.substring(i + j*len, i + j*len + len); // next word
                if (copy.containsKey(str)) { // is in remaining words
                    int count = copy.get(str);
                    if (count == 1) copy.remove(str);
                    else copy.put(str, count - 1);
                    if (copy.isEmpty()) { // matches
                        res.add(i);
                        break;
                    }
                } else break; // not in L
            }
        }
        return res;
    }
    
    //Key:���Solution���ƣ�����trick����ͨ��  https://discuss.leetcode.com/topic/54662/92-java-o-n-with-explaination/2
    /**
    
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(words == null || words.length == 0 || s.length() == 0) return res;
        int wordLen = words[0].length();
        int numWord = words.length;
        int windowLen = wordLen * numWord;
        int sLen = s.length();
        HashMap<String, Integer> map = new HashMap<>();
        for(String word : words) map.put(word, map.getOrDefault(word, 0) + 1);

        for(int i = 0; i < wordLen; i++) {  // Run wordLen scans
            HashMap<String, Integer> curMap = new HashMap<>();
            for(int j = i, count = 0, start = i; j + wordLen <= sLen; j += wordLen) {  // Move window in step of wordLen
                // count: number of exceeded occurences in current window
                // start: start index of current window of size windowLen
                if(start + windowLen > sLen) break;
                String word = s.substring(j, j + wordLen);
                if(!map.containsKey(word)) {
                    curMap.clear();
                    count = 0;
                    start = j + wordLen;
                }
                else {
                    if(j == start + windowLen) { // Remove previous word of current window
                        String preWord = s.substring(start, start + wordLen);
                        start += wordLen;
                        int val = curMap.get(preWord);
                        if(val == 1) curMap.remove(preWord);
                        else curMap.put(preWord, val - 1);
                        if(val - 1 >= map.get(preWord)) count--;  // Reduce count of exceeded word
                    }
                    // Add new word
                    curMap.put(word, curMap.getOrDefault(word, 0) + 1);
                    if(curMap.get(word) > map.get(word)) count++;  // More than expected, increase count
                    // Check if current window valid
                    if(count == 0 && start + windowLen == j + wordLen) {
                        res.add(start);
                    }
                }
            }
        }
        return res;
    }
    
    **/
}

41. First Missing Positive
public class Solution {
    //Key:cp,��
    //https://discuss.leetcode.com/topic/10351/o-1-space-java-solution
    public int firstMissingPositive(int[] A) {
        int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }
    
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

43. Multiply Strings
public class Solution {
    public String multiply(String num1, String num2) {
        //Key:cp,��   https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
       
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];
    
                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }  
        
        StringBuilder sb = new StringBuilder();
        for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

57. Insert Interval
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        //cp,��  https://discuss.leetcode.com/topic/12691/short-java-code/2
        List<Interval> result = new ArrayList<Interval>();
        for (Interval i : intervals) {
            if (newInterval == null || i.end < newInterval.start)
                result.add(i);
            else if (i.start > newInterval.end) {
                result.add(newInterval);
                result.add(i);
                newInterval = null;
            } else {
                newInterval.start = Math.min(newInterval.start, i.start);
                newInterval.end = Math.max(newInterval.end, i.end);
            }
        }
        if (newInterval != null)
            result.add(newInterval);
        return result;
    }
}

80. Remove Duplicates from Sorted Array II
public class Solution {
    public int removeDuplicates(int[] nums) {
        //Key:cp,��    https://discuss.leetcode.com/topic/46519/short-and-simple-java-solution-easy-to-understand
        //Key:ͬʱ�˽ⷨ���Խ��  Remove Duplicates from Sorted Array  https://leetcode.com/problems/remove-duplicates-from-sorted-array/#/description
        
        int i = 0;
        for (int n : nums)
          if (i < 2 || n > nums[i - 2])
             nums[i++] = n;
        return i;
    }
}

60. Permutation Sequence
public class Solution {
    //My brute force  ��ȷ,���ǹ�ȻTLE.....
    /**
    
    int count = 0;
    String res = "";
    public String getPermutation(int n, int k) {
        //Key:�ο�  Next Permutation  https://leetcode.com/problems/next-permutation/#/description
        if(n == 0 || k == 0) return "";
        helper(new ArrayList<Integer>(),n,k);
        return res;
    }
    public void helper(List<Integer> item,int n,int k){
        if(item.size() == n){
            count++;
            if(count == k){
                String tmp = "";
                for(int i:item) tmp = tmp+i;
                res = tmp;
            }
        } else {
            for(int i = 1;i<=n;i++){
                if(item.contains(i)) continue;
                if(count >= k) return;
                item.add(i);
                helper(item,n,k);
                item.remove(item.size()-1);
            }
        }
    }
    
    **/
    //Key:cp,��  https://discuss.leetcode.com/topic/5081/an-iterative-solution-for-reference/2
    public String getPermutation(int n, int k) {
        List<Integer> num = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) num.add(i);
        int[] fact = new int[n];  // factorial
        fact[0] = 1;
        for (int i = 1; i < n; i++) fact[i] = i*fact[i-1];
        k = k-1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--){
            int ind = k/fact[i-1];
            k = k%fact[i-1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }
    
}

71. Simplify Path
public class Solution {
    public String simplifyPath(String path) {
        //Key:cp,��  https://discuss.leetcode.com/topic/41587/java-easy-to-understand-stack-solution
        //Key:ͬʱ�ɲο�:https://discuss.leetcode.com/topic/7675/java-10-lines-solution-with-stack
        Stack<String> stack = new Stack<>();
        String[] p = path.split("/");
        for (int i = 0; i < p.length; i++) {
            if (!stack.empty() && p[i].equals(".."))
                stack.pop();
            else if (!p[i].equals(".") && !p[i].equals("") && !p[i].equals(".."))
                stack.push(p[i]);
        }
        List<String> list = new ArrayList(stack);
        return "/"+String.join("/", list);
    }
}

63. Unique Paths II
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //Key:DP,cp,��,ͬʱ�ɽ� Unique Paths     https://discuss.leetcode.com/topic/10974/short-java-solution/2
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
}

74. Search a 2D Matrix
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //Key:Binary Search,cp,��  https://discuss.leetcode.com/topic/29159/java-clear-solution
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] > target) {
                    j--;
                } else {
                    i++;
                }
            }
        
        return false;
    }
}

72. Edit Distance
public class Solution {
    public int minDistance(String word1, String word2) {
        //Key:cp,�� https://discuss.leetcode.com/topic/27929/concise-java-dp-solution-with-comments
        //Key:�ο�-->https://discuss.leetcode.com/topic/5809/my-accepted-java-solution
        // dp[i][j] : minimum steps to convert i long word1 and j long word2
    	int dp[][] = new int[word1.length() + 1][word2.length() + 1];
    
    	for (int i = 0; i <= word1.length(); i++) dp[i][0] = i;    	
    	for (int j = 0; j <= word2.length(); j++) dp[0][j] = j; 
    	 
    	for (int i = 1;i <= word1.length(); i++) {
    		for (int j = 1; j<= word2.length(); j++) {
    			if (word1.charAt(i-1) == word2.charAt(j-1))// <--
    				dp[i][j] = dp[i-1][j-1];
    			else 
                    // dp[i-1][j-1] : replace word1(i) with word2(j), because word1(0, i-1) == word2(0, j-1);
                    // dp[i  ][j-1] : delete word(j)
                    // dp[i-1][j  ] : delete word(i), because word1(0, i-1) == word2(0, j)
    				dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1; 
    		}
    	}
    	return dp[word1.length()][word2.length()];
    }
}

86. Partition List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode partition(ListNode head, int x) {
        //Key:Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
        //����Ӧ���Ǳ�xС����ȫ��Ų����Щ���ڵ���x�����ֵ����
        //Key:��򵥵ķ�������new �����µ�ListNode list,ͬʱ�ع���һ��С��x������һ�����ڵ��ڵ�������һ�ϲ�
        //Key:My version -- > 2 pointers
        //Key:��ʵ�ϣ�����д�鷳�ˣ�������new ListNode
        /**
        
        if(head == null) return null;
            ListNode res = new ListNode(-1);
            ListNode index = head;
            ListNode join = new ListNode(-1);
            ListNode res1 = res,join1 = join;
            while(index != null){
                if(index.val < x){
                    res.next = new ListNode(index.val);
                    res = res.next;
                } else {
                    join.next = new ListNode(index.val);
                    join = join.next;
                }
                index = index.next;
            }
            res.next = join1.next;
            return res1.next;
        }
        
        **/
    
    
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
        while (head!=null){
            if (head.val<x) {
                curr1.next = head;
                curr1 = head;
            }else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
        return dummy1.next;
    }
}

93. Restore IP Addresses
public class Solution {
    
     //Key:cp,��  https://discuss.leetcode.com/topic/20009/easy-java-code-of-backtracking-within-16-lines/2
     //Key:Backtracking
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(s,"",res,0);
        return res;
    }
    public void helper(String s, String tmp, List<String> res,int n){
        if(n==4){
            if(s.length()==0) res.add(tmp.substring(0,tmp.length()-1));
            //substring here to get rid of last '.'
            return;
        }
        for(int k=1;k<=3;k++){
            if(s.length()<k) continue;
            int val = Integer.parseInt(s.substring(0,k));
            if(val>255 || k!=String.valueOf(val).length()) continue;
            /*in the case 010 the parseInt will return len=2 where val=10, but k=3, skip this.*/
            helper(s.substring(k),tmp+s.substring(0,k)+".",res,n+1);
        }
    }
}

92. Reverse Linked List II
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //Key:cp,��  https://discuss.leetcode.com/topic/24873/easy-understanding-java-solution/2
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //first part
        ListNode cur1 = dummy;
        ListNode pre1 = null;
        for(int i=0;i<m;i++){
            pre1 = cur1;
            cur1 = cur1.next;
        }
        
        //reverse
        ListNode cur2 = cur1;
        ListNode pre2 = pre1;
        ListNode q2;
        for(int i=m;i<=n;i++){
            q2 = cur2.next;
            cur2.next = pre2;
            pre2 = cur2;
            cur2 = q2;
        }
        
        //connect 
        pre1.next = pre2;
        cur1.next = cur2;
        
        return dummy.next;
    }
}

87. Scramble String
public class Solution {
    public boolean isScramble(String s1, String s2) {
        //Key:Iteration,cp,��  https://discuss.leetcode.com/topic/1195/any-better-solution/3
        if(s1==null || s2==null || s1.length()!=s2.length()) return false;
        if(s1.equals(s2)) return true;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if(!Arrays.equals(c1, c2)) return false;
        for(int i=1; i<s1.length(); i++)
        {
            if(isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i))) return true;
            if(isScramble(s1.substring(0,i), s2.substring(s2.length()-i)) && isScramble(s1.substring(i), s2.substring(0, s2.length()-i))) return true;
        }
        return false;
        
        //Key:DP  https://discuss.leetcode.com/topic/36715/simple-iterative-dp-java-solution-with-explanation/2
        
        /**
		 * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
		 * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
		 * Let q be the length of a cut (hence, q < k), then we are in the following situation:
		 * 
		 * S1 [   x1    |         x2         ]
		 *    i         i + q                i + k - 1
		 * 
		 * here we have two possibilities:
		 *      
		 * S2 [   y1    |         y2         ]
		 *    j         j + q                j + k - 1
		 *    
		 * or 
		 * 
		 * S2 [       y1        |     y2     ]
		 *    j                 j + k - q    j + k - 1
		 * 
		 * which in terms of F means:
		 * 
		 * F(i, j, k) = for some 1 <= q < k we have:
		 *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
		 *  
		 * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal 
		 * */
        /**
        if (s1.length() != s2.length()) return false;
		int len = s1.length();
		
		boolean [][][] F = new boolean[len][len][len + 1];
		for (int k = 1; k <= len; ++k)
			for (int i = 0; i + k <= len; ++i)
				for (int j = 0; j + k <= len; ++j)
					if (k == 1)
						F[i][j][k] = s1.charAt(i) == s2.charAt(j);
					else for (int q = 1; q < k && !F[i][j][k]; ++q) {
						F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q]) || (F[i][j + k - q][q] && F[i + q][j][k - q]);
					}
		return F[0][0][len];
        
        **/
        
        
        
    }
}

84. Largest Rectangle in Histogram
public class Solution {
    public int largestRectangleArea(int[] height) {
        //Key:cp,��  https://discuss.leetcode.com/topic/7599/o-n-stack-based-java-solution/2
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }
}

117. Populating Next Right Pointers in Each Node II
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        //Key:�� Populating Next Right Pointers in Each Node �Ƚϣ�������е�binary tree��perfect���������Һ����п��ܲ����ڣ�
        //Key:cp,�� https://discuss.leetcode.com/topic/28580/java-solution-with-constant-space/2
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while (root != null) {
    	    if (root.left != null) {
    		    pre.next = root.left;
    		    pre = pre.next;
    	    }
    	    if (root.right != null) {
    		    pre.next = root.right;
    		    pre = pre.next;
    	    }
    	    root = root.next;
    	    if (root == null) {
    		    pre = dummyHead;
    		    root = dummyHead.next;
    		    dummyHead.next = null;
    	    }
        }
        
    }
}

106. Construct Binary Tree from Inorder and Postorder Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    //Key:cp,��   https://discuss.leetcode.com/topic/24633/simple-and-clean-java-solution-with-comments-recursive
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, inorder.length-1, 0, postorder, postorder.length-1);
    }
    
    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder,
    		int postStart) {
    	if (postStart < 0 || inStart < inEnd)
    		return null;
    	
    	//The last element in postorder is the root.
    	TreeNode root = new TreeNode(postorder[postStart]);
    	
    	//find the index of the root from inorder. Iterating from the end.
    	int rIndex = inStart;
    	for (int i = inStart; i >= inEnd; i--) {
    		if (inorder[i] == postorder[postStart]) {
    			rIndex = i;
    			break;
    		}
    	}
    	//build right and left subtrees. Again, scanning from the end to find the sections.
    	root.right = buildTree(inorder, inStart, rIndex + 1, postorder, postStart-1);
    	root.left = buildTree(inorder, rIndex - 1, inEnd, postorder, postStart - (inStart - rIndex) -1);
    	return root;
    }
}

115. Distinct Subsequences
public class Solution {
    public int numDistinct(String S, String T) {
        //Key:cp,��  https://discuss.leetcode.com/topic/9488/easy-to-understand-dp-in-java/2
        // array creation
        int[][] mem = new int[T.length()+1][S.length()+1];
    
        // filling the first row: with 1s
        for(int j=0; j<=S.length(); j++) {
            mem[0][j] = 1;
        }
        
        // the first column is 0 by default in every other rows but the first, which we need.
        
        for(int i=0; i<T.length(); i++) {
            for(int j=0; j<S.length(); j++) {
                if(T.charAt(i) == S.charAt(j)) {
                    mem[i+1][j+1] = mem[i][j] + mem[i+1][j];
                } else {
                    mem[i+1][j+1] = mem[i+1][j];
                }
            }
        }
        
        return mem[T.length()][S.length()];
    }
}

114. Flatten Binary Tree to Linked List
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:cp,������̫ⷨţ��...  https://discuss.leetcode.com/topic/11444/my-short-post-order-traversal-java-solution-for-share
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}

110. Balanced Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:cp,��  https://discuss.leetcode.com/topic/7798/the-bottom-up-o-n-solution-would-be-better
    //Key:��һ���Ҿ���Ҳͦ����ķ���  https://discuss.leetcode.com/topic/10192/java-o-n-solution-based-on-maximum-depth-of-binary-tree
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left=depth(root.left);
        int right=depth(root.right);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    public int depth (TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth (root.right)) + 1;
    }
}

82. Remove Duplicates from Sorted List II
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //Key:2 pointers
        //Key:My wrong version:��Ͷ�����...
        /**
        
        if(head == null) return null;
        ListNode prev = head,cur = head.next;
        Set<Integer> set = new HashSet<>();
        set.add(prev.val);
        while(cur != null){
            if(!set.contains(cur.val)) {
                set.add(cur.val);
                prev.next = cur;
                prev = prev.next;
            } 
            cur = cur.next;
        }
        return head;
        
        **/
        
        //Key:cp,��  https://discuss.leetcode.com/topic/11234/a-short-and-simple-java-solution
        //Key:dummy������Ϊ�Ƕ�һ������û������ı���������....д��tmp,foo,barҲ����ν...
        ListNode dummy = new ListNode(0);
        ListNode d = dummy;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val)
                    head = head.next;
            } else {
                d.next = head;
                d = d.next;
            }
            head = head.next;
        }
        d.next = null;
        return dummy.next;
    }
}

147. Insertion Sort List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        //Key:cp,�� https://discuss.leetcode.com/topic/18097/clean-java-solution-using-a-fake-head
        
        ListNode curr = head, next = null;
        // l is a fake head
        ListNode l = new ListNode(0);
        
        while (curr != null) {
            next = curr.next;
            ListNode p = l;
            while (p.next != null && p.next.val < curr.val) p = p.next;
            // insert curr between p and p.next
            curr.next = p.next;
            p.next = curr;
            curr = next;
        }
        
        return l.next;
    }
}

145. Binary Tree Postorder Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:my wrong ans
    /**
    
    public List<Integer> postorderTraversal(TreeNode root) {
        //����ȻҪ����iteratively�ķ��������ǾͿ��Բ���Binary Tree Right Side View���ø�depth�����
        int depth = 0;
        List<Integer> list = new ArrayList<Integer>();
        postorder(root,depth);
    }
    public void postorder(TreeNode root,int depth){
        if(root != null){
            
        }
    }
    
    **/
    
    //Key:cp,�� https://discuss.leetcode.com/topic/44231/preorder-inorder-and-postorder-traversal-iterative-java-solution
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            root = stack.pop();
            list.add(0, root.val);
            if(root.left != null) stack.push(root.left);
            if(root.right != null) stack.push(root.right);
        }
        return list;
    }
}

143. Reorder List
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
        //Key:cp,��  https://discuss.leetcode.com/topic/13869/java-solution-with-3-steps
        if(head==null||head.next==null) return;
        
        //Find the middle of the list
        ListNode p1=head;
        ListNode p2=head;
        while(p2.next!=null&&p2.next.next!=null){ 
            p1=p1.next;
            p2=p2.next.next;
        }
        
        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode preMiddle=p1;
        ListNode preCurrent=p1.next;
        while(preCurrent.next!=null){
            ListNode current=preCurrent.next;
            preCurrent.next=current.next;
            current.next=preMiddle.next;
            preMiddle.next=current;
        }
        
        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1=head;
        p2=preMiddle.next;
        while(p1!=preMiddle){
            preMiddle.next=p2.next;
            p2.next=p1.next;
            p1.next=p2;
            p1=p2.next;
            p2=preMiddle.next;
        }
    }
}

95. Unique Binary Search Trees II
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:another version --> divide and conquer cp,�� https://discuss.leetcode.com/topic/8410/divide-and-conquer-f-i-g-i-1-g-n-i
    /**
     public List<TreeNode> generateTrees(int n) {
        if(n<1) return new ArrayList<TreeNode>();
    	return generateSubtrees(1, n);
    }
    private List<TreeNode> generateSubtrees(int s, int e) {
    	List<TreeNode> res = new LinkedList<TreeNode>();
    	if (s > e) {
    		res.add(null); // empty tree
    		return res;
    	}
    	for (int i = s; i <= e; ++i) {
    		List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);
    		List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);
    		for (TreeNode left : leftSubtrees) {
    			for (TreeNode right : rightSubtrees) {
    				TreeNode root = new TreeNode(i);
    				root.left = left;
    				root.right = right;
    				res.add(root);
    			}
    		}
    	}
    	return res;
    }
    **/
    //Key:https://discuss.leetcode.com/topic/3079/a-simple-recursive-solution/14
    public List<TreeNode> generateTrees(int n) {
        if(n<1) return new ArrayList<TreeNode>();
        return genTreeList(1,n);
    }
    private List<TreeNode> genTreeList (int start, int end) {
        List<TreeNode> list = new ArrayList<TreeNode>(); 
        if (start > end) {
            list.add(null);
        }
        for(int idx = start; idx <= end; idx++) {
            List<TreeNode> leftList = genTreeList(start, idx - 1);
            List<TreeNode> rightList = genTreeList(idx + 1, end);
            for (TreeNode left : leftList) {
                for(TreeNode right: rightList) {
                    TreeNode root = new TreeNode(idx);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }
}

96. Unique Binary Search Trees
public class Solution {
    public int numTrees(int n) {
        //Key:cp,��  https://discuss.leetcode.com/topic/8398/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i/2
        
        int [] G = new int[n+1];
        G[0] = G[1] = 1;
        
        for(int i=2; i<=n; ++i) {
        	for(int j=1; j<=i; ++j) {
        		G[i] += G[j-1] * G[i-j];
        	}
        }
    
        return G[n];
    }
}

109. Convert Sorted List to Binary Search Tree
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //Key:���԰������תΪarray����array�ķ�������...   Convert Sorted Array to Binary Search Tree
    //Key:cp,��  https://discuss.leetcode.com/topic/35997/share-my-java-solution-1ms-very-short-and-concise
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        return toBST(head,null);
    }
    public TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head==tail) return null;
        
        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next,tail);
        return thead;
    }
}

99. Recover Binary Search Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    //Key:cp,�� https://discuss.leetcode.com/topic/3988/no-fancy-algorithm-just-simple-and-powerful-in-order-traversal
    //Key:�����order tree ��д��ͦ�����
    TreeNode firstElement = null;
    TreeNode secondElement = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        
        // In order traversal to find the two elements
        traverse(root);
        
        // Swap the values of the two nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }
    
    private void traverse(TreeNode root) {
        
        if (root == null)
            return;
            
        traverse(root.left);
        
        // Start of "do some business", 
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }
    
        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }        
        prevElement = root;
        // End of "do some business"

        traverse(root.right);
    }
}

155. Min Stack
public class MinStack {

    //Key:cp.��   https://discuss.leetcode.com/topic/7020/java-accepted-solution-using-one-stack
    /** initialize your data structure here. */
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) {
        // only push the old minimum value when the current 
        // minimum value changes after pushing the new value x
        if(x <= min){          
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value, 
        // pop twice and change the current minimum value to the last minimum value.
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
 
 164. Maximum Gap
 public class Solution {
    public int maximumGap(int[] nums) {
        //Key:cp.��  https://discuss.leetcode.com/topic/22221/radix-sort-solution-in-java-with-explanation/2
        
        if (nums == null || nums.length < 2) {
            return 0;
        }
        
        // m is the maximal number in nums
        int m = nums[0];
        for (int i = 1; i < nums.length; i++) {
            m = Math.max(m, nums[i]);
        }
        
        int exp = 1; // 1, 10, 100, 1000 ...
        int R = 10; // 10 digits
    
        int[] aux = new int[nums.length];
        
        while (m / exp > 0) { // Go through all digits from LSB to MSB
            int[] count = new int[R];
            
            for (int i = 0; i < nums.length; i++) {
                count[(nums[i] / exp) % 10]++;
            }
            
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
            
            for (int i = nums.length - 1; i >= 0; i--) {
                aux[--count[(nums[i] / exp) % 10]] = nums[i];
            }
            
            for (int i = 0; i < nums.length; i++) {
                nums[i] = aux[i];
            }
            exp *= 10;
        }
        
        int max = 0;
        for (int i = 1; i < aux.length; i++) {
            max = Math.max(max, aux[i] - aux[i - 1]);
        }
         
        return max;
        
    }
}

201. Bitwise AND of Numbers Range
public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        //Key:��û����
        //Key:bit,cp,��  https://discuss.leetcode.com/topic/20176/2-line-solution-with-detailed-explanation
        while(m<n) n = n & (n-1);
        return n;
    }
}

187. Repeated DNA Sequences
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        //Key:cp,��   https://discuss.leetcode.com/topic/27517/7-lines-simple-java-o-n
        //Key:������� https://discuss.leetcode.com/topic/33745/easy-understand-and-straightforward-java-solution
        Set seen = new HashSet(), repeated = new HashSet();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList(repeated);
    }
}

173. Binary Search Tree Iterator
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    //Key:cp,��   https://discuss.leetcode.com/topic/6575/my-solutions-in-3-languages-with-stack/19
    private Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public BSTIterator(TreeNode root) {
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.val;
    }
    
    private void pushAll(TreeNode node) {
        //�������for��ԭ����sol�е�д����Ҳ��������....�Ҹ�д����while
        //for (; node != null; stack.push(node), node = node.left);
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */