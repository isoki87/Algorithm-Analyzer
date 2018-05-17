import java.util.List;

public class PerfEvaluator {
	public static interface Algorithm {
		public void run();
	}

	public static class ComparisonCandidates {
		String comparisonName;
		Algorithm controlAlgo;
		Algorithm experimentAlgo;
	}

	public void evaluate(List<ComparisonCandidates> candidates, int runs) {
		System.gc();
		for (ComparisonCandidates candidatePair : candidates) {
			System.out.println("Comparison Name: " + candidatePair.comparisonName);
			System.out.println("Control results:");
			runAndPrintData(candidatePair.controlAlgo, runs);
			System.out.println("Experiment results:");
			runAndPrintData(candidatePair.experimentAlgo, runs);
			System.out.println("\n");
		}
	}

	private void runAndPrintData(Algorithm algorithm, int runs) {
		System.gc();
		long longestDuration = 0;
		long shortestTime = Integer.MAX_VALUE;
		double totalTime = 0;
		double averageTime = 0;
		for (int i = 0; i < runs; i++) {
			long startTime = System.nanoTime();
			algorithm.run();
			long endTime = System.nanoTime();
			long duration = endTime - startTime;
			if (duration < shortestTime) {
				shortestTime = duration;
			} else if (duration > longestDuration) {
				longestDuration = duration;
			}
			totalTime += duration;
		}
		averageTime = totalTime / runs;
		System.gc();
		System.out.println("LONGE: " + longestDuration);
		System.out.println("SHORT: " + shortestTime);
		System.out.println("AVERG: " + averageTime);
	}
}
