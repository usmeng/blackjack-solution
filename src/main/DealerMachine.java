package main;

import inters.IDealAction;

import java.util.Random;

import bean.Card;

public class DealerMachine implements IDealAction {

	private final int DEFAULT_PERCENT = 80;
	private final int HUNDRED_PERCENT = 100;
	private Card[] cards;
	/** shuffle cards when reaches the stopPosition */
	private int stopPosition;
	private int currentPosition;
	private boolean onStop;
	private int deckSize;
	private int cardLength;

	public DealerMachine(int number) {
		initDeckCard(number, DEFAULT_PERCENT);
	}

	public DealerMachine(int number, int availableRate) {
		initDeckCard(number, availableRate);
	}

	public void initDeckCard(int number, int availableRate) {
		this.deckSize = number;
		this.cardLength = DeckCard.DECK_LENGTH * number;
		this.stopPosition = cardLength * availableRate / HUNDRED_PERCENT;

		cards = new Card[cardLength];
		for (int i = 0; i < number; i++) {
			DeckCard deck = DeckCard.newInstance();
			Card[] cs = deck.getCards();
			for (int j = 0; j < cs.length; j++) {
				cards[i * DeckCard.DECK_LENGTH + j] = cs[j];
			}
		}
	}

	@Override
	public void setStopPosition(int position) {
		stopPosition = position;
	}

	@Override
	public Card deal() {
		if (currentPosition < 0)
			return null;
		if (currentPosition == stopPosition) {
			onStop = true;
		}
		return cards[currentPosition++];
	}

	@Override
	public void shuffle() {

		Random random = new Random();
		for (int i = 0; i < cards.length; i++) {
			int value = random.nextInt(cards.length - i) + i;
			Card temp = cards[value];
			cards[value] = cards[i];
			cards[i] = temp;
		}

		currentPosition = 0;
		onStop = false;
	}

	public boolean isOnStop() {
		return onStop;
	}

	public void setOnStop(boolean stop) {
		this.onStop = stop;
	}

	public int getCardLength() {
		return cardLength;
	}

	public int getDeckSize() {
		return deckSize;
	}

	public void print() {
		for (int i = 0; i < cards.length; i++) {
			Card card = cards[i];
			System.out.println(i + 1 + ": " + card.toString());
		}
	}

	public static void main(String[] args) {
		DealerMachine machine = new DealerMachine(8, 100);
		machine.shuffle();
		machine.print();
	}
}
