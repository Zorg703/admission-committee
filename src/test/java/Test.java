import by.mordas.project.entity.Subject;

import java.util.HashMap;

public class Test  {
    public static void main(String[] args) {
        HashMap<Subject,Integer> d=new HashMap<>();
        Subject s=new Subject();
        s.setSubjectId(2);
        d.put(s,1);
       System.out.println(d.get(s));
    }


}
