package bs.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javax.swing.border.EmptyBorder;



import bs.dao.FilmDao;

import bs.dao.PersonneDao;

import bs.model.Film;

import bs.model.Personne;

import bs.model.User;


public class FilmInfo extends JFrame {
	private static int id_film;
	private JPanel contentPane;
	private JTextArea textActeurs;
	private static User user;


	

	public FilmInfo(int id_film, User user) {
		this.user= user;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 976, 879);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(224, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initiu(id_film);
		
	
	}

	private void initiu(int id_film) {
		FilmInfo.id_film = id_film;
		FilmDao filmDao = new FilmDao();
		Film film = filmDao.find(id_film);

		ImageIcon image = new ImageIcon(new ImageIcon("images/"+film.getPhoto()).getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT));
		JLabel img = new JLabel(image);
		img.setBounds(12, 13, 462, 402);
		img.setOpaque(true);
		img.setBackground(new Color(0, 0, 51));
		contentPane.add(img);

		JTextArea resumer = new JTextArea(film.getResume());
		resumer.setFont(new Font("Monospaced", Font.PLAIN, 20));
		resumer.setBounds(12, 428, 934, 186);
		resumer.setLineWrap(true);
		resumer.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(resumer);

		JLabel lblTitre = new JLabel(film.getTitre());
		lblTitre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblTitre.setForeground(new Color(0, 0, 51));
		lblTitre.setBounds(516, 24, 410, 76);
		contentPane.add(lblTitre);

		JLabel lblDate = new JLabel("Date de sortie : '"+film.getDate_sorti()+"'");
		lblDate.setBounds(516, 113, 171, 59);
		contentPane.add(lblDate);

		JLabel lblTime = new JLabel("Durée : '"+film.getDuree()+"'");
		lblTime.setBounds(708, 113, 171, 59);
		contentPane.add(lblTime);

		JLabel lblrealisateur = new JLabel("Réalisateur : "+film.getPersonne().getNom()+" "+film.getPersonne().getPrenom());
		lblrealisateur.setBounds(516, 188, 355, 32);
		contentPane.add(lblrealisateur);

		List<Personne> acteurs = new ArrayList<Personne>();
		PersonneDao pesonneDao = new PersonneDao();
		acteurs = pesonneDao.listActeursByFilm(id_film);
		if(acteurs.size() >0) {
		String listeActeurs = "";
		for(Personne personne : acteurs) {
			listeActeurs += personne.getPrenom()+" "+personne.getNom() +",";
		}
		
		String LesActeurs = listeActeurs.substring(0,listeActeurs.length()-1);
		
		textActeurs = new JTextArea("Acteurs : "+LesActeurs);
		}else {
			textActeurs = new JTextArea("Acteurs : ");
		}
		textActeurs.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textActeurs.setBounds(509, 229, 437, 186);
		textActeurs.setColumns(10);
		textActeurs.setBorder(new EmptyBorder(10, 10, 10, 10));
		textActeurs.setLineWrap(true);
		contentPane.add(textActeurs);


	}

}
