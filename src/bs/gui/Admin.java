package bs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import bs.dao.FilmDao;
import bs.dao.FormatDao;
import bs.dao.PersonneDao;
import bs.dao.StockerDao;
import bs.model.Film;
import bs.model.Format;
import bs.model.Personne;
import bs.model.Stocker;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JPanel Add;
	private JPanel Acteurs;
	private JPanel pFilm;
	private JTextField textDate;
	private JTextField textDuration;
	private JTextField textRealisateur;
	private JTextArea resumer;
	private JTextField textSubtitles;
	private JTextField textAudio;
	private JLabel lblNewLabel;
	private JTextField TTitle;
	private JTextField TDate;
	private JTextField TDuration;
	private JLabel lblNewLabel_1;
	private JLabel lblDate;
	private JLabel lblDuaration;
	private JLabel lblNewLabel_2;
	private JLabel lblAudio;
	private JTextField TSub;
	private JTextField TAudio;
	private JTextField TRealisateur;
	private JTextField TImg;
	private JLabel lblRalisateur;
	private JLabel lblImage;
	private JTextField textResumer;
	private JButton ajouter;
	private JButton pImg;
	private Film film;
	private JComboBox comboBox;
	private JComboBox comboFormat;
	private JComboBox comboFilm;
	private JLabel lblImageLink;
	private JTextField textLink;
	private JPanel addPersone;
	private JTextField name;
	private JTextField lastName;
	private JTextArea textAreaDiscribe;
	private JTextField Born;
	private FilmDao filmDao = new FilmDao();
	private PersonneDao personneDao = new PersonneDao();
	private JPanel stocks;
	private Format format;
	private List<Format> formats;
	private List<Personne> personnes;
	private FormatDao formaDao = new FormatDao();
	private StockerDao stockDao = new StockerDao();
	private Stocker stock;
	private JTextField textQte;
	private static Personne persone;
	private JTextArea ActeurDiscription = new JTextArea();
	private JTextField DateNaissance = new JTextField();
	private JPanel pMenue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1071, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pMenue = new JPanel();
		pMenue.setBounds(12, 13, 189, 476);
		pMenue.setBackground(Color.DARK_GRAY);
		contentPane.add(pMenue);
		JButton bFilm = new JButton("Films");
		bFilm.setBackground(Color.LIGHT_GRAY);
		bFilm.setFont(new Font("Tahoma", Font.BOLD, 13));
		bFilm.setBounds(12, 26, 165, 25);
		bFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					getContentPane().removeAll();
					getContentPane().add(pMenue);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				reload();
				listFilm();
			}
		});
		pMenue.setLayout(null);
		pMenue.add(bFilm);

		JButton personne = new JButton("Actor");
		personne.setBackground(Color.LIGHT_GRAY);
		personne.setFont(new Font("Tahoma", Font.BOLD, 13));
		personne.setBounds(12, 102, 165, 25);
		pMenue.add(personne);
		personne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getContentPane().removeAll();
					getContentPane().add(pMenue);				

				} catch (Exception e2) {
					// TODO: handle exception
				}
				reload();
				showActeurs();
			}
		});

		JButton btstock = new JButton("Stocks");
		btstock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					getContentPane().removeAll();
					getContentPane().add(pMenue);

				} catch (Exception e2) {
					// TODO: handle exception
				}
				reload();
				PanelStock ps = new PanelStock();
				stocks = ps.stocks;
				getContentPane().add(stocks);
				
				//ListStocks();
			}
		});
		btstock.setBackground(Color.LIGHT_GRAY);
		btstock.setFont(new Font("Tahoma", Font.BOLD, 13));
		btstock.setBounds(12, 64, 165, 25);
		pMenue.add(btstock);


		JButton btActeurFilm = new JButton("Add Acteur To Film");
		btActeurFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					getContentPane().removeAll();
					getContentPane().add(pMenue);

				} catch (Exception e2) {
					// TODO: handle exception
				}
				reload();
				PanelActeurFilm Paf = new PanelActeurFilm();
				contentPane.add(Paf.ActeursFilms);
				Paf.ActeursFilms.setVisible(true);
				
			}
		});
		btActeurFilm.setBackground(Color.LIGHT_GRAY);
		btActeurFilm.setFont(new Font("Tahoma", Font.BOLD, 13));
		btActeurFilm.setBounds(12, 140, 165, 25);
		pMenue.add(btActeurFilm);
	}


	private void showActeurs() {
		Acteurs = new JPanel();
		Acteurs.setBounds(213, 13, 832, 476);
		Acteurs.setBackground(Color.DARK_GRAY);
		contentPane.add(Acteurs);
		Acteurs.setLayout(null);



		JButton btnAddActeur = new JButton("Add ");
		btnAddActeur.setBackground(Color.LIGHT_GRAY);
		btnAddActeur.setBounds(441, 108, 100, 25);
		btnAddActeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getContentPane().removeAll();
					getContentPane().add(pMenue);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				reload();
				addPersonneForm();
			}
		});
		Acteurs.add(btnAddActeur);

		personnes = personneDao.getAll();
		String[] personneName= new String[personnes.size()];
		int i=0;
		for (Personne p : personnes) {
			personneName[i++]= p.getNom()+" "+p.getPrenom();

		}
		JComboBox comboNomActeur = new JComboBox(personneName);
		comboNomActeur.setBackground(Color.LIGHT_GRAY);
		comboNomActeur.setBounds(258, 161, 283, 30);
		Acteurs.add(comboNomActeur);
		persone = personnes.get(comboNomActeur.getSelectedIndex());
		ActeurValues(persone);

		comboNomActeur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				persone = personnes.get(comboNomActeur.getSelectedIndex());
				ActeurValues(persone);

			}
		});

		JLabel lbActeur = new JLabel("Acteur");
		lbActeur.setForeground(Color.LIGHT_GRAY);
		lbActeur.setBounds(161, 168, 73, 16);
		Acteurs.add(lbActeur);

		JLabel lbActeure = new JLabel("Acteur");
		lbActeure.setForeground(Color.LIGHT_GRAY);
		lbActeure.setBackground(Color.LIGHT_GRAY);
		lbActeure.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lbActeure.setBounds(183, 102, 174, 30);
		Acteurs.add(lbActeure);


		DateNaissance.setBackground(Color.LIGHT_GRAY);
		DateNaissance.setBounds(258, 215, 283, 30);
		Acteurs.add(DateNaissance);
		DateNaissance.setColumns(10);



		JLabel lblDateNaissance = new JLabel("Date Naissance ");
		lblDateNaissance.setForeground(Color.LIGHT_GRAY);
		lblDateNaissance.setBounds(161, 222, 91, 16);
		Acteurs.add(lblDateNaissance);

		//		JButton DeleteActeur = new JButton("Delete");
		//		DeleteActeur.setBackground(Color.LIGHT_GRAY);
		//		DeleteActeur.setBounds(441, 340, 100, 25);
		//		Acteurs.add(DeleteActeur);
		//		DeleteActeur.addActionListener(new ActionListener() {
		//	
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				persone = personnes.get(comboNomActeur.getSelectedIndex());
		//				personneDao.delete(persone);
		//				
		//			}
		//		});

		JButton UpdateActeur = new JButton("Update");
		UpdateActeur.setBackground(Color.LIGHT_GRAY);
		UpdateActeur.setBounds(258, 340, 100, 25);
		Acteurs.add(UpdateActeur);
		UpdateActeur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String phrase = (String) comboNomActeur.getSelectedItem();
				String mots[] = phrase.split(" ");		
				persone = personnes.get(comboNomActeur.getSelectedIndex());
				persone.setPrenom(mots[1]);
				persone.setNom(mots[0]);
				persone.setDescription(ActeurDiscription.getText());
				LocalDate date = LocalDate.parse(DateNaissance.getText());
				persone.setDate_naissance(date);
				personneDao.update(persone);

			}
		});


		ActeurDiscription.setBackground(Color.LIGHT_GRAY);
		ActeurDiscription.setBounds(258, 268, 283, 52);
		ActeurDiscription.setLineWrap(true);
		Acteurs.add(ActeurDiscription);

		JLabel Description = new JLabel("Description");
		Description.setForeground(Color.LIGHT_GRAY);
		Description.setBounds(161, 284, 91, 16);
		Acteurs.add(Description);

	}

	private void ActeurValues(Personne presonne) {	
		ActeurDiscription.setText(presonne.getDescription());
		DateNaissance.setText(presonne.getDate_naissance().toString());
	}

	

	private void listFilm() {
		try {
			getContentPane().remove(stocks);
			addPersone.setVisible(false);
			Add.setVisible(false);
			stocks.setVisible(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		reload();
		pFilm = new JPanel();
		pFilm.setBounds(213, 13, 832, 476);
		pFilm.setBackground(Color.DARK_GRAY);
		contentPane.add(pFilm);
		pFilm.setLayout(null);

		List<Film> films = filmDao.list();
		String[] films_title= new String[films.size()];
		int i=0;
		for (Film f : films) {
			films_title[i++]= f.getTitre();
		}
		comboBox = new JComboBox<Object>(films_title);
		String title = (String) comboBox.getSelectedItem();
		film = filmDao.find(title);
		comboBox.setOpaque(true);
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setBounds(25, 113, 269, 22);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String title = (String) ((JComboBox<String>)e.getSource()).getSelectedItem();
				film = filmDao.find(title);
				filmValue();
			}
		});
		pFilm.add(comboBox);

		textDate = new JTextField();
		textDate.setBackground(Color.LIGHT_GRAY);
		textDate.setBounds(408, 113, 116, 22);
		pFilm.add(textDate);
		textDate.setColumns(10);


		JLabel lblNewLabel = new JLabel("SELECT YOUR FILM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(25, 52, 413, 33);
		pFilm.add(lblNewLabel);

		textDuration = new JTextField();
		textDuration.setColumns(10);
		textDuration.setBackground(Color.LIGHT_GRAY);
		textDuration.setBounds(408, 148, 116, 22);
		pFilm.add(textDuration);

		textRealisateur = new JTextField();
		textRealisateur.setColumns(10);
		textRealisateur.setBackground(Color.LIGHT_GRAY);
		textRealisateur.setBounds(128, 148, 166, 22);
		pFilm.add(textRealisateur);

		JLabel lblRealisateur = new JLabel("R\u00E9alisateur");
		lblRealisateur.setForeground(Color.LIGHT_GRAY);
		lblRealisateur.setBounds(35, 151, 81, 16);
		pFilm.add(lblRealisateur);

		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setBounds(342, 116, 54, 16);
		pFilm.add(lblDate);

		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setForeground(Color.LIGHT_GRAY);
		lblDuration.setBounds(342, 151, 54, 16);
		pFilm.add(lblDuration);

		resumer = new JTextArea();
		resumer.setBackground(Color.LIGHT_GRAY);
		resumer.setFont(new Font("Monospaced", Font.PLAIN, 15));
		resumer.setBounds(25, 222, 499, 124);
		resumer.setLineWrap(true);
		resumer.setBorder(new EmptyBorder(10, 10, 10, 10));
		pFilm.add(resumer);
		resumer.setColumns(10);


		pImg = new JButton();
		pImg.setOpaque(true);
		pImg.setBackground(Color.LIGHT_GRAY);
		pImg.setBounds(552, 113, 200, 185);

		pFilm.add(pImg);

		JButton btnAddFilm = new JButton("Add Film");
		btnAddFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFilmForm();
				try {
					getContentPane().remove(pFilm);
					getContentPane().remove(addPersone);
				} catch (Exception e2) {
					// TODO: handle exception
				}

				reload();
			}
		});
		btnAddFilm.setBackground(Color.LIGHT_GRAY);
		btnAddFilm.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddFilm.setBounds(562, 59, 165, 25);
		pFilm.add(btnAddFilm);

		JButton btnUpdateFilm = new JButton("Update Film");
		btnUpdateFilm.setBackground(Color.LIGHT_GRAY);
		btnUpdateFilm.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdateFilm.setBounds(46, 359, 165, 25);
		btnUpdateFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFilm();
			}
		});
		pFilm.add(btnUpdateFilm);

		JButton btnDeleteFilm = new JButton("Delete Film");
		btnDeleteFilm.setBackground(Color.LIGHT_GRAY);
		btnDeleteFilm.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDeleteFilm.setBounds(275, 359, 165, 25);
		pFilm.add(btnDeleteFilm);

		textSubtitles = new JTextField();
		textSubtitles.setColumns(10);
		textSubtitles.setBackground(Color.LIGHT_GRAY);
		textSubtitles.setBounds(128, 183, 166, 22);
		pFilm.add(textSubtitles);

		textAudio = new JTextField();
		textAudio.setColumns(10);
		textAudio.setBackground(Color.LIGHT_GRAY);
		textAudio.setBounds(408, 187, 116, 22);
		pFilm.add(textAudio);

		JLabel lblSubtitles = new JLabel("Subtitles");
		lblSubtitles.setForeground(Color.LIGHT_GRAY);
		lblSubtitles.setBounds(35, 180, 81, 16);
		pFilm.add(lblSubtitles);

		JLabel lblAudio = new JLabel("Audio");
		lblAudio.setForeground(Color.LIGHT_GRAY);
		lblAudio.setBounds(342, 193, 54, 16);
		pFilm.add(lblAudio);
		textLink = new JTextField();
		textLink.setColumns(10);
		textLink.setBackground(Color.LIGHT_GRAY);
		textLink.setBounds(616, 324, 136, 22);
		pFilm.add(textLink);

		lblImageLink = new JLabel("Image link");
		lblImageLink.setForeground(Color.LIGHT_GRAY);
		lblImageLink.setBounds(552, 327, 81, 16);
		pFilm.add(lblImageLink);

		filmValue();
	}

	private void filmValue() {
		ImageIcon image = new ImageIcon(new ImageIcon("images/"+film.getPhoto()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		pImg.setIcon(image);
		textDate.setText(film.getDate_sorti().toString());
		textSubtitles.setText(film.getSous_titre());
		textAudio.setText(film.getAudio());
		textRealisateur.setText(film.getPersonne().getNom());
		textDuration.setText(film.getDuree().toString());
		resumer.setText(film.getResume());
		textLink.setText(film.getPhoto());

	}
	private void updateFilm() {
		int id = film.getId_film();
		String titre = (String) comboBox.getSelectedItem();
		LocalDate date_sorti = LocalDate.parse(textDate.getText());
		String resume = resumer.getText().replaceAll("'", "\'");
		String photo = textLink.getText();
		String audio = textAudio.getText();
		String sous_titre = textSubtitles.getText();
		LocalTime duree = LocalTime.parse(textDuration.getText());
		Personne personne = personneDao.find(textRealisateur.getText());
		film = new Film(id,titre, date_sorti, resume, photo, audio, sous_titre, duree, personne);
		if(personne!=null) {
			filmDao.update(film);
		}else {
			try {
				getContentPane().remove(pFilm);
			} catch (Exception e) {
				// TODO: handle exception
			}


			addPersonneForm();
		}
		reload();
	}
	private void addFilm() {
		try {
			pFilm.setVisible(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String titre = TTitle.getText();
		LocalDate date_sorti = LocalDate.parse(TDate.getText());
		String resume = textResumer.getText().replaceAll("'", "\'");
		String photo = TImg.getText();
		String audio = TAudio.getText();
		String sous_titre = TSub.getText();
		LocalTime duree = LocalTime.parse(TDuration.getText());
		Personne personne = personneDao.find(TRealisateur.getText());
		film = new Film(titre, date_sorti, resume, photo, audio, sous_titre, duree, personne);
		if(personne!=null) {
			filmDao.add(film);
		}else {

			addPersonneForm();
		}
		reload();

	}
	private void addPersonneForm(){
		try {
			getContentPane().remove(Add);
			getContentPane().remove(pFilm);
		} catch (Exception e) {
			// TODO: handle exception
		}
		reload();
		addPersone = new JPanel();
		addPersone.setBounds(213, 13, 832, 476);
		addPersone.setBackground(Color.DARK_GRAY);
		contentPane.add(addPersone);
		addPersone.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add New R\u00E9alisateur ");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(194, 84, 347, 30);
		addPersone.add(lblNewLabel);

		name = new JTextField();
		name.setBackground(Color.LIGHT_GRAY);
		name.setBounds(194, 127, 347, 30);
		addPersone.add(name);
		name.setColumns(10);

		JLabel lblDate = new JLabel("FirstName");
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setBounds(117, 134, 65, 16);
		addPersone.add(lblDate);

		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBackground(Color.LIGHT_GRAY);
		lastName.setBounds(194, 170, 347, 30);
		addPersone.add(lastName);



		Born = new JTextField();
		Born.setColumns(10);
		Born.setBackground(Color.LIGHT_GRAY);
		Born.setBounds(194, 319, 347, 30);
		addPersone.add(Born);



		JLabel lblBorn = new JLabel("Born");
		lblBorn.setForeground(Color.LIGHT_GRAY);
		lblBorn.setBounds(117, 326, 76, 16);
		addPersone.add(lblBorn);

		JButton btnAddRealis = new JButton("Add ");
		btnAddRealis.setBackground(Color.LIGHT_GRAY);
		btnAddRealis.setBounds(301, 362, 130, 25);
		btnAddRealis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPersonne();
			}
		});
		addPersone.add(btnAddRealis);

		JLabel lblLastname = new JLabel("LastName");
		lblLastname.setForeground(Color.LIGHT_GRAY);
		lblLastname.setBounds(117, 177, 65, 16);
		addPersone.add(lblLastname);

		JLabel lblDescribe = new JLabel("Describe");
		lblDescribe.setForeground(Color.LIGHT_GRAY);
		lblDescribe.setBounds(117, 255, 76, 16);
		addPersone.add(lblDescribe);

		textAreaDiscribe = new JTextArea();
		textAreaDiscribe.setBackground(Color.LIGHT_GRAY);
		textAreaDiscribe.setBounds(194, 213, 347, 85);
		addPersone.add(textAreaDiscribe);

		JLabel lblToContinueAdding = new JLabel("to continue adding the film you must add the  R\u00E9alisateur");
		lblToContinueAdding.setForeground(Color.LIGHT_GRAY);
		lblToContinueAdding.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblToContinueAdding.setBackground(Color.LIGHT_GRAY);
		lblToContinueAdding.setBounds(194, 41, 347, 30);
		addPersone.add(lblToContinueAdding);

	}
	private void addPersonne() {
		String nom = name.getText();
		String prenom = lastName.getText();
		String description = textAreaDiscribe.getText().replaceAll("'", "\'");
		LocalDate date_naissance = LocalDate.parse(Born.getText());
		Personne p1 = new Personne(nom, prenom, description, date_naissance);
		System.out.println(film);
		personneDao.add(p1);
		Personne p = personneDao.find(nom);
		film.setPersonne(p);
		if(film.getId_film()>0) {
			filmDao.update(film);
		}else {
			filmDao.add(film);
		}

	}
	private void addFilmForm() {
		pFilm.setVisible(false);
		Add = new JPanel();
		Add.setBounds(213, 13, 832, 476);
		Add.setBackground(Color.DARK_GRAY);
		contentPane.add(Add);
		Add.setLayout(null);

		lblNewLabel = new JLabel("Add New Film");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(194, 29, 347, 30);
		Add.add(lblNewLabel);

		TTitle = new JTextField();
		TTitle.setBackground(Color.LIGHT_GRAY);
		TTitle.setBounds(194, 84, 347, 30);
		Add.add(TTitle);
		TTitle.setColumns(10);

		TDate = new JTextField();
		TDate.setBackground(Color.LIGHT_GRAY);
		TDate.setBounds(194, 127, 130, 30);
		Add.add(TDate);
		TDate.setColumns(10);

		TDuration = new JTextField();
		TDuration.setBackground(Color.LIGHT_GRAY);
		TDuration.setColumns(10);
		TDuration.setBounds(411, 127, 130, 30);
		Add.add(TDuration);

		lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBounds(117, 91, 65, 16);
		Add.add(lblNewLabel_1);

		lblDate = new JLabel("Date");
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setBounds(117, 134, 65, 16);
		Add.add(lblDate);

		lblDuaration = new JLabel("Duaration");
		lblDuaration.setForeground(Color.LIGHT_GRAY);
		lblDuaration.setBounds(348, 134, 65, 16);
		Add.add(lblDuaration);

		lblNewLabel_2 = new JLabel("Subtitles");
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(117, 177, 56, 16);
		Add.add(lblNewLabel_2);

		lblAudio = new JLabel("Audio");
		lblAudio.setForeground(Color.LIGHT_GRAY);
		lblAudio.setBounds(357, 177, 56, 16);
		Add.add(lblAudio);

		TSub = new JTextField();
		TSub.setColumns(10);
		TSub.setBackground(Color.LIGHT_GRAY);
		TSub.setBounds(194, 170, 130, 30);
		Add.add(TSub);

		TAudio = new JTextField();
		TAudio.setColumns(10);
		TAudio.setBackground(Color.LIGHT_GRAY);
		TAudio.setBounds(411, 170, 130, 30);
		Add.add(TAudio);

		TRealisateur = new JTextField();
		TRealisateur.setColumns(10);
		TRealisateur.setBackground(Color.LIGHT_GRAY);
		TRealisateur.setBounds(194, 213, 130, 30);
		Add.add(TRealisateur);

		TImg = new JTextField();
		TImg.setColumns(10);
		TImg.setBackground(Color.LIGHT_GRAY);
		TImg.setBounds(411, 213, 130, 30);
		Add.add(TImg);

		lblRalisateur = new JLabel("R\u00E9alisateur");
		lblRalisateur.setForeground(Color.LIGHT_GRAY);
		lblRalisateur.setBounds(117, 220, 76, 16);
		Add.add(lblRalisateur);

		lblImage = new JLabel("Image");
		lblImage.setForeground(Color.LIGHT_GRAY);
		lblImage.setBounds(357, 220, 56, 16);
		Add.add(lblImage);

		textResumer = new JTextField();
		textResumer.setBackground(Color.LIGHT_GRAY);
		textResumer.setBounds(194, 256, 347, 93);
		Add.add(textResumer);
		textResumer.setColumns(10);

		ajouter = new JButton("Add");
		ajouter.setBackground(Color.LIGHT_GRAY);
		ajouter.setBounds(301, 362, 130, 25);
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFilm();
			}
		});
		Add.add(ajouter);
	}
	private void reload() {
		this.revalidate();
		this.repaint();
	}
}
