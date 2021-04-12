package bs.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import bs.dao.FilmDao;
import bs.dao.FormatDao;
import bs.dao.GenreDao;
import bs.dao.PersonneDao;
import bs.model.Film;
import bs.model.Format;
import bs.model.Genre;
import bs.model.Personne;

public class Test {

	static Scanner clavier = new Scanner(System.in);
	static GenreDao genreDao = new GenreDao();
	static FilmDao filmDao = new FilmDao();
	static PersonneDao personneDao = new PersonneDao();

	public static void main(String[] args) {




		int choix=0;
		do
		{
			choix = DisplayMenu(choix == 0);
			switch (choix)
			{
			case 1:
				getFilms();
				break;
			case 2:
				getGenres();
				break;
			case 3:
				addFilm();
				break;
			case 4:
				addGenre();
				break;
			case 5:
				editFilm();
				break;
			case 6:
				editGenre();
				break;
			case 7:
				deleteFilm();
				break;
			case 8:
				deleteGenre();
				break;
			default:
				if(choix!=9)
					choix = 0;
				break;
			}

		}
		while (choix!=9);
		System.out.println("Fin Gestion .");

	}




	private static void deleteGenre() {
		String nom = lireClavier("Saisir libelle de la Genre ");
		Genre genre = genreDao.find(nom);
		if(genre != null) {
			genreDao.delete(genre);
		}else {
			System.out.println("la Genre "+nom+" n'existe pas dans la base de données !!");
		}

	}

	private static void deleteFilm() {
		String nom = lireClavier("Saisir Titre du film ");
		Film film = filmDao.find(nom);
		if(film != null) {
			filmDao.delete(film);
		}else {
			System.out.println("le Film "+nom+" n'existe pas dans la base de données !!");
		}
	}

	private static void editGenre() {
		String oldNom = lireClavier("Saisir la libelle de la  Genre à changer ");
		Genre genre = genreDao.find(oldNom);
		String nom = lireClavier("Saisir la nouvelle libelle de la Genre  ");
		genre.setLibelle(nom);
		genreDao.update(genre);

	}

	private static void editFilm() {
		String oldTitre = lireClavier("Saisir le titre du film");
		Film film = filmDao.find(oldTitre);
		String titre = lireClavier("Saisir le nouveau titre ");
		String date_sorti = lireClavier("Saisir new date de sorti ex : yyyy-mm-dd ");
		String resume = lireClavier("Saisir le resumé du film");
		String photo = lireClavier("Saisir photo ");
		String audio = lireClavier("Saisir audio ");
		String sous_titre = lireClavier("Saisir sous_titre ");
		String duree = lireClavier("Saiser durée");
		String personneSaisie =lireClavier("personne");
		if(isValidFormat("yyyy-MM-dd",date_sorti)) {
			LocalDate date = LocalDate.parse(date_sorti);
			Personne personne = personneDao.find(personneSaisie);
			LocalTime time = LocalTime.parse(duree);
			
			film.setTitre(titre);
			film.setDate_sorti(date);
			film.setResume(resume);
			film.setPhoto(photo);
			film.setAudio(audio);
			film.setSous_titre(sous_titre);
			film.setDuree(time);
			film.setPersonne(personne);
			filmDao.update(film);
		}else {
			System.out.println("echoé !! la format de date incorrect");
		}


	}

	private static void addGenre() {
		String nom = lireClavier("Saisir la libelle du Genre");
		boolean ok = genreDao.add(new Genre(nom));
		if(ok) {
			System.out.println("la Genre a bien été enregistrée");
		}

	}

	private static void addFilm() {
		String titre = lireClavier("Saisir le nouveau titre ");
		String date_sorti = lireClavier("Saisir new date de sorti ex : yyyy-mm-dd ");
		String resume = lireClavier("Saisir le resumé du film");
		String photo = lireClavier("Saisir photo ");
		String audio = lireClavier("Saisir audio ");
		String sous_titre = lireClavier("Saisir sous_titre ");
		String duree = lireClavier("Saiser durée");
		String personneSaisie = lireClavier("personne");
		if(isValidFormat("yyyy-MM-dd",date_sorti)) {
			LocalDate date = LocalDate.parse(date_sorti);
			LocalTime time = LocalTime.parse(duree);
			Personne personne = personneDao.find(personneSaisie);
			boolean flag = filmDao.add(new Film(titre,date,resume,photo,audio,sous_titre,time,personne));
			if(flag) {
				System.out.println("le Film a bien été enregistré");
			}
		}else {
			System.out.println("echoé !! la format de date incorrect");
		}

	}

	private static void getGenres() {
//		List<Genre> genres = genreDao.list();
//		for(Genre v : genres) {
//			System.out.println(v);
//		}
		
//		getPersonnes
//		List<Personne> personnes = personneDao.listRialisateur();
//		for(Personne p : personnes) {
//			System.out.println(p);
//		}
		//getformats
		FormatDao formatDao = new FormatDao();
		List<Format> fs = formatDao.list();
		for (Format f : fs) {
			System.out.println(f);
		}

	}

	private static void getFilms() {
		List<Film> films = filmDao.list();
		for(Film film : films) {
			System.out.println(film);
		}

	}

	private static int DisplayMenu(boolean complet)
	{
		int choix=0;
		String message;
		if (complet)
		{
			System.out.println(" ***************************************************************");
			System.out.println(" ********** Gestion Films ==>  : *************");
			System.out.println(" ********** 1. Afficher Films*********************************");
			System.out.println(" ********** 2. Afficher Genre  ***********************");


			System.out.println(" ********** 3. Création Film *****************************");
			System.out.println(" ********** 4. Création Genre******************************");
			System.out.println(" ********** 5. Modifier Film ******************************");
			System.out.println(" ********** 6. Modifier Genre *****************************");
			System.out.println(" ********** 7. delete Film ******************************");
			System.out.println(" ********** 8. delete Genre ******************************");

			System.out.println(" ********** 9. Quitter *****************************************");
			System.out.println(" ***************************************************************");

			message="===>";
		}
		else
		{
			message="Choix Menu ===>";
		}

		try {
			choix = Integer.parseInt(lireClavier(message));
		} catch (Exception e) {}

		return choix;
	}

	private static String lireClavier(String message)
	{        
		String saisie;
		do
		{
			System.out.print(message + " : ");
			saisie = clavier.nextLine();
		}
		while (saisie.equals(""));
		return saisie;
	}

	public static boolean isValidFormat(String format, String value) {
		LocalDate ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format);

		try {
			ldt = LocalDate.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			System.out.println("invalide date");

		}
		return false;
	}

}
