package project;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class Bank {

	private JFrame frame;
	private SavingsAccount acc = null;
	private void writeLog(String message) {
	    try {
	        java.io.FileWriter fw = new java.io.FileWriter("log.txt", true);
	        java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
	        java.time.LocalDateTime now = java.time.LocalDateTime.now();
	        bw.write(encrypt("[" + now + "] " + message));
	        bw.newLine();
	        bw.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private String encrypt(String text) {
	    StringBuilder result = new StringBuilder();
	    int key = 3; // 🔑 Caesar Cipher Key
	    for (char c : text.toCharArray()) {
	        result.append((char)(c + key));
	    }
	    return result.toString();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bank window = new Bank();
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
	public Bank() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
		private void initialize() {
			// 🔐 Password Login
			javax.swing.JPasswordField pf = new javax.swing.JPasswordField();
			int ok = javax.swing.JOptionPane.showConfirmDialog(
			    null,
			    pf,
			    "BankKKU Login - กรุณากรอกรหัสผ่าน:",
			    javax.swing.JOptionPane.OK_CANCEL_OPTION,
			    javax.swing.JOptionPane.QUESTION_MESSAGE
			);

			if (ok != javax.swing.JOptionPane.OK_OPTION) {
			    System.exit(0);
			}

			String password = new String(pf.getPassword());

			if (password == null || !password.equals("kku1234")) {
			    javax.swing.JOptionPane.showMessageDialog(
			        null,
			        "❌ รหัสผ่านไม่ถูกต้อง!!",
			        "Access Denied",
			        javax.swing.JOptionPane.ERROR_MESSAGE
			    );
			    writeLog("❌ Login ล้มเหลว!!");
			    System.exit(0);
			}
			
			frame = new JFrame("BankKKU System");
		    frame.setBounds(100, 100, 450, 400);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
		    // Panel หลัก
		    JPanel panel = new JPanel();
		    panel.setLayout(new GridLayout(6, 2, 5, 5));
		    
		    // Components ที่ต้องเพิ่ม
		    JLabel lblName = new JLabel("ชื่อบัญชี:");
		    JTextField txtName = new JTextField();
		    
		    JLabel lblAmount = new JLabel("จำนวนเงิน:");
		    JTextField txtAmount = new JTextField();
		    
		    JButton btnDeposit = new JButton("ฝาก");
		    JButton btnWithdraw = new JButton("ถอน");
		    JButton btnCheck = new JButton("ดูยอด");
		    JButton btnInterest = new JButton("ดอกเบี้ย");
		    
		    JTextArea txtResult = new JTextArea();
		    
		    // เพิ่มลง panel
		    panel.add(lblName);
		    panel.add(txtName);
		    panel.add(lblAmount);
		    panel.add(txtAmount);
		    panel.add(btnDeposit);
		    panel.add(btnWithdraw);
		    panel.add(btnCheck);
		    panel.add(btnInterest);
		    frame.getContentPane().add(panel, BorderLayout.NORTH);
		    frame.getContentPane().add(txtResult, BorderLayout.CENTER);
		    // ✅ ถูก = ลบ @Override ออกไปเลยครับ!!
		    btnDeposit.addActionListener(new ActionListener() {
		    	  public void actionPerformed(ActionEvent e) {
		    	        String name = txtName.getText().trim();
		    	        String amountText = txtAmount.getText().trim();

		    	        // Validate ชื่อก่อน
		    	        if (name.isEmpty()) {
		    	            txtResult.setText("❌ กรุณากรอกชื่อบัญชีครับ!!");
		    	            return;
		    	        }

		    	        // Validate จำนวนเงิน
		    	        double amount;
		    	        try {
		    	            amount = Double.parseDouble(amountText);
		    	            if (amount <= 0) {
		    	                txtResult.setText("❌ จำนวนเงินต้องมากกว่า 0 ครับ!!");
		    	                return;
		    	            }
		    	        } catch (NumberFormatException ex) {
		    	            txtResult.setText("❌ กรุณากรอกตัวเลขเท่านั้นครับ!!");
		    	            return;
		    	        }

		    	        if (acc == null) {
		    	            acc = new SavingsAccount(name, 0);
		    	        }
		    	        acc.deposit(amount); writeLog("ฝาก: " + name + " จำนวน " + amount + " บาท");
		    	        txtResult.setText("ฝากสำเร็จ!!\n" +
		    	                         "ชื่อ: " + name + "\n" +
		    	                         "ยอดเงิน: " + acc.checkBalance());
		    	    }
		    });
		 // ถอน
		    btnWithdraw.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		            String amountText = txtAmount.getText().trim();

		            if (acc == null) {
		                txtResult.setText("❌ ยังไม่มีบัญชีครับ!!");
		                return;
		            }

		            double amount;
		            try {
		                amount = Double.parseDouble(amountText);
		                if (amount <= 0) {
		                    txtResult.setText("❌ จำนวนเงินต้องมากกว่า 0 ครับ!!");
		                    return;
		                }
		            } catch (NumberFormatException ex) {
		                txtResult.setText("❌ กรุณากรอกตัวเลขเท่านั้นครับ!!");
		                return;
		            }

		            try {
		                acc.withdraw(amount);writeLog("ถอน: " + acc.accountName + " จำนวน " + amount + " บาท");
		                txtResult.setText("ถอนสำเร็จ!!\n" +
		                                 "ยอดเงิน: " + acc.checkBalance());
		            } catch (IllegalArgumentException ex) {
		                txtResult.setText("❌ Error: " + ex.getMessage());
		            }
		        }
		    });

		    // ดูยอด
		    btnCheck.addActionListener(new ActionListener() {
		    	 public void actionPerformed(ActionEvent e) {
		    	        if (acc == null) {
		    	            txtResult.setText("ยังไม่มีบัญชีค่ะ!!");
		    	            return;
		    	        }
		    	        txtResult.setText("ยอดเงินปัจจุบัน\n" +
		    	                         "ชื่อ: " + acc.accountName + "\n" +
		    	                         "ยอดเงิน: " + acc.checkBalance());
		    	    }
		    });

		    // ดอกเบี้ย
		    btnInterest.addActionListener(new ActionListener() {
		    	 public void actionPerformed(ActionEvent e) {
		    	        
		    	        // ✅ เช็คก่อนว่ามีบัญชีหรือยัง
		    	        if (acc == null) {
		    	            txtResult.setText("❌ ยังไม่มีบัญชีครับ!!");
		    	            return;
		    	        }

		    	        // ✅ ใช้ acc ที่มีอยู่แล้ว ไม่สร้างใหม่!!
		    	        txtResult.setText("ดอกเบี้ย!!\n" +
		    	                         "ชื่อ: " + acc.accountName + "\n" +
		    	                         "ยอดเงินปัจจุบัน: " + acc.checkBalance() + "\n" +
		    	                         "ดอกเบี้ย 2%: " + acc.calculateInterest());
		    	    }
		    });
		
		
		}
		
}


