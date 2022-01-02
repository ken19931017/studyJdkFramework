import org.junit.jupiter.api.Test;

/**
 * <p>二分查找-在排序数组中查找元素的第一个和最后一个位置</p>
 * */
public class BinarySearchRangeTest {

    @Test
    public void test(){

        int[] nums = {-2,-2};
        int target = 2;
        searchRange(nums,target);

    }

    private int[] searchRange(int[] nums, int target) {

        int[] res = {-1,-1};
        int[] res1= {0,0};
        int n =nums.length;

        if(n==0){
            return res;
        }else if(n==1){
            if(nums[0]==target){
                return res1;
            }else{
                return res;
            }
        }

        res[0]=searchMin(nums,n,target);
        res[1]=searchMax(nums,n,target);
        return res;

    }

    private int searchMin(int[] nums,int n,int target){
        int low=0;
        int high=n-1;
        while(low<n&&high>=0&&nums[low]<=nums[high]){
            int mid= low + ((high-low)>>1);

            if(nums[mid]>target){

                high=mid-1;

            }else if(nums[mid]<target){

                low=mid+1;

            }else {
                if((mid==0)||(nums[mid-1]!=target)){
                    return mid;
                }else{
                    high = mid-1;
                }
            }
        }

        return -1;


    }

    private int searchMax(int[] nums,int n,int target){
        int low=0;
        int high=n-1;
        while(low<n&&high>=0&&nums[low]<=nums[high]){
            int mid= low + ((high-low)>>1);
            if(nums[mid]>target){

                high=mid-1;

            }else if(nums[mid]<target){

                low=mid+1;

            }else {
                if(mid==n-1||nums[mid+1]!=target){
                    return mid;
                }else{
                    low = mid+1;
                }
            }
        }

        return -1;


    }

}
