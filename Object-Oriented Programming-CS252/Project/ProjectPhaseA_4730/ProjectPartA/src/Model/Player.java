package Model;

/**
 * Player class describes the characteristics of a player
 * and provides modification methods.
 *
 * @author Manos Platakis
 */
public class Player {
    private String name;
    private int money=-1;
    private int bills=-2;
    private int loan=-2;
    private Position pos;
    private static int plnum = 1;
    private Sullogi sullogi;


    /**
     * Default Constructor.
     *
     * <b>Postcondition</b>Creates a new instance of Player with name= "Player"+String.valueOf(plnum)
     */
    public Player() {
        this.name = "Player" + String.valueOf(plnum);
        if (plnum == 1) {
            plnum++;
        }
        this.money = 0;
        this.bills = 0;
        this.loan = 0;
        this.sullogi= new Sullogi("DealCard");
    }

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new instance of Player with new name, money, bills, loan, position.
     */
    public Player(String name, int money, int bills, int loan, Position pos) {
        this.name = name;
        this.money = money;
        this.bills = bills;
        this.loan = loan;
        this.pos = pos;
    }

    /**
     * <b>transformer(mutative)</b>: It sets the name of a player <br />
     * <b>postcondition</b>:the name of the player is changed to name
     *
     * @param name the new name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <b>accessor(selector)</b>:Returns the name of the player <br />
     *
     * <p><b>Postcondition:</b> returns the name of the player </p>
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * <b>transformer(mutative)</b>: It sets the money of a player <br />
     * <b>postcondition</b>:the money of the player is changed to money
     *
     * @param money the new money of the player
     */
    public void setMoney(int money) {
        if (money>0) this.money=money;
        else throw new IllegalArgumentException("money is >=0");
    }

    /**
     * <b>accessor(selector)</b>:Returns the money of the player <br />
     *
     * <p><b>Postcondition:</b> returns the money of the player </p>
     *
     * @return the money of the player
     */
    public int getMoney() throws Exception {
        if(money!=-1) return this.money;
        else throw new Exception("Money<0");
    }
    /**
     * <b>transformer(mutative)</b>: It sets the loan of a player <br />
     * <b>postcondition</b>:the loan of the player is changed to loan
     *
     * @param loan the new loan of the player
     */
    public void setLoan(int loan) {
        if (loan>=0) this.name = name;
        else throw new IllegalArgumentException("Loan must be >=0");
    }

    /**
     * <b>accessor(selector)</b>:Returns the loan of the player <br />
     *
     * <p><b>Postcondition:</b> returns the loan of the player </p>
     *
     * @return the loan of the player
     */
    public int getLoan() throws Exception {
        if(loan!=-2) return this.loan;
        else throw new Exception("Loan<0");
    }
    /**
     * <b>transformer(mutative)</b>: It sets the bills of a player <br />
     * <b>postcondition</b>:the bills of the player is changed to bills
     *
     * @param bills the new bills of the player
     */
    public void setBills(int bills) {
        if (bills>=0) this.bills= bills;
        else throw new IllegalArgumentException("Bills must be >=0");
    }

    /**
     * <b>accessor(selector)</b>:Returns the bills of the player <br />
     *
     * <p><b>Postcondition:</b> returns the bills of the player </p>
     *
     * @return the bills of the player
     */
    public int getBills() throws Exception {
        if(bills!=-2) return this.bills;
        else throw new Exception("bills<0");
    }
    /**
     * <b>transformer(mutative)</b>: It sets the pos of a player <br />
     * <b>postcondition</b>:the pos of the player is changed to pos
     *
     * @param pos the new pos of the player
     */
    public void setPos(Position pos) {
        this.pos=pos;
    }

    /**
     * <b>accessor(selector)</b>:Returns the pos of the player <br />
     *
     * <p><b>Postcondition:</b> returns the pos of the player </p>
     *
     * @return the loan of the player
     */
    public Position getPos() throws Exception {
        if(this.pos!=null) return this.pos;
        else throw new Exception("pos=null");
    }

}
