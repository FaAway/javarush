package com.javarush.test.level20.lesson02.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution
{
    public static void main(String[] args)
    {
        try
        {
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
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println(ivanov.name + (ivanov.equals(somePerson) ? " is equal to " : " isn't equal to ") + somePerson.name);
            Human somePerson2 = new Human();
            somePerson2.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println(petrov.name + (petrov.equals(somePerson2) ? " is equal to " : " isn't equal to ") + somePerson2.name);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human
    {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human()
        {
        }

        public Human(String name, Asset... assets)
        {
            this.name = name;
            if (assets != null)
            {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception
        {
            PrintWriter writeFile = new PrintWriter(outputStream);
            String isNamePresent = (name != null) ? "yes" : "no";
            writeFile.println(isNamePresent);
            if (isNamePresent.equals("yes"))
            {
                writeFile.println(name);
                if (assets.size() > 0)
                {
                    for (Asset a : assets)
                        writeFile.write(a.getName() + "\r\n");
                }
            }
            writeFile.close();
        }

        public void load(InputStream inputStream) throws Exception
        {
            BufferedReader readFile = new BufferedReader(new InputStreamReader(inputStream));
            String hasName = readFile.readLine();
            if (hasName.equals("yes"))
            {
                this.name = readFile.readLine();
                String temp;
                while ((temp = readFile.readLine()) != null)
                    assets.add(new Asset(temp));
            }
            readFile.close();
        }

        @Override
        public String toString()
        {
            return "Human {" + "name = '" + name + '\'' + ", assets = " + assets + '}';
        }
    }
}