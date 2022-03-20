package SPMS;

import java.util.List;

public class student extends  information{

    public static  int STU_NUM;

    private String id;


    student(){}


    public student(String name, List<information> sut_of_cou, String id) {
        super(name,sut_of_cou);
        this.id = id;
    }

    public student(String name,double value,String id) {
        super(name,value);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
