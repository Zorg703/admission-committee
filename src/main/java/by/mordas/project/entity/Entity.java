package by.mordas.project.entity;



public class Entity{
    public static void main(String[] args) {

    }
}
class Parent{
    Parent(){

    }
    Parent add(int x){
        System.out.println("p");
        return new Parent();
    }
}
class Child extends Parent{
    Child(){

    }
    double add(int x,int y){
        return (double)++x;
    }
    void add(int x,int y,long z){

    }
    @Override
    Child add(int x){
        System.out.println("c");
       return new Child();
    }

}



