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
        
        //Key:��һ���Լ��ģ��̫ⷨ����...�����Ǵ���
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
        //�����������ߵ��Ǹ�����������
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
    //Key:����BST,In Order���������ǰ���˳������....
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