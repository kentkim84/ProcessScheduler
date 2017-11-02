package ie.gmit.os;

public class Process implements Comparable<Process> {
	private char name;
	private int burstTime;
	private int remainTime;
	private int runCount;
	
	public Process(char name, int burstTime) {
		super();
		this.name = name;
		this.burstTime = burstTime;
	}

	public int getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(int remainTime) {
		this.remainTime = remainTime;
	}

	public int getRunCount() {
		return runCount;
	}

	public void setRunCount(int runCount) {
		this.runCount = runCount;
	}

	public char getName() {
		return name;
	}

	public int getBurstTime() {
		return burstTime;
	}

	@Override
	public int compareTo(Process o) {
		int compareBurstTime = ((Process)o).getBurstTime();
        /* For Ascending order*/
        return this.burstTime - compareBurstTime;
	}

	@Override
	public String toString() {
		return "Process [name=" + name + ", burstTime=" + burstTime + ", remainTime=" + remainTime + "]";
	}
}
