# DemoAppValidations
Login Page Validation of a Demo App  


<h3>Framework</h3>  


Navigate to src\test\Java : Where you will find 3 packages 


1-	Com.genericlibraries
2-	Com.pagelocatores
3-	TestCaseDefinations



In Generic Libraries Package you will find Utilities class which contains all the common selenium WebDriver Functions 


In Page Locators Package you will find Locators details of the Each Page : LoginPage and HomePage



In TestCaseDefination Package  you will find all the Test Cases Execution Steps 


And also I have used Data Driven Approach for acessing the TestData while execution , like URL,UserName,Password



To execute the code Please Run the testng.xml file as a TestNG Suite



<h3>HTML Report Path : target/Spark.html</h3>h3>



<h3>Test Scenarios used are  :</h3>



TestCase001: Navigate to DemoApp URL and Validated all the Elements are visible or not like UserName Text Box , PassWord Text Box , Login Button , Remember Me Check Box , Company LOGO , FooterLinks



TestCase002:Login to Demo App with Multiple UserName and Password (Alpha/Alphanumeric/Special Characters) Formatted , Verify the Login Functionality



TestCase003:Try to Login to Demo App with valid Username and Password as Empty and verify the alert Popup



TestCase004:Try to Login to Demo App with valid Password and Username as Empty and verify the alert Popup



TestCase005:Try to Login to Demo App with Empty Username and Password and verify the alert Popup



TestCase006:Validation of RememberMe Functionality while Login the DemoApp



TestCase007:Home Page Account Header Sorting Validation on DemoApp
