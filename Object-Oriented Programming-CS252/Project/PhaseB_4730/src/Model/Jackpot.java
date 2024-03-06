package Model;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Jackpot {
    private  int money=0;
    private String image;

    /**
     * <b>constructor</b>: Creates a new Jackpot   <br />
     * <b>postconditions</b>: Creates a new Jackpot
     * starting a new Jackpot
     */
    public Jackpot(){
    }

    public void refresh_money( int num, String operation ){
        if (operation=="+") this.money+=num;
        else if(operation=="-") {
            if (num>this.money) this.money=0;
            else this.money-=num;
        }
    }

    public void init_money(){

    }

    public void setMoney( int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}