package com.sxt.myclinema.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.sxt.myclinema.bean.Cinema;
import com.sxt.myclinema.bean.MovieType;
import com.sxt.myclinema.bean.Schedule;
import com.sxt.myclinema.bean.ScheduleItem;
import com.sxt.myclinema.bean.Seat;
import com.sxt.myclinema.bean.Ticket;
import com.sxt.myclinema.bean.TicketFactory;
import com.sxt.myclinema.utils.ImageHelper;

/**
 * ��ӳ������
 * 
 * @author Ryan
 * @version ����ʱ��:2017��5��25��
 */
public class MovieFrame extends JFrame implements TreeSelectionListener,MouseListener,ActionListener {
	
	//��ȡ����
	private Schedule schedule = Cinema.getSchedule();
	private Map<String,Seat> seats = Cinema.getSeats();
	private List<Ticket> soldTicket = Cinema.getSoldTicket();
	
	//�������� ��ʼֵ
	private ScheduleItem item = schedule.getItems().get("12:00-13:00");
	//СƱ����
	private String ticketType="";
	//ѡ����ۿ�
	private int discount=10;
	//��λ����
	private HashMap<String,JLabel> labels = new HashMap<String,JLabel>();
	
	private JLabel lblName = new JLabel(item.getMovie().getMovieName());
	private JLabel lblAuthor = new JLabel(item.getMovie().getDirector());
	private JLabel lblPicture = new JLabel();
	private JLabel lblActor = new JLabel(item.getMovie().getActor());
	private JLabel lblType = new JLabel(item.getMovie().getMovieType()+"");
	private JLabel lblPlayTime = new JLabel(item.getTime());
	private JLabel lblPrice = new JLabel(item.getMovie().getPrice()+"");
	private JLabel lblPriceDisc = new JLabel();
	private JRadioButton rdoNormal = new JRadioButton("��ͨƱ");
	private JRadioButton rdoFree = new JRadioButton("��Ʊ");
	private JRadioButton rdoStu = new JRadioButton("ѧ��Ʊ");
	private JTextField txtSender = new JTextField();
	private JComboBox cbx = new JComboBox();
	private JTree tree = new JTree();

	
	public MovieFrame() {
		init();
	}

	private void init() {
		// ����ĳ�ʼ��
		this.setTitle("ӰԺ��Ʊϵͳ");
		this.setSize(1200, 750);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);// �ر�
		this.setResizable(true);

		// �����˵���
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("��Ʊ");
		JMenu menu2 = new JMenu("ϵͳ");
		menuBar.add(menu1);
		menuBar.add(menu2);

		this.setJMenuBar(menuBar);
		this.add(pnlGridBagMain());
		
