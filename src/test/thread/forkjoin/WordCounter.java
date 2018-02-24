package org.zt.test.thread.forkjoin;

public class WordCounter {

	String[] wordsIn(String line) {
		return line.trim().split("(\\s|\\p{Punct})+");
	}

	public Long occurrencesCount(Document document, String searchedWord) {
		long count = 0;
		for (String line : document.getLines()) {
			for (String word : wordsIn(line)) {
				if (searchedWord.equals(word)) {
					count = count + 1;
				}
			}
		}
		return count;
	}
	
	public Long countOccurrencesOnSingleThread(Folder folder, String searchedWord) {
	    long count = 0;
	    for (Folder subFolder : folder.getSubFolders()) {
	        count = count + countOccurrencesOnSingleThread(subFolder, searchedWord);
	    }
	    for (Document document : folder.getDocuments()) {
	        count = count + occurrencesCount(document, searchedWord);
	    }
	    return count;
	}
}