package bs.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import bs.dao.CommandeDao;
import bs.dao.FilmDao;
import bs.dao.FormatDao;
import bs.dao.Ligne_CmdDao;
import bs.dao.PrixDao;
import bs.dao.StockerDao;
import bs.dao.UserDao;
import bs.gui.Admin;
import bs.gui.FilmInfo;
import bs.model.Commande;
import bs.model.Film;
import bs.model.Format;
import bs.model.Ligne_Cmd;
import bs.model.Prix;
import bs.model.Stocker;
import bs.model.User;


public class Shope extends JFrame {

	private JButton btnPanier = new JButton("Panier"); ;
	private JButton btnConection = new JButton("LogIn");
	private JButton btnDeconnection = new JButton("LogOut");
	private JPanel panelFilm;
	private JLabel lblWelcom;
	private static  User user;
	private JPanel test;
	private JPanel panelMenu;

	UserDao userDao  = new UserDao();
	JDialog current;
	INotifier notifier;
	private JTextField tfUser;
	private JPasswordField tfPassword;
	private JTextField CreateUserName;
	private JPasswordField CreatePassword;
	private JTextField tPseudo;
	private JLabel lblError;

	private JButton btnDelete;
	private JTextField textField;
	private JTextField prixTotal;

	private JPanel panelMenu1;
	private JPanel produit;

