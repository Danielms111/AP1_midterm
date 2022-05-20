package model;

import java.util.Collections;

/**
 * @author avillota
 * @since may 2022
 */
public class CrosswordController {
	
	/**
	 * Matrix of cells representing the crossword puzzle
	 */
	private Cell [][] crossword;
	
	/**
	 * method for initializing a crossword puzzle
	 * @param puzzle is a matrix of Strings containing 
	 * the initial state of a crossword puzzle
	 */
	public void initCrossword(String[][] puzzle) {

		crossword = new Cell[4][4];
		int contState = 1;
		//puzzle = new String [4][4];
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){

				if(puzzle[i][j].equals(" ")){
				
				  Cell cell1 = new Cell(CellType.BLACK, "", 0);

				  crossword[i][j]=cell1;
			    } else {
					Cell cell2 = new Cell(CellType.CLOSED, puzzle[i][j], contState);
					crossword[i][j] = cell2;
					contState++;
				}

			}
		}
				
	}
	/**
	 * Method to verify if a crossword puzzle is initialized
	 * @return boolean, true if it is initialized, else false
	 */
	public boolean isInitialized(){
		return crossword!=null;
	}
	
	/**
	 * Method to provide the dimensions of the crossword puzzle
	 * @return
	 */
	public int[] getGameDimensions() {
		int[] dims = new int[2];
		dims[0]= crossword.length;
		dims[1]= crossword[0].length;
		
		return dims;
	}
	
	public Cell[][] getCells(){
		return crossword;
	}
	/**
	 * 
	 * @param letter
	 * @return
	 */

    public String getHint(String letter) {
		String out = "";
		boolean bandera = false;
	
		for (int i=0; i< crossword.length && !bandera; i++) {
			for (int j=0; j< crossword[0].length && !bandera; j++) {
				if(crossword[i][j].getState().equals(CellType.CLOSED) && letter.equals(crossword[i][j].getLetter())) {
					out = "Hay una palabra con esa " + letter + " en el crucigrama en la casilla [" + i +"][" + j + "]";
					crossword[i][j].setState(CellType.OPEN); 
					bandera = true;
				} else {
					out = "Lo siento, no hay palabras con esa " + letter;
				}
			}
		}
		return out;
	}
		
	
	/**
	 * 
	 * @param letter
	 * @param num
	 * @return
	 */
	public String evaluateCell(String letter, int num) {
		
		return null;
	}
	
	public String showCrossword() {
		int rows= crossword.length;
		int columns= crossword[0].length;
	
		String out="";
		String separator = "+---+ ";
		String line = "" + String.join("", Collections.nCopies(columns, separator));
		
		
		String numbers ="";
		String letters = "";
		int count =0;
		for(int i=0 ;i<rows ; i++) {
			numbers ="";
			letters ="";
			for(int j=0 ;j<columns ; j++) {
				count++;
				Cell actual = crossword[i][j];
				
				// numeros de dos cifras
				if (count>10) {
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else {
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}else //una cifra
				{
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else {
						numbers += " "+actual.getNumber() +"    ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}
			}
			//por cada fila se imprimen las lineas
			out+= line + "\n";
			out+= numbers + "\n";
			out+= letters + "\n";
			
			
		}
		out+= line + "\n";
		return out;
	}


}
