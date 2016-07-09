package com.aa.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.aa.EditDistance;
import com.aa.algo.BranchAndBound;
import com.aa.algo.ClassicalDynamic;
import com.aa.algo.DivideAndConquer;
import com.aa.algo.Greedy;
import com.aa.algo.RandomizedVersion;
import com.aa.algo.Recursive;
import com.aa.algo.SizeKAround;

public class EDWorker extends SwingWorker<Integer, Integer>{

	EditDistance ed;
	JButton runitBtn;
	JLabel exeTime, result, cost;
	String word1, word2;
	Long startT;
	DefaultTableModel model;
	
	public EDWorker(JButton runitBtn, JLabel exeTime, JLabel result, JLabel cost, EditDistance ed, DefaultTableModel model) {
		this.ed = ed;
		this.runitBtn = runitBtn;
		this.exeTime = exeTime;
		this.result = result;
		this.cost = cost;
		this.model = model;
	}
	int curRow = -1;
	public EDWorker(JButton runitBtn, JLabel exeTime, JLabel result, JLabel cost, EditDistance ed, DefaultTableModel model, int curRow) {
		new EDWorker(runitBtn, exeTime, result, cost, ed, model);
		this.curRow = curRow;
	}
	
	@Override
	protected Integer doInBackground() throws Exception {
		this.result.setEnabled(false); this.result.setOpaque(false);
		this.runitBtn.setEnabled(false);
		this.exeTime.setText("- ms");
		this.cost.setText("?");
		
		this.startT = System.currentTimeMillis();
		SimpleTimer.start();
		int cost = this.ed.editDistance(this.word1, this.word2);
		publish(cost);
        return null;
	}


	@Override
	protected void done() {
		super.done();
		SimpleTimer.stop();
		this.result.setOpaque(true);
		this.result.setEnabled(true);
		this.runitBtn.setEnabled(true);
		
		curRow = (curRow == -1) ? (model.getRowCount()-1) : curRow;
		
		if(this.ed instanceof ClassicalDynamic)
			model.setValueAt(this.exeTime.getText(), curRow, 3);
		else if(this.ed instanceof Recursive)
			model.setValueAt(this.exeTime.getText(), curRow, 2);
		else if(this.ed instanceof DivideAndConquer)
			model.setValueAt(this.exeTime.getText(), curRow, 4);
		else if(this.ed instanceof BranchAndBound)
			model.setValueAt(this.exeTime.getText(), curRow, 5);
		else if(this.ed instanceof Greedy)
			model.setValueAt(this.exeTime.getText(), curRow, 6);
		else if(this.ed instanceof SizeKAround)
			model.setValueAt(this.exeTime.getText(), curRow, 7);
		else if(this.ed instanceof RandomizedVersion)
			model.setValueAt(this.exeTime.getText(), curRow, 8);
	}

	@Override
	protected void process(List<Integer> chunks) {
		super.process(chunks);
		this.cost.setText(chunks.get(0) + "");
	}
	
	
	Timer SimpleTimer = new Timer(10, new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        exeTime.setText((System.currentTimeMillis() - startT) + " ms");
	    }
	});

	public void setWord1(String word1) {
		this.word1 = word1;
	}
	public void setWord2(String word2) {
		this.word2 = word2;
	}
	
	public static EDWorker newInstance(EDWorker edw){
		EditDistance ed = null;
		if(edw.ed instanceof ClassicalDynamic)
			ed = new ClassicalDynamic();
		else if(edw.ed instanceof Recursive)
			ed = new Recursive();
		else if(edw.ed instanceof DivideAndConquer)
			ed = new DivideAndConquer();
		else if(edw.ed instanceof BranchAndBound)
			ed = new BranchAndBound();
		else if(edw.ed instanceof Greedy)
			ed = new Greedy();
		else if(edw.ed instanceof SizeKAround)
			ed = new SizeKAround();
		else if(edw.ed instanceof RandomizedVersion)
			ed = new RandomizedVersion();
		return new EDWorker(edw.runitBtn, edw.exeTime, edw.result, edw.cost, ed, edw.model);
	}
}
