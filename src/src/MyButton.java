import javax.swing.*;
public class MyButton extends JButton {
    protected int x;
    protected int y;
    protected int znn;
    public MyButton(int i, int j, int z) {
        super(String.valueOf(z));
        this.x = i;
        this.y = j;
        this.znn = z;
    }
    public void setValue(int value){
        znn = value;
        setText(String.valueOf(znn));
    }
    public boolean sravnenie(int a){
        return (this.znn==a);
    }
    public boolean sravn(MyButton my){
        return (this.znn==my.znn);
    }
}