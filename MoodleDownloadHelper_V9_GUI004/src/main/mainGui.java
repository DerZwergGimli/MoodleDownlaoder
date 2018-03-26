package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.util.Hashtable;
import java.util.TreeSet;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import functions.MConnect;
import objects.FolderAndFiles;

public class mainGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGui window = new mainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String username = "ya721pau";
		String password = "htwg-gai-22";
		String urlMoodleBase = "https://moodle.htwg-konstanz.de/moodle/login/index.php";
		
		MConnect moodleConnection = new MConnect(username, password, urlMoodleBase);
		//moodleConnection.documentToElements("a[href]");
		
		
		System.out.println(moodleConnection.getUrlSiteInMoodle());
		
		
		//Folder???
		
		FolderAndFiles kurse = new FolderAndFiles(moodleConnection.getElements());
		
		
		
		
		
		JTree tree = new JTree(makeJTree());
		tree.setBounds(12, 30, 154, 258);
		
		
		
       /* TreeSet<String> tset = new TreeSet<String>();

        // Adding elements to TreeSet<String>
        tset.add("ABC");
        tset.add("String");
        tset.add("Test");
        tset.add("Pen");
        tset.add("Ink");
        tset.add("Jack");

        //Displaying TreeSet
        System.out.println(tset);
        */
		
		frame.getContentPane().add(tree);
		
	}
	
	
	


	private DefaultMutableTreeNode makeJTree() 
	{
		
		    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		    Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
		    hashtable.put ("java2s.com", new String[]{"A", "B", "C"});
		    
		    Hashtable<Object, Object> innerHashtable = new Hashtable<Object, Object>();

		    innerHashtable.put ("Two", new String[]{"A", "B", "C"});
		    
		    hashtable.put ("Three", innerHashtable);
		    JTree.DynamicUtilTreeNode.createChildren(root, hashtable);
		
		    return root;
	}
	
}
