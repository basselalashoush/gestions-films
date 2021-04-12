package bs.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import bs.dao.ActeurDao;
import bs.dao.FilmDao;
import bs.dao.PersonneDao;
import bs.model.Acteur;
import bs.model.Film;
import bs.model.Personne;

import javax.swing.JLabel;
import java.awt.Font;

public class PanelActeurFilm extends JPanel {
	public JPanel ActeursFilms;
	private FilmDao filmDao = new FilmDao();
	private List<Film> films = new ArrayList<Film>();
	private Film film;
	private JTextArea textArea = new JTextArea();
	private List<Personne> acteurs = new ArrayList<Personne>();
	private PersonneDao pesonneDao = new PersonneDao();
	private JComboBox ActeurBox;
	private JComboBox BoxFilm;
	private Personne personne;

	/**
	 * Create the panel.
	 */
	public PanelActeurFilm() {
		ActeursFilms = new JPanel();
		ActeursFilms.setBounds(213, 13, 832, 476);
		ActeursFilms.setBackground(Color.DARK_GRAY);
		ActeursFilms.setLayout(null);


		films = filmDao.list();
		String[] films_title= new String[films.size()];
		int j=0;
		for (Film f : films) {
			films_title[j++]= f.getTitre();
		}
		BoxFilm = new JComboBox(films_title);
		BoxFilm.setBounds(523, 210, 244, 44);
		ActeursFilms.add(BoxFilm);
		String title = (String) BoxFilm.getSelectedItem();
		film = filmDao.find(title);
		listActeur(film);
		BoxFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = (String) BoxFilm.getSelectedItem();
				film = filmDao.find(title);
				listActeur(film);
			}
		});
		acteurs = pesonneDao.getAll();
		String [] acteuresName =new String [acteurs.size()];
		int h=0;
		for(Personne p : acteurs) {
			acteuresName[h++]=p.getNom()+" "+p.getPrenom();
		}
		ActeurBox = new JComboBox(acteuresName);
		ActeurBox.setBounds(523, 308, 244, 44);
		ActeursFilms.add(ActeurBox);
		String Phrase = (String) ActeurBox.getSelectedItem();
		personne =find(Phrase);
		ActeurBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Phrase = (String) ActeurBox.getSelectedItem();
				personne =find(Phrase);
			}
		});

		JButton AddActeurFilm = new JButton("Add");
		AddActeurFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Phrase = (String) ActeurBox.getSelectedItem();
				personne =find(Phrase);
				String title = (String) BoxFilm.getSelectedItem();
				film = filmDao.find(title);
				Acteur act = new Acteur(film, personne);
				ActeurDao acteurDao = new ActeurDao();
				Acteur acteur = acteurDao.find(film.getId_film(), personne.getId_personne());
				if (acteur == null) {
					acteurDao.add(act);
				}
				reload() ;
			}
		});
		AddActeurFilm.setBounds(525, 378, 97, 44);
		ActeursFilms.add(AddActeurFilm);

		JButton DeleteActeurFilm = new JButton("Delete");
		DeleteActeurFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Phrase = (String) ActeurBox.getSelectedItem();
				personne =find(Phrase);
				String title = (String) BoxFilm.getSelectedItem();
				film = filmDao.find(title);
				Acteur act = new Acteur(film, personne);
				ActeurDao acteurDao = new ActeurDao();
				Acteur acteur = acteurDao.find(film.getId_film(), personne.getId_personne());
				if (acteur != null) {
					acteurDao.delete(act);
				}
				reload() ;
			}
		});
		DeleteActeurFilm.setBounds(670, 378, 97, 44);
		ActeursFilms.add(DeleteActeurFilm);

	
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(51, 161, 402, 261);
		textArea.setLineWrap(true);
		ActeursFilms.add(textArea);

		JLabel ListeActeur = new JLabel("Actors");
		ListeActeur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		ListeActeur.setForeground(Color.LIGHT_GRAY);
		ListeActeur.setBackground(Color.LIGHT_GRAY);
		ListeActeur.setBounds(51, 118, 402, 30);
		ActeursFilms.add(ListeActeur);

		JLabel Film = new JLabel("Film");
		Film.setForeground(Color.LIGHT_GRAY);
		Film.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		Film.setBackground(Color.LIGHT_GRAY);
		Film.setBounds(523, 161, 244, 30);
		ActeursFilms.add(Film);

		JLabel lblActeur = new JLabel("Actor");
		lblActeur.setForeground(Color.LIGHT_GRAY);
		lblActeur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblActeur.setBackground(Color.LIGHT_GRAY);
		lblActeur.setBounds(523, 265, 244, 30);
		ActeursFilms.add(lblActeur);
	}

	private void listActeur(Film f) {
		
		acteurs = pesonneDao.listActeursByFilm(f.getId_film());
		if(acteurs.size() >0) {
			String listeActeurs = "";
			for(Personne personne : acteurs) {
				listeActeurs += personne.getPrenom()+" "+personne.getNom() +",";
			}

			String LesActeurs = listeActeurs.substring(0,listeActeurs.length()-1);
			textArea.setText("Actors : "+LesActeurs);

		}else {
			textArea.setText("Actors : ");
		}

	}
	private Personne find(String phrase) {
		String mots[] = phrase.split(" ");		
		personne =pesonneDao.find(mots[0], mots[1]);
		return personne;
	}
	private void reload() {
		ActeursFilms.revalidate();
		ActeursFilms.repaint();
	}
}
