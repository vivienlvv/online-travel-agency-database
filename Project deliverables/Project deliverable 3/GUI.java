package sqlProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.TextArea;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GuiFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static DatabaseConnection currentConnection; 
	private JTextField txtListOfAttributes;
	private JTextField txtListOfparameters;
	private JTextField txtTableName;
	private JTextField txtTableName1;
	
	
	public static void main(String[] args) {
		
		//runs the guiframe
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiFrame frame = new GuiFrame();
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
	public GuiFrame() throws SQLException {
		//opens the connection to the database
		currentConnection = new DatabaseConnection();
		currentConnection.createConnection();
		
		//basic set-up of the guiframe itself
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//main label
		JLabel lblGuiFrameworkSelect = new JLabel("GUI Framework: Fill out the necessary information and select the choice button");
		lblGuiFrameworkSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblGuiFrameworkSelect.setBounds(18, 11, 397, 16);
		contentPane.add(lblGuiFrameworkSelect);
		
		//label for choice 1
		JLabel lblChoiceSelect = new JLabel("Choice 1: Select an attribute from a table");
		lblChoiceSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblChoiceSelect.setBounds(23, 54, 300, 16);
		contentPane.add(lblChoiceSelect);
		
		//text field for attribute name
		txtListOfAttributes = new JTextField();
		txtListOfAttributes.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtListOfAttributes.setText("Attribute Name");
		txtListOfAttributes.setBounds(23, 70, 202, 26);
		contentPane.add(txtListOfAttributes);
		txtListOfAttributes.setColumns(10);
		
		//text field for table name
		txtTableName = new JTextField();
		txtTableName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtTableName.setText("Table Name");
		txtTableName.setBounds(234, 70, 82, 26);
		contentPane.add(txtTableName);
		txtTableName.setColumns(10);
		
		//for choice 3
		//text field for table
		JTextField txtTable3= new JTextField();
		txtTable3.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtTable3.setText("Table name");
		txtTable3.setBounds(23, 165, 82, 26);
		contentPane.add(txtTable3);
		txtTable3.setColumns(10);
		
		//optional columns
		JTextField txtColumns3 = new JTextField();
		txtColumns3.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtColumns3.setText("Columns (optional)");
		txtColumns3.setBounds(130, 165, 82, 26);
		contentPane.add(txtColumns3);
		txtColumns3.setColumns(10);
		
		//parameters
		JTextField txtParameters3 = new JTextField();
		txtParameters3.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtParameters3.setText("Parameters");
		txtParameters3.setBounds(234, 165, 82, 26);
		contentPane.add(txtParameters3);
		txtParameters3.setColumns(10);
		
		//label for choice 3
		JLabel lblChoiceSelect3 = new JLabel("Choice 3: Insert new data or delete from table.");
		JLabel syntax = new JLabel("Syntax: INSERT INTO table (cols-opt) VALUES params or DELETE FROM table WHERE cols IN (param)");
		lblChoiceSelect3.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblChoiceSelect3.setBounds(23, 135, 300, 16);
		syntax.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		syntax.setBounds(23, 145, 500, 16);
		contentPane.add(lblChoiceSelect3);
		contentPane.add(syntax);
		

		
		
		
		//text field for button named 'choice 1'
		JTextArea output=new JTextArea();
		JScrollPane scroll = new JScrollPane(output);
		//this.add(textArea); // get rid of this
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
		JButton btnTemptest = new JButton("Select");
		btnTemptest.addActionListener(new ActionListener() {
			
			//if you press choice 1, then the action is performed
		public void actionPerformed(ActionEvent e) {
				try {
					String attribute = txtListOfAttributes.getText();
					String tableName = txtTableName.getText();
					String text=currentConnection.choiceOne(attribute, tableName);
					output.setText(text);
					output.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
					output.setBounds(160, 200, 350, 400);
					contentPane.add(output);
					contentPane.repaint();
					contentPane.revalidate();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: applying the connection in the action listener");
					e1.printStackTrace();
				}
			}
		});
		
		
		btnTemptest.setBounds(404, 68, 107, 29);
		contentPane.add(btnTemptest);
		//text field for button named 'choice 2'
		JButton button = new JButton("Number of customers");
		button.addActionListener(new ActionListener() {
		//if you press choice 2, then the action is performed
		public void actionPerformed(ActionEvent e) {
			try {
					String attribute = txtListOfAttributes.getText();
					String tableName = txtTableName.getText();
					String text=currentConnection.choiceTwo();
					output.setText(text);
					output.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
					output.setBounds(160, 200, 350, 400);
					contentPane.add(output);
					contentPane.repaint();
					contentPane.revalidate();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: applying the connection in the action listener");
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(18, 108, 117, 29);
		contentPane.add(button);
		
		//insert button
		JButton button_1 = new JButton("Insert");
		button_1.addActionListener(new ActionListener() {
			//if you press choice 3, then the action is performed
			public void actionPerformed(ActionEvent e) {
				try {
						String attribute = txtColumns3.getText();
						String tableName = txtTable3.getText();
						String parameters= txtParameters3.getText();
						
						String text=currentConnection.choiceThree(tableName, attribute, parameters);
						output.setText(text);
						output.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
						output.setBounds(160, 200, 350, 400);
						contentPane.add(output);
						contentPane.repaint();
						contentPane.revalidate();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error: applying the connection in the action listener");
						e1.printStackTrace();
					}
				}
			});
		
		button_1.setBounds(330, 161, 85, 29);
		contentPane.add(button_1);
		
		//text field for button named 'delete'
		JButton button_delete = new JButton("Delete");
		button_delete.addActionListener(new ActionListener() {
			//if you press choice 3, then the action is performed
			public void actionPerformed(ActionEvent e) {
				try {
						String attribute = txtColumns3.getText();
						String tableName = txtTable3.getText();
						String parameters= txtParameters3.getText();
						
						String text=currentConnection.choiceThree1(tableName, attribute, parameters);
						output.setText(text);
						output.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
						output.setBounds(160, 200, 350, 400);
						contentPane.add(output);
						contentPane.repaint();
						contentPane.revalidate();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error: applying the connection in the action listener");
						e1.printStackTrace();
					}
				}
			});
		
		button_delete.setBounds(430, 161, 85, 29);
		contentPane.add(button_delete);
		
		//text field for button named 'choice 4'
		JButton button_2 = new JButton("associates");
		button_2.addActionListener(new ActionListener() {
			//if you press choice 4, then the action is performed
			public void actionPerformed(ActionEvent e) {
				try {
						String attribute = txtColumns3.getText();
						String tableName = txtTable3.getText();
						String parameters= txtParameters3.getText();
						
						String text=currentConnection.choiceFour();
						output.setText(text);
						output.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
						output.setBounds(160, 200, 350, 400);
						contentPane.add(output);
						contentPane.repaint();
						contentPane.revalidate();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error: applying the connection in the action listener");
						e1.printStackTrace();
					}
				}
			});
		button_2.setBounds(18, 202, 117, 29);
		contentPane.add(button_2);
		
		//text field for button named 'choice 5'
		JButton button_3 = new JButton("consultations by employee");
		button_3.addActionListener(new ActionListener() {
			//if you press choice 5, then the action is performed
			public void actionPerformed(ActionEvent e) {
				try {
						String text=currentConnection.choiceFive();
						output.setText(text);
						output.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
						output.setBounds(160, 200, 350, 400);
						contentPane.add(output);
						contentPane.repaint();
						contentPane.revalidate();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error: applying the connection in the action listener");
						e1.printStackTrace();
					}
				}
			});
		button_3.setBounds(18, 270, 117, 29);
		contentPane.add(button_3);
		
		//text field for button named 'quit'
		JButton button_4 = new JButton("Quit");
		button_4.addActionListener(new ActionListener() {
			//will close the connection and then close the guiframe when button is pressed
			public void actionPerformed(ActionEvent e) {
				try {
					currentConnection.closeConnection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: Problem with closing the connection.");
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		button_4.setBounds(427, 6, 117, 29);
		contentPane.add(button_4);
		
		//label for the quit button
		JLabel lblPressQuitTo = new JLabel("Press Quit to close the connection and exit the GUI framework");
		lblPressQuitTo.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblPressQuitTo.setBounds(18, 28, 319, 16);
		contentPane.add(lblPressQuitTo);
		
	}
}
