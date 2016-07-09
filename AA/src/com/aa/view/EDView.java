package com.aa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.SwingWorker.StateValue;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.aa.EDBase;
import com.aa.EditDistance;
import com.aa.algo.BranchAndBound;
import com.aa.algo.ClassicalDynamic;
import com.aa.algo.DivideAndConquer;
import com.aa.algo.Greedy;
import com.aa.algo.RandomizedVersion;
import com.aa.algo.Recursive;
import com.aa.algo.SizeKAround;

import java.awt.TextField;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class EDView {

	private JFrame frame;
	private JTextField costReplace;
	private JTextField costDelete;
	private JTextField costInsert;
	private JTextField word1;
	private JTextField word2;
	
	private EDWorker workers[] = new EDWorker[7];
	private JTable resTable;
	
	private String[] cNames = {"Word1", "Word2", "Recursive" ,"Classical Dynamic", "Divide&Conquer", "Branch&Bound", "Greedy", "Size K Around", "Randomized"}; 
	Object[][] data = {};
	DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try 
	    { 
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
	    } 
	    catch(Exception e){ 
	    }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EDView window = new EDView();
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
	public EDView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Edit Distance solution in different Algorithms");
		frame.setSize(1020, 720);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		frame.setLocation((int)((dim.getWidth()/2) - (frame.getWidth()/2)), (int)((dim.getHeight()/2) - (frame.getHeight()/2)));
		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		
		JPanel framePanel = new JPanel();
		JPanel pnlAlgo = new JPanel();
		JPanel pnlCostEntry = new JPanel();
		pnlCostEntry.setBorder(BorderFactory.createTitledBorder(border, "Edit Cost Functions"));
		pnlCostEntry.setPreferredSize(new Dimension(150, 100));
		
		JPanel pnlResult = new JPanel();
		pnlResult.setBackground(Color.WHITE);
		
		JPanel pnlClassicAlg = new JPanel();
	    pnlClassicAlg.setBorder(BorderFactory.createTitledBorder(border, "Dynamic programming"));
		
		JPanel pnlRecursiveAlg = new JPanel();
		pnlRecursiveAlg.setBorder(BorderFactory.createTitledBorder(border, "Recursive"));
		
		JPanel pnlDivideAndConquerAlg = new JPanel();
		pnlDivideAndConquerAlg.setBorder(BorderFactory.createTitledBorder(border, "Divide And Conquer"));
		
		JPanel pnlBranchAndBoundAlg = new JPanel();
		pnlBranchAndBoundAlg.setBorder(BorderFactory.createTitledBorder(border, "Branch And Bound"));
		
		JPanel pnlGreedyAlg = new JPanel();
		pnlGreedyAlg.setBorder(BorderFactory.createTitledBorder(border, "Greedy"));
		
		pnlAlgo.setLayout(new GridLayout(1,7, 0, 10));
		pnlAlgo.add(pnlClassicAlg);
		pnlClassicAlg.setLayout(null);
		
		JLabel exeTime_1 = new JLabel("0 ms");
		exeTime_1.setHorizontalAlignment(SwingConstants.CENTER);
		exeTime_1.setForeground(Color.RED);
		exeTime_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		exeTime_1.setBounds(10, 84, 124, 14);
		pnlClassicAlg.add(exeTime_1);
		
		JButton btnRun_1 = new JButton("Run It");
		btnRun_1.setBounds(22, 183, 89, 33);
		pnlClassicAlg.add(btnRun_1);
		
		JLabel result_1 = new JLabel("Completed!");
		result_1.setEnabled(false);
		result_1.setForeground(new Color(255, 255, 255));
		result_1.setBackground(new Color(34, 139, 34));
		result_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		result_1.setHorizontalAlignment(SwingConstants.CENTER);
		result_1.setBounds(10, 25, 124, 23);
		pnlClassicAlg.add(result_1);
		
		JLabel lblMinCost = new JLabel("Min Cost : ");
		lblMinCost.setBounds(10, 120, 67, 14);
		pnlClassicAlg.add(lblMinCost);
		
		JLabel cost_1 = new JLabel("0");
		cost_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cost_1.setForeground(new Color(0, 0, 205));
		cost_1.setBounds(77, 120, 57, 14);
		pnlClassicAlg.add(cost_1);
		pnlAlgo.add(pnlRecursiveAlg);
		pnlRecursiveAlg.setLayout(null);
		
		JButton btnRun_2 = new JButton("Run It");
		btnRun_2.setBounds(29, 183, 89, 33);
		pnlRecursiveAlg.add(btnRun_2);
		
		JLabel exeTime_2 = new JLabel("0 ms");
		exeTime_2.setHorizontalAlignment(SwingConstants.CENTER);
		exeTime_2.setForeground(Color.RED);
		exeTime_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		exeTime_2.setBounds(10, 86, 124, 14);
		pnlRecursiveAlg.add(exeTime_2);
		
		JLabel result_2 = new JLabel("Completed!");
		result_2.setHorizontalAlignment(SwingConstants.CENTER);
		result_2.setForeground(new Color(255, 255, 255));
		result_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		result_2.setEnabled(false);
		result_2.setBackground(new Color(34, 139, 34));
		result_2.setBounds(10, 25, 124, 23);
		pnlRecursiveAlg.add(result_2);
		
		JLabel cost_2 = new JLabel("0");
		cost_2.setForeground(new Color(0, 0, 205));
		cost_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cost_2.setBounds(77, 120, 57, 14);
		pnlRecursiveAlg.add(cost_2);
		
		JLabel label_17 = new JLabel("Min Cost : ");
		label_17.setBounds(10, 120, 67, 14);
		pnlRecursiveAlg.add(label_17);
		pnlAlgo.add(pnlDivideAndConquerAlg);
		pnlDivideAndConquerAlg.setLayout(null);
		
		JButton btnRun_3 = new JButton("Run It");
		btnRun_3.setBounds(31, 183, 89, 33);
		pnlDivideAndConquerAlg.add(btnRun_3);
		
		JLabel exeTime_3 = new JLabel("0 ms");
		exeTime_3.setHorizontalAlignment(SwingConstants.CENTER);
		exeTime_3.setForeground(Color.RED);
		exeTime_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		exeTime_3.setBounds(10, 87, 124, 14);
		pnlDivideAndConquerAlg.add(exeTime_3);
		
		JLabel result_3 = new JLabel("Completed!");
		result_3.setHorizontalAlignment(SwingConstants.CENTER);
		result_3.setForeground(new Color(255, 255, 255));
		result_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		result_3.setEnabled(false);
		result_3.setBackground(new Color(34, 139, 34));
		result_3.setBounds(10, 25, 124, 23);
		pnlDivideAndConquerAlg.add(result_3);
		
		JLabel cost_3 = new JLabel("0");
		cost_3.setForeground(new Color(0, 0, 205));
		cost_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cost_3.setBounds(77, 125, 57, 14);
		pnlDivideAndConquerAlg.add(cost_3);
		
		JLabel label_19 = new JLabel("Min Cost : ");
		label_19.setBounds(10, 125, 67, 14);
		pnlDivideAndConquerAlg.add(label_19);
		pnlAlgo.add(pnlBranchAndBoundAlg);
		pnlBranchAndBoundAlg.setLayout(null);
		
		JButton btnRun_4 = new JButton("Run It");
		btnRun_4.setBounds(32, 183, 89, 33);
		pnlBranchAndBoundAlg.add(btnRun_4);
		
		JLabel exeTime_4 = new JLabel("0 ms");
		exeTime_4.setHorizontalAlignment(SwingConstants.CENTER);
		exeTime_4.setForeground(Color.RED);
		exeTime_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		exeTime_4.setBounds(10, 86, 124, 14);
		pnlBranchAndBoundAlg.add(exeTime_4);
		
		JLabel result_4 = new JLabel("Completed!");
		result_4.setHorizontalAlignment(SwingConstants.CENTER);
		result_4.setForeground(new Color(255, 255, 255));
		result_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		result_4.setEnabled(false);
		result_4.setBackground(new Color(34, 139, 34));
		result_4.setBounds(10, 25, 124, 23);
		pnlBranchAndBoundAlg.add(result_4);
		
		JLabel cost_4 = new JLabel("0");
		cost_4.setForeground(new Color(0, 0, 205));
		cost_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cost_4.setBounds(77, 124, 90, 14);
		pnlBranchAndBoundAlg.add(cost_4);
		
		JLabel label_21 = new JLabel("Min Cost : ");
		label_21.setBounds(10, 124, 67, 14);
		pnlBranchAndBoundAlg.add(label_21);
		pnlAlgo.add(pnlGreedyAlg);
		pnlGreedyAlg.setLayout(null);
		
		JButton btnRun_5 = new JButton("Run It");
		btnRun_5.setBounds(26, 183, 89, 33);
		pnlGreedyAlg.add(btnRun_5);
		
		JPanel pnlSizeKArounddAlg = new JPanel();
		pnlSizeKArounddAlg.setBorder(BorderFactory.createTitledBorder(border, "Size K Around"));
		pnlAlgo.add(pnlSizeKArounddAlg);
		pnlSizeKArounddAlg.setLayout(null);
		
		JLabel cost_6 = new JLabel("0");
		cost_6.setForeground(new Color(0, 0, 205));
		cost_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cost_6.setBounds(77, 123, 57, 14);
		pnlSizeKArounddAlg.add(cost_6);
		
		JLabel label_1 = new JLabel("Min Cost : ");
		label_1.setBounds(10, 123, 67, 14);
		pnlSizeKArounddAlg.add(label_1);
		
		JButton btnRun_6 = new JButton("Run It");
		btnRun_6.setBounds(26, 183, 89, 33);
		pnlSizeKArounddAlg.add(btnRun_6);
		
		JLabel exeTime_6 = new JLabel("0 ms");
		exeTime_6.setHorizontalAlignment(SwingConstants.CENTER);
		exeTime_6.setForeground(Color.RED);
		exeTime_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		exeTime_6.setBounds(10, 85, 124, 14);
		pnlSizeKArounddAlg.add(exeTime_6);
		
		JLabel result_6 = new JLabel("Completed!");
		result_6.setHorizontalAlignment(SwingConstants.CENTER);
		result_6.setForeground(Color.WHITE);
		result_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		result_6.setEnabled(false);
		result_6.setBackground(new Color(34, 139, 34));
		result_6.setBounds(10, 25, 124, 23);
		pnlSizeKArounddAlg.add(result_6);
		
		JPanel pnlRandomzedAlg = new JPanel();
		pnlRandomzedAlg.setBorder(BorderFactory.createTitledBorder(border, "Randomized"));
		pnlAlgo.add(pnlRandomzedAlg);
		pnlRandomzedAlg.setLayout(null);
		
		JLabel cost_7 = new JLabel("0");
		cost_7.setForeground(new Color(0, 0, 205));
		cost_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cost_7.setBounds(77, 123, 57, 14);
		pnlRandomzedAlg.add(cost_7);
		
		JLabel label_5 = new JLabel("Min Cost : ");
		label_5.setBounds(10, 123, 67, 14);
		pnlRandomzedAlg.add(label_5);
		
		JButton btnRun_7 = new JButton("Run It");
		btnRun_7.setBounds(26, 183, 89, 33);
		pnlRandomzedAlg.add(btnRun_7);
		
		JLabel exeTime_7 = new JLabel("0 ms");
		exeTime_7.setHorizontalAlignment(SwingConstants.CENTER);
		exeTime_7.setForeground(Color.RED);
		exeTime_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		exeTime_7.setBounds(10, 85, 124, 14);
		pnlRandomzedAlg.add(exeTime_7);
		
		JLabel result_7 = new JLabel("Completed!");
		result_7.setHorizontalAlignment(SwingConstants.CENTER);
		result_7.setForeground(Color.WHITE);
		result_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		result_7.setEnabled(false);
		result_7.setBackground(new Color(34, 139, 34));
		result_7.setBounds(10, 25, 124, 23);
		pnlRandomzedAlg.add(result_7);
		
		JLabel exeTime_5 = new JLabel("0 ms");
		exeTime_5.setHorizontalAlignment(SwingConstants.CENTER);
		exeTime_5.setForeground(Color.RED);
		exeTime_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		exeTime_5.setBounds(10, 85, 124, 14);
		pnlGreedyAlg.add(exeTime_5);
		
		JLabel result_5 = new JLabel("Completed!");
		result_5.setHorizontalAlignment(SwingConstants.CENTER);
		result_5.setForeground(new Color(255, 255, 255));
		result_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		result_5.setEnabled(false);
		result_5.setBackground(new Color(34, 139, 34));
		result_5.setBounds(10, 25, 124, 23);
		pnlGreedyAlg.add(result_5);
		
		JLabel cost_5 = new JLabel("0");
		cost_5.setForeground(new Color(0, 0, 205));
		cost_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cost_5.setBounds(77, 123, 57, 14);
		pnlGreedyAlg.add(cost_5);
		
		JLabel label_23 = new JLabel("Min Cost : ");
		label_23.setBounds(10, 123, 67, 14);
		pnlGreedyAlg.add(label_23);

		
		framePanel.setLayout(new GridLayout(3, 1, 0, 5));
		framePanel.add(pnlCostEntry);
		pnlCostEntry.setLayout(null);
		
		JLabel lblReplace = new JLabel("Replace ");
		lblReplace.setBounds(10, 55, 50, 14);
		pnlCostEntry.add(lblReplace);
		
		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setBounds(11, 93, 46, 14);
		pnlCostEntry.add(lblDelete);
		
		JLabel lblInsert = new JLabel("Insert");
		lblInsert.setBounds(11, 126, 46, 14);
		pnlCostEntry.add(lblInsert);
		
		costReplace = new JFormattedTextField(createFormatter("#"));
		costReplace.setText("1");
		costReplace.setBounds(70, 51, 86, 26);
		pnlCostEntry.add(costReplace);
		costReplace.setColumns(10);
		
		costDelete = new JFormattedTextField(createFormatter("#"));
		costDelete.setText("1");
		costDelete.setBounds(71, 86, 86, 26);
		pnlCostEntry.add(costDelete);
		costDelete.setColumns(10);
		
		costInsert = new JFormattedTextField(createFormatter("#"));
		costInsert.setText("1");
		costInsert.setBounds(71, 119, 86, 26);
		pnlCostEntry.add(costInsert);
		costInsert.setColumns(10);
		
		JLabel lblEditDistance = new JLabel("EDIT DISTANCE");
		lblEditDistance.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEditDistance.setBounds(390, 23, 121, 14);
		pnlCostEntry.add(lblEditDistance);
		
		JButton btnRunAll = new JButton("Run All");
		btnRunAll.setBounds(72, 164, 89, 33);
		pnlCostEntry.add(btnRunAll);
		
		JLabel lblNewLabel = new JLabel("String word1");
		lblNewLabel.setBounds(245, 55, 75, 14);
		pnlCostEntry.add(lblNewLabel);
		
		JLabel lblStringWord = new JLabel("String word2");
		lblStringWord.setBounds(245, 90, 75, 14);
		pnlCostEntry.add(lblStringWord);
		
		word1 = new JTextField();
		word1.setText("abc");
		word1.setBounds(330, 48, 560, 28);
		pnlCostEntry.add(word1);
		word1.setColumns(10);
		
		word2 = new JTextField();
		word2.setText("xyz");
		word2.setToolTipText("");
		word2.setColumns(10);
		word2.setBounds(330, 83, 560, 28);
		pnlCostEntry.add(word2);
		
		JButton btnFileChooser = new JButton("Read Words From File");
		btnFileChooser.setBounds(330, 134, 181, 33);
		pnlCostEntry.add(btnFileChooser);
		
		framePanel.add(pnlAlgo);
		

		
		framePanel.add(pnlResult);
		
		
		
		pnlResult.setBorder(BorderFactory.createTitledBorder(border, "RESULTS"));
		pnlResult.setLayout(null);
	
		model = new DefaultTableModel(cNames,0);
		
		resTable = new JTable(model);
		resTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		resTable.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(resTable);
		resTable.setFillsViewportHeight(true);
		
       scrollPane.setBounds(7, 26, 1000, 180);
       pnlResult.add(scrollPane);
		
	   frame.getContentPane().add(framePanel);	
		
		btnRunAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initCostVals();
				
				model.addRow(new Object[]{word1.getText(), word2.getText(), "?","?","?","?","?","?", "?"});
				
				for (int i = 0; i < workers.length; i++) {
					EDWorker edWorker = EDWorker.newInstance(workers[i]);
					if(!workers[i].getState().toString().equalsIgnoreCase(StateValue.STARTED.toString())){
						workers[i] = edWorker;
						edWorker.setWord1(word1.getText());
						edWorker.setWord2(word2.getText());
						edWorker.execute();
					}
				}
				
			}
		});
		
		// Classical Dynamic Algo
		btnRun_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!workers[0].getState().toString().equalsIgnoreCase(StateValue.STARTED.toString())){
					initCostVals();
					workers[0] = EDWorker.newInstance(workers[0]);
					workers[0].setWord1(word1.getText());
					workers[0].setWord2(word2.getText());
					workers[0].execute();
				}
			}
		});
		
		// Recursive Algo
		btnRun_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!workers[1].getState().toString().equalsIgnoreCase(StateValue.STARTED.toString())){
					initCostVals();
					workers[1] = EDWorker.newInstance(workers[1]);
					workers[1].setWord1(word1.getText());
					workers[1].setWord2(word2.getText());
					workers[1].execute();
				}
			}
		});
		
		// Divide And Conquer Algo
		btnRun_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!workers[2].getState().toString().equalsIgnoreCase(StateValue.STARTED.toString())){
					initCostVals();
					workers[2] = EDWorker.newInstance(workers[2]);
					workers[2].setWord1(word1.getText());
					workers[2].setWord2(word2.getText());
					workers[2].execute();
				}
			}
		});
		
		// Branch And Bound Algo
		btnRun_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!workers[3].getState().toString().equalsIgnoreCase(StateValue.STARTED.toString())){
					initCostVals();
					workers[3] = EDWorker.newInstance(workers[3]);
					workers[3].setWord1(word1.getText());
					workers[3].setWord2(word2.getText());
					workers[3].execute();
				}
			}
		});
		
		// Greedy Algo
		btnRun_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!workers[4].getState().toString().equalsIgnoreCase(StateValue.STARTED.toString())){
					initCostVals();
					workers[4] = EDWorker.newInstance(workers[4]);
					workers[4].setWord1(word1.getText());
					workers[4].setWord2(word2.getText());
					workers[4].execute();
				}
			}
		});
		
		// Size K Around Algo
		btnRun_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!workers[5].getState().toString().equalsIgnoreCase(StateValue.STARTED.toString())){
					initCostVals();
					workers[5] = EDWorker.newInstance(workers[5]);
					workers[5].setWord1(word1.getText());
					workers[5].setWord2(word2.getText());
					workers[5].execute();
				}
			}
		});
		
		// Randomized Version Algo
		btnRun_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!workers[6].getState().toString().equalsIgnoreCase(StateValue.STARTED.toString())){
					initCostVals();
					workers[6] = EDWorker.newInstance(workers[6]);
					workers[6].setWord1(word1.getText());
					workers[6].setWord2(word2.getText());
					workers[6].execute();
				}
			}
		});
		
		btnFileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser(new File("C:\\Users\\emin\\Desktop"));
				fileChooser.setDialogTitle("Choose a file including 2 lines of string");
				fileChooser.setFileFilter(new FileTypeFilter(".txt", "Only Text File"));
				int fRes = fileChooser.showOpenDialog(null);
				if(fRes == JFileChooser.APPROVE_OPTION){
					try {
						BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getPath()));
						
						String s = "";
						String line = "";
						while((line = br.readLine()) != null){
							s += line;
						}
						
						if(s.trim().isEmpty())
							throw new Exception("File can not be empty!");
						if(!s.contains("#"))
							throw new Exception("File content should include '#' special character for word1 and word2");
						
						String strF[] = s.split("#");
						
						if(strF.length>2)
							throw new Exception("There are more than 1 '#' special character in the file");
						
						word1.setText(strF[0].trim());
						word2.setText(strF[1].trim());
						
						if(br != null)
							br.close();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		
		initThreats(btnRun_1, exeTime_1, result_1, cost_1, btnRun_2, exeTime_2, result_2, cost_2, btnRun_3, exeTime_3, result_3, cost_3, 
				btnRun_4, exeTime_4, result_4, cost_4, btnRun_5, exeTime_5, result_5, cost_5, btnRun_6, exeTime_6, result_6, cost_6, 
				btnRun_7, exeTime_7, result_7, cost_7);
	}
	

	private void initThreats(JButton btnRun_1, JLabel exeTime_1, JLabel result_1, JLabel cost_1, JButton btnRun_2,
			JLabel exeTime_2, JLabel result_2, JLabel cost_2, JButton btnRun_3, JLabel exeTime_3, JLabel result_3,
			JLabel cost_3, JButton btnRun_4, JLabel exeTime_4, JLabel result_4, JLabel cost_4, JButton btnRun_5,
			JLabel exeTime_5, JLabel result_5, JLabel cost_5, JButton btnRun_6, JLabel exeTime_6, JLabel result_6,
			JLabel cost_6, JButton btnRun_7, JLabel exeTime_7, JLabel result_7, JLabel cost_7) {
		
		workers[0] = new EDWorker(btnRun_1, exeTime_1, result_1, cost_1, new ClassicalDynamic(), model);
		workers[1] = new EDWorker(btnRun_2, exeTime_2, result_2, cost_2, new Recursive(), model);
		workers[2] = new EDWorker(btnRun_3, exeTime_3, result_3, cost_3, new DivideAndConquer(), model);
		workers[3] = new EDWorker(btnRun_4, exeTime_4, result_4, cost_4, new BranchAndBound(), model);
		workers[4] = new EDWorker(btnRun_5, exeTime_5, result_5, cost_5, new Greedy(), model);
		workers[5] = new EDWorker(btnRun_6, exeTime_6, result_6, cost_6, new SizeKAround(), model);
		workers[6] = new EDWorker(btnRun_7, exeTime_7, result_7, cost_7, new RandomizedVersion(), model);
		
	}

	protected void initCostVals() {
		EDBase.replaceCost = Integer.valueOf(costReplace.getText().isEmpty() ? "1" : costReplace.getText());
		EDBase.deleteCost = Integer.valueOf(costDelete.getText().isEmpty() ? "1" : costDelete.getText());
		EDBase.insertCost = Integer.valueOf(costInsert.getText().isEmpty() ? "1" : costInsert.getText());
	}



	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
}
