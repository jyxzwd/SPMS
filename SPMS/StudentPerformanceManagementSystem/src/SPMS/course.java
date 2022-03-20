package SPMS;


import java.util.List;

import static SPMS.menu.course_list;
import static SPMS.student.STU_NUM;

public class course extends information{
    /**
     * 一些常量 为了见字知意
     */
    public static final int EXCELLENT = 90;
    public static final int GOOD = 80;
    public static final int MEDIUM = 70;
    public static final int PASS = 60;
    public static final int TOP0 = 0;
    public static final int TOP1 = 1;
    public static final int TOP2 = 2;
    public static final int TOP3 = 3;
    public static final int TOP4 = 4;
    public static final int LEVEL_NUM = 5;
    public static final String [] LEVEL ={"优秀","良好","中等","及格","不及格"};
    public static  int COU_NUM;
    public static  String [] course_name =  new String[6];


    course(){}

    public course(String name,double value) {
        super(name,value);
    }

    public course(String name, List<information> cou_of_stu) {
        super(name,cou_of_stu);
    }


    public static void calculate_level() {

        int i=0;
        for(information one_course : course_list){
            int [] sum_stu= new int[LEVEL_NUM];
            for(information the_student : one_course.getSecond_information_list()){
                double tem= the_student.getValue();
                if(tem>=EXCELLENT){
                    sum_stu[TOP0]++;
                }
                else if(tem>=GOOD){
                    sum_stu[TOP1]++;
                }
                else if(tem>=MEDIUM){
                    sum_stu[TOP2]++;
                }
                else if(tem>=PASS){
                    sum_stu[TOP3]++;
                }
                else{
                    sum_stu[TOP4]++;
                }
            }
            System.out.println(course_name[i++]+"中");
            for(int j=0;j<LEVEL_NUM;j++){
                System.out.print(LEVEL[j]+"级别"+"有"+sum_stu[j]+"个人"+"\t"+"所占本课程人数百分比为");
                double percentage = sum_stu[j]*100.0/STU_NUM;
                System.out.printf("%.2f%%%n", percentage );
            }
        }
        System.out.println("计算完成");
    }
}
