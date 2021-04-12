package bs.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bs.dao.FilmDao;
import bs.dao.FormatDao;
import bs.dao.StockerDao;
import bs.model.Film;
import bs.model.Format;
import bs.model.Stocker;

public class PanelStock extends JPanel {
public  JPanel Panier;
public JPanel stocks;
private List<Format> formats;
private FormatDao formaDao = new FormatDao();
private JComboBox comboFormat;
private Format format;
private static FilmDao filmDao = new FilmDao();
private Film film ;
private JTextField textQte;
private JComboBox comboFilm;
private static StockerDao stockDao = new StockerDao();
private Stocker stock;
private List<Film> films;

	/**
	 * Create the panel.
	 */
	public PanelStock() {
		stocks = new JPanel();
		stocks.setBounds(213, 13, 832, 476);
		stocks.setBackground(Color.DARK_GRAY);
		stocks.setLayout(null);

		JLabel lblTitle = new JLabel("Stocks");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setBackground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblTitle.setBounds(183, 102, 174, 30);
		stocks.add(lblTitle);

		formats = formaDao.list();
		String[] format_liblle= new String[formats.size()];
		int i=0;
		for (Format format : formats) {
			format_liblle[i++]= format.getType();
		}
		comboFormat = new JComboBox(format_liblle);
		comboFormat.setBackground(Color.LIGHT_GRAY);
		comboFormat.setBounds(229, 215, 116, 30);
		String type = (String) comboFormat.getSelectedItem();
		format = (Format) formaDao.find(type);
		comboFormat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String title = (String) ((JComboBox<String>)e.getSource()).getSelectedItem();
				film = filmDao.find(title);
				StockValue();
			}
		});
		stocks.add(comboFormat);


		JLabel lblFormat = new JLabel("Format");
		lblFormat.setForeground(Color.LIGHT_GRAY);
		lblFormat.setBounds(183, 222, 44, 16);
		stocks.add(lblFormat);

		textQte = new JTextField();
		textQte.setColumns(10);
		textQte.setBackground(Color.LIGHT_GRAY);
		textQte.setBounds(425, 215, 116, 30);
		stocks.add(textQte);



		JLabel Qte = new JLabel("Quntité");
		Qte.setForeground(Color.LIGHT_GRAY);
		Qte.setBounds(368, 222, 55, 16);
		stocks.add(Qte);

		films = filmDao.list();
		String[] films_title= new String[films.size()];
		int j=0;
		for (Film f : films) {
			films_title[j++]= f.getTitre();
		}
		comboFilm = new JComboBox(films_title);
		comboFilm.setBackground(Color.LIGHT_GRAY);
		comboFilm.setBounds(229, 161, 312, 30);
		String title = (String) comboFilm.getSelectedItem();
		film = filmDao.find(title);
		comboFilm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String title = (String) ((JComboBox<String>)e.getSource()).getSelectedItem();
				film = filmDao.find(title);
				StockValue();
			}
		});
		stocks.add(comboFilm);

		JLabel lblFilm = new JLabel("Film");
		lblFilm.setForeground(Color.LIGHT_GRAY);
		lblFilm.setBounds(183, 168, 44, 16);
		stocks.add(lblFilm);



		JButton UpdateStock = new JButton("Update");
		UpdateStock.setBackground(Color.LIGHT_GRAY);
		UpdateStock.setBounds(330, 269, 100, 25);
		stocks.add(UpdateStock);
		UpdateStock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String titre = (String) comboFilm.getSelectedItem();
				film = filmDao.find(titre);
				String type = (String) comboFormat.getSelectedItem();
				format = (Format) formaDao.find(type);
				stock = stockDao.find(film.getId_film(), format.getId_format());
				if(stock == null) {
					stock = new Stocker(film, format,Integer.parseInt(textQte.getText()));
					stockDao.add(stock);
				}
				stock.setQte(Integer.parseInt(textQte.getText()));
				stockDao.updateStock(stock);
			}
		});
		StockValue();
		
	}
	private void StockValue() {
		String titre = (String) comboFilm.getSelectedItem();
		film = filmDao .find(titre);
		String type = (String) comboFormat.getSelectedItem();
		format = (Format) formaDao.find(type);
		stock = stockDao.find(film.getId_film(), format.getId_format());
		if(stock == null) {
			stock = new Stocker(film, format, 0);
			stockDao.add(stock);
		}
		textQte.setText(String.valueOf(stock.getQte()));

	}

}
