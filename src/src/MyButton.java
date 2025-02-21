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
}
