package com.mydeenAbdul.snakeGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SnakeGame {

	private int height,width,score;
	private Scanner sc = new Scanner(System.in);
//	private Queue<List<Integer>> foodPos ;
	private List<Integer> currentFoodPos;
	private static SnakeGame game;
	private char previousDir;
	private List<List<Integer>> snake;
	private SnakeGame() {
		List<Integer> head = List.of(0,0);
		snake = new ArrayList<>();
		snake.add(head);
	}
	public static SnakeGame getInstance() {
		if(game ==null) game =new SnakeGame();
		return game;
	}
	public void create(int w, int h, List<Integer> pos) {
		height = h;
		width = w;
		currentFoodPos = pos;
//		if(!foodPos.isEmpty()) {
//			currentFoodPos = foodPos.poll();
//		}
//		System.out.println(foodPos);
//		System.out.println(currentFoodPos);
	}
	public void print() {
		print(placeSnake());
	}
	
	private int[][] placeSnake() {
//		char[][] ch = new char[height][width];
		int[][] m = new int[height][width];
//		char head = '0';
		int head = 0;
		for(List<Integer> s : snake) {
			if(head == 0) {
				m[s.get(0)][s.get(1)] = -1;
			}else {
				m[s.get(0)][s.get(1)] = head;
			}
			head++;
		}
		if(currentFoodPos!=null) {
			int f = m[currentFoodPos.get(0)][currentFoodPos.get(1)];
			if((f==0)) {
				m[currentFoodPos.get(0)][currentFoodPos.get(1)] = -2;
//				if(!foodPos.isEmpty()) {
//					currentFoodPos = foodPos.poll();
//				}
			}
		}
		return m;
	}
	private void print(int[][] m) {
		System.out.println("Score : "+score);
		for(int i=0;i<m.length;i++) {
//			System.out.println(Arrays.toString(m[i]));
			for(int j=0;j<m[i].length;j++) {
				if(m[i][j]==-1) {
					System.out.print("H  |");
				}else if(m[i][j]==-2) {
					System.out.print("F  |");
				}else if(m[i][j]==0) {
					System.out.print("   |");
				}else if(m[i][j]>9) {
					System.out.print(m[i][j]+" |");
				}else {
					System.out.print(m[i][j]+"  |");
				}
			}
			System.out.println();
		}
	}
	public boolean changePlace(char input) {
		List<Integer> head = snake.get(0);
		switch(input) {
			case 'R' : {
				if(head.get(1)<width-1&&previousDir!='L') {
					List<Integer> newHead = List.of(head.get(0),head.get(1)+1);
					if(snake.contains(newHead)) return false;
					if(newHead.equals(currentFoodPos)) {
//						if(!foodPos.isEmpty()) {
//						    currentFoodPos = foodPos.poll();
//						}else currentFoodPos = null;
						currentFoodPos = null;
						score++;
					    snake.add(0, newHead);
					}else {
						snake.add(0,newHead);
						snake.remove(snake.size()-1);
					}
					previousDir = 'R';
				}else return false;
				break;
			}
			case 'L' : {
				if(head.get(1)>0&&previousDir!='R') {
					List<Integer> newHead = List.of(head.get(0),head.get(1)-1);
					if(snake.contains(newHead)) return false;
					if(newHead.equals(currentFoodPos)) {
//						if(!foodPos.isEmpty()) {
//						    currentFoodPos = foodPos.poll();
//						}else currentFoodPos = null;
						currentFoodPos = null;
						score++;
					    snake.add(0, newHead);
					}else {
						snake.add(0,newHead);
						snake.remove(snake.size()-1);
					}
					previousDir = 'L';
				}else return false;
				break;
			}
			case 'U' : {
				if(head.get(0)>0&&previousDir!='D') {
					List<Integer> newHead = List.of(head.get(0)-1,head.get(1));
					if(snake.contains(newHead)) return false;
					if(newHead.equals(currentFoodPos)) {
//						if(!foodPos.isEmpty()) {
//						    currentFoodPos = foodPos.poll();
//						}else currentFoodPos = null;
						currentFoodPos = null;
						score++;
					    snake.add(0, newHead);
					}else {
						snake.add(0,newHead);
						snake.remove(snake.size()-1);
					}
					previousDir = 'U';
				}else return false;
				break;
			}
			case 'D' : {
				if(head.get(0)<height-1&&previousDir!='U') {
					List<Integer> newHead = List.of(head.get(0)+1,head.get(1));
					if(snake.contains(newHead)) return false;
					if(newHead.equals(currentFoodPos)) {
//						if(!foodPos.isEmpty()) {
//						    currentFoodPos = foodPos.poll();
//						}else currentFoodPos = null;
						currentFoodPos = null;
						score++;
					    snake.add(0, newHead);
					}else {
						snake.add(0,newHead);
						snake.remove(snake.size()-1);
					}
					previousDir = 'D';
				}else return false;
				break;
			}
			default : {
				System.out.println("you supposed to enter only R L U D");
				break;
			}
		}
		if(currentFoodPos == null) {
			print();
			askMoreFood();
		}
		return true;
	}
	private void askMoreFood() {
		while(true) {
			System.out.println("Enter the food Position space seperated");
			int foodI = sc.nextInt(),foodJ = sc.nextInt();
			List<Integer> l = new ArrayList<>();
			l.add(foodI);
			l.add(foodJ);
			if(snake.contains(l)) {
				System.out.println("Snake is in that position");
				continue;
			}
			if(foodI>=0&&foodI<height&&foodJ>=0&&foodJ<width) {
//				List<Integer> l = new ArrayList<>();
				currentFoodPos = new ArrayList<>(l);
//				pos.offer(l);
				break;
			}else {
				System.out.println("Wrong position");
			}
		}
//		System.out.println("Food was Empty would you want to add more Y/N");
//		char choice = sc.next().charAt(0);
//		switch(choice) {
//		case 'Y' : {
//			System.out.println("Enter the number of food :");
//			int n = sc.nextInt();
//			for(int i=0;i<n;) {
//				System.out.println("Enter the food Position space seperated");
//				int foodI = sc.nextInt(),foodJ = sc.nextInt();
//				List<Integer> l = new ArrayList<>();
//				l.add(foodI);
//				l.add(foodJ);
//				if(snake.contains(l)) {
//					System.out.println("Snake is in that position");
//					continue;
//				}
//				if(foodI>=0&&foodI<height&&foodJ>=0&&foodJ<width) {
//					foodPos.offer(l);
//					i++;
//				}else {
//					System.out.println("Wrong position");
//				}
//			}
//			currentFoodPos = foodPos.poll();
//			break;
//		}
//		case 'N' : {
//			System.out.println("Thank you");
//			break;
//		}
//		default : {
//			askMoreFood();
//		}
//		}
	}
	
}
