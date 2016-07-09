package com.aa.view;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.aa.EditDistance;

public class EDWorker2 extends SwingWorker<Integer, Integer>{

	String[] ss;
	DefaultTableModel model;
	
	public EDWorker2(String[] ss, DefaultTableModel model) {
		this.ss = ss;
		this.model = model;
	}
	
	
	@Override
	protected Integer doInBackground() throws Exception {
		EditDistance ed;
		for (int i = 0; i < ss.length; i += 2) {
			
		}
		return null;
	}

	@Override
	protected void done() {
		super.done();
	}


	@Override
	protected void process(List<Integer> chunks) {
		super.process(chunks);
	}

}
