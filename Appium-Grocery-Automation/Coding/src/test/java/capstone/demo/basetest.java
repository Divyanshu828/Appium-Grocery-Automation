package capstone.demo;



import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import java.net.URL;

import java.time.Duration;



public class basetest {



    public static void main(String[] args) {

        System.out.println("=== STARTING SEQUENTIAL COMPATIBILITY TEST ===");



        // --- RUN 1: EMULATOR ---

        // REPLACE "emulator-5554" with your actual emulator ID from 'adb devices'

        runTest("Pixel_Emulator", "16", "emulator-5554");



        // --- RUN 2: REAL DEVICE ---

        // REPLACE "RMX5070" with your actual device ID from 'adb devices'

        runTest("Realme_Device", "16", "f5668fba");

    }



    @SuppressWarnings("deprecation")

	public static void runTest(String deviceName, String androidVersion, String udid) {

        AndroidDriver driver = null;

        System.out.println("\n------------------------------------------------");

        System.out.println("TESTING DEVICE: " + deviceName);



        try {

            UiAutomator2Options options = new UiAutomator2Options();

            

            // --- 1. DEVICE CONFIGURATION ---

            options.setDeviceName(deviceName);

            options.setPlatformVersion(androidVersion);

            options.setUdid(udid); // IMPORTANT: This ensures the correct device is picked!

            

            // --- 2. APP PATH ---

        

         // Example: If your path is "C:\Users\Downloads\General-Store.apk"

         options.setApp("D:\\wiproo trainin\\python-automative-batch-9\\eclipse\\demo\\General-Store.apk");



            // --- 3. DRIVER INITIALIZATION ---

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));



            // --- 4. THE TEST SCENARIO ---

            System.out.println("Step 1: App Launched on " + deviceName);

            

            // Simple Login Flow

            driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Divyanshu");

            driver.hideKeyboard();

            driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

            try { 

                Thread.sleep(3000); // Wait 3 seconds for page to load

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            // Validation

            WebElement title = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));

            if (title.getText().equals("Products")) {

                System.out.println("RESULT: PASS - Layout is compatible.");

            } else {

                System.err.println("RESULT: FAIL - Layout issue detected!");

            }



        } catch (Exception e) {

            System.err.println("CRITICAL ERROR on " + deviceName + ": " + e.getMessage());

        } finally {
            if (driver != null) {
                driver.quit(); // Closes the app to prepare for the next device
                System.out.println("Finished testing " + deviceName);

            }

        }

    }

}