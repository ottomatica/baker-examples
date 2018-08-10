# Selenium

For this workshop, we will practice using [Selenium](http://www.seleniumhq.org/), which is a powerful tool for scripting web browsers, such as Chrome. Selenium can be useful for supporting automatic *integration and acceptance testing* of a web applications. Additionally, Selenium can be integrated with continuous integration systems, such as Jenkins, to verify software changes in an automated build system.

## Preq

* Requires running instance of Onboarding project (system under test).
* Chrome version >= 60 (for headless browser support).
* From Eclipse, use Import Existing Maven project. 

Clone the base Onboarding project [repo](https://github.ncsu.edu/engr-csc326-staff/Onboarding/), then import it into Eclipse (use Import -> Maven -> Existing Maven Projects).  Run the Maven build (provide the goal `spring-boot:run` as normal).  Verify that the site is accessible at `localhost:8080` via your web browser.  Alternatively, you can run `mvn spring-boot:run` from the console instead of running the Maven build inside Eclipse.

Clone this repo, then import it into Eclipse as above, and then right-click on the project and select Run As -> JUnit Test. Alternatively, in the console, run `mvn test`. You should see 3 passing test cases.

## Concepts

### Drivers

A driver provides a unified interface for automating interaction with a browser. For example, `WebDriver` is an abstract interface that allows you to open a browser window and navigate, or drive, through the page to get to the desired page or perform the requested action, such as entering values in a form and clicking buttons. Further, you read the page and verify that it contains the correct values and properties.

There are several concrete drivers available, such as `FirefoxDriver`, and `ChromeDriver`, which will drive Firefox and Chrome, respectively. Using these drivers, you can see the browser perform the actions in real-time. While this can be useful for debugging, for testing purposes, this would create too much performance overhead. Instead, it is common to use light-weight versions of the web driver for faster tests. For example, [HtmlUnitDriver](https://github.com/SeleniumHQ/selenium/wiki/HtmlUnitDriver) provides a fast driver; however, it is still generally effective for rendering pages with Javascript. Alternatively, it is possible to use headless versions of browsers, such as the recent headless version of Chrome. This is what we'll be using for this workshop.

**EXERCISE**: Comment out the headless options for Chrome. You should be able to see the browser startup.


### Checking values

In a browser, a html page is represented by DOM, a document model consisting of a tree of elements, attributes, and values. XPath is a tree selector language that makes it easy to write a query to select all elements that match a criterion.

##### Quick reference

* `//` Select all ancestors.
* `/` Select child
* `..` Select parent
* `//span[@id='currentCoffee]` Select span with id = "currrentCoffee".
* `//a[@data-href]` Select all links that have an attribute "data-href".
* `//h2[.='Search Results']` Select all h2 elements with value = "Search Results".
* `//h2/following-sibling::div"` Select the sibiling div after a h2 element.

Let's play around in Chrome's console (Hit `F12`, and then click `Console` to open).  Search for anything, and go to Google's search result page.  In the Chrome console, type: `$x("//a")`. This allows us to use a xpath expression to select all links.

**EXERCISE**: How could could you select all the search results links on Google? Write the xpath query.

## Writing Selenium Tests

A Selenium test is essentially a unit test that generally does the following:

1. Loads a web page.
2. Locates a target item (using a resource id or xpath) and performs an action.
3. Verifies the resulting DOM's properites with assertions.

To visit a page, you can simply give a URL.

```java
driver.get("http://localhost:8080/inventory.html");
```

To read a value for the page.

```java
WebElement span = driver.findElement(By.id("currentCoffee"));
// ...OR...
WebElement span = driver.findElement(By.xpath("//span[@id='currentCoffee']"));
```

To verify the results, you can extract out the property of the DOM element, by reading its tag attribute, text node value, or children nodes.

```
span.getText()
span.getAttribute();
```

### Waiting for Loading.

Unfortunately, due to the dynamic nature of HTML and Javascript, pages loads and actions can take place asynchronously, which we need to wait for the page to reach the correct state before interacting with it.

There are generally two strategies for waiting. 

1. Implicit waits:

An *implicit wait* will wait for a set limit of time before timing out if an element has not appeared on the page. 

   ```java
   driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
   ```

2. Explicit waits.

   An explicit wait will wait until a defined certain conditions to occur before continuing to the next step. For example, this will wait until the desired `span` element is visible on the page.

   ```java
   WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='currentCoffee']")));
   ```

   For Angular, it is possible to use a helper class to wait for to finish data-binding:

   ```java
   new NgWebDriver((ChromeDriver)driver).waitForAngularRequestsToFinish();
   ```
   **Note**: Particularly for Javascript-based applications, such as the CoffeeMaker app and iTrust2, explicit waits result in more reliable tests.

### Expired objects

When a page changes, you may receive a `StaleElementReferenceException`. This occurs when a `WebElement` no longer appears in the page's DOM, but you're still trying to iteract with it. One way to avoid this problem is to store the raw value (String/int) you need from the WebElement before performing an action that changes the page.

## Practice

1. Create a new Selenium test that fills in the Add Recipe Form.
2. Create a test that deletes all receipes.
3. Verify an error message is displayed when Making Coffee without first selecting a recipe.

## Onboarding Project Task

Now you should be able to write Selenium tests to verify your Edit Receipt feature.
