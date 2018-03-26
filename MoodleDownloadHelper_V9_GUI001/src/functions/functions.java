
package functions;

import java.util.Hashtable;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class functions 
{
	
	private String extractedWebseiteEelemnts[][];
	private DefaultMutableTreeNode myJTreeRoot;
	
	private DefaultMutableTreeNode makeJTree() 
	{
		
		    myJTreeRoot = new DefaultMutableTreeNode("Root");
		    Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
		    hashtable.put ("java2s.com", new String[]{"A", "B", "C"});
		    
		    Hashtable<Object, Object> innerHashtable = new Hashtable<Object, Object>();

		    innerHashtable.put ("Two", new String[]{"A", "B", "C"});
		    
		    hashtable.put ("Three", innerHashtable);
		    JTree.DynamicUtilTreeNode.createChildren(myJTreeRoot, hashtable);
		
		    return myJTreeRoot;
	}
	
	
	
	

}
