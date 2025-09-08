package BaseUtilties;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	int init=1;
	int count=3;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(init<count)
		{
			init++;
			return true;
		}
		return false;
	}

}
