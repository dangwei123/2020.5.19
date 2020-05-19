对角线打印数组
public class Printer {
    public int[] arrayPrint(int[][] arr, int n) {
        // write code here
        int[] res=new int[n*n];
        int k=0;
        for(int i=n-1;i>=0;i--){
            int col=i;
            int row=0;
            while(row<n&&col<n){
                res[k++]=arr[row][col];
                row++;
                col++;
            }
        }
        
        for(int i=1;i<n;i++){
            int row=i;
            int col=0;
            while(row<n&&col<n){
                res[k++]=arr[row][col];
                row++;
                col++;
            }
        }
        return res;
    }
}

判断能否通过添加一个字母使字符串成为回文串
public class Main{
    private static boolean isValid(String s){
        int n=s.length();
        int[][] dp=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=n;j>=1;j--){
                if(s.charAt(i-1)==s.charAt(j-1)){
                    dp[i][n-j+1]=dp[i-1][n-j]+1;
                }else{
                    dp[i][n-j+1]=Math.max(dp[i-1][n-j+1],dp[i][n-j]);
                }
            }
        }
        return (n-dp[n][n])==1;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String s=sc.next();
            if(isValid(s)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
}


查找当前薪水(to_date='9999-01-01')排名第二多的员工编号emp_no、薪水salary、last_name以及first_name，不准使用order by
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`from_date`));


select  e2.emp_no,s2.salary,e2.last_name,e2.first_name
from employees e2,salaries s2
where s2.emp_no=e2.emp_no
and s2.salary=(
    select max(salary) from salaries where to_date='9999-01-01'
    and salary<(select max(salary) from salaries where to_date='9999-01-01')
    )
	
	

查找所有员工的last_name和first_name以及对应的dept_name，也包括暂时没有分配部门的员工
CREATE TABLE `departments` (
`dept_no` char(4) NOT NULL,
`dept_name` varchar(40) NOT NULL,
PRIMARY KEY (`dept_no`));
CREATE TABLE `dept_emp` (
`emp_no` int(11) NOT NULL,
`dept_no` char(4) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`));
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));


select e.last_name,e.first_name,tmp.dept_name
from employees e left join (select d.emp_no,ds.dept_name from
      dept_emp d join departments ds on ds.dept_no=d.dept_no) tmp
on e.emp_no=tmp.emp_no;


查找员工编号emp_no为10001其自入职以来的薪水salary涨幅值growth
CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`from_date`));

select max(salary)-min(salary) growth from salaries where emp_no=10001



查找所有员工自入职以来的薪水涨幅情况，给出员工编号emp_no以及其对应的薪水涨幅growth，并按照growth进行升序
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`from_date`));


select s1.emp_no,s1.salary-s2.salary growth from
(select emp_no,salary from salaries where to_date='9999-01-01') s1
join 
(select s.emp_no,s.salary from salaries s join employees e on s.emp_no=e.emp_no
 and s.from_date=e.hire_date) s2
on s1.emp_no=s2.emp_no
order by growth


统计各个部门的工资记录数，给出部门编码dept_no、部门名称dept_name以及次数sum
CREATE TABLE `departments` (
`dept_no` char(4) NOT NULL,
`dept_name` varchar(40) NOT NULL,
PRIMARY KEY (`dept_no`));
CREATE TABLE `dept_emp` (
`emp_no` int(11) NOT NULL,
`dept_no` char(4) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`));
CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`from_date`));

select de.dept_no,de.dept_name,count(dept.dept_no)from 
dept_emp dept join salaries s on dept.emp_no=s.emp_no
join departments de on de.dept_no=dept.dept_no
group by de.dept_no


