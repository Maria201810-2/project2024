import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    protected int x;
    protected int y;
    protected int znn;
    public MyButton(int i, int j, int z) {
        super(String.valueOf(z));
        this.x = i;
        this.y = j;
        this.znn = z;
        Color d=new Color(238,238,238);
        super.setBackground(d);
    }
    public void setValue(int value){
        this.znn = value;
        setText(String.valueOf(znn));
    }
    public boolean sravnenie(int a){
        return (this.znn==a);
    }
    public boolean sravn(MyButton my){
        return (this.znn==my.znn);
    }
    public void setBackground() {
        Color cl=new Color(0,255,255);
        super.setBackground(cl);
    }
    public void delBackground(){
        Color cl=new Color(238,238,238);
        super.setBackground(cl);
    }
    public int getkx(){
        return this.x;
    }
    public int getky(){
        return this.y;
    }
    public int getnznn(){
        return this.znn;
    }
    public boolean getBackgr(){
        boolean sost=false;
        Color zv=super.getBackground();
        if (zv.getRed()==0 && zv.getGreen()==255 && zv.getBlue()==255){
            sost=true;
        }
        return sost;
    }
    public void Exchangeznn(MyButton a){
        int t=this.znn;
        this.znn=a.getnznn();
        a.znn=t;
    }
}