package com.javarush.test.level20.lesson02.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
Метод main реализован только для вас и не участвует в тестировании
*/
public class BestSolution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = new File("ivanov");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();
            Human petrov = new Human("Petrov", new Asset("home2"), new Asset("car2"));
            petrov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            if (somePerson.name == null) System.out.println("somePerson не загружен. Объект не был записан.");
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            else System.out.println(ivanov.name + (ivanov.equals(somePerson) ? " is equal to " : " isn't equal to ") + somePerson.name);

            Human somePerson2 = new Human();
            somePerson2.load(inputStream);
            if (somePerson2.name == null) System.out.println("somePerson2 не загружен. Объект не был записан.");
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            else System.out.println(petrov.name + (petrov.equals(somePerson2) ? " is equal to " : " isn't equal to ") + somePerson2.name);

            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public ArrayList<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object obj) {
            Human objHuman = (Human)obj;
            return name.equals(objHuman.name) && assets.equals(objHuman.assets);
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            DataOutputStream dOS = new DataOutputStream(outputStream);
            if (name != null && !name.equals("")) {
                dOS.writeBoolean(true); //маркер что человек сохранен
                dOS.writeUTF(name);
                dOS.writeInt(assets.size());
                for (Asset asset : assets) {
                    dOS.writeUTF(asset.getName());
                    dOS.writeDouble(asset.getPrice());
                }
                dOS.flush();
            } else
                dOS.writeBoolean(false); //маркер что человек не сохранен
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            DataInputStream dIS = new DataInputStream(inputStream);
            boolean saved = dIS.readBoolean();
            if (saved) {
                name = dIS.readUTF();
                int assetsCount = dIS.readInt();
                for (int i = 0; i < assetsCount; i++) {
                    Asset ass;
                    String assName = dIS.readUTF();
                    assets.add(ass = new Asset(assName));
                    ass.setPrice(dIS.readDouble());
                }
            }
        }
    }
}
