import java.awt.*;
import javax.swing.*;

import static javax.swing.SwingUtilities.paintComponent;

public class GridLayoutTes extends JFrame {
    private int rnd(int min, int max) {//функция случайного числа из диапазона(разобраться с синтаксисом)
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
    private MyButton[][] NewButt(){
        MyButton[][] mybutt=new MyButton[9][9];
        for (int i=0;i<9;i++){
          mybutt[0][i]=new MyButton(0, i,1);
        }
        int a = 2;//номер минимального пользовательского элемента
        int b = 6;//номер максимального пользовательского элемента
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mybutt[i][j] = new MyButton(i,j,rnd(a,b));
            }
        }
        return mybutt;
    }
    private MyButton[][] mybuttons= NewButt();
    public GridLayoutTes() {
        super("Prilogenie");
        setSize(320, 320);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Вспомогательная панель
        JPanel grid = new JPanel();
        /*
         * Первые два параметра конструктора GridLayout определяют количество
         * строк и столбцов в таблице. Вторые 2 параметра - расстояние между
         * ячейками по горизонтали и вертикали
         */
        GridLayout layout = new GridLayout(9, 9, 5, 12);
        grid.setLayout(layout);
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                mybuttons[i][j].setText(String.valueOf(mybuttons[i][j].znn));
                grid.add(mybuttons[i][j]);
            }
        }
        // Размещаем нашу панель в панели содержимого
        getContentPane().add(grid);
        // Устанавливаем оптимальный размер окна
        pack();
        // Открываем окно
        setVisible(true);
    }
    private void check1(MyButton mas[][]){
        for (int i=1;i<9;i++){
            for (int j=1;j<9;j++){
                int c=0;
                int v=0;
                if (mas[i][j-1].sravn(mas[i][j]) && !mas[i][j].sravnenie(1)){
                    c=2;
                    v=j-1;
                    while(j<8 && mas[i][j].sravn(mas[i][j+1])){
                        c++;
                        j++;
                    }
                    j=v+1;
                    switch (c){
                        case 2:
                            break;
                        case 3:
                            for (int k=v;k<v+c;k++){
                                mas[i][k].setValue(0);
                            }
                            break;
                        case 4:
                            for (int k=v+1;k<v+c;k++){
                                mas[i][k].setValue(0);
                            }
                            mas[i][v].setValue(8);
                            break;
                        default:
                            for (int k=v+1;k<v+c;k++){
                                mas[i][k].setValue(0);
                            }
                            mas[i][v].setValue(9);
                            break;
                    }
                }
            }
        }
    }
    static void check2(MyButton mas[][]){
        for (int j=0;j<9;j++){
            for (int i=2;i<9;i++){
                int c=0;
                int v=0;
                if (mas[i-1][j].sravn(mas[i][j]) && !mas[i][j].sravnenie(1)){
                    c=2;
                    v=i-1;
                    while(i<8 && mas[i][j].sravn(mas[i+1][j])){
                        c++;
                        i++;
                    }
                    i=v+1;
                    switch (c){
                        case 2:
                            break;
                        case 3:
                            for (int k=v;k<v+c;k++){
                                mas[k][j].setValue(0);
                            }
                            break;
                        case 4:
                            for (int k=v;k<v+c-1;k++){
                                mas[k][j].setValue(0);
                            }
                            mas[v+c-1][j].setValue(7);
                            break;
                        default:
                            for (int k=v;k<v+c-1;k++){
                                mas[k][j].setValue(0);
                            }
                            mas[v+c-1][j].setValue(9);
                            break;
                    }
                }
            }
        }
    }
    public void RePaint(){
        check1(mybuttons);
        check2(mybuttons);
        boolean marker=false;
        while(marker==false) {
            for (int j=0;j<9;j++) {
                for (int i = 0; i < 8; i++) {
                    if (this.mybuttons[i+1][j].sravnenie(0)){
                        this.mybuttons[i+1][j].setValue(this.mybuttons[i][j].znn);
                        this.mybuttons[i][j].setValue(0);
                    }
                }
            }
            marker=true;
            for (int j=0;j<9;j++) {
                for (int i = 8; i>0; i=i-1) {
                    if (this.mybuttons[i][j].sravnenie(0) && !this.mybuttons[i-1][j].sravnenie(0)){
                        marker=false;
                    }
                }
            }
        }
        int a=2;
        int b=6;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.mybuttons[i][j].sravnenie(0)){
                    this.mybuttons[i][j].setValue(rnd(a,b));
                }
            }
        }
        check1(mybuttons);
        check2(mybuttons);
        boolean ost=true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.mybuttons[i][j].sravnenie(0)){
                    ost=false;
                }
            }
        }
        if (ost==false){
            RePaint();
        }
    }
}
