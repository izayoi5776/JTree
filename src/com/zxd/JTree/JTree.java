package com.zxd.JTree;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.JFrame;

public class JTree extends DropTarget{

	// output windows tree method
	static void printFilesTree(String forMe, String forSub, File f) throws UnsupportedEncodingException{
		PrintStream out = new PrintStream(System.out, true, "UTF-8");
		out.println(forMe + f.getName());
		File[] ff = f.listFiles();
		for(int i=0; ff != null && i<ff.length; i++){
			String forSubMe  = i==ff.length-1 ? "└─" : "├─";	// for files
			String forSubSub = i==ff.length-1 ? "    " : "│  ";	// for sub folder
			printFilesTree(forSub + forSubMe, forSub + forSubSub, ff[i]);
		}
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		if(args.length<1){
			//System.out.println("Usage: JTree path");
		    JFrame frame = new JFrame("JTree");
		    frame.setBounds(100, 100, 400, 320);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setVisible(true);
		    frame.setDropTarget(new JTree());

		}else{
			printFilesTree("", "", new File(args[0]));
		}
	}
	public void drop(DropTargetDropEvent dtde) {
		Transferable t = dtde.getTransferable();
		if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
			dtde.acceptDrop(DnDConstants.ACTION_REFERENCE);
			try {
				List list = (List)t.getTransferData(DataFlavor.javaFileListFlavor);
				for (int i = 0; i < list.size(); i++) {
					File f = (File) list.get(i);
					printFilesTree("", "", f);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
