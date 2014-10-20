package onekr.framework.lang;

public class Observable extends java.util.Observable {
	
	@Override
	public void setChanged() {
		super.setChanged();
	}
	
	@Override
	public void notifyObservers(Object arg) {
		super.notifyObservers(arg);
	}
	
	public void notifyObserversNow(Object arg) {
		this.setChanged();
		this.notifyObservers(arg);
	}
}
