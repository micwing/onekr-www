package onekr.framework.java.util.observer;

public class Observable extends java.util.Observable {
	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}
}
