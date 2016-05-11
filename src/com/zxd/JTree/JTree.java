package com.zxd.JTree;

import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class JTree {

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
			System.out.println("Usage: JTree path");
		}else{
			printFilesTree("", "", new File(args[0]));
		}
	}

}
