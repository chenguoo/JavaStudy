package com.cheney.study.designpatterns.singleton.register;


import java.lang.reflect.Constructor;

/**
 * Created by Tom.
 */
public class EnumSingletonTest {
    /*public static void main(String[] args) {
        try {

            EnumSingleton instance1 = EnumSingleton.getInstance();
            instance1.setData(new Object());
            FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(instance1);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("EnumSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            EnumSingleton instance2 = (EnumSingleton) ois.readObject();
            ois.close();

            System.out.println(instance1.getData());
            System.out.println(instance2.getData());
            System.out.println(instance1.getData() == instance2.getData());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        try {
            Class clazz = EnumSingleton.class;
            Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
            c.setAccessible(true);
            EnumSingleton enumSingleton = (EnumSingleton)c.newInstance("Tom",666);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}