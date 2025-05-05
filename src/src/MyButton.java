import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    protected int x;
    protected int y;
    protected int znn;
    public MyButton(int i, int j, int z) {
        super();
        this.x = i;
        this.y = j;
        this.znn = z;
        Color d=new Color(238,238,238);
        super.setBackground(d);
        setmyIcon(z);
    }
    public void setValue(int value){
        this.znn = value;
        setmyIcon(value);
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
        setmyIcon(this.znn);
        a.setmyIcon(a.getnznn());
        //setText(String.valueOf(znn));
        //a.setText(String.valueOf(a.znn));
    }
    public void setmyIcon(int a){
        String Path;
        switch (a){
            case 1:
                Path="MyImages/flower.png";
                break;
            case 2:
                Path="MyImages/fox4.png";
                break;
            case 3:
                Path="MyImages/tiger.png";
                break;
            case 4:
                Path="MyImages/crane.png";
                break;
            case 5:
                Path="MyImages/snake.png";
                break;
            case 6:
                Path="MyImages/fishs.png";
                break;
            case 7:
                Path="MyImages/dragon2.png";
                break;
            case 8:
                Path="MyImages/dragon1.png";
                break;
            case 9:
                Path="MyImages/veer.png";
                break;
            default:
                Path="MyImages/veer.png";
                break;
        }
        setIcon(new ImageIcon(Path));
    }
}