	private static List<Ligne_Cmd>ligne_cmds = new ArrayList<Ligne_Cmd>();
	private static int q;
	private static int y;
	private static Prix px;
	private JLabel historyFormat;
	private JLabel historyQte;
	private JComboBox qte;
	private JComboBox ShopQte;
	private List<JComboBox> ListShopQte = new ArrayList<JComboBox>();
	private JComboBox ShopType;
	private List<JComboBox> ListShopType = new ArrayList<JComboBox>();
	private JButton Payer;
	private List<JButton> delete = new ArrayList<JButton>();
	private List<JComboBox> qtes = new ArrayList<JComboBox>();
	private List<JComboBox> formatsFilm = new ArrayList<JComboBox>();
	private List<JLabel> historyQtes = new ArrayList<JLabel>();
	private List<JLabel> historyFor = new ArrayList<JLabel>();
	private List<JTextField> prices = new ArrayList<JTextField>();
	private JLabel historyPrice;
	private List<JLabel> historyPrices = new ArrayList<JLabel>();
	private JLabel lblNewLabel;
	private JTextField prixFilm;
	private List<JTextField> ListPrixFilm = new ArrayList<JTextField>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shopping frame = new Shopping(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Shope (User user) {
		this.user= user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test = new JPanel();
		JScrollPane scrollFrame = new JScrollPane(test);
		test.setAutoscrolls(true);
		test.setLayout(null);
		test.setBackground(new Color(0, 0, 51));
		
		constract(user);
		getContentPane().add(scrollFrame);
		scrollFrame.setPreferredSize(new Dimension( 800,300));

	}
	/**
	 * create the shoping page (catalogue)
	 * @param user
	 */
	private void constract(User user) {
		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(0, 0, 51));
		panelMenu.setBounds(12, 13, 1400, 100);
		test.add(panelMenu);
		panelMenu.setLayout(null);

		lblWelcom = new JLabel("");
		lblWelcom.setForeground(Color.WHITE);
		lblWelcom.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblWelcom.setBounds(1160, 13, 228, 33);
		panelMenu.add(lblWelcom);
		if(user == null) {
			btnConection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						test.removeAll();
						auth();

					} catch (Exception e2) {
						// TODO: handle exception
					}
					update();

				}
			});
			btnConection.setBounds(1260, 60, 130, 30);
			btnConection.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			btnConection.setForeground(new Color(0, 0, 51));
			btnConection.setBackground(new Color(255, 255, 255));
			panelMenu.add(btnConection);
		}else {
			lblWelcom.setText("Welcome : "+user.getNom());
			btnDeconnection.setBounds(1260, 60, 130, 30);
			btnDeconnection.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			btnDeconnection.setForeground(new Color(0, 0, 51));
			btnDeconnection.setBackground(new Color(255, 255, 255));
			panelMenu.add(btnDeconnection);

			btnDeconnection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					test.removeAll();
					update();
					Shope.user = null;
					constract(null);

				}


			});

			btnPanier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Ligne_Cmd> ligne_cmds =  new Ligne_CmdDao().list(user.getId_user(),0);
					Panier(user,ligne_cmds);
				}
			});
			btnPanier.setBounds(1100,60, 130, 30);
			btnPanier.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			btnPanier.setForeground(new Color(0, 0, 51));
			btnPanier.setBackground(new Color(255, 255, 255));

			panelMenu.add(btnPanier);
		}
		List<Film>films = new ArrayList<Film>();
		FilmDao filmDao = new FilmDao();
		films = filmDao.list();
		test.setPreferredSize(new Dimension(1420, 1620));
		setBounds(100, 100, 1460,800);
		int i=0;
		for(Film film : films) {
			int y = 300*i;

			panelFilm = new JPanel();
			panelFilm.setBackground(new Color(224, 255, 255));
			panelFilm.setBounds(12, 120+y, 1400, 285);
			test.add(panelFilm);
			panelFilm.setLayout(null);

			ImageIcon image = new ImageIcon(new ImageIcon("images/"+film.getPhoto()).getImage().getScaledInstance(230, 230, Image.SCALE_DEFAULT));
			JButton btnPhoto = new JButton("");
			btnPhoto.setBackground(new Color(0, 0, 51));
			btnPhoto.setBounds(12, 13, 258, 259);
			btnPhoto.setIcon(image);
			panelFilm.add(btnPhoto);
			btnPhoto.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					FilmInfo filmInfo = new FilmInfo(film.getId_film(),user);
					filmInfo.setAlwaysOnTop(true);
					filmInfo.setVisible(true);


				}
			});

			JLabel lblTitre = new JLabel(film.getTitre());
			lblTitre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
			lblTitre.setBounds(428, 13, 485, 58);
			lblTitre.setForeground(new Color(0, 0, 51));
			panelFilm.add(lblTitre);

			JLabel lblRealisateur = new JLabel(film.getPersonne().getPrenom()+" "+film.getPersonne().getNom());
			lblRealisateur.setBounds(282, 128, 214, 33);
			panelFilm.add(lblRealisateur);

			JTextArea textArea = new JTextArea(film.getResume());
			textArea.setBounds(282, 174, 871, 98);
			textArea.setBackground(new Color(255, 255, 255));
			textArea.setFont(new Font("Tahoma", Font.ITALIC, 16));
			textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
			textArea.setLineWrap(true);
			panelFilm.add(textArea);

			JLabel lblAudio = new JLabel(film.getAudio());
			lblAudio.setBounds(620, 128, 168, 33);
			panelFilm.add(lblAudio);

			JLabel lblSousTitre = new JLabel(film.getSous_titre());
			lblSousTitre.setBounds(985, 128, 168, 33);
			panelFilm.add(lblSousTitre);

			JButton btnDetail = new JButton("See More");
			btnDetail.setBackground(new Color(0, 0, 51));
			btnDetail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			btnDetail.setForeground(new Color(255, 255, 255));
			btnDetail.setBounds(1215, 25, 120, 25);
			btnDetail.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					FilmInfo filmInfo = new FilmInfo(film.getId_film(),user);
					filmInfo.setAlwaysOnTop(true);
					filmInfo.setVisible(true);


				}
			});
			panelFilm.add(btnDetail);
			/**
			 * changeHomePage
			 */
			Integer[] quntite = {0,1,2,3,4,5,6,7,8,9,10};
			ShopQte = new JComboBox<Object>(quntite);
			ShopQte.setBackground((new Color(0, 0, 51)));
			ShopQte.setForeground((new Color(255,255,255)));
			ShopQte.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			q = ShopQte.getSelectedIndex();
			ShopQte.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					q = ((JComboBox<Integer>)e.getSource()).getSelectedIndex();
					for(JComboBox j : ListShopQte) {
						if (j == ((JComboBox<Object>)e.getSource())) {
							calculerPrixUnite(ListShopQte.indexOf(j));
						}

					}


				}	
			});
			ShopQte.setBounds(1175, 70, 200, 25);
			panelFilm.add(ShopQte);

			ListShopQte.add(ShopQte);
			
			FormatDao formatDao = new FormatDao();
			List<Format> fs = formatDao.list();
			String[] formatType = new String[fs.size()];
			int g=0;
			for (Format f : fs) {
				formatType[g++]= f.getType();
			}
			ShopType = new JComboBox<Object>(formatType);

			PrixDao prixDao = new PrixDao();
			String type = (String) ShopType.getSelectedItem();
			Format ChosinFormat = (Format) formatDao.find(type);
			px = prixDao.find(film.getId_film(),ChosinFormat.getId_format());
			ShopType.setBackground((new Color(0, 0, 51)));
			ShopType.setForeground((new Color(255,255,255)));
			ShopType.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String type = (String) ((JComboBox<String>)e.getSource()).getSelectedItem();
					Format format = (Format) new FormatDao().find(type);
					px = prixDao.find(film.getId_film(),format.getId_format());
					for(JComboBox j : ListShopType) {
						if (j == ((JComboBox<Object>)e.getSource())) {
							for(JComboBox jc : qtes) {
								if ( ListShopType.indexOf(j)== qtes.indexOf(jc)) {
									q= jc.getSelectedIndex();

								}

							}

							calculerPrixUnite(ListShopType.indexOf(j));

						}

					}


				}
			});

			ShopType.setBounds(1175, 115, 200, 25);
			panelFilm.add(ShopType);
			ListShopType.add(ShopType);
			
			prixFilm = new JTextField();
			prixFilm.setForeground(new Color(51, 153, 102));
			prixFilm.setBounds(597, 39, 158, 22);
			panelFilm.add(prixFilm);
			prixFilm.setColumns(10);
			ListPrixFilm.add(prixFilm);
			prixFilm.setBounds(1175, 160, 200, 25);
			
			JButton btnValider = new JButton("Ajouter");
			btnValider.setBackground(new Color(0, 0, 51));
			btnValider.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			btnValider.setForeground(new Color(255, 255, 255));
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (user !=null) {
					CommandeDao cmdDao = new CommandeDao();
					Commande cmd = new Commande( LocalDate.now(), false, q, q*px.getPrix(), user);
					cmdDao.add(cmd,q*px.getPrix());
					Commande lastCommande = cmdDao.getCommande();
					Format f= new FormatDao().find(px.getFormat().getId_format());
			
					Ligne_Cmd lc = new Ligne_Cmd(film, lastCommande,f, q);
					Ligne_CmdDao lcDao = new Ligne_CmdDao();
					lcDao.add(lc);					
					}else {
						test.removeAll();
						update();
						auth();
					}

				}


			});
			btnValider.setBounds(1215, 205, 120, 25);
			panelFilm.add(btnValider);
			
			/**
			 * 
			 */



			JPanel panel2 = new JPanel();
			panel2.setBackground(Color.RED);
			panel2.setBounds(12, 13+y, 1400, 285);
			test.add(panelFilm);
			i++;
		}
	}
	/**
	 * create the page connection
	 */
	private void auth() {
		JPanel panelConnection = new JPanel();
		panelConnection.setBackground(new Color(0, 0, 51));
		panelConnection.setBounds(750, 300, 352, 308);
		test.add(panelConnection);
		panelConnection.setLayout(null);
		panelConnection.setBorder(new LineBorder(new Color(224, 255, 255), 2));

		tfUser = new JTextField();
		tfUser.setColumns(10);
		tfUser.setBounds(123, 100, 187, 35);
		panelConnection.add(tfUser);

		JLabel label = new JLabel("UserName");
		label.setForeground(new Color(255, 255, 255));
		label.setBackground(new Color(255, 255, 255));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		label.setBounds(40, 110, 71, 14);
		panelConnection.add(label);

		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		label_1.setBounds(37, 150, 74, 30);
		panelConnection.add(label_1);

		tfPassword = new JPasswordField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(123, 148, 187, 35);
		panelConnection.add(tfPassword);

		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = userDao.find(tfUser.getText(), tfPassword.getText());
				if (user==null) {
					System.out.println("Identifiant Erroné");
					lblError.setText("Identifiant Erroné");
				}else {
					if(user.getRole().equalsIgnoreCase("admin")) {

						System.out.println("welcom admin");
						Admin manager = new Admin();
						manager.setAlwaysOnTop(true);
						manager.setVisible(true);
						close();
					}else {
						System.out.println("welcom user");
						test.removeAll();
						update();
						constract(user);

					}
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button.setForeground(new Color(0, 0, 51));
		button.setBackground(new Color(255, 255, 255));
		button.setBounds(152, 196, 136, 26);
		panelConnection.add(button);

		JLabel lblLogin = new JLabel("LogIn");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblLogin.setBounds(51, 32, 259, 35);
		panelConnection.add(lblLogin);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblError.setBounds(40, 246, 270, 35);
		panelConnection.add(lblError);

		JPanel panelCreate = new JPanel();
		panelCreate.setLayout(null);
		panelCreate.setBackground(new Color(0, 0, 51));
		panelCreate.setBounds(363, 300, 352, 308);
		test.add(panelCreate);
		panelCreate.setBorder(new LineBorder(new Color(224, 255, 255), 2));

		CreateUserName = new JTextField();
		CreateUserName.setColumns(10);
		CreateUserName.setBounds(116, 90, 198, 35);
		panelCreate.add(CreateUserName);

		JLabel label_2 = new JLabel("UserName");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(33, 100, 71, 14);
		panelCreate.add(label_2);

		JLabel label_3 = new JLabel("Password");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		label_3.setBounds(33, 188, 74, 30);
		panelCreate.add(label_3);

		CreatePassword = new JPasswordField();
		CreatePassword.setColumns(10);
		CreatePassword.setBounds(116, 186, 198, 35);
		panelCreate.add(CreatePassword);

		JButton buttonCreate = new JButton("Create");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User(CreateUserName.getText(),"user",tPseudo.getText() ,CreatePassword.getText());
				userDao.add(user);
				test.removeAll();
				update();
				constract(user);
			}
		});
		buttonCreate.setForeground(new Color(0, 0, 51));
		buttonCreate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		buttonCreate.setBackground(Color.WHITE);
		buttonCreate.setBounds(144, 239, 136, 26);
		panelCreate.add(buttonCreate);

		JLabel pseudo = new JLabel("Pseudo");
		pseudo.setForeground(new Color(255, 255, 255));
		pseudo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		pseudo.setBounds(33, 147, 56, 16);
		panelCreate.add(pseudo);

		tPseudo = new JTextField();
		tPseudo.setColumns(10);
		tPseudo.setBounds(116, 138, 198, 35);
		panelCreate.add(tPseudo);

		JLabel lblNewLabel = new JLabel("LogUp");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(55, 30, 259, 35);
		panelCreate.add(lblNewLabel);
		setLocationRelativeTo(null);
	}

	/**
	 * create the panier 
	 * @param user conected or null 
	 * @param ligne_cmds les commandes qui sont passées par this user => panier 
	 *         ou  les commandes qui sont  déja payer => history 
	 */
	private void Panier(User user, List<Ligne_Cmd> ligne_cmds) {

		panelMenu1 = new JPanel();
		panelMenu1.setBackground(new Color(0, 0, 51));
		panelMenu1.setBounds(12, 13, 1400, 100);
		test.add(panelMenu1);
		panelMenu1.setLayout(null);

		JButton btnCatalogue = new JButton("Catalogue");
		btnCatalogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				test.removeAll();
				update();
				constract(user);
			}
		});
		btnCatalogue.setBounds(1200, 35, 130, 30);
		btnCatalogue.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnCatalogue.setForeground(new Color(0, 0, 51));
		btnCatalogue.setBackground(new Color(255, 255, 255));
		panelMenu1.add(btnCatalogue);

		JButton btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Ligne_Cmd> ligne_cmds =  new Ligne_CmdDao().list(user.getId_user(),1);
				test.setPreferredSize(new Dimension( 1420,ligne_cmds.size()*250 +120));
				Panier(user,ligne_cmds);
				try {
					for(JButton jb : delete) {
						jb.setVisible(false);
					}
					Payer.setVisible(false);
					for(JComboBox jcb : qtes) {
						jcb.setVisible(false);
					}
					for(JComboBox jcbf : formatsFilm) {
						jcbf.setVisible(false);
					}
					for(JLabel jlq : historyQtes) {
						jlq.setVisible(true);
					}
					for(JLabel jlf : historyFor) {
						jlf.setVisible(true);
					}
					for(JTextField jtf : prices) {
						jtf.setVisible(false);
					}
					for(JLabel jl : historyPrices) {
						jl.setVisible(true);
					}
					lblNewLabel.setText("Histoire Des Commandes");

				} catch (Exception e2) {
					// TODO: handle exception
				}

				update();
			}
		});
		btnHistory.setBounds(1050, 35, 130, 30);
		btnHistory.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnHistory.setForeground(new Color(0, 0, 51));
		btnHistory.setBackground(new Color(255, 255, 255));
		panelMenu1.add(btnHistory);

		JButton btnPanier = new JButton("Panier");
		btnPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Ligne_Cmd> ligne_cmds =  new Ligne_CmdDao().list(user.getId_user(),0);
				test.setPreferredSize(new Dimension( 1420,ligne_cmds.size()*250 +120));
				Panier(user,ligne_cmds);
				update();
			}
		});
		btnPanier.setBounds(900, 35, 130, 30);
		btnPanier.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnPanier.setForeground(new Color(0, 0, 51));
		btnPanier.setBackground(new Color(255, 255, 255));
		panelMenu1.add(btnPanier);

		Payer = new JButton("Payer");
		Payer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommandeDao cd = new CommandeDao();

				try {
					for(Ligne_Cmd lc : ligne_cmds) {
						Commande cmd = new Commande(lc.getCde().getId_cmd(),null, true, lc.getCde().getQte(), lc.getCde().getTotal(), user);
						cd.update(cmd);

						StockerDao stockDao = new StockerDao();
						Stocker  stock = new Stocker(lc.getFilm(), lc.getFormat(),lc.getCde().getQte());
						stockDao.update(stock);
						ligne_cmds.remove(lc);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}


				Panier(user, ligne_cmds);
				update();
			}
		});
		Payer.setBounds(750, 35, 130, 30);
		Payer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		Payer.setForeground(new Color(0, 0, 51));
		Payer.setBackground(new Color(255, 255, 255));
		panelMenu1.add(Payer);

		lblNewLabel = new JLabel("Votre Panier");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(91, 13, 331, 31);
		panelMenu1.add(lblNewLabel);
		constractPanier(ligne_cmds);


	}
	public void constractPanier(List<Ligne_Cmd> ligne_cmds) {
		try {
			test.removeAll();
			test.add(panelMenu1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		update();
		int z = 0;
		for(Ligne_Cmd lc : ligne_cmds) {
			y = z*250;
			produit = new JPanel();
			produit.setBackground(new Color(224, 255, 255));
			produit.setBounds(12, 120+y, 1400, 223);
			test.add(produit);
			produit.setLayout(null);

			ImageIcon image = new ImageIcon(new ImageIcon("images/"+lc.getFilm().getPhoto()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
			JButton btnNewButton = new JButton(image);
			btnNewButton.setBackground(new Color(0, 0, 51));
			btnNewButton.setBounds(12, 13, 196, 197);
			produit.add(btnNewButton);

			btnDelete = new JButton("Delete");
			btnDelete.setBounds(968, 155, 97, 30);
			btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			btnDelete.setForeground(new Color(255, 255, 255));
			btnDelete.setBackground(new Color(0, 0, 51));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Ligne_CmdDao ligneC = new Ligne_CmdDao();
					ligneC.delete(lc);
					CommandeDao cd = new CommandeDao();
					cd.delete(lc.getCde());
					ligne_cmds.remove(lc);
					Panier(user, ligne_cmds);

				}
			});
			produit.add(btnDelete);
			delete.add(btnDelete);
			historyQte = new JLabel("");
			historyQte.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			historyQte.setForeground(new Color(0, 0, 51));
			historyQte.setBounds(359, 156, 137, 22);
			historyQte.setText(String.valueOf(lc.getCde().getQte()));
			produit.add(historyQte);
			historyQtes.add(historyQte);
			historyQte.setVisible(false);


			Integer[] qutite = {0,1,2,3,4,5,6,7,8,9,10};
			qte = new JComboBox<Object>(qutite);
			qte.setSelectedIndex(lc.getQte());
			qte.setBackground((new Color(0, 0, 51)));
			qte.setForeground((new Color(255,255,255)));
			qte.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			q = qte.getSelectedIndex();
			qte.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					q = ((JComboBox<Integer>)e.getSource()).getSelectedIndex();
					// update le cmd
					Commande cmd= lc.getCde();
					cmd.setQte(q);
					
					CommandeDao cmdDao = new CommandeDao();
					
					
					for(JComboBox j : qtes) {
						if (j == ((JComboBox<Object>)e.getSource())) {
							calculerPrix(qtes.indexOf(j));
							double total = calculer(qtes.indexOf(j));
							cmd.setTotal(total);
							cmdDao.updatePanier(cmd);
						}

					}


				}	
			});
			qte.setBounds(359, 156, 137, 30);
			produit.add(qte);

			qtes.add(qte);
			JLabel label = new JLabel("Type");
			label.setBounds(522, 159, 56, 16);
			produit.add(label);

			//Film film = new FilmDao().find();
			FormatDao formatDao = new FormatDao();
			List<Format> fs = formatDao.list();
			String[] format_libelle = new String[fs.size()];
			int i=0;
			for (Format f : fs) {
				format_libelle[i++]= f.getType();
			}
			historyFormat = new JLabel("");
			historyFormat.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			historyFormat.setForeground((new Color(0, 0, 51)));
			historyFormat.setBounds(590, 156, 100, 22);
			historyFormat.setText(lc.getFormat().getType());
			produit.add(historyFormat);
			historyFor.add(historyFormat);
			historyFormat.setVisible(false);
			JComboBox formats = new JComboBox<Object>(format_libelle);

			PrixDao prixDao = new PrixDao();
			px = prixDao.find(lc.getFilm().getId_film(),lc.getFormat().getId_format());
			formats.setSelectedItem(lc.getFormat().getType());
			formats.setBackground((new Color(0, 0, 51)));
			formats.setForeground((new Color(255,255,255)));
			formats.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String type = (String) ((JComboBox<String>)e.getSource()).getSelectedItem();
					Format format = (Format) new FormatDao().find(type);
					px = prixDao.find(lc.getFilm().getId_film(),format.getId_format());
					for(JComboBox j : formatsFilm) {
						if (j == ((JComboBox<Object>)e.getSource())) {
							for(JComboBox jc : qtes) {
								if ( formatsFilm.indexOf(j)== qtes.indexOf(jc)) {
									q= jc.getSelectedIndex();

								}

							}
							
							Commande cmd= lc.getCde();
							cmd.setQte(q);
							
							CommandeDao cmdDao = new CommandeDao();
							// update le lcmd
							
							
							lc.setFormat(format);
							new Ligne_CmdDao().update(lc);
							
							calculerPrix(formatsFilm.indexOf(j));
							double total = calculer(qtes.indexOf(j));
							cmd.setTotal(total);
							cmdDao.updatePanier(cmd);

						}

					}


				}
			});

			formats.setBounds(590, 156, 100, 30);
			produit.add(formats);
			formatsFilm.add(formats);
			JLabel lblPrix = new JLabel("prix");
			lblPrix.setBounds(706, 159, 34, 16);
			produit.add(lblPrix);

			textField = new JTextField();
			Prix prix = new PrixDao().find(lc.getFilm().getId_film(), lc.getFormat().getId_format());

			textField.setColumns(10);
			textField.setBounds(752, 156, 158, 30);
			textField.setBackground((new Color(0, 0, 51)));
			textField.setForeground((new Color(255,255,255)));
			textField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			produit.add(textField);
			prices.add(textField);

			historyPrice = new JLabel("");
			historyPrice.setBounds(752, 156, 158, 30);
			historyPrice.setForeground((new Color(0, 0, 51)));
			historyPrice.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			produit.add(historyPrice);
			historyPrices.add(historyPrice);
			historyPrice.setVisible(false);

			JLabel label_2 = new JLabel("Quntité");
			label_2.setBounds(291, 159, 56, 16);
			produit.add(label_2);

			JLabel label_1 = new JLabel(lc.getFilm().getTitre());
			label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
			label_1.setForeground(new Color(0, 0, 51));
			label_1.setBounds(291, 27, 410, 76);
			produit.add(label_1);
			z++;
			String price = Double.toString(q*px.getPrix());
			textField.setText(price);
			historyPrice.setText(Double.toString(lc.getCde().getTotal()));
		}
	}
	private void calculerPrix(int x) {
		for(int h = 0;h< prices.size();h++) {
			String price = Double.toString(q*px.getPrix());
			if(h == x) {
				prices.get(h).setText(price);
				
			}

		}		
	}
	
	private Double calculer(int x) {
		String price= "";
		for(int h = 0;h< prices.size();h++) {
			price = Double.toString(q*px.getPrix());
			if(h == x) {
				prices.get(h).setText(price);
				
			}

		}	
		return Double.parseDouble(price);
	}
	
	private void calculerPrixUnite(int x) {
		for(int h = 0;h< ListPrixFilm.size();h++) {
			String price = Double.toString(q*px.getPrix());
			if(h == x) {
				ListPrixFilm.get(h).setText(price);
				
			}

		}		
	}
	
	

	private void close() {
		this.dispose();	
	}
	private void update () {
		this.revalidate();
		this.repaint();
	}
}
