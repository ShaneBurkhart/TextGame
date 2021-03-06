package com.donkka.helpers;

import com.badlogic.gdx.Gdx;
import com.donkka.debug.DBug;

public class Dictionary {

	private static Dictionary instance;
	String[] words;
	
	private Dictionary(){
		loadWords();
		DBug.print("Dictionary Initialized...");
	}
	
	public static Dictionary getInstance(){
		if(instance == null)
			instance = new Dictionary();
		return instance;
	}
	
	public boolean containsValidChars(String chars){
		return lookForWord(chars.toCharArray(), 0, "");
	}
	
	private boolean lookForWord(char[] chars, int index, String word){
		if(isWord(word))
			return true;
		if(index > chars.length - 1)
			return false;
		return lookForWord(chars, index + 1, word) || lookForWord(chars, index + 1, word + chars[index]);
	}
	
	public boolean isWord(String word){
		String wordLower = word.toLowerCase();
		int minIndex = 0;
		int maxIndex = words.length - 1;
		int currentIndex = 0;
		String currentWord;
		char myChar = 0, indexChar = 0;
		int charIndex = 0;
		while(minIndex <= maxIndex){
			charIndex = 0;
			myChar = indexChar = 0;
			currentIndex = (maxIndex + minIndex) / 2;
			currentWord = words[currentIndex];
			//Equal then return true;
			if(currentWord.toLowerCase().equals(wordLower))
				return true;
			//Figure out where to cut down search
			while(myChar == indexChar){
				if(charIndex > wordLower.length() - 1){
					myChar = 0;
					break;
				}else
					myChar = wordLower.charAt(charIndex);
				if(charIndex > currentWord.length() - 1){
					indexChar = 0;
					break;
				}else
					indexChar = currentWord.charAt(charIndex);
				charIndex++;
			}
			if(myChar > indexChar)
				minIndex = currentIndex + 1;
			else
				maxIndex = currentIndex - 1;
		}
		return false;
	}
	
	public String[] getPossibleWords(char[] chars){
		return null;
	}
	
	private void loadWords(){
		words = Gdx.files.internal("data/dictionary.txt").readString().split("\r\n");
	}
}
