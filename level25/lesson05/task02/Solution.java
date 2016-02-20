package com.javarush.test.level25.lesson05.task02;

/* Без дураков
1. Создай private class MyUncaughtExceptionHandler, который на перехват исключения должен подождать половину секунды,
а затем вывести на экран secretKey, имя трэда и сообщение возникшего исключения.
Используй String.format(...). Пример:
super secret key, Thread-0, it's an example
2. Разберитесь в последовательности выполняемого кода и обеспечьте логирование возникновения исключения в п.1.
3. Метод main в тестировании не участвует.
*/
public class Solution {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
        myThread.join();

    }

    public class MyThread extends Thread {
        private String secretKey;

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            setDaemon(true);
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }

        private class MyUncaughtExceptionHandler extends Throwable implements Thread.UncaughtExceptionHandler{
            @Override
            public void uncaughtException(Thread t, Throwable e) {

                //try (BufferedWriter out = new BufferedWriter(new FileWriter("log.txt"))) {

                    try {
                        t.sleep(500);
                    } catch (InterruptedException ie) {}

                    String logMessage = String.format("%s, %s, %s", ((MyThread) t).secretKey, t.getName(), e.getMessage());
                    System.out.println(logMessage);
              /*      out.write(logMessage);
                    out.close();
                } catch (FileNotFoundException fe) {}
                catch (IOException ioe) {}*/
            }
        }
    }
}

