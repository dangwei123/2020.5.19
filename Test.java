public class Solution {
    /**
     * 计算有多少对符合条件的数对
     * @param a int整型一维数组 数组a
     * @param b int整型一维数组 数组b
     * @return int整型
     */
    public int countLR (int[] a, int[] b) {
        // write code here
        int n=a.length;
        int[] sumPre=new int[n+1];
        for(int i=0;i<n;i++){
            sumPre[i+1]=sumPre[i]+a[i];
        }
        int res=0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if((b[i]+b[j])==(sumPre[j+1]-sumPre[i])){
                    res++;
                }
            }
        }
        return res;
    }
}



有n个水桶，第i个水桶里面水的体积为Ai，你可以用1秒时间向一个桶里添加1体积的水。
有q次询问，每次询问一个整数pi,你需要求出使其中pi个桶中水的体积相同所花费的最少时间。
对于一次询问如果有多种方案，则采用使最终pi个桶中水的体积最小的方案。

public class Solution {
    /**
     * 
     * @param n int整型 水桶的个数
     * @param q int整型 询问的次数
     * @param a int整型一维数组 n个水桶中初始水的体积
     * @param p int整型一维数组 每次询问的值
     * @return int整型一维数组
     */
    public int[] solve (int n, int q, int[] a, int[] p) {
        Arrays.sort(a);
        int[] res=new int[q];
        int maxcount=0;
        for(int i=0;i<q;i++){
            int num=p[i];
            if(num<=maxcount){
                res[i]=0;
                continue;
            }
            int sum=0;
            for(int j=0;j<num;j++){
                sum+=a[j];
            }
            int min=num*a[num-1]-sum;
            int begin=0;
            for(int j=1;j<=n-num;j++){
                sum=sum-a[j-1]+a[j+num-1];
                if(min>(num*a[j+num-1]-sum)){
                    min=num*a[j+num-1]-sum;
                    begin=j;
                }
            }
            
            res[i]=min;
            for(int k=begin;k<begin+num;k++){
                a[k]=a[begin+num-1];
            }
            maxcount=Math.max(maxcount,num);
        }
        
        return res;
    }
}


最近公共祖先
public class LCA {
    public int getLCA(int a, int b) {
        Set<Integer> set=new HashSet<>();
        while(a!=0){
            set.add(a);
            a/=2;
        }
        while(!set.contains(b)){
            b/=2;
        }
        return b;
    }
}




空格替换
public class Replacement {
    public String replaceSpace(String iniString, int length) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<iniString.length();i++){
            char c=iniString.charAt(i);
            if(c==' '){
                sb.append("%20");
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}





你有一个长度为 n 的队伍，从左到右依次为 1~n，有 m 次插队行为，用数组 cutIn 进行表示，
cutIn 的元素依次代表想要插队的人的编号，每次插队，这个人都会直接移动到队伍的最前方。
你需要返回一个整数，代表这 m 次插队行为之后，有多少个人已经不在原来队伍的位置了。


public class Solution {
    /**
     * 计算有多少个人最终不在自己原来的位置上
     * @param n int整型 队伍总长
     * @param cutIn int整型一维数组 依次会插队到最前方的人的编号
     * @return int整型
     */
    public int countDislocation (int n, int[] cutIn) {
       int max=0;
        int count=0;
        Set<Integer> set=new HashSet<>();
        for(int i=cutIn.length-1;i>=0;i--){
            if(!set.contains(cutIn[i])){
                if(cutIn[i]-1==set.size()){
                    count++;
                }
                set.add(cutIn[i]);
                max=Math.max(max,cutIn[i]);
            }
        }
        return max-count;
    }
}


众所周知，牛妹需要很多衣服，有直播的时候穿的、有舞剑的时候穿的、有跳舞的时候穿的、有弹琴的时候穿的，等等
这些衣服都是由固定大小的矩形布料裁剪而成，心灵手巧的牛妹也知道每件衣服所需要的具体矩形布料的长和宽
然而，她只有一块大的布料可供她裁剪。裁剪的时候可以随便剪
那么问题来了，美腻的牛妹能最多可以做出多少件衣服呢？

public class Solution {
    /**
     * 
     * @param L int整型 给定布料的长
     * @param W int整型 给定布料的宽
     * @param clothSize int整型二维数组 依次列举每件衣服所需的长和宽
     * @return int整型
     */
    public int clothNumber (int L, int W, int[][] clothSize) {
        int[][] dp=new int[L+1][W+1];
        for(int k=0;k<clothSize.length;k++){
            int x=clothSize[k][0];
            int y=clothSize[k][1];
            for(int i=1;i<=L;i++){
                for(int j=1;j<=W;j++){
                    if(i>=x&&j>=y){
                        dp[i][j]=Math.max(dp[i][j],
                        Math.max(dp[i-x][j]+dp[x][j-y]+1,dp[i-x][y]+dp[i][j-y]+1));
                    }
                    
                    int tmp=x;
                    x=y;
                    y=tmp;
                    if(i>=x&&j>=y){
                        dp[i][j]=Math.max(dp[i][j],
                        Math.max(dp[i-x][j]+dp[x][j-y]+1,dp[i-x][y]+dp[i][j-y]+1));
                    }
                    
                }
            }
                
        }
        return dp[L][W];
    }
}
​	
