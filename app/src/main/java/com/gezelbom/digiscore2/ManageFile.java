package com.gezelbom.digiscore2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import android.util.Log;

/**
 * Class to manage a given file. Create, write and read methods for FriendDTO
 * 
 * @author Alex
 * 
 */
public class ManageFile {

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	private ArrayList<FriendDTO> friendsTemp;

	/**
	 * Constructor sets the variables and if no file exists i creates the file
	 * 
	 * @param path
	 *            The path to the file
	 * @param fileName
	 *            The filename
	 */
	public ManageFile(String path, String fileName) {
		this.file = new File(path, fileName);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Log.d("DigiScore2", "From inside ManageFile on creation");

	}

	/**
	 * Constructor that only takes the filename and uses the default path
	 * 
	 * @param fileName
	 *            Filename of the file to manage
	 */
	public ManageFile(String fileName) {

		this.file = new File(fileName);

	}

	/**
	 * Method to write the friend to the file
	 * 
	 * @param friend
	 *            The friend to store in the file
	 */
	public void writeToFile(FriendDTO friend) {

		// Firstly read all the current friends from the file to the temp array
		friendsTemp = readFromFile();

		// Add the new friend to the friendsTemp arrayList
		friendsTemp.add(friend);

		// Sort all the friendsDTOs
		Collections.sort(friendsTemp);

		// Write the temporary array to the file
		try {
			fos = new FileOutputStream(this.file);
			oos = new ObjectOutputStream(fos);

			for (FriendDTO f : friendsTemp) {
				oos.writeObject(f);
			}

			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Read all friendsDTO objects from the file into an arraylist and return
	 * the arraylist to the caller
	 * 
	 * @return
	 */
	public ArrayList<FriendDTO> readFromFile() {

		ArrayList<FriendDTO> friends = new ArrayList<FriendDTO>();

		try {

			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			while (true) {
				try {
					friends.add((FriendDTO) ois.readObject());
				} catch (Exception e) {
					ois.close();
					return friends;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return friends;
		}

	}
}
