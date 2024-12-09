import javax.swing.*;
import java.lang.Math;
import java.awt.BorderLayout;
public class Main {
    static int[][] pole = new int[9][9];
    static int rnd (int min, int max){//функция случайного числа из диапазона(разобраться с синтаксисом)
        max-=min;
        return (int) (Math.random() * ++max) + min;
    }
    public static void main(String[] args) {
        for (int i=0;i<9;i++){
            pole[0][i]=1;//заполнение непользовательскими элементами
        }
        int a=2;//номер минимального пользовательского элемента
        int b=6;//номер максимального пользовательского элемента
        for (int i=1;i<9;i++){
            for(int j=0;j<9;j++){
                pole [i][j]=rnd(a,b);
            }
        }
        JFrame frame = new JFrame("My First GUI"); // Для окна нужна "рама" - Frame
// стандартное поведение при закрытии окна - завершение приложения
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel buttonsPanel1=new JPanel();//новая панель
        frame.setSize(300, 300); // размеры окна
        frame.setLocationRelativeTo(null); // окно - в центре экрана
        for(int j=0;j<9;j++){
            JButton button= new JButton(String.valueOf(pole[0][j]));
            buttonsPanel1.add(button);
            frame.getContentPane().add(BorderLayout.NORTH, buttonsPanel1);
            frame.setVisible(true);
        }
        JPanel buttonsPanel2=new JPanel();//новая панель2
        for(int j=0;j<9;j++){
            JButton button= new JButton(String.valueOf(pole[1][j]));
            buttonsPanel2.add(button);
            frame.getContentPane().add(BorderLayout.NORTH, buttonsPanel2);
            frame.setVisible(true);
        }

//JButton button = new JButton(String.valueOf(pole[0][0])); // Экземпляр класса JButton
// getContentPane() - клиентская область окна
//frame.getContentPane().add(button); // Добавляем кнопку на Frame
//frame.setVisible(true); // Делаем окно видимым*/
    }
}