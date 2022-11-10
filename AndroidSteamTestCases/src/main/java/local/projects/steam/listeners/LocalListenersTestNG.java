package local.projects.steam.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;

public class LocalListenersTestNG implements ITestListener {

    public void onTestSuccess(ITestResult result) {
        File file = new File("results/" +"status_code_"+ result.getStatus() + "_success_thread_number" + Thread.currentThread().getId() + ".txt");
        writeToFile(result, file);

        System.out.println("SUCCEED " + result.getName());
    }


    public void onTestFailure(ITestResult result) {
        File file = new File("results/" +"status_code_"+ result.getStatus() + "_fail_thread_number" + Thread.currentThread().getId() + ".txt");
        writeToFile(result, file);
        System.out.println("FAILED " + result.getName());
    }

    private void writeToFile(ITestResult result, File file) {
        BufferedWriter writer = null;
        try {
            if (file.createNewFile()) {
                System.out.println("new file Created");

            }
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.newLine();
            writer.write(result.getName());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("SKIPPED " + result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("PASSED BY PERCENT  " + result.getName());
    }

}
