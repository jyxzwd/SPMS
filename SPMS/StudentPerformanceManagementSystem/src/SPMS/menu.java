package SPMS;
/**
 * 主体结构：
 * 一个父类：information
 *                                           对学生而言           /      对课程而言
 * 包含学生与课程所共有的：        名称(name)           姓名                  课程名称
 *                     第二级信息列表(list)       成绩集合                   该课程下学生集合
 *                                value    成绩集合中具体某个课程的分数      学生集合中具体某个学生该课程分数
 *                    value_sum and ave    该学生总分/平均分               该课程总分/平均分
 *
 * 还包含其他输入输出等静态方法
 * 两个子类 学生(独有学号)与课程(独有计算五类学生方法)
 *
 *
 * 主要思想：
 * 利用继承和多态提高代码复用(主要体现在计算学生和课程总分与平均分时，，一份代码同时实现两个不同类别对象的相同功能)
 *
 */



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static SPMS.information.*;

public class menu {
    public static int MENU_Button = -1;
    public static List<information> student_list = new ArrayList<>();//存储各个学生的课程信息 其中存储学生对象
    public static List<information> course_list = new ArrayList<>();//存储每门课程下的学生信息 其中存储课程对象

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            call_menu();
            MENU_Button = sc.nextInt();
            switch (MENU_Button){
                case 1 : information.cin();break;
                case 2 : information.calculate_sum_and_ave(course_list);break;
                case 3 : information.calculate_sum_and_ave(student_list);break;
                case 4 : information.sort_by_(SORT_BY_SUM_SCORE);break;
                case 5 : information.sort_by_(SORT_BY_ID);break;
                case 6 : information.sort_by_(SORT_BY_NAME);break;
                case 7 :
                    System.out.println("请输入你所要查询的学生学号");
                    information.inquiry_by_(INQUIRY_BY_ID,sc.next());
                    break;
                case 8 :
                    System.out.println("请输入你所要查询的学生学号");
                    information.inquiry_by_(INQUIRY_BY_NAME,sc.next());
                    break;
                case 9 : course.calculate_level();break;
                case 10 : information.cout();break;
                default: break;
            }
            if(MENU_Button==0){
                System.out.println("BYE");
                break;
            }
            System.out.println("输入非空字符以继续");
            sc.next();
        }
    }
    public static void call_menu(){
        System.out.println("""
                *******************************************************
                   \t1. Input record
                \t2. Caculate total and average score of every course
                \t3. Caculate total and average score of every student
                \t4. Sort in decending order by total socre of every student
                \t5. Sort in ascending order by number
                \t6. Sort in ascending order by name
                \t7. Search by number
                \t8. Search by name
                \t9. Statistic analysis for every course
                \t10. List reocrd
                  \t0. Exit
                  *******************************************************""");
    }
}
