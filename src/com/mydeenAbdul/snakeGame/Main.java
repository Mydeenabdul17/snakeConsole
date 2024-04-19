package com.mydeenAbdul.snakeGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	Scanner sc = new Scanner(System.in);
	private SnakeGame game;
	Main(){
		game = SnakeGame.getInstance();
	}
	public static void main(String[] args) {
		Main m  = new Main();
		m.start();
	}

	private void start() {
		System.out.println("Enter the width :");
		int w = sc.nextInt();
		System.out.println("Enter the height :");
		int h = sc.nextInt();
//		System.out.println("Enter the number of food :");
//		int n = sc.nextInt();
//		Queue<List<Integer>> pos = new LinkedList<List<Integer>>();
		List<Integer> pos = new ArrayList<>();
		while(true) {
			System.out.println("Enter the food Position space seperated");
			int foodI = sc.nextInt(),foodJ = sc.nextInt();
			if(foodI==0&&foodJ==0) {
				System.out.println("snake is in that position");
				continue;
			}
			if(foodI>=0&&foodI<h&&foodJ>=0&&foodJ<w) {
//				List<Integer> l = new ArrayList<>();
				pos.add(foodI);
				pos.add(foodJ);
//				pos.offer(l);
				break;
			}else {
				System.out.println("Wrong position");
			}
		}
		game.create(w,h,pos);
		while(true) {
			game.print();
			System.out.println("'E to end game'");
			char input = sc.next().charAt(0);
			if(input == 'E') break;
			boolean isOver = game.changePlace(input);
			if(!isOver) break;
		}
		System.out.println("***************************************************");
		System.out.println("Game Ended");
	}
}
