package com.javarush.test.level20.lesson07.task03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Externalizable Person
Класс Person должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку сериализации.
Сигнатуры методов менять нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person p1 = new Person("Mikhail", "Terentyev", 27);
        FileOutputStream fOS = new FileOutputStream("object");
        ObjectOutputStream oOS = new ObjectOutputStream(fOS);

        Person m1 = new Person("Nadezhda", "Terentyeva", 50);
        List<Person> l1 = new ArrayList<Person>();
        l1.add(p1);
        m1.setChildren(l1);
        p1.setMother(m1);

        oOS.writeObject(p1);
        oOS.writeObject(p1);
        oOS.close();

        FileInputStream fIS = new FileInputStream("object");
        ObjectInputStream oIS= new ObjectInputStream(fIS);
        Person p2 = (Person)oIS.readObject();
        Person m2 = p2.mother;
        oIS.close();
    }

    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public Person() {}

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            mother = (Person)in.readObject();
            father = (Person)in.readObject();
            age = in.readInt();
            children = (List)in.readObject();
        }
    }
}
