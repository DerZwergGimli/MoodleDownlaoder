//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Menu;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.MenuItem;

public class JFrame {

	protected Shell shlMyjavagui; 

	/**
	 * Launch the application. 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JFrame window = new JFrame();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlMyjavagui.open();
		shlMyjavagui.layout();
		while (!shlMyjavagui.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlMyjavagui = new Shell();
		shlMyjavagui.setSize(450, 300);
		shlMyjavagui.setText("MyJava_GUI");
		shlMyjavagui.setLayout(null);
		
		Menu menu = new Menu(shlMyjavagui, SWT.BAR);
		shlMyjavagui.setMenuBar(menu);
		
		
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.CASCADE);
		mntmNewItem.setText("Menu");
		
		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_2.setText("New Item");
		
		
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("New");
		
		MenuItem mntmNewItem_3 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_3.setText("New Item");

	}
}
