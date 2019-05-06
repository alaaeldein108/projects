package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;
import java.awt.Color;
public class lzwgui {
	//d:\\data.txt
	private JFrame lzw;
	private JTextField textpath;
	private JTextField textnewpath;
	private JLabel lblNewPath;
	private JButton btnDecompress;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	public File file;
	public String path;
	public String created;
	public ArrayList<String> dict = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lzwgui window = new lzwgui();
					window.lzw.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public lzwgui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		lzw = new JFrame();
		lzw.setBackground(new Color(0, 0, 0));
		lzw.getContentPane().setForeground(new Color(139, 0, 0));
		lzw.getContentPane().setFont(new Font("Wingdings 3", Font.BOLD | Font.ITALIC, 11));
		lzw.getContentPane().setBackground(Color.ORANGE);
		lzw.setFont(new Font("Dialog", Font.BOLD, 12));
		lzw.setBounds(100, 100, 450, 300);
		lzw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lzw.getContentPane().setLayout(null);
		
		textpath = new JTextField();
		textpath.setBackground(SystemColor.menu);
		textpath.setBounds(129, 56, 153, 20);
		lzw.getContentPane().add(textpath);
		textpath.setColumns(10);
		
		JLabel lblPath = new JLabel("Path:");
		lblPath.setBounds(36, 59, 46, 14);
		lzw.getContentPane().add(lblPath);
		
		textnewpath = new JTextField();
		textnewpath.setBackground(SystemColor.menu);
		textnewpath.setBounds(129, 98, 153, 20);
		lzw.getContentPane().add(textnewpath);
		textnewpath.setColumns(10);
		
		lblNewPath = new JLabel("New Path:");
		lblNewPath.setBounds(25, 101, 72, 17);
		lzw.getContentPane().add(lblNewPath);
		
		JButton btnCompress = new JButton("Compress");
		btnCompress.setForeground(Color.WHITE);
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*dict.add("A");dict.add("B");dict.add("C");dict.add("D");dict.add("E");dict.add("F");dict.add("G");dict.add("H");dict.add("I");
				dict.add("J");dict.add("K");dict.add("L");dict.add("M");dict.add("N");dict.add("O");dict.add("P");dict.add("Q");dict.add("R");
				dict.add("S");dict.add("T");dict.add("U");dict.add("V");dict.add("W");dict.add("X");dict.add("Y");dict.add("Z");*/
				for(int i=0;i<256;i++)
					dict.add((char)i+"");
				String buffer="",str="";
				boolean flag=false;
				path=textpath.getText();
				created=textnewpath.getText();
				try
				{
					file=new File(path);
					Scanner fread=new Scanner(file);
					while (fread.hasNextLine()) 
					     buffer=fread.nextLine();}
				catch(Exception e)
		            {System.out.println(e);}
				char[] chars = buffer.toCharArray();
				for(int i=0;i<chars.length;++i)
				{
					int x=i;
					str+=chars[i];
					if(flag==false)
					{
					for(int y=0;y<=dict.size();++y)
					{
						if(str.equals(dict.get(y)))
							{
							if(x+1!=chars.length)
								{x++;
								str+=chars[x];}
								if(x+1==chars.length&&str.equals(dict.get(y)))
									flag=true;
								System.out.println("str"+str);
								y=0;
								i=x-1;
							}
						if((y+1==dict.size()&&!str.equals(dict.get(y)))||flag==true)
						{
							dict.add(str);
							System.out.println("str in dict"+str+" "+dict.indexOf(str));
							if(flag==true)
								dict.remove(dict.size()-1);
							if(flag==false)
								str=str.substring(0,str.length()-1);
							try
							{
							BufferedWriter writer = new BufferedWriter(new FileWriter(created, true));
							writer.write(Integer.toString(dict.indexOf(str))+"|");
							str="";
							writer.close();
							break;
							}
							catch(IOException e){
								System.out.println(e);}
						}
						}
					}
					
			}
				JOptionPane.showMessageDialog(null,"Compressed Successfully");
			}
		});
		btnCompress.setBackground(new Color(139, 0, 0));
		btnCompress.setBounds(90, 173, 89, 23);
		lzw.getContentPane().add(btnCompress);
		
		btnDecompress = new JButton("Decompress");
		btnDecompress.setForeground(Color.WHITE);
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				path=textpath.getText();
				created=textnewpath.getText();
				dict.add("A");dict.add("B");dict.add("C");dict.add("D");dict.add("E");dict.add("F");dict.add("G");dict.add("H");dict.add("I");
				dict.add("J");dict.add("K");dict.add("L");dict.add("M");dict.add("N");dict.add("O");dict.add("P");dict.add("Q");dict.add("R");
				dict.add("S");dict.add("T");dict.add("U");dict.add("V");dict.add("W");dict.add("X");dict.add("Y");dict.add("Z");
				String buffer="",result="";
				try{
					file=new File(path);
					Scanner s=new Scanner(file);
					buffer=s.nextLine();
					}
				catch(Exception e)
				{System.out.println(e);}
			String[] index = buffer.split("\\|");
			for(int i=0;i<index.length;i++)
			{
				if(Integer.parseInt(index[i])>=dict.size())
					{
					if(Integer.parseInt(index[i+1])>=dict.size())
						dict.add(dict.get(Integer.parseInt(index[i-1]))+dict.get(Integer.parseInt(index[i-1])));
					else
						dict.add(dict.get(Integer.parseInt(index[i-1]))+dict.get(Integer.parseInt(index[i+1])).charAt(0));
					result+=dict.get(Integer.parseInt(index[i]));
					continue;
					}
				result+=dict.get(Integer.parseInt(index[i]));
				System.out.println(result);
				if(i!=0)
					dict.add(dict.get(Integer.parseInt(index[i-1]))+dict.get(Integer.parseInt(index[i])).charAt(0));
			}
			try{
			PrintWriter writer = new PrintWriter(created,"UTF-8");
			writer.println(result);
			writer.close();
			}
		catch(Exception e){
            System.out.println(e);}
			JOptionPane.showMessageDialog(null, "Decmpressed Successfully");
			}
		});
		btnDecompress.setBackground(new Color(139, 0, 0));
		btnDecompress.setBounds(242, 173, 91, 23);
		lzw.getContentPane().add(btnDecompress);

	}
}
