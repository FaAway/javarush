package com.javarush.test.level20.lesson02.task02.backup;

import com.javarush.test.level20.lesson02.task02.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = new File("level20_lesson02_task02.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush1 = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user1 = new User();
            user1.setFirstName("Михаил");
            user1.setLastName("Терентьев");
            user1.setBirthDate(new GregorianCalendar(1998, 02, 12).getTime());
            user1.setMale(true);
            user1.setCountry(User.Country.RUSSIA);
            javaRush1.users.add(user1);

            User user2 = new User();
            user2.setFirstName("Aleksandr");
            user2.setLastName("Meshcheryakov");
            user2.setBirthDate(new GregorianCalendar(1985, 11, 22).getTime());
            user2.setMale(true);
            user2.setCountry(User.Country.UKRAINE);
            javaRush1.users.add(user2);

            javaRush1.save(outputStream);

            JavaRush javaRush2 = new JavaRush();
            User user3 = new User();
            user3.setFirstName("Bon");
            user3.setLastName("Jovi");
            user3.setBirthDate(new GregorianCalendar(1985, 03, 01).getTime());
            user3.setMale(true);
            user3.setCountry(User.Country.OTHER);
            javaRush2.users.add(user3);

            javaRush2.save(outputStream);

            outputStream.flush();

            JavaRush loadedObject1 = new JavaRush();
            loadedObject1.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject
            System.out.println("loadedObject1" + (loadedObject1.equals(javaRush1) ? " is equal to " : " isn't equal to ") + "javaRush1");

            JavaRush loadedObject2 = new JavaRush();
            loadedObject2.load(inputStream);
            System.out.println("loadedObject2" + (loadedObject2.equals(javaRush2) ? " is equal to " : " isn't equal to ") + "javaRush2");

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public ArrayList<User> users = new ArrayList<>();

        public boolean equals(Object obj) {
            JavaRush objJavaRush = (JavaRush) obj;
            return users.equals(objJavaRush.users);
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            DataOutputStream dOS = new DataOutputStream(outputStream);
            dOS.writeInt(users.size());
            for (User user : users) {
                boolean flag;
                dOS.writeBoolean(flag = user.getFirstName() != null);
                if (flag) dOS.writeUTF(user.getFirstName());
                dOS.writeBoolean(flag = user.getLastName() != null);
                if (flag) dOS.writeUTF(user.getLastName());
                dOS.writeBoolean(flag = user.getBirthDate() != null);
                if (flag) dOS.writeLong(user.getBirthDate().getTime());
                dOS.writeBoolean(user.isMale());
                dOS.writeBoolean(flag = user.getCountry() != null);
                if (flag) dOS.writeUTF(user.getCountry().name());
            }
            dOS.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            DataInputStream dIS = new DataInputStream(inputStream);
            int usersCount = dIS.readInt();
            for (int i = 0; i < usersCount; i++) {
                User user = new User();
                boolean flag;
                if (flag = dIS.readBoolean())
                    user.setFirstName(dIS.readUTF());
                if (flag = dIS.readBoolean())
                    user.setLastName(dIS.readUTF());
                if (flag = dIS.readBoolean())
                    user.setBirthDate(new Date(dIS.readLong()));
                user.setMale(dIS.readBoolean());
                if (flag = dIS.readBoolean())
                    user.setCountry(User.Country.valueOf(dIS.readUTF()));
                this.users.add(user);
            }
        }
    }
}
