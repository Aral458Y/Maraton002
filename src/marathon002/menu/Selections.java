package marathon002.menu;

public interface Selections {
	public void showMenu();
	public int getInput(String message);
	public void menuLoop();
	public void endLoop();
	public void menuSelection(int choice);

}
