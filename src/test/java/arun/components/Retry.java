package arun.components;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
int count=0;
final int max=1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<max) {
			count++;
            System.out.println("Retrying test: " + result.getName() + " | Attempt: " + (count + 1));

			return true;
		}
		return false;
	}

}
