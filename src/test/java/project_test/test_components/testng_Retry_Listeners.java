package project_test.test_components;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class testng_Retry_Listeners implements IRetryAnalyzer {
    /**
     * @param iTestResult
     * @return
     */

    int count = 0;

    int max_try = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<max_try){
            count++;
            return true;
        }

        return false;
    }
}
