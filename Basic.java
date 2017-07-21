import java.util.*;
class Basic{
	public static void main(String[] s){
		//new Basic().mergeSort();
		//new Basic().binarySearch();
		new Basic().medianSearch();
	}
	public void mergeSort(){
		int[] nums = {3,1,4,5,5,2,5,6,66,33,22,1,21,13,14};
		int[] tmp = new int[nums.length];
		new MergeSort().sort(nums,tmp,0,nums.length-1);
		for(int i:nums) System.out.print(i+",");
	}
	public void binarySearch(){
		int[] nums = {4,2,5,2,5,2,2,1,88,4,22,45,23,121,9,65};
		Arrays.sort(nums);
		System.out.println(new BinarySearch().search(nums,45));
	}
	/*170714*/
	public void medianSearch(){
		int[] nums1 = {1,2};
		int[] nums2 = {3,4};
		MedianSearch obj = new MedianSearch();
		System.out.println(obj.search(nums1,nums2));
	}
}
class BinarySearch{
	public int search(int[] nums,int target){
		return helper(nums,target,0,nums.length-1);
	}
	public int helper(int[] nums,int target,int low,int high){
		if(low <= high){
			int mid = (low+high)/2;
			if(nums[mid] == target) return mid;
			if(nums[mid] < target) return helper(nums,target,mid+1,high);
			else return helper(nums,target,low,mid-1);
		}
		return -1;
	}
}
/*170711*/
//mark1:ֻ��static ��������non-static��������Ҫnew Class(),non-static ��������non-static ��������Ҫnew Class()
class MergeSort{
	public void MergeSort(){}
	public void sort(int[] nums,int[] tmp,int low,int high){
		if(low < high){
			int mid = (low+high)/2;
			sort(nums,tmp,low,mid);
			sort(nums,tmp,mid+1,high);
			merge(nums,tmp,low,mid,high);
		}
	} 
	public void merge(int[] nums,int[] tmp,int low,int mid,int high){
		int index1 = low,index2 = mid+1,index3 = low;
		while(index1 <= mid && index2 <= high){
			if(nums[index1] <= nums[index2]){
				tmp[index3++] = nums[index1++];
			} else {
				tmp[index3++] = nums[index2++];
			}
		}
		while(index1 <= mid) tmp[index3++] = nums[index1++];
		while(index2 <= high) tmp[index3++] = nums[index2++];
		while(low <= high){
			nums[low] = tmp[low++];
		}
	}
}

/*170714*/
class MedianSearch{
	public double search(int[] nums1,int[] nums2){
		//mark-0.9:��סleft,right�ֱ���ż����ʱ���м�index,����һ��Ҫ�ǵ�(n1+n2+1)����2
		int n1 = nums1.length,n2 = nums2.length,left = (n1+n2+1)/2,right = (n1+n2+2)/2;
		//mark-0.99:��Ϊreturn double,so �ǵ��ǳ���2.0��������2
		//case:[1,2][3,4] ����2ʱ�����ص���2.0,���Խ���ǲ��Ե�
		//return (findKth(nums1,0,nums2,0,left)+findKth(nums1,0,nums2,0,right))/2;
		return (findKth(nums1,0,nums2,0,left)+findKth(nums1,0,nums2,0,right))/2.0;
	}
	//mark0:�ǵ�KС�����ǵ�k��
	public int findKth(int[] nums1,int aStart,int[] nums2,int bStart,int k){
		int n1 = nums1.length,n2 = nums2.length;
		//mark1:�������ų�
		//mark1.5:k-1�����Ǽ���aStart
		//if(aStart >= n1) return nums2[k-1];
		if(aStart >= n1) return nums2[bStart+k-1];
		if(bStart >= n2) return nums1[aStart+k-1];		
		//mark1.6:����nums1[0]������nums1[aStart]
		//if(k == 1) return Math.min(nums1[0],nums2[0]);
		if(k == 1) return Math.min(nums1[aStart],nums2[bStart]);
		//mark2
		int midIndA = aStart+k/2-1,midIndB = bStart+k/2-1;
		//mark3:�ų����������
		int midValA = midIndA >= n1?Integer.MAX_VALUE:nums1[midIndA];
		int midValB = midIndB >= n2?Integer.MAX_VALUE:nums2[midIndB];
		//mark4:==���ı�����ν
		if(midValA < midValB) 
			//mark5:��סmidIndAҪ+1
			return findKth(nums1,midIndA+1,nums2,bStart,k-k/2);
		else
			return findKth(nums1,aStart,nums2,midIndB+1,k-k/2);
	}
}

