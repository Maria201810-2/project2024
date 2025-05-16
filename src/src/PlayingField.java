import javax.swing.*;
import java.awt.*;

public class PlayingField extends JFrame {
    public static final int NotUserElement=1;
    public static final int SpecialLine=7;
    public static final int SpecialColumn=8;
    public static final int SpecialElement=9;
    public static final int NoElement=0;
    public static final int minElement=2;
    public static final int maxElement=6;

    private static final int sizeField = 9;
    private static final int horizontalGap = 5;
    private static final int verticalGap = 12;

    private int random(int min, int max) {//функция случайного числа из диапазона(разобраться с синтаксисом)
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private MyButton[][] NewButt() {
        MyButton[][] mybutt = new MyButton[sizeField][sizeField];
        for (int i=0; i<9; i++) {
            mybutt[0][i] = new MyButton(0, i, NotUserElement);
        }
        for (int i = 1; i < sizeField; i++) {
            for (int j = 0; j < sizeField; j++) {
                mybutt[i][j] = new MyButton(i, j, random(minElement, maxElement)
                );
            }
        }
        return mybutt;
    }

    private MyButton[][] mybuttons = NewButt();

    public PlayingField() {
        super("Three in a row");
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
        GridLayout layout = new GridLayout(sizeField, sizeField, horizontalGap, verticalGap);
        grid.setLayout(layout);
        for (int i = 0; i < sizeField; i++) {
            for (int j = 0; j < sizeField; j++) {
                grid.add(mybuttons[i][j]);
                mybuttons[i][j].addActionListener(e -> {
                    MyButton btn = (MyButton) e.getSource();
                    int x0 = btn.getKX();
                    int y0 = btn.getKY();
                    int x1 = 0;
                    int y1 = 0;
                    boolean AnyButtonPressed = false;
                    for (int i1 = 0; i1 < sizeField; i1++) {
                        for (int j1 = 0; j1 < sizeField; j1++) {
                            if (mybuttons[i1][j1].getBack()) {
                                x1= i1;
                                y1= j1;
                                AnyButtonPressed = true;
                            }
                        }
                    }
                    if (btn.compareNumber(NotUserElement) && !AnyButtonPressed) {}
                    else if (!AnyButtonPressed) {
                        btn.setBackground();
                    }
                    else if (x0==x1 && y0==y1) {
                        switch (btn.getnZnn()){
                            case 7:
                                mybuttons[x0][y0].delBackground();
                                for (int q=0;q<sizeField;q++){
                                    mybuttons[x0][q].setValue(NoElement);
                                }
                                rePaint();
                                break;
                            case 8:
                                mybuttons[x0][y0].delBackground();
                                for (int q=0;q<sizeField;q++){
                                    mybuttons[q][y0].setValue(NoElement);
                                }
                                rePaint();
                                break;
                            default:
                                btn.delBackground();
                        }
                    }
                    else if (((x0==x1 && Math.abs(y0-y1)==1) || (y0==y1 && Math.abs(x0-x1)==1))
                            && (btn.compareNumber(SpecialElement) || mybuttons[x1][y1].compareNumber(SpecialElement))
                            && !mybuttons[x1][y1].compareNumber(NotUserElement)
                            && !btn.compareNumber(NotUserElement)){
                        int deletedElement=Math.min(mybuttons[x1][y1].getnZnn(),mybuttons[x0][y0].getnZnn());
                        mybuttons[x1][y1].delBackground();
                        mybuttons[x0][y0].setValue(NoElement);
                        mybuttons[x1][y1].setValue(NoElement);
                        for (int i1 = 0; i1 <9; i1++){
                            for (int j1 = 0; j1 <9; j1++){
                                if (mybuttons[i1][j1].getnZnn()==deletedElement){
                                    mybuttons[i1][j1].setValue(NoElement);
                                }
                            }
                        }
                        rePaint();
                    }
                    else if (x0==x1 && Math.abs(y0-y1)==1) {
                        mybuttons[x1][y1].delBackground();
                        mybuttons[x0][y0].exchangeZnn(mybuttons[x1][y1]);
                        boolean testRealized= checkInColumsCorrect(mybuttons, y0) || checkInColumsCorrect(mybuttons, y1) || checkInRowsCorrect(mybuttons, x0);
                        if (!testRealized) {
                            mybuttons[x0][y0].exchangeZnn(mybuttons[x1][y1]);
                        }
                        if (testRealized) {
                            rePaint();
                        }
                    }
                    else if (y0==y1 && Math.abs(x0-x1)==1) {
                        mybuttons[x1][y1].delBackground();
                        mybuttons[x0][y0].exchangeZnn(mybuttons[x1][y1]);
                        boolean testRealized = checkInRowsCorrect(mybuttons, x0) || checkInRowsCorrect(mybuttons, x1) || checkInColumsCorrect(mybuttons, y0);
                        if (!testRealized) {
                            mybuttons[x0][y0].exchangeZnn(mybuttons[x1][y1]);
                        }
                        if (testRealized) {
                            rePaint();
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

    private void checkInRows(MyButton mas[][]) {
        for (int i = 0; i < sizeField; i++) {
            for (int j = 1; j < sizeField; j++) {
                int numberinarow = 0;
                int linestart = 0;
                if (mas[i][j - 1].compareButton(mas[i][j]) && !mas[i][j].compareNumber(NotUserElement) && !mas[i][j].compareNumber(NoElement)) {
                    numberinarow = 2;
                    linestart = j - 1;
                    while (j < sizeField-1 && mas[i][j].compareButton(mas[i][j + 1])) {
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

    static void checkInColumns(MyButton mas[][]) {
        for (int j = 0; j < sizeField; j++) {
            for (int i = 1; i < sizeField; i++) {
                int numberinarow = 0;
                int linestart = 0;
                if (mas[i - 1][j].compareButton(mas[i][j]) && !mas[i][j].compareNumber(NotUserElement) && !mas[i][j].compareNumber(NoElement)) {
                    numberinarow = 2;
                    linestart = i - 1;
                    while (i < sizeField-1 && mas[i][j].compareButton(mas[i + 1][j])) {
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

    private boolean checkInColumsCorrect(MyButton mas[][], int numberColumn) {
        boolean result=false;
        for (int i = 1; i < sizeField; i++) {
            int numberinarow = 0;
            int linestart = 0;
            if (mas[i - 1][numberColumn].compareButton(mas[i][numberColumn]) && !mas[i][numberColumn].compareNumber(NotUserElement)) {
                numberinarow = 2;
                linestart = i - 1;
                while (i < sizeField-1 && mas[i][numberColumn].compareButton(mas[i + 1][numberColumn])) {
                    numberinarow++;
                    i++;
                }
                i = linestart + 1;
                switch (numberinarow) {
                    case 2:
                        break;

                    case 3:
                        for (int k = linestart; k < linestart + numberinarow; k++) {
                            mas[k][numberColumn].setValue(NoElement);
                        }
                        result=true;
                        break;
                    case 4:
                        for (int k = linestart; k < linestart + numberinarow - 1; k++) {
                            mas[k][numberColumn].setValue(NoElement);
                        }
                        mas[linestart + numberinarow - 1][numberColumn].setValue(SpecialLine);
                        result=true;
                        break;
                    default:
                        for (int k = linestart; k < linestart + numberinarow - 1; k++) {
                            mas[k][numberColumn].setValue(NoElement);
                        }
                        mas[linestart + numberinarow - 1][numberColumn].setValue(SpecialElement);
                        result=true;
                        break;
                }
            }
        }
        return result;
    }

    private boolean checkInRowsCorrect(MyButton mas[][], int numberRow) {
        boolean result = false;
        for (int j = 1; j < sizeField; j++) {
            int numberinarow = 0;
            int linestart = 0;
            if (mas[numberRow][j - 1].compareButton(mas[numberRow][j]) && !mas[numberRow][j].compareNumber(1)) {
                numberinarow = 2;
                linestart = j - 1;
                while (j < sizeField-1 && mas[numberRow][j].compareButton(mas[numberRow][j + 1])) {
                    numberinarow++;
                    j++;
                }
                j = linestart + 1;
                switch (numberinarow) {
                    case 2:
                        break;
                    case 3:
                        for (int k = linestart; k < linestart + numberinarow; k++) {
                            mas[numberRow][k].setValue(NoElement);
                            result = true;
                        }
                        break;
                    case 4:
                        for (int k = linestart + 1; k < linestart + numberinarow; k++) {
                            mas[numberRow][k].setValue(NoElement);
                        }
                        mas[numberRow][linestart].setValue(SpecialColumn);
                        result = true;
                        break;
                    default:
                        for (int k = linestart + 1; k < linestart + numberinarow; k++) {
                            mas[numberRow][k].setValue(NoElement);
                        }
                        mas[numberRow][linestart].setValue(SpecialElement);
                        result = true;
                        break;
                }
            }
        }
        return result;
    }

    public void rePaint() {
        for (int g=0;g<sizeField;g++){
            if (mybuttons[sizeField-1][g].compareNumber(NotUserElement)){
                mybuttons[sizeField-1][g].setValue(NoElement);
            }
        }
        checkInRows(mybuttons);
        checkInColumns(mybuttons);
        boolean marker = false;
        while (!marker) {
            for (int j = 0; j < sizeField; j++) {
                for (int i = 0; i < sizeField; i++) {
                    if (mybuttons[i + 1][j].compareNumber(NoElement)) {
                        mybuttons[i+1][j].exchangeZnn(mybuttons[i][j]);
                    }
                }
            }
            marker = true;
            for (int j = 0; j < sizeField; j++) {
                for (int i = sizeField-1; i > 0; i = i - 1) {
                    if (mybuttons[i][j].compareNumber(NoElement) && !mybuttons[i - 1][j].compareNumber(NoElement)) {
                        marker = false;
                    }
                }
            }
            for (int g=0;g<sizeField;g++){
                if (mybuttons[sizeField-1][g].compareNumber(NotUserElement)){
                    marker=false;
                    mybuttons[sizeField-1][g].setValue(NoElement);
                }
            }
        }
        int a = 2;
        int b = 6;
        for (int i = 0; i < sizeField; i++) {
            for (int j = 0; j < sizeField; j++) {
                if (mybuttons[i][j].compareNumber(NoElement)) {
                    mybuttons[i][j].setValue(random(a, b));
                }
            }
        }
        checkInRows(mybuttons);
        checkInColumns(mybuttons);
        boolean existsZeroInTable =false;
        for (int i = 0; i < sizeField; i++) {
            for (int j = 0; j < sizeField; j++) {
                if (mybuttons[i][j].compareNumber(NoElement)) {
                    existsZeroInTable = true;
                }
            }
        }
        if (existsZeroInTable) {
            rePaint();
        }
    }
}