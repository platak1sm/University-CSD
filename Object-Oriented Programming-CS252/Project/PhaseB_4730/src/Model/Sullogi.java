package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Stack;

public class Sullogi {
    public Stack<DealCard> DealCardStack;
    public Stack<MailCard> MailCardStack;
    public Stack<Card> RejectedCards;

    private String type;

    /**
     * Constructor.
     *
     * <b>Postcondition</b>Creates a new Sullogi with a new deal/mail card Stack.
     */
    public Sullogi(String type) {
        if (Objects.equals(type, "Deal")) {
            DealCardStack = new Stack<DealCard>();
        } else if (Objects.equals(type, "Mail")) {
            MailCardStack = new Stack<MailCard>();
        } else if (Objects.equals(type, "Card")) {
            RejectedCards = new Stack<Card>();
        } else {
            throw new IllegalArgumentException("Wrong Type(Game only accepts Deal,Mail and Card as Card types)");
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
     * <b>Accessor:</b> returns all the rejected cards
     * <b>Postcondition:</b> all the rejected cards has been returned
     *
     * @return all the rejected cards
     */
    public Stack<Card> getRejectedCards() {
        return this.RejectedCards;
    }


    /**
     * <b>Transformer(mutative)</b>: It sets the new stack of rejected cards <br />
     * <b>Postcondition</b>:the new stack of rejected cards is changed to arr
     *
     * @param arr the new stack of rejected cards
     */
    public void setRejectedCards(Stack<Card> arr) {
        this.RejectedCards = arr;
    }

    /**
     * Initializes and shuffles the cards.
     * <b>Postcondition:</b> The cards have been initialized and shuffled.
     */
    public void initCards(String type) {
        if (Objects.equals(type, "Deal")) {
            if (!DealCardStack.isEmpty())
                Collections.shuffle(DealCardStack);
        }
        if (Objects.equals(type, "Mail")) {
            if (!MailCardStack.isEmpty())
                Collections.shuffle(MailCardStack);
        }
        if (Objects.equals(type, "Card")) {
            if (!RejectedCards.isEmpty())
                Collections.shuffle(RejectedCards);
        }
    }

    /**
     * Returns true if deal card deck contains no elements.
     * <b>Postcondition:</b> Returns true if deal card deck contains no elements.
     *
     * @return true if deal card deck contains no elements
     */
    public boolean isEmptyDeal() {
        return DealCardStack.isEmpty();
    }

    /**
     * Returns true if mail card deck contains no elements.
     * <b>Postcondition:</b> Returns true if mail card deck contains no elements.
     *
     * @return true if mail card deck contains no elements
     */
    public boolean isEmptyMail() {
        return MailCardStack.isEmpty();
    }

    /**
     * Adds a deal card to the stack.
     * <b>Postcondition:</b> A deal card has been added to the stack.
     *
     * @param dealCard a DealCard
     */
    public void addCard(DealCard dealCard) {
        DealCardStack.add(dealCard);
    }

    /**
     * Adds a mail card to the stack.
     * <b>Postcondition:</b> A mail card has been added to the stack.
     *
     * @param mailCard a MailCard
     */
    public void addCard(MailCard mailCard) {
        MailCardStack.add(mailCard);
    }

    /**
     * Adds a rejected card to the stack.
     * <b>Postcondition:</b> A rejected card has been added to the stack.
     *
     * @param card a Card
     */
    public void addCard(Card card) {
        RejectedCards.add(card);
    }

    /**
     * Removes a card from the list.
     * <b>Postcondition:</b> A card has been removed from the list.
     *
     * @param i the index
     */
    public void removeCard(int i, String type) {
        if (Objects.equals(type, "Mail")) {
            MailCardStack.remove(i);
        } else if (Objects.equals(type, "Deal")) {
            DealCardStack.remove(i);
        }
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
    public void clearAll(String type) {
        if (Objects.equals(type, "Mail")) {
            MailCardStack.clear();
        } else if (Objects.equals(type, "Deal")) {
            DealCardStack.clear();
        }
    }


}
