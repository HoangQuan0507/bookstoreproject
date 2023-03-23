package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.mindrot.jbcrypt.BCrypt;

import com.toedter.calendar.JDateChooser;

import entities.Account;
import entities.Author;
import entities.Book;
import entities.Category;
import models.AccountModel;
import models.AuthorModel;
import models.BookModel;
import models.CategoryModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JFrameCreateAuthor extends JFrame {

	private JPanel contentPane;
	private JTextField jtextName;
	private JTextArea jtextArea;
	private JButton jbtnSave;
	private JButton jbtnCancel;
	private JTable jtable;
	private JButton jbtnEdit;
	private JButton jbtnDelete;
	private JButton jbtnUpdate;
	private int id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameCreateAuthor frame = new JFrameCreateAuthor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameCreateAuthor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Author", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 6, 191, 241);
		contentPane.add(panel);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(25, 17, 90, 29);
		panel.add(lblName);

		JLabel lblNewLabel_3 = new JLabel("Story");
		lblNewLabel_3.setBounds(25, 49, 90, 29);
		panel.add(lblNewLabel_3);

		jtextName = new JTextField();
		jtextName.setColumns(10);
		jtextName.setBounds(64, 17, 114, 28);
		panel.add(jtextName);

		jtextArea = new JTextArea();
		jtextArea.setLineWrap(true);
		jtextArea.setBounds(64, 49, 114, 122);
		panel.add(jtextArea);

		jbtnSave = new JButton("Save");
		jbtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSave_actionPerformed(e);
			}
		});
		jbtnSave.setBounds(25, 189, 60, 28);
		panel.add(jbtnSave);

		jbtnUpdate = new JButton("Update");
		jbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnUpdate_actionPerformed(e);
			}
		});
		jbtnUpdate.setBounds(97, 189, 81, 28);
		panel.add(jbtnUpdate);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "List Author", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(209, 6, 348, 241);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 19, 324, 163);
		panel_1.add(scrollPane);

		jtable = new JTable();
		scrollPane.setViewportView(jtable);

		jbtnEdit = new JButton("Edit");
		jbtnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnEdit_actionPerformed(e);
			}
		});
		jbtnEdit.setBounds(126, 194, 78, 28);
		panel_1.add(jbtnEdit);

		jbtnDelete = new JButton("Delete");
		jbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnDelete_actionPerformed(e);
			}
		});
		jbtnDelete.setBounds(30, 194, 71, 28);
		panel_1.add(jbtnDelete);

		jbtnCancel = new JButton("Cancel");
		jbtnCancel.setBounds(228, 194, 81, 28);
		panel_1.add(jbtnCancel);
		jbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		loadData();
	}

	public void jbtnDelete_actionPerformed(ActionEvent e) {
		try {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				AuthorModel authorModel = new AuthorModel();
				int selectedRow = jtable.getSelectedRow();
				id = Integer.parseInt(jtable.getValueAt(selectedRow, 0).toString());
				if (authorModel.delete(id)) {
					JOptionPane.showMessageDialog(null, "Success");
					fillDataToJTable(authorModel.findAll());
				} else {
					JOptionPane.showMessageDialog(null, "Failed");
				}
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}

	public void jbtnUpdate_actionPerformed(ActionEvent e) {
		AuthorModel authorModel = new AuthorModel();
		Author author = new Author();
		author.setId(id);
		author.setName(jtextName.getText());
		author.setStory(jtextArea.getText());
		if (authorModel.update(author)) {
			JOptionPane.showMessageDialog(null, "Success");

			fillDataToJTable(authorModel.findAll());
		} else {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}

	public void jbtnEdit_actionPerformed(ActionEvent e) {
		int selectedRow = jtable.getSelectedRow();
		id = Integer.parseInt(jtable.getValueAt(selectedRow, 0).toString());
		AuthorModel authorModel = new AuthorModel();
		Author author = authorModel.find(id);
		jtextName.setText(author.getName());
		jtextArea.setText(author.getStory());

	}

	public void jbtnSave_actionPerformed(ActionEvent e) {
		try {

			Author author = new Author();

			author.setName(jtextName.getText());

			author.setStory(jtextArea.getText());

			AuthorModel authorModel = new AuthorModel();
			if (authorModel.create(author)) {
				JOptionPane.showMessageDialog(null, "Success");
				fillDataToJTable(authorModel.findAll());

			} else {
				JOptionPane.showMessageDialog(null, "Failed");
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}

	private void loadData() {
		AuthorModel authorModel = new AuthorModel();

		fillDataToJTable(authorModel.findAll());

	}

	private void fillDataToJTable(List<Author> authors) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}

		};
		defaultTableModel.addColumn("Id");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Story");
		AuthorModel authorModel = new AuthorModel();

		for (Author author : authors) {
			defaultTableModel.addRow(new Object[] { author.getId(), author.getName(), author.getStory(), });
		}
		jtable.setModel(defaultTableModel);
		jtable.getTableHeader().setReorderingAllowed(false);
		jtable.setModel(defaultTableModel);
	}

}
