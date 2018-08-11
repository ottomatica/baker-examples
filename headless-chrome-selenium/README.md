# headless-selenium

In this project, we demonstrate using [Selenium](http://www.seleniumhq.org/) running on Google Chrome in **headless mode** to run tests against a simple Java spring-boot web site.

Selenium is a powerful tool for scripting web browsers, such as Chrome. Selenium can be useful for supporting automatic *integration and acceptance testing* of a web applications. Additionally, Selenium can be integrated with continuous integration systems, such as Jenkins, to verify software changes in an automated build system.

### Baker environment

The [baker environment](baker.yml/) sets up Java 8, maven, and Google Chrome automatically.

### Project

A simple "CoffeeMaker" application serves up coffee ingredients and receipes via a REST endpoint (e.g. /api/v1/inventory). Visiting the site at http://192.168.9.11:8080, will allow you to interact with the web app. In this version of CoffeeMaker, the DB and other functionality has been removed in order to keep this application simple. See [CoffeeMaker-Lite](CoffeeMaker-Lite/).

A set of selenium tests will use the ChromeDriver to verify the _limited_ functionality of the web app.

### Try it out

Create the vm.

``` bash
baker bake
```

Start up the web app. Then visit: http://192.168.9.11:8080

``` bash
baker run serve
```

Run selenium tests.

``` bash
baker run web-test
```

You should see the following results.
