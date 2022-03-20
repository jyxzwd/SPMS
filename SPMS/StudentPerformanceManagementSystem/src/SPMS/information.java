package SPMS;

import java.util.*;

import static SPMS.course.course_name;
import static SPMS.menu.*;
import static SPMS.student.STU_NUM;
import static SPMS.course.COU_NUM;

public class information  {

    public static final int SORT_BY_NAME = 0;
    public static final int SORT_BY_ID = 1;
    public static final int SORT_BY_SUM_SCORE = 2;
    public static  int SORT_FLAG = -1;

    public static final int INQUIRY_BY_ID = 0;
    public static final int INQUIRY_BY_NAME = 1;
    public static  int INQUIRY_FLAG = -1;


    private String name;
    private double value;
    private double value_sum;
    private double value_ave;
    private List<information> second_information_list = new ArrayList<>();


    public information(String name, List<information> second_information) {
        this.name = name;
        this.second_information_list = second_information;
    }

    public information(String name,double value) {
        this.name = name;
        this.value = value;
    }

    public information() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue_sum() {
        return value_sum;
    }

    public void setValue_sum(double value_sum) {
        this.value_sum = value_sum;
    }

    public double getValue_ave() {
        return value_ave;
    }

    public void setValue_ave(double value_ave) {
        this.value_ave = value_ave;
    }

    public List<information> getSecond_information_list() {
        return second_information_list;
    }

    public void setSecond_information_list(List<information> second_information_list) {
        this.second_information_list = second_information_list;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }



    public static void  cin() {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入学生总数：");
        STU_NUM = sc.nextInt();
        System.out.println("请输入学科总数");
        COU_NUM = sc.nextInt();
        System.out.println("请依次输入各个课程名称：");
        for(int i=0;i<COU_NUM;i++){
            String name;
            name = sc.next();
            course_name[i]=name;
            /**
             * 创建COU_NUM个课程对象，放进集合
             */
            course_list.add(new course(course_name[i], new ArrayList<>()));

        }
        String name,id;
        double value;
        for (int i=0;i<STU_NUM;i++){
            System.out.println("请输入第"+(i+1)+"个学生的姓名");
            name= sc.next();

            System.out.println("请输入第"+(i+1)+"个学生的学号");
            id = sc.next();

            //创建中间课程集合，用于存放该学生的每门课程信息
            List<information> temp_courseList = new ArrayList<>();

            for(int k=0;k<COU_NUM;k++){
                System.out.println("请输入第"+(i+1)+"个学生的"+course_name[k]+"的成绩");
                value = sc.nextDouble();
                temp_courseList.add(new course(course_name[k],value));
                //把第 K 个课程下 的一个学生对象 放进 该课程下的学生集合里
                course_list.get(k).getSecond_information_list().add(new student(name,value,id));
            }

            student_list.add(new student(name,temp_courseList,id));
        }
    }

    public static void cout() {
        if(student_list.size()==0){
            System.out.println("还未录入任何一个学生的信息，请录入后再输出");
            return;
        }

        calculate_sum_and_ave(student_list);
        calculate_sum_and_ave(course_list);

        for (int i=0;i<STU_NUM;i++){
            student one_stu = (student) student_list.get(i);
            System.out.println("第"+(i+1)+"\n"+"姓名"+one_stu.getName()+"\t\t"+"学号"+one_stu.getId());

            for(int k=0;k<COU_NUM;k++){
                System.out.println(course_name[k]+"成绩为"+one_stu.getSecond_information_list().get(k).getValue());
            }
            System.out.printf("总分为%.2f\t\t",+one_stu.getValue_sum());
            System.out.printf("平均分为%.2f\n",one_stu.getValue_ave());

        }
        for(int i=0;i<COU_NUM;i++){
            System.out.print(course_name[i]+"的总分数为");
            System.out.printf("%.2f\t\t",course_list.get(i).getValue_sum());
            System.out.print(course_name[i]+"的平均分为");
            System.out.printf("%.2f\n",course_list.get(i).getValue_ave());
        }
    }

    public static void sort_by_(int FLAG) {
        if(student_list.size()==0){
            System.out.println("还未录入任何一个学生的信息，请录入后再执行");
            return;
        }
        SORT_FLAG = FLAG;
        student_list.sort(new my_comparator());//自定义排序
        System.out.println("排序完成");
    }


    public static void calculate_sum_and_ave(List<information> list) {

        //传进来一个infor集合，该集合内可能为学生对象，也可能为课程对象

        if(student_list.size()==0){
            System.out.println("还未录入任何一个学生的信息，请录入后再执行");
            return;
        }
        //从该集合内取出 第一级信息对象
        for (information first_information : list) {
            double sum = 0.0;

            /**
             *  对 第一级信息对象下的 第二级信息进行计算
             *  如传进来的如果是学生集合(第一级信息对象为学生)，对学生的 每门课程（第二级信息）进行计算
             */
            for(information second_information : first_information.getSecond_information_list()){
                sum+=second_information.getValue();
            }
            first_information.setValue_sum(sum);
            first_information.setValue_ave(sum/STU_NUM);
        }
        System.out.println("计算完成");
    }

    public static void inquiry_by_(int FLAG,String to_inquiry) {

        sort_by_(SORT_BY_SUM_SCORE);

        INQUIRY_FLAG = FLAG;
        if(student_list.size()==0){
            System.out.println("还未录入任何一个学生信息，无法查找");
            return;
        }

        boolean  can_inquiry =false;
        boolean  can_cout =false;

        for(int i=0;i<student_list.size();i++){
            student one_student = (student) student_list.get(i);

            //判断查找条件
            if(INQUIRY_FLAG==INQUIRY_BY_ID){
                if((one_student).getId().equals(to_inquiry)){
                    can_inquiry=true;
                }
            }else if(INQUIRY_FLAG==INQUIRY_BY_NAME){
                if((one_student).getName().equals(to_inquiry)){
                    can_inquiry=true;
                    }
                }

            if(can_inquiry){
                can_inquiry = false;
                can_cout = true;
                System.out.println(one_student.getName()+"的排名为第"+(i+1));
                for(information the_student_of_cour : one_student.getSecond_information_list()){
                    System.out.println(one_student.getName()+"的"+
                            the_student_of_cour.getName()+"成绩为"+the_student_of_cour.getValue());
                }
            }
        }
        if(!can_cout){
            System.out.println("查无此人，请检查输入");
        }
    }


    /**
     * 自定义排序规则
     * 静态内部类 便于sort方法中直接new compare对象
     */

    static class my_comparator implements Comparator<information>{
        public int compare(information o1, information o2) {
            int t = 0;
            student s1 = (student) o1;
            student s2 = (student) o2;
            if(SORT_FLAG==SORT_BY_NAME){
                t= s1.getName().compareTo(s2.getName());
            }
            else if(SORT_FLAG==SORT_BY_ID){
                if(Long.parseLong(s1.getId())- Long.parseLong(s2.getId())>0){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            else if(SORT_FLAG==SORT_BY_SUM_SCORE){
                if (o1.getValue_sum()-o2.getValue_sum()>0){
                    return -1;
                }
                else{
                    return 1;
                }
            }
            return t;
        }
    }
}


