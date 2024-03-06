package Model;

import java.util.ArrayList;

/**
 * MailCard class describes the characteristics of a mail card
 * extends Class Card
 * and provides modification methods.
 *
 * @author Manos Platakis
 */
public class MailCard extends Card {
    private MailCardsType type;
    private String choice;
    private int price;

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new Card with the new image.
     *
     * @param image   the image of the card
     * @param type    the type of mail card
     * @param message the message
     */
    public MailCard(String image, String message, MailCardsType type, String choice, int price) {
        super(image, message);
        this.type = type;
        this.choice = choice;
        this.price = price;
    }

    @Override
    public void action(Player p1, Player p2, int price, Jackpot jp, ArrayList<Integer> db, int z) throws Exception {
        if (type == MailCardsType.PayTheNeighbor) {
            int currmoney1 = p1.getMoney(), currmoney2 = p2.getMoney();
            if (currmoney1 < price) {
                int l = 0;
                int currloan = p1.getLoan();
                do {
                    currmoney1 += 1000;
                    l += 1000;
                } while (currmoney1 < price);
                p1.setLoan(currloan + l);
            }
            p1.setMoney(currmoney1 - price);
            p2.setMoney(currmoney2 + price);

        } else if (type == MailCardsType.Advertisement) {
            int currmoney1 = p1.getMoney();
            p1.setMoney(currmoney1 + price);
        } else if (type == MailCardsType.Charity) {
            int currmoney1 = p1.getMoney();
            p1.setMoney(currmoney1 - price);
            jp.refresh_money(price, "+");
        } else if (type == MailCardsType.MoveToDeal_Buyer) {
            int currpos = p1.posi;
            if (!db.isEmpty()) p1.movePosition(db.get(0) - currpos);
            p1.movetodb = 1;
        } else if (type == MailCardsType.PayTheBills) {
            int currbills = p1.getBills();
            p1.setBills(currbills + price);
        } else if (type == MailCardsType.TakeMoneyFromTheNeighbor) {
            int currmoney1 = p1.getMoney(), currmoney2 = p2.getMoney();
            if (currmoney2 < price) {
                int l = 0;
                int currloan = p2.getLoan();
                do {
                    currmoney2 += 1000;
                    l += 1000;
                } while (currmoney2 < price);
                p2.setLoan(currloan + l);
            }
            p2.setMoney(currmoney2 - price);
            p1.setMoney(currmoney2 + price);
        }
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setType(MailCardsType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public String getChoice() {
        return choice;
    }

    public MailCardsType getType() {
        return type;
    }

}
