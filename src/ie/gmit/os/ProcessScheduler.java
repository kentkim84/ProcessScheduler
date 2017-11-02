package ie.gmit.os;

import java.util.*;

public class ProcessScheduler {
	public static void main(String args[]) {
		// set variables
		int pcNum;
		char pcName;
		int pcBurstTime;
		int algorithm;
		int quanta;
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Process> pc = new ArrayList<Process>();
		
		System.out.println("Please enter the number of process");
		pcNum = sc.nextInt();
		
		// looping over number of process times
		for (int i=0; i<pcNum; i++) {
			System.out.println("Please enter the name of process");
			pcName = sc.next().charAt(0);
			System.out.println("Please enter the burst time of process");
			pcBurstTime = sc.nextInt();
			pc.add(new Process(pcName, pcBurstTime));
		}
		
		for(Process str: pc){
			System.out.println(str);
	   }
		
		System.out.println("Please choose a process scheduling algorithm to run");
		System.out.println("Press 1: FCFS");
		System.out.println("Press 2: SJF");
		System.out.println("Press 3: Round Robin");
		System.out.println("Press 4: Program Exit");
		algorithm = sc.nextInt();
		
		while (algorithm != 4) {
			switch (algorithm) {
			case 1:
				fcfs(pc, pcNum);
				break;
			case 2:
				sjf(pc, pcNum);		
				break;
			case 3:
				System.out.println("Please enter a quanta");
				quanta = sc.nextInt();
				rr(pc, pcNum, quanta);
				break;
			default:
				System.out.println("Something is wrong!");
				break;
			}
			System.out.println("Please choose a process scheduling algorithm to run");
			System.out.println("Press 1: FCFS");
			System.out.println("Press 2: SJF");
			System.out.println("Press 3: Round Robin");
			System.out.println("Press 4: Program Exit");
			algorithm = sc.nextInt();
		}
		
		if (algorithm == 4) {
			System.exit(0);
		}
		
	}
	
	public static void fcfs (ArrayList<Process> pc, int pcNum) {
		// dose first come first serve
		System.out.println("Processing first come first serve");
		
		int totWaitTime = 0;
		int serviceTime = 0;
		float average = 0;
		
		for (int i=0; i<pcNum; i++) {
			serviceTime += pc.get(i).getBurstTime();
			totWaitTime += serviceTime;
			
			if (i == (pcNum - 1)) {
				totWaitTime -= serviceTime;
			}
		}
		
		// get the total wait time and average
		average = (float)totWaitTime/pcNum;

		System.out.println("Average wait time of FCFS is: " + average);
	}
	
	public static void sjf (ArrayList<Process> pc, int pcNum) {
		// dose shortest job first
		System.out.println("Processing shortest job first");
		
		int totWaitTime = 0;
		int serviceTime = 0;
		float average = 0;
		
		// clone the array list
		ArrayList<Process> pcClone = (ArrayList<Process>) pc.clone();
		
		// sort the array list
		Collections.sort(pcClone);
		
		for(Process str: pcClone) {
			System.out.println(str);
		}
		
		for (int i=0; i<pcNum; i++) {
			serviceTime += pcClone.get(i).getBurstTime();
			totWaitTime += serviceTime;
			
			if (i == (pcNum - 1)) {
				totWaitTime -= serviceTime;
			}
		}
		
		// get the total wait time and average
		average = (float)totWaitTime/pcNum;

		System.out.println("Average wait time of FCFS is: " + average);
	}
	
	public static void rr (ArrayList<Process> pc, int pcNum, int quanta) {
		// dose round robin
		System.out.println("Processing round robin");
		
		int totWaitTime = 0;
		int serviceTime = 0;
		int totRemainTime;
		float average = 0;
		boolean done = false;
		
		// initialise the remain time
		initrr(pc, pcNum);
		
		do {
			// set default zero
			totRemainTime = 0;
			for (int i=0; i<pcNum; i++) {
				if (pc.get(i).getRemainTime() > quanta) {
					serviceTime += quanta;
					pc.get(i).setRemainTime(pc.get(i).getRemainTime() - quanta);				
					pc.get(i).setRunCount(pc.get(i).getRunCount() + 1);
				}
				else if (pc.get(i).getRemainTime() <= quanta && pc.get(i).getRemainTime() != 0) {
					// get the total wait time
					totWaitTime += serviceTime - quanta * pc.get(i).getRunCount();
					serviceTime += pc.get(i).getRemainTime();
					pc.get(i).setRemainTime(0);
				}
				totRemainTime += pc.get(i).getRemainTime();
				System.out.println(pc.get(i).toString() + " wait time: " + serviceTime);
			}
			
			// check the iteration ends
			if (totRemainTime == 0) {
				done = true;
			}
		} while (done == false);
		System.out.println("total wait time: " + totWaitTime);
		
		// get the total wait time and average
		average = (float)totWaitTime/pcNum;
		System.out.println("Average wait time of FCFS is: " + average);
	}
	
	public static void initrr (ArrayList<Process> pc, int pcNum) {
		for (int i=0; i<pcNum; i++) {	
			pc.get(i).setRemainTime(pc.get(i).getBurstTime());
		}
	}
}
