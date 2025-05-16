import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    protected int x;
    protected int y;
    protected int znn;
    public MyButton(int x, int y, int znn) {
        super();
        this.x = x;
        this.y = y;
        this.znn = znn;
        super.setBackground(new Color(238,238,238));
        setMyIcon(znn);
    }
    public void setValue(int value){
        this.znn = value;
        setMyIcon(value);
    }
    public boolean compareNumber(int a){
        return (this.znn==a);
    }
    public boolean compareButton(MyButton my){
        return (this.znn==my.znn);
    }
    public void setBackground() {
        super.setBackground(new Color(0,255,255));
    }
    public void delBackground(){
        super.setBackground(new Color(238,238,238));
    }
    public int getKX(){
        return this.x;
    }
    public int getKY(){
        return this.y;
    }
    public int getnZnn(){
        return this.znn;
    }
    public boolean getBack(){
        boolean testPressButton=false;
        Color colorButton=super.getBackground();
        if (colorButton.getRed()==0 && colorButton.getGreen()==255 && colorButton.getBlue()==255){
            testPressButton=true;
        }
        return testPressButton;
    }
    public void exchangeZnn(MyButton a){
        int t=this.znn;
        this.znn=a.getnZnn();
        a.znn=t;
        setMyIcon(this.znn);
        a.setMyIcon(a.getnZnn());
    }
    public void setMyIcon(int elementNumber){
        String Path;
        switch (elementNumber){
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