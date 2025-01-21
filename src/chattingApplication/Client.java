package chattingApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class Client implements ActionListener {
	 JTextField text ;
	static JPanel a1;
	static Box vertical =Box.createVerticalBox();
	 static DataOutputStream dout ;
	 static JFrame f =new JFrame();
	 
	 
    Client() {
        // Set layout to null
        f.setLayout(null);

        // Create a panel with a background color
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null); // Set layout to null for precise positioning
        f.add(p1);

        // Load the back image icon
        ImageIcon i1 = new ImageIcon(getClass().getResource("/images/3.png"));
        Image i2 =i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
       ImageIcon i3 =new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25); // Set bounds for the image
        p1.add(back);
 
        
        // Add event listener on back button
        back.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent ae) {
        		System.exit(0);
        	}
        });
        
        
        //load the profile image icon
        ImageIcon i4 =new ImageIcon(getClass().getResource("/images/1.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 =new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50); // Set bounds for the image
        p1.add(profile);
        
        //load video image icon
        ImageIcon i7 =new ImageIcon(getClass().getResource("/images/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 =new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30); // Set bounds for the image
        p1.add(video);
        
        //load call image icon
        ImageIcon i10 =new ImageIcon(getClass().getResource("/images/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 =new ImageIcon(i11);
        JLabel call = new JLabel(i12);
        call.setBounds(360, 20, 35, 30); // Set bounds for the image
        p1.add(call);
        
        //load 3dot image icon
        ImageIcon i13 =new ImageIcon(getClass().getResource("/images/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 =new ImageIcon(i14);
        JLabel dot = new JLabel(i15);
        dot.setBounds(420, 20, 10, 25); // Set bounds for the image
        p1.add(dot);
        
        //add the person name
        JLabel name =new JLabel("Kapish");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF" ,Font.BOLD,18));
        p1.add(name);
       
        //add the status of person
        JLabel status =new JLabel("Active Now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.white);
        status.setFont(new Font("SAN_SERIF" ,Font.BOLD,14));
        p1.add(status);
        
        
         a1 = new JPanel();
        a1.setBounds(0,75,450,535);
        f.add(a1);
       
        //add textfield to write chat
       text =new JTextField();
        text.setBounds(5,610,320,40);
        text.setFont(new Font("SAN_SERIF" ,Font.PLAIN,16));
        f.add(text);
        
        //add send button
        JButton send =new JButton("Send");
        send.setBounds(330,610,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.white);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF" ,Font.PLAIN,18));
        f.add(send);
        
        // Frame properties
        f.setSize(450, 650);
        f.setLocation(800, 30);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
   try { 	
    String out=	text.getText();
   
    JPanel p2 = formatLabel(out);
  
    
    a1.setLayout(new BorderLayout());
    	
    JPanel right = new JPanel(new BorderLayout());
    right.add(p2,BorderLayout.LINE_END);
    vertical.add(right);
    vertical.add(Box.createVerticalStrut(15));
    a1.add(vertical,BorderLayout.PAGE_START);
    dout.writeUTF(out);
    	text.setText("");
    	f.repaint();
    	f.invalidate();
    	f.validate();
   }catch(Exception e){
	   e.printStackTrace();
   }
    	}
    
    public static JPanel formatLabel(String out) {
    	JPanel panel = new JPanel();
    	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
    	JLabel output =new JLabel("<html> <p style =\"width:150px\">" + out + "</p></html>");
    	output.setFont(new Font("Tahoma",Font.PLAIN,18));
    	output.setBackground(new Color(37,211,102));
    	output.setOpaque(true);
    	output.setBorder(new EmptyBorder(15,15,15,50));
    	
    	panel.add(output);
    	
    	
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf =new SimpleDateFormat("hh:mm");
    	
    	JLabel time = new JLabel();
    	time.setText(sdf.format(cal.getTime()));
    	panel.add(time);
    	
    	return panel;
    }
    
    public static void main(String[] args) {
        new Client();
        
        try {
        	Socket s=new Socket("127.0.0.1",6001);
        	DataInputStream din = new DataInputStream(s.getInputStream());
        	dout = new DataOutputStream(s.getOutputStream());
        	
        	while(true) {
        		a1.setLayout(new BorderLayout());
        		String msg =din.readUTF();
        		JPanel panel =formatLabel(msg);
        		
        		
        		JPanel left =new JPanel(new BorderLayout());
        		left.add(panel,BorderLayout.LINE_START);
        		vertical.add(left);
        		vertical.add(Box.createVerticalStrut(15));
        		a1.add(vertical,BorderLayout.PAGE_START);
        		f.validate();
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        
        
    }
}