		//����һ��
		loadSeat();
	}

	/**
	 * ��������
	 * 
	 * @return
	 */
	public JPanel pnlGridBagMain() {
		JPanel pnl = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		pnl.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		// panelTree
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);
		JPanel pnlTree = new JPanel();
		pnlTree.setBackground(Color.RED);
		pnlTree.setLayout(new BorderLayout());
		pnlTree.add(pnlTree());
		gbl.setConstraints(pnlTree, gbc);
		pnl.add(pnlTree);

		// panelDetail
		gbc.gridx = 1;
		gbc.gridheight = 1;
		gbc.weightx = 6;
		JPanel pnlDetail = new JPanel();
		pnlDetail.setBackground(Color.YELLOW);
		pnlDetail.setLayout(new BorderLayout());
		pnlDetail.add(pnlDetails());
		gbl.setConstraints(pnlDetail, gbc);
		pnl.add(pnlDetail);

		// panelTicket
		gbc.gridx = 2;
		gbc.weightx = 1;
		JPanel pnlTicket = new JPanel();
		pnlTicket.setBackground(Color.BLUE);
		pnlTicket.setLayout(new BorderLayout());
		pnlTicket.add(pnlTicket());
		gbl.setConstraints(pnlTicket, gbc);
		pnl.add(pnlTicket);

		// panelScreen
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weighty = 2;
		JPanel pnlScreen = new JPanel();
		pnlScreen.setBackground(Color.PINK);
		pnlScreen.setLayout(new BorderLayout());
		pnlScreen.add(pnlScreen());
		gbl.setConstraints(pnlScreen, gbc);
		pnl.add(pnlScreen);

		return pnl;
	}

	/**
	 * �����
	 * 
	 * @return
	 */
	public JPanel pnlMovie() {
		JPanel pnlMain = new JPanel();
		pnlMain.setLayout(new BorderLayout());
		pnlMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		JPanel pnlLeft = new JPanel();
		pnlLeft.setPreferredSize(new Dimension(300, 750));
		pnlLeft.setLayout(new BorderLayout());
		JPanel pnlCenter = new JPanel();
		pnlMain.add(pnlLeft, BorderLayout.WEST);

		pnlLeft.add(pnlTree());
		pnlMain.add(pnlCenter);

		JPanel pnlCenterTop = new JPanel();
		JPanel pnlCenterMiddle = new JPanel();
		pnlCenterTop.setLayout(new BorderLayout());
		// pnlCenterTop.setPreferredSize(new Dimension(900, 250));
		pnlCenterMiddle.setLayout(new BorderLayout());
		pnlCenter.setLayout(new BorderLayout());
		pnlCenter.add(pnlCenterTop, BorderLayout.NORTH);
		pnlCenter.add(pnlCenterMiddle);

		JPanel panlCenterTopLeft = new JPanel();
		panlCenterTopLeft.setLayout(new BorderLayout());
		JPanel panlCenterTopRight = new JPanel();
		panlCenterTopRight.setLayout(new BorderLayout());

		pnlCenterTop.add(panlCenterTopLeft);
		pnlCenterTop.add(panlCenterTopRight, BorderLayout.EAST);
		pnlCenterMiddle.add(pnlScreen());
		panlCenterTopLeft.add(pnlDetails());
		panlCenterTopRight.add(pnlTicket());
		return pnlMain;
	}

	/**
	 * Ʊ���
	 * 
	 * @return
	 */
	public JPanel pnlTicket() {
		JPanel pnl = new JPanel();
		JPanel pnl1 = new JPanel();
		pnl1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		JPanel pnl2 = new JPanel();
		JPanel pnl3 = new JPanel();
		pnl.setLayout(new GridLayout(3, 1));
		pnl.setBorder(BorderFactory.createTitledBorder("����Ʊ:"));
		ButtonGroup group = new ButtonGroup();
		rdoNormal = new JRadioButton("��ͨƱ");
		rdoNormal.setSelected(true);
		rdoNormal.addActionListener(this);
		rdoFree = new JRadioButton("��Ʊ");
		rdoFree.addActionListener(this);
		rdoStu = new JRadioButton("ѧ��Ʊ");
		rdoStu.addActionListener(this);
		JLabel lblStuCout = new JLabel("ѧ���ۿ�:");
		JLabel lblSender = new JLabel("������:��");

		// cbx.setPreferredSize(new Dimension(120, 25));
		cbx.addItem("------��ѡ��------");
		for (int i = 9; i > 0; i--) {
			cbx.addItem(i);
		}
		cbx.addActionListener(this);
		txtSender = new JTextField();
		txtSender.setPreferredSize(new Dimension(120, 25));

		group.add(rdoNormal);
		group.add(rdoFree);
		group.add(rdoStu);

		pnl.add(pnl1);
		pnl.add(pnl2);
		pnl.add(pnl3);
		pnl1.add(rdoNormal);
		pnl1.add(rdoFree);
		pnl1.add(rdoStu);
		pnl2.add(lblStuCout);
		pnl2.add(cbx);
		pnl3.add(lblSender);
		pnl3.add(txtSender);
		
		return pnl;
	}

	/**
	 * �������
	 * 
	 * @return
	 */
	public JPanel pnlDetails() {
		JPanel pnl = new JPanel();
		pnl.setBorder(BorderFactory.createTitledBorder("����"));
		// ������������
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		pnl.setLayout(gbl);

		// lbl1
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);
		JLabel lbl1 = new JLabel("Ƭ��:");
		gbl.setConstraints(lbl1, gbc);
		pnl.add(lbl1);
		// lblName
		gbc.gridx = 1;
		gbl.setConstraints(lblName, gbc);
		pnl.add(lblName);
		// lbl2
		gbc.gridx = 2;
		JLabel lbl2 = new JLabel("����:");
		// lbl2.setHorizontalAlignment(SwingConstants.RIGHT);���Ҷ���
		gbl.setConstraints(lbl2, gbc);
		pnl.add(lbl2);
		// lblAuthor
		gbc.gridx = 3;
		gbl.setConstraints(lblAuthor, gbc);
		pnl.add(lblAuthor);
		// lblPicture
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 5;
		gbl.setConstraints(lblPicture, gbc);
		//��ʼͼƬ
		lblPicture.setIcon(ImageHelper.sclaredImg("img/"+item.getMovie().getMovieName()+".jpg", 160, 200 ));
		pnl.add(lblPicture);
		// lbl3
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		JLabel lbl3 = new JLabel("����:");
		gbl.setConstraints(lbl3, gbc);
		pnl.add(lbl3);
		// lblActor
		gbc.gridx = 3;
		gbl.setConstraints(lblActor, gbc);
		pnl.add(lblActor);
		// lbl4
		gbc.gridx = 2;
		gbc.gridy = 2;
		JLabel lbl4 = new JLabel("����:");
		gbl.setConstraints(lbl4, gbc);
		pnl.add(lbl4);
		// lblType
		gbc.gridx = 3;
		gbl.setConstraints(lblType, gbc);
		pnl.add(lblType);
		// lbl5
		gbc.gridx = 2;
		gbc.gridy = 3;
		JLabel lbl5 = new JLabel("��ӳʱ��:");
		gbl.setConstraints(lbl5, gbc);
		pnl.add(lbl5);
		// lblPlayTime
		gbc.gridx = 3;
		gbl.setConstraints(lblPlayTime, gbc);
		pnl.add(lblPlayTime);
		// lbl6
		gbc.gridx = 2;
		gbc.gridy = 4;
		JLabel lbl6 = new JLabel("Ʊ��:");
		gbl.setConstraints(lbl6, gbc);
		pnl.add(lbl6);
		// lblPrice
		gbc.gridx = 3;
		gbl.setConstraints(lblPrice, gbc);
		pnl.add(lblPrice);
		// lbl7
		gbc.gridx = 2;
		gbc.gridy = 5;
		JLabel lbl7 = new JLabel("�Żݼ�:");
		lbl7.setForeground(Color.RED);
		gbl.setConstraints(lbl7, gbc);
		pnl.add(lbl7);
		// lblPriceDisc
		gbc.gridx = 3;
		lblPriceDisc = new JLabel();
		lblPriceDisc.setForeground(Color.RED);
		gbl.setConstraints(lblPriceDisc, gbc);
		pnl.add(lblPriceDisc);

		return pnl;
	}

	/**
	 * ��ӳ�����
	 * 
	 * @return
	 */
	public JPanel pnlScreen() {
		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		JPanel pnl1 = new JPanel();
		pnl1.setLayout(new GridLayout(5, 7, 40, 30));
		pnl1.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
		int seatLine = 5;
		int seatRow = 7;
		for (int i = 0; i < seatLine; i++) {
			for (int j = 0; j < seatRow; j++) {
				//������λ
				String seatNum = (i+1) + "-" + (j+1);
				JLabel lbl = new JLabel(seatNum, JLabel.CENTER);
				lbl.setOpaque(true);
				lbl.setBackground(Color.YELLOW);
				lbl.addMouseListener(this);
				pnl1.add(lbl);
				//�����λ
				labels.put(seatNum, lbl);
				Seat seat = new Seat();
				seat.setSeatNum(seatNum);
				seat.setColor(Color.YELLOW);
				Cinema.getSeats().put(seatNum, seat);
			}
		}

		JTabbedPane tabpnl = new JTabbedPane();
		tabpnl.addTab("��ӳ��", pnl1);

		pnl.add(tabpnl);
		return pnl;
	}

	/**
	 * ��ӳ�б���
	 * 
	 * @return
	 */
	public JPanel pnlTree() {
		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		Map<String, ScheduleItem> items = schedule.getItems();
		for (String key : items.keySet()) {
			// �õ�key��ӳ����
			ScheduleItem scheduleItem = items.get(key);
			// �õ��÷�ӳ���εĵ�Ӱ��
			String movieName = scheduleItem.getMovie().getMovieName();
			DefaultMutableTreeNode nodeMovieName=null;
			// �Ƿ���ڸ�ӰƬ�ڵ�
			boolean isExitNode = false;
			Enumeration enumeration = root.children();
			while (enumeration.hasMoreElements()) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
				// ���ڸ�ӰƬ
				if (node.getUserObject().equals(movieName)) {
					nodeMovieName=node;
					isExitNode = true;
//					node.add(new DefaultMutableTreeNode(scheduleItem));
				}
			}
			// �����ڸ�ӰƬ
			if (!isExitNode) {
				nodeMovieName=new DefaultMutableTreeNode(movieName);
//				DefaultMutableTreeNode nodeMovie = new DefaultMutableTreeNode(movieName);
//				nodeMovie.add(new DefaultMutableTreeNode(scheduleItem));
				root.add(nodeMovieName);
			}
			DefaultMutableTreeNode  nodeTime =new DefaultMutableTreeNode(scheduleItem);
			nodeMovieName.add(nodeTime);
		}
		tree = new JTree(root);
		tree.setRootVisible(false);
		tree.addTreeSelectionListener(this);	
		
		//�Զ�չ�����нڵ�
		for(int i=0;i<tree.getRowCount();i++){
			tree.expandRow(i);
		}

		JTabbedPane tabpnl = new JTabbedPane();
		tabpnl.addTab("��ӳ�б�", tree);

		pnl.add(tabpnl);
		return pnl;
	}

	/**
	 * ��ѡ���¼�
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		try{
			item = (ScheduleItem) selectNode.getUserObject();
			String author = item.getMovie().getDirector();
			String actor = item.getMovie().getActor();
			String director = item.getMovie().getDirector();
			String movieName = item.getMovie().getMovieName();
			MovieType movieType = item.getMovie().getMovieType();
			String poster = item.getMovie().getPoster();
			float price = item.getMovie().getPrice();
			
			lblName.setText(movieName);
			lblAuthor.setText(author);
			lblPicture.setIcon(ImageHelper.sclaredImg("img/"+movieName+".jpg", 160, 200 ));
			lblActor.setText(actor);
			lblType.setText(movieType.toString());
			String time = item.getTime();
			lblPlayTime.setText(time);
			lblPrice.setText(price+"");
			lblPriceDisc.setText(price*discount/10+"");
		} catch(ClassCastException e1){
			
		} finally {
			clearSeat();
			loadSeat();
		}
	}
	
	
	//����¼�
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel label = (JLabel)e.getSource();
		Seat seat= seats.get(label.getText());
		if(item==null){
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ����ĵ�Ӱ����");
			return;
		}
		if("".equals(txtSender.getText())&&rdoFree.isSelected()){
			JOptionPane.showMessageDialog(null, "����д������");
			return;
		}
		if(seat.getColor()==Color.YELLOW){
			int n = JOptionPane.showConfirmDialog(null, "ȷ�Ϲ������λ����", "ȷ�Ͽ�", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				//������λ��ɫ ��ӵ����۳�Ʊ
				seat.setColor(Color.RED);
				label.setBackground(Color.RED);
				//��ӡСƱ
				String customerName = txtSender.getText();
				Ticket ticket = TicketFactory.CreatTicket(item, seat, discount, customerName, ticketType);
				//��ӵ�����Ʊ
				soldTicket.add(ticket);
				//������ʾ
				JOptionPane.showMessageDialog(null, "�Ѵ�ӡ��ӰƱ");
				//��ӡ��ӰƱ
				ticket.print();
				//������λ״̬
				updateSeat();
				//��������
				try {
					Cinema.save();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "����λ�ѱ�����");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	public static void main(String[] args) {
		MovieFrame mf = new MovieFrame();
		mf.setVisible(true);
	}
	
	//�����Żݼ�
	@Override
	public void actionPerformed(ActionEvent e) {
		//�ж�Ʊ������
		if(rdoNormal.isSelected()){
			lblPriceDisc.setText(lblPrice.getText()+"");
			ticketType="";
		}
		if(rdoFree.isSelected()){
			lblPriceDisc.setText("0");
			ticketType="free";
		}
		if(rdoStu.isSelected()){
			//ѧ��Ʊ�ۿ�
			ticketType="student";
			String select=cbx.getSelectedItem().toString();
			if(select!="------��ѡ��------"){
				discount=Integer.parseInt(select);
				lblPriceDisc.setText(Float.parseFloat(lblPrice.getText())*Integer.parseInt(cbx.getSelectedItem().toString())/10+"");				
			}else{
				discount=10;
				lblPriceDisc.setText(lblPrice.getText());
			}
		}
	}
	
	/**
	 * ������λ״̬
	 */
	private void updateSeat(){
		for(String key : seats.keySet()) {
			//��lbl��ɫ������seats
			seats.get(key).setColor(labels.get(key).getBackground());
		}
	}
	
	/**
	 * ������λ�������
	 */
	private void loadSeat(){
		for (Ticket ticket : soldTicket) {
			for(Seat seat:seats.values()){
				if((ticket.getScheduleItem().getTime().equals(item.getTime()))&&(ticket.getSeat().getSeatNum().equals(seat.getSeatNum()))){
					seat.setColor(Color.RED);
					labels.get(seat.getSeatNum()).setBackground(Color.RED);
				}
			}
		}
	}
	
	/**
	 * �����λ
	 */
	private void clearSeat(){
		for(Seat seat:seats.values()){
			seat.setColor(Color.YELLOW);
			labels.get(seat.getSeatNum()).setBackground(Color.YELLOW);
		}
	}
}
