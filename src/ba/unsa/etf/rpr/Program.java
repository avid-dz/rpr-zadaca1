package ba.unsa.etf.rpr;

import java.util.Scanner;

public class Program {
     public static void main(String[] args) {
          Scanner scan = new Scanner(System.in);
          String scanned = "";
          Board board = new Board();
          outer:
          while (true) {
              innerWhite:
              while (true) {
                  System.out.print("White move: ");
                  scanned = scan.next();
                  if (scanned.trim().toUpperCase().equals("X")) {
                      break outer;
                  }
                  try {
                      if (scanned.trim().length() == 2) {
                          board.move(Pawn.class, ChessPiece.Color.WHITE, scanned.trim().toUpperCase());
                          if (board.isCheck(ChessPiece.Color.BLACK) || board.isCheck(ChessPiece.Color.WHITE)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerWhite;
                      }
                      else if (scanned.trim().charAt(0) == 'K') {
                          board.move(King.class, ChessPiece.Color.WHITE, scanned.trim().toUpperCase().substring(1));
                          if (board.isCheck(ChessPiece.Color.BLACK) || board.isCheck(ChessPiece.Color.WHITE)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerWhite;
                      }
                      else if (scanned.trim().charAt(0) == 'Q') {
                          board.move(Queen.class, ChessPiece.Color.WHITE, scanned.trim().toUpperCase().substring(1));
                          if (board.isCheck(ChessPiece.Color.BLACK) || board.isCheck(ChessPiece.Color.WHITE)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerWhite;
                      }
                      else if (scanned.trim().charAt(0) == 'R') {
                          board.move(Rook.class, ChessPiece.Color.WHITE, scanned.trim().toUpperCase().substring(1));
                          if (board.isCheck(ChessPiece.Color.BLACK) || board.isCheck(ChessPiece.Color.WHITE)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerWhite;
                      }
                      else if (scanned.trim().charAt(0) == 'B') {
                          board.move(Bishop.class, ChessPiece.Color.WHITE, scanned.trim().toUpperCase().substring(1));
                          if (board.isCheck(ChessPiece.Color.BLACK) || board.isCheck(ChessPiece.Color.WHITE)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerWhite;
                      }
                      else if (scanned.trim().charAt(0) == 'N') {
                          board.move(Knight.class, ChessPiece.Color.WHITE, scanned.trim().toUpperCase().substring(1));
                          if (board.isCheck(ChessPiece.Color.BLACK) || board.isCheck(ChessPiece.Color.WHITE)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerWhite;
                      }
                  } catch (Exception e) {
                      System.out.println(e.getMessage());
                  }
              }
              innerBlack:
              while (true) {
                  System.out.print("Black move: ");
                  scanned = scan.next();
                  if (scanned.trim().toUpperCase().equals("X")) {
                      break outer;
                  }
                  try {
                      if (scanned.trim().length() == 2) {
                          board.move(Pawn.class, ChessPiece.Color.BLACK, scanned.trim().toUpperCase());
                          if (board.isCheck(ChessPiece.Color.WHITE) || board.isCheck(ChessPiece.Color.BLACK)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerBlack;
                      }
                      else if (scanned.trim().charAt(0) == 'K') {
                          board.move(King.class, ChessPiece.Color.BLACK, scanned.trim().toUpperCase().substring(1));
                          if (board.isCheck(ChessPiece.Color.WHITE) || board.isCheck(ChessPiece.Color.BLACK)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerBlack;
                      }
                      else if (scanned.trim().charAt(0) == 'Q') {
                          board.move(Queen.class, ChessPiece.Color.BLACK, scanned.trim().toUpperCase().substring(1));
                          if (board.isCheck(ChessPiece.Color.WHITE) || board.isCheck(ChessPiece.Color.BLACK)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerBlack;
                      }
                      else if (scanned.trim().charAt(0) == 'R') {
                          board.move(Rook.class, ChessPiece.Color.BLACK, scanned.trim().toUpperCase().substring(1));
                          if (board.isCheck(ChessPiece.Color.WHITE) || board.isCheck(ChessPiece.Color.BLACK)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerBlack;
                      }
                      else if (scanned.trim().charAt(0) == 'B') {
                          board.move(Bishop.class, ChessPiece.Color.BLACK, scanned.trim().toUpperCase().substring(1));
                          if (board.isCheck(ChessPiece.Color.WHITE) || board.isCheck(ChessPiece.Color.BLACK)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerBlack;
                      }
                      else if (scanned.trim().charAt(0) == 'N') {
                          board.move(Knight.class, ChessPiece.Color.BLACK, scanned.trim().toUpperCase().substring(1));
                          if (board.isCheck(ChessPiece.Color.WHITE) || board.isCheck(ChessPiece.Color.BLACK)) {
                              System.out.println("CHECK!!!");
                          }
                          break innerBlack;
                      }
                  } catch (Exception e) {
                      System.out.println(e.getMessage());
                  }
              }
          }
     }
}
