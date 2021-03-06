/**
 * 
 *   Copyright (C) 2008 Lasse Parikka
 *
 *   This program is free software; you can redistribute it and/or modify it under the terms of
 *   the GNU General Public License as published by the Free Software Foundation; either version 2
 *   of the License, or (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 *   without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *   See the GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License along with this program;
 *   if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, 
 *   MA 02111-1307 USA
 *
 *   Also add information on how to contact you by electronic and paper mail.
 *
 */
package checker.gui.cvs;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.regex.Pattern;

import org.jdesktop.swingworker.SwingWorker;
import org.netbeans.lib.cvsclient.event.BinaryMessageEvent;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.netbeans.lib.cvsclient.event.FileAddedEvent;
import org.netbeans.lib.cvsclient.event.FileInfoEvent;
import org.netbeans.lib.cvsclient.event.FileRemovedEvent;
import org.netbeans.lib.cvsclient.event.FileToRemoveEvent;
import org.netbeans.lib.cvsclient.event.FileUpdatedEvent;
import org.netbeans.lib.cvsclient.event.MessageEvent;
import org.netbeans.lib.cvsclient.event.ModuleExpansionEvent;
import org.netbeans.lib.cvsclient.event.TerminationEvent;

import checker.repository.CVSRepository;
import checker.localization.Locale;



/**
 *  the CheckoutEvent class shows progress information
 *  about checkout operation.
 */
public class CVSCheckoutEvent extends javax.swing.JDialog {


	private CVSRepository repository;
	private String modulePath;
	private String localFolder;
	private CheckoutTask task; 
	private boolean checkoutInterrupted = true;
	private boolean checkoutCancelled = false;
	private javax.swing.JDialog  parentDialog;
	private ArrayList<String> modules;
	/* Localization */
	private Locale loc = new Locale();
	

	/** Creates new form CheckoutEvent */
	public CVSCheckoutEvent(CVSCheckoutWizard wizard, java.awt.Frame parent, boolean modal, 
			CVSRepository repository, String module,
			String localFolder) {
		
		super(parent, modal);
		parentDialog = this;
		modules = null;
		//checkout all modules
		if (module.equals("")) {
			
			try {
				modules = repository.listModules("");
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
		setTitle(loc.lc("CVS checkout"));
		this.repository = repository ;
		initComponents();
		modulePath = module;
		this.localFolder = localFolder;
		task = new CheckoutTask();
		task.execute();
		this.setLocationRelativeTo(wizard);
		this.setVisible(true);
		
	}
	


	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jProgressBar1 = new javax.swing.JProgressBar();
		cancelButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jLabel1 = new javax.swing.JLabel();
		okButton = new javax.swing.JButton();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setName("Form"); // NOI18N

		jProgressBar1.setName("jProgressBar1"); // NOI18N

		cancelButton.setText(loc.lc("Cancel")); // NOI18N
		cancelButton.setName("cancelButton"); // NOI18N
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButton1ActionPerformed(evt);
			}
		});
		jScrollPane1.setName("jScrollPane1"); // NOI18N

		jTextArea1.setColumns(25);
		jTextArea1.setRows(8);
		jTextArea1.setName("jTextArea1"); // NOI18N
		jTextArea1.setEditable(false);
		jScrollPane1.setViewportView(jTextArea1);

		jLabel1.setText(loc.lc("Checking out...")); // NOI18N
		jLabel1.setName("jLabel1"); // NOI18N
		okButton.setText(loc.lc("OK")); // NOI18N
		okButton.setEnabled(false);
		okButton.setName("okButton"); // NOI18N
		okButton.setPreferredSize(new java.awt.Dimension(75, 25));
		okButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		 org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
	                .addContainerGap()
	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
	                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
	                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
	                        .add(jLabel1)
	                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 203, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                        .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                        .add(cancelButton)))
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
	                .addContainerGap()
	                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
	                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
	                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
	                        .add(cancelButton)
	                        .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
	                    .add(layout.createSequentialGroup()
	                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	                            .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                            .add(jLabel1))
	                        .add(1, 1, 1)))
	                .addContainerGap())
	        );

		pack();
	}// </editor-fold>

	

	/**
	 * Cancel button event handler. 
	 */
	private void cancelButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
		checkoutCancelled = true;
		task.cancel(true);
	} 
	
	public boolean isCancelled() {
		return checkoutCancelled || checkoutInterrupted;
	}
	/**
	 * closes the dialog.
	 */
	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
		this.setVisible(false);
	} 

	// Variables declaration - do not modify
	private javax.swing.JButton cancelButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JProgressBar jProgressBar1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JButton okButton;
	// End of variables declaration

	/**
	 * Worker thread for checkout operation.
	 */
	class CheckoutTask extends SwingWorker<Void, Void> {
		

		protected Void doInBackground() throws Exception {
			jProgressBar1.setIndeterminate(true);
			
			//checkout all modules.
			if (modules != null) {
				ListIterator<String> litr = modules.listIterator();
				while (litr.hasNext()) {
					String jee = litr.next();
					//System.out.println(jee);
					try {
						repository.doCheckout(jee, localFolder, new CVSCommandListener());
						
					} catch(Exception e) {
						javax.swing.JOptionPane.showMessageDialog(parentDialog,
								loc.lc("Checkout cancelled"),
								loc.lc("checkout failed"),
								javax.swing.JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				
			}
			else {
			
    			try {
    				repository.doCheckout(modulePath, localFolder, new CVSCommandListener());
    				
    			} catch(Exception e) {
    				javax.swing.JOptionPane.showMessageDialog(null,
    						loc.lc("Checkout cancelled"),
    						loc.lc("checkout failed"),
    						javax.swing.JOptionPane.ERROR_MESSAGE);
    			}
			}
			return null;
		}

		protected void done() {
			
			if (this.isCancelled())
				checkoutCancelled = true;
			checkoutInterrupted = false;
			cancelButton.setEnabled(false);
			jProgressBar1.setIndeterminate(false);
			jProgressBar1.setVisible(false);
			jLabel1.setVisible(false);
			okButton.setEnabled(true);
			
		}

	}
	
	/**
	 * Listens events generated by CVS client during the
	 * checkout operation.
	 */
	class CVSCommandListener implements CVSListener  {

		private String message = null;
		
		public void commandTerminated(TerminationEvent arg0) {
			
			
		}

		public void fileAdded(FileAddedEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void fileInfoGenerated(FileInfoEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void fileRemoved(FileRemovedEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void fileToRemove(FileToRemoveEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void fileUpdated(FileUpdatedEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void messageSent(MessageEvent arg0) {
			
			Pattern p = Pattern.compile(" ");
			String[] words = p.split(arg0.getMessage());
		
			if (message != null && message.equals("text")) {
				jTextArea1.append(("U " +  words[1] + "\n"));
				jTextArea1.setCaretPosition(jTextArea1.getDocument()
						.getLength());
			}
			if (words[0].equals("cvs")) {
				jTextArea1.append((arg0.getMessage() + "\n"));
				jTextArea1.setCaretPosition(jTextArea1.getDocument()
						.getLength());
			}
		
			message = words[0];
			
			
		}

		public void messageSent(BinaryMessageEvent arg0) {
			
			
		}

		public void moduleExpanded(ModuleExpansionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	


	


}
