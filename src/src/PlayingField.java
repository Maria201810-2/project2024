import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayingField extends JFrame {
    public static final int NotUserElement=1;
    public static final int SpecialLine=7;
    public static final int SpecialColumn=8;
    public static final int SpecialElement=9;
    public static final int NoElement=0;
    public static final int minElement=2;
    public static final int maxElement=6;

    private int random(int min, int max) {//функция случайного числа из диапазона(разобраться с синтаксисом)
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private MyButton[][] NewButt() {
        MyButton[][] mybutt = new MyButton[9][9];
        for (int i=0; i<9; i++) {
            mybutt[0][i] = new MyButton(0, i, NotUserElement);
        }
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mybutt[i][j] = new MyButton(i, j, random(minElement, maxElement)
                );
            }
        }
        return mybutt;
    }

    private MyButton[][] mybuttons = NewButt();

    public PlayingField() {
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid.add(mybuttons[i][j]);
                mybuttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MyButton btn = (MyButton) e.getSource();
                        int x0 = btn.getkx();
                        int y0 = btn.getky();
                        int x1 = 0;
                        int y1 = 0;
                        boolean AnyButtonPressed = false;
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (mybuttons[i][j].getBackgr()) {
                                    x1=i;
                                    y1=j;
                                    AnyButtonPressed = true;
                                }
                            }
                        }
                        if (btn.sravnenie(NotUserElement) && !AnyButtonPressed) {}
                        else if (!AnyButtonPressed) {
                            btn.setBackground();
                        }
                        else if (x0==x1 && y0==y1) {
                            switch (btn.getnznn()){
                                case 7:
                                    for (int q=0;q<9;q++){
                                        mybuttons[x0][q].setValue(NoElement);
                                    }
                                    rePaint();
                                    break;
                                case 8:
                                    for (int q=0;q<9;q++){
                                        mybuttons[q][y0].setValue(NoElement);
                                    }
                                    rePaint();
                                    break;
                                default:
                                    btn.delBackground();
                            }
                        }
                        else if ((x0==x1 && Math.abs(y0-y1)==1) || (y0==y1 && Math.abs(x0-x1)==1) && btn.sravnenie(SpecialElement) || mybuttons[x1][y1].sravnenie(SpecialElement)){
                            int znver=Math.min(mybuttons[x1][y1].getnznn(),mybuttons[x0][y0].getnznn());
                            for (int i=0;i<9;i++){
                                for (int j=0;j<0;j++){
                                    if (mybuttons[i][j].getnznn()==znver){
                                        mybuttons[i][j].setValue(NoElement);
                                    }
                                }
                            }
                            rePaint();
                        }
                        else if (x0==x1 && Math.abs(y0-y1)==1) {
                            mybuttons[x1][y1].delBackground();
                            mybuttons[x0][y0].Exchangeznn(mybuttons[x1][y1]);
                            boolean veupol= check2y(mybuttons, y0) || check2y(mybuttons, y1) || check1y(mybuttons, x0);
                            if (!veupol) {
                                mybuttons[x0][y0].Exchangeznn(mybuttons[x1][y1]);
                            }
                            if (veupol) {
                                rePaint();
                            }
                        }
                        else if (y0==y1 && Math.abs(x0-x1)==1) {
                            mybuttons[x1][y1].delBackground();
                            mybuttons[x0][y0].Exchangeznn(mybuttons[x1][y1]);
                            boolean veupol = check1y(mybuttons, x0) || check1y(mybuttons, x1) || check2y(mybuttons, y0);
                            if (!veupol) {
                                mybuttons[x0][y0].Exchangeznn(mybuttons[x1][y1]);
                            }
                            if (veupol) {
                                rePaint();
                            }
                        }
                    }
                });

            }
        }
        rePaint();
        // Размещаем нашу панель в панели содержимого
        getContentPane().add(grid);
        // Устанавливаем оптимальный размер окна
        pack();
        // Открываем окно
        setVisible(true);
    }

    private void check1(MyButton mas[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                int numberinarow = 0;
                int linestart = 0;
                if (mas[i][j - 1].sravn(mas[i][j]) && !mas[i][j].sravnenie(NotUserElement) && !mas[i][j].sravnenie(NoElement)) {
                    numberinarow = 2;
                    linestart = j - 1;
                    while (j < 8 && mas[i][j].sravn(mas[i][j + 1])) {
                        numberinarow++;
                        j++;
                    }
                    j = linestart + 1;
                    switch (numberinarow) {
                        case 2:
                            break;
                        case 3:
                            for (int k = linestart; k < linestart + numberinarow; k++) {
                                mas[i][k].setValue(NoElement);
                            }
                            break;
                        case 4:
                            for (int k = linestart + 1; k < linestart + numberinarow; k++) {
                                mas[i][k].setValue(NoElement);
                            }
                            mas[i][linestart].setValue(SpecialColumn);
                            break;
                        default:
                            for (int k = linestart + 1; k < linestart + numberinarow; k++) {
                                mas[i][k].setValue(NoElement);
                            }
                            mas[i][linestart].setValue(SpecialElement);
                            break;
                    }
                }
            }
        }
    }

    static void check2(MyButton mas[][]) {
        for (int j = 0; j < 9; j++) {
            for (int i = 1; i < 9; i++) {
                int numberinarow = 0;
                int linestart = 0;
                if (mas[i - 1][j].sravn(mas[i][j]) && !mas[i][j].sravnenie(NotUserElement) && !mas[i][j].sravnenie(NoElement)) {
                    numberinarow = 2;
                    linestart = i - 1;
                    while (i < 8 && mas[i][j].sravn(mas[i + 1][j])) {
                        numberinarow++;
                        i++;
                    }
                    i = linestart + 1;
                    switch (numberinarow) {
                        case 2:
                            break;
                        case 3:
                            for (int k = linestart; k < linestart + numberinarow; k++) {
                                mas[k][j].setValue(NoElement);
                            }
                            break;
                        case 4:
                            for (int k = linestart; k < linestart + numberinarow - 1; k++) {
                                mas[k][j].setValue(NoElement);
                            }
                            mas[linestart + numberinarow - 1][j].setValue(SpecialLine);
                            break;
                        default:
                            for (int k = linestart; k < linestart + numberinarow - 1; k++) {
                                mas[k][j].setValue(NoElement);
                            }
                            mas[linestart + numberinarow - 1][j].setValue(SpecialElement);
                            break;
                    }
                }
            }
        }
    }

    private boolean check2y(MyButton mas[][], int t) {
        boolean result=false;
        for (int i = 1; i < 9; i++) {
            int numberinarow = 0;
            int linestart = 0;
            if (mas[i - 1][t].sravn(mas[i][t]) && !mas[i][t].sravnenie(NotUserElement)) {
                numberinarow = 2;
                linestart = i - 1;
                while (i < 8 && mas[i][t].sravn(mas[i + 1][t])) {
                    numberinarow++;
                    i++;
                }
                i = linestart + 1;
                switch (numberinarow) {
                    case 2:
                        break;
                    case 3:
                        for (int k = linestart; k < linestart + numberinarow; k++) {
                            mas[k][t].setValue(NoElement);
                        }
                        result=true;
                        break;
                    case 4:
                        for (int k = linestart; k < linestart + numberinarow - 1; k++) {
                            mas[k][t].setValue(NoElement);
                        }
                        mas[linestart + numberinarow - 1][t].setValue(SpecialLine);
                        result=true;
                        break;
                    default:
                        for (int k = linestart; k < linestart + numberinarow - 1; k++) {
                            mas[k][t].setValue(NoElement);
                        }
                        mas[linestart + numberinarow - 1][t].setValue(SpecialElement);
                        result=true;
                        break;
                }
            }
        }
        return result;
    }

    private boolean check1y(MyButton mas[][], int t) {
        boolean result = false;
        for (int j = 1; j < 9; j++) {
            int numberinarow = 0;
            int linestart = 0;
            if (mas[t][j - 1].sravn(mas[t][j]) && !mas[t][j].sravnenie(1)) {
                numberinarow = 2;
                linestart = j - 1;
                while (j < 8 && mas[t][j].sravn(mas[t][j + 1])) {
                    numberinarow++;
                    j++;
                }
                j = linestart + 1;
                switch (numberinarow) {
                    case 2:
                        break;
                    case 3:
                        for (int k = linestart; k < linestart + numberinarow; k++) {
                            mas[t][k].setValue(NoElement);
                            result = true;
                        }
                        break;
                    case 4:
                        for (int k = linestart + 1; k < linestart + numberinarow; k++) {
                            mas[t][k].setValue(NoElement);
                        }
                        mas[t][linestart].setValue(SpecialColumn);
                        result = true;
                        break;
                    default:
                        for (int k = linestart + 1; k < linestart + numberinarow; k++) {
                            mas[t][k].setValue(NoElement);
                        }
                        mas[t][linestart].setValue(SpecialElement);
                        result = true;
                        break;
                }
            }
        }
        return result;
    }

    public void rePaint() {
        check1(mybuttons);
        check2(mybuttons);
        boolean marker = false;
        while (!marker) {
            for (int j = 0; j < 9; j++) {
                for (int i = 0; i < 8; i++) {
                    if (mybuttons[i + 1][j].sravnenie(NoElement)) {
                        mybuttons[i+1][j].Exchangeznn(mybuttons[i][j]);
                    }
                }
            }
            marker = true;
            for (int j = 0; j < 9; j++) {
                for (int i = 8; i > 0; i = i - 1) {
                    if (mybuttons[i][j].sravnenie(NoElement) && !mybuttons[i - 1][j].sravnenie(NoElement)) {
                        marker = false;
                    }
                }
            }
        }
        int a = 2;
        int b = 6;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (mybuttons[i][j].sravnenie(NoElement)) {
                    mybuttons[i][j].setValue(random(a, b));
                }
            }
        }
        check1(mybuttons);
        check2(mybuttons);
        boolean existsZeroInTable =false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (mybuttons[i][j].sravnenie(NoElement)) {
                    existsZeroInTable = true;
                }
            }
        }
        if (existsZeroInTable) {
            rePaint();
        }
    }
}
