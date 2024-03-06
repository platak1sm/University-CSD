package Model;

import java.util.Objects;
import java.util.Stack;

public class Sullogi {
    public Stack<DealCard> DealCardStack;
    public Stack<MailCard> MailCardStack;

    private String type;

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new Sullogi with a new deal/mail card Stack.
     */
    public Sullogi(String type) {
        if (Objects.equals(type, "DealCard")) {
            DealCardStack = new Stack<DealCard>();
        } else if (Objects.equals(type, "MailCard")) {
            MailCardStack = new Stack<MailCard>();
        } else {
            throw new IllegalArgumentException("Wrong Type(Game only accepts DealCard and MailCard as Card types)");
        }
        this.type = type;
    }

    /**
     * <b>Accessor:</b> returns all the deal cards
     * <b>Postcondition:</b> all the deal cards has been returned
     *
     * @return all the deal cards
     */
    public Stack<DealCard> getDealCardStack() {
        return this.DealCardStack;
    }

    /**
     * <b>Accessor:</b> returns all the mail cards
     * <b>Postcondition:</b> all the mail cards has been returned
     *
     * @return all the mail cards
     */
    public Stack<MailCard> getMailCardStack() {
        return this.MailCardStack;
    }


    /**
     * <b>Transformer(mutative)</b>: It sets the new stack of deal cards <br />
     * <b>Postcondition</b>:the new stack of deal cards is changed to arr
     *
     * @param arr the new stack of deal cards
     */
    public void setDealCardStack(Stack<DealCard> arr) {
        this.DealCardStack = arr;
    }

    /**
     * <b>Transformer(mutative)</b>: It sets the new stack of mail cards <br />
     * <b>Postcondition</b>:the new stack of mail cards is changed to arr
     *
     * @param arr the new stack of mail cards
     */
    public void setMailCardStack(Stack<MailCard> arr) {
        this.MailCardStack = arr;
    }

    /**
     * Initializes and shuffles the cards.
     * <b>Postcondition:</b> The cards have been initialized and shuffled.
     */
    public void initCards() {

    }

    /**
     * Returns true if deal card deck contains no elements.
     * <b>Postcondition:</b> Returns true if deal card deck contains no elements.
     *
     * @return true if deal card deck contains no elements
     */
    public boolean isEmptyDeal() {

        return false;
    }

    /**
     * Returns true if mail card deck contains no elements.
     * <b>Postcondition:</b> Returns true if mail card deck contains no elements.
     *
     * @return true if mail card deck contains no elements
     */
    public boolean isEmptyMail() {

        return false;
    }

    /**
     * Adds a deal card to the stack.
     * <b>Postcondition:</b> A deal card has been added to the stack.
     *
     * @param dealCard a DealCard
     */
    public void addCard(DealCard dealCard) {

    }

    /**
     * Adds a mail card to the stack.
     * <b>Postcondition:</b> A mail card has been added to the stack.
     *
     * @param mailCard a MailCard
     */
    public void addCard(MailCard mailCard) {

    }

    /**
     * Adds a card to the stack.
     * <b>Postcondition:</b> A card has been added to the stack.
     *
     * @param Card a Card
     */
    public void addCardtoPlayer(Card Card) {

    }

    /**
     * Removes a card from the list.
     * <b>Postcondition:</b> A card has been removed from the list.
     *
     * @param i the index
     */
    public void removeCard(int i) {

    }

    /**
     * Returns the size of a DealCardStack.
     * <Postcondition:</b> The size of the DealCardStack has been returned.
     *
     * @return size of the DealCardStack
     */
    public int sizeDeal() {
        return DealCardStack.size();
    }

    /**
     * Returns the MailCardStack of a stack.
     * <Postcondition:</b> The size of the MailCardStack has been returned.
     *
     * @return size of the MailCardStack
     */
    public int sizeMail() {
        return MailCardStack.size();
    }



    /**
     * <b>Accessor:</b> returns the deal card in position 'index'
     * <b>Postcondition:</b> the deal card in position 'index' has been returned
     *
     * @return the card in position 'index'
     */
    public DealCard getCardD(int index) {
        return DealCardStack.get(index);
    }

    /**
     * <b>Accessor:</b> returns the mail card in position 'index'
     * <b>Postcondition:</b> the mail card in position 'index' has been returned
     *
     * @return the card in position 'index'
     */
    public MailCard getCardM(int index) {
        return MailCardStack.get(index);
    }

    /**
     * Clears a Stack
     * <b>Postcondition:</b> A Stack is cleared.
     */
    public void clearAll() {

    }

}